package com.liveyc.mina.client;

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

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.client.hander.DKClientStartHandler;
import com.liveyc.mina.client.message.MessageCodecDKFactory;
import com.liveyc.mina.hander.ItsClientHandler;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.mina.model.ResConnetMode;
import com.liveyc.mina.sichun.NextIpAndPort;

public class DKMsgProtocol {
	private NioSocketConnector connector;
	private static Logger iLog = Logger.getLogger(DKMsgProtocol.class);;
	private static DKMsgProtocol client = new DKMsgProtocol();
	private ConnectFuture connFuture = null;

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

	private DKMsgProtocol() {
		// clientStart();
	}

	public void restart() {
		if (null != connector && !connector.isActive()) {
			try {
				// 尝试连接默认的地址和端口
				setIsconenected(false);
				connFuture = connector.connect();
				connFuture.awaitUninterruptibly();
			} catch (Exception e) {
				iLog.error(e.toString());
				e.printStackTrace();
			}
		}
	}

	private NextIpAndPort nextIpAndPort = new NextIpAndPort();

	public NextIpAndPort getNextIpAndPort() {
		return nextIpAndPort;
	}

	public void setNextIpAndPort(NextIpAndPort nextIpAndPort) {
		this.nextIpAndPort = nextIpAndPort;
	}

	public void clientStart(NextIpAndPort nextIpAndPort) {
		if (connector != null) {
			try {
				if (connFuture != null && connFuture.getSession() != null) {
					connFuture.getSession().close(true);
					connector.dispose();
					connFuture = null;
					connector = null;
					iLog.error("销毁资源");
				} else {
					connector.dispose();
					iLog.error("销毁资源");
					connector = null;
				}
			} catch (Exception e) {
				iLog.error(e.toString());
				e.printStackTrace();
			}
		} else {
			connFuture = null;
		}
		connector = new NioSocketConnector();
		connector.setHandler(new DKClientStartHandler());
		connector.getSessionConfig().setReadBufferSize(4096);
		connector.getSessionConfig().setSoLinger(0);
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("objectFilter", new ProtocolCodecFilter(new MessageCodecDKFactory(false)));
		connector.setDefaultRemoteAddress(new InetSocketAddress(nextIpAndPort.getIp(), nextIpAndPort.getPort()));
		if (null != connector && !connector.isActive()) {
			try {
				// 尝试连接默认的地址和端口
				setIsconenected(false);
				connFuture = connector.connect();
				connFuture.awaitUninterruptibly();
			} catch (Exception e) {
				iLog.error(e.toString());
				e.printStackTrace();
			}
		}

	}

	public NioSocketConnector getConnector() {
		return connector;
	}

	public static DKMsgProtocol getInstances() {
		return client;
	}
}
