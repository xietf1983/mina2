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
	 * ����Scats
	 */
	public void clientStart() {
		connector = new NioSocketConnector();
		connector.setHandler(new ItsClientHandler());
		connector.getSessionConfig().setReadBufferSize(4096);
		connector.getSessionConfig().setSoLinger(0);
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("objectFilter", new ProtocolCodecFilter(new MessageCodecFactory(true))); //���ñ��������
		connector.setDefaultRemoteAddress(new InetSocketAddress(PropsValues.ITS_SERVERIP, PropsValues.ITS_PORT));  // ����Ĭ�Ϸ��ʵ�ַ  
		//��ʱ��
		new Timer().schedule(new TimerTask() {  
			public void run() {
				if (null != connector && !connector.isActive()) {
					try {
						// ��������Ĭ�ϵĵ�ַ�Ͷ˿�
						setIsconenected(false);
						connFuture = connector.connect();
						connFuture.awaitUninterruptibly();  // �������̵߳�ִ�У��ȴ��Ƿ����ӳɹ����൱���ǽ��첽ִ��ת��Ϊͬ��ִ��
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
		connector.getSessionConfig().setUseReadOperation(true); // ͬ���Ŀͻ���,�������ô���,��Ĭ��Ϊfalse
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory(true)));
		// connector.setHandler(this);
		// //��Ϊͬ���Ŀͻ���,���Բ���ҪIoHandler,Mina���Զ����һ��Ĭ�ϵ�IoHandlerʵ��,��AbstractIoConnector
		IoSession session = null;
		// Object respData = null;
		try {
			ConnectFuture connectFuture = connector.connect(new InetSocketAddress(ipAddress, port));
			connectFuture.awaitUninterruptibly();
			session = connectFuture.getSession();
			session.write(req).awaitUninterruptibly(); // ���������Ѿ�����
			ReadFuture readFuture = session.read();
			if (readFuture.awaitUninterruptibly(90, TimeUnit.SECONDS)) {
				ResConnetMode respData = (ResConnetMode) readFuture.getMessage();
				if (respData != null) {
					
				}

			} else {
				// ��ȡ��ʱ
			}
		} catch (Exception e) {
			System.out.println("��������" + ipAddress + ":" + port + "δ�����ӣ�" + e.toString());
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
