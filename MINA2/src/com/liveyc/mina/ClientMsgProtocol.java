package com.liveyc.mina;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.hander.ItsClientHandler;
import com.liveyc.mina.message.code.MessageCodecFactory;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.mina.model.ResConnetMode;

public class ClientMsgProtocol {
	private NioSocketConnector connector;
	private static Logger iLog = Logger.getLogger(ClientMsgProtocol.class);;
	private static ClientMsgProtocol client = new ClientMsgProtocol();
	private ConnectFuture connFuture;
	public ConnectFuture getConnFuture() {
		return connFuture;
	}

	public void setConnFuture(ConnectFuture connFuture) {
		this.connFuture = connFuture;
	}

	private Object mutex = new Object();
	private boolean isconenected = false;

	public boolean isIsconenected() {
		return isconenected;
	}

	public void setIsconenected(boolean isconenected) {
		this.isconenected = isconenected;
	}

	private void ClientMsgProtocol() {
		clientStart();
	}

	/**
	 * 启动Scats
	 */
	public void clientStart() {
		connector = new NioSocketConnector();
		connector.setHandler(new ItsClientHandler());
		connector.getSessionConfig().setReadBufferSize(4096);
		connector.getSessionConfig().setSoLinger(0);
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("objectFilter", new ProtocolCodecFilter(new MessageCodecFactory(true))); //设置编码过滤器
		connector.setDefaultRemoteAddress(new InetSocketAddress(PropsValues.ITS_SERVERIP, PropsValues.ITS_PORT));  // 设置默认访问地址  
		//定时器
		new Timer().schedule(new TimerTask() {  
			public void run() {
				if (null != connector && !connector.isActive()) {
					try {
						// 尝试连接默认的地址和端口
						setIsconenected(false);
						connFuture = connector.connect();
						connFuture.awaitUninterruptibly();  // 阻塞主线程的执行，等待是否连接成功，相当于是将异步执行转换为同步执行
					} catch (Exception e) {
						iLog.error(e.toString());
						e.printStackTrace();
					}
				}
			}
		}, new Date(), 5 * 1000);
	}
	/*
	public static void sendTCPMessage(ReqConnetModel req, String ipAddress, int port, String charset, List<ReqRealLocation> list) {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(1000);
		connector.getSessionConfig().setUseReadOperation(true); // 同步的客户端,必须设置此项,其默认为false
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory(true)));
		// connector.setHandler(this);
		// //作为同步的客户端,可以不需要IoHandler,Mina会自动添加一个默认的IoHandler实现,即AbstractIoConnector
		IoSession session = null;
		// Object respData = null;
		try {
			ConnectFuture connectFuture = connector.connect(new InetSocketAddress(ipAddress, port));
			connectFuture.awaitUninterruptibly();
			session = connectFuture.getSession();
			session.write(req).awaitUninterruptibly(); // 由于上面已经设置
			ReadFuture readFuture = session.read();
			if (readFuture.awaitUninterruptibly(90, TimeUnit.SECONDS)) {
				ResConnetMode respData = (ResConnetMode) readFuture.getMessage();
				if (respData != null) {
					
				}

			} else {
				// 读取超时
			}
		} catch (Exception e) {
			System.out.println("服务器：" + ipAddress + ":" + port + "未能连接！" + e.toString());
		} finally {
			if (session != null) {
				session.close(true);
				connector.dispose();
			}
		}
	}
	*/

	public NioSocketConnector getConnector() {
		return connector;
	}

	public static ClientMsgProtocol getInstances() {
		return client;
	}
}
