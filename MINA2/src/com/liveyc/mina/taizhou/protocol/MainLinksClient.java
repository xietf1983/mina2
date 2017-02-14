package com.liveyc.mina.taizhou.protocol;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.taizhou.filter.FilterFactory;
import com.liveyc.mina.taizhou.handler.MainLinksHandler;

public class MainLinksClient {
	private static Logger iLog = Logger.getLogger(MainLinksClient.class);
	private static MainLinksClient mainLink = new MainLinksClient();
	private NioSocketConnector connector = null;
	private ConnectFuture connFuture = null;
	private boolean whetherLinked = false;
	
	public NioSocketConnector getConnector() {
		return connector;
	}

	public void setConnector(NioSocketConnector connector) {
		this.connector = connector;
	}

	public ConnectFuture getConnFuture() {
		return connFuture;
	}

	public void setConnFuture(ConnectFuture connFuture) {
		this.connFuture = connFuture;
	}

	public static MainLinksClient getInstances() {
		return mainLink;
	}
	
	public boolean isWhetherLinked() {
		return whetherLinked;
	}

	public void setWhetherLinked(boolean whetherLinked) {
		this.whetherLinked = whetherLinked;
	}
	
	public void recreateLink() {
		if (null != connector && !connector.isActive()) {
			try {
				// 尝试连接默认的地址和端口
				setWhetherLinked(false);
				connFuture = connector.connect();
				connFuture.awaitUninterruptibly();
			} catch (Exception e) {
				iLog.error(e.toString());
				e.printStackTrace();
			}
		}
	}
	
	public void createLink() {
		if (connector != null) {
			try {
				if (connFuture != null && connFuture.getSession() != null) {			
					connFuture.getSession().close(true);
					connFuture = null;
				} 
				whetherLinked = false;
				connector.dispose();
				connector = null;
			} catch (Exception e) {
				iLog.error(e.toString());
				e.printStackTrace();
			}
		}
		try {
			connector = new NioSocketConnector();
			connector.setConnectTimeout(30000);  
			connector.setHandler(new MainLinksHandler());
			connector.getSessionConfig().setReadBufferSize(4096);
			connector.getSessionConfig().setSoLinger(0);
			DefaultIoFilterChainBuilder chain = connector.getFilterChain();
			chain.addLast("objectFilter", new ProtocolCodecFilter(new FilterFactory()));
			connector.setDefaultRemoteAddress(new InetSocketAddress(PropsUtil.get("taizhou.serverIP"), Integer.parseInt(PropsUtil.get("taizhou.port"))));
			if (null != connector && !connector.isActive()) {
				try {
					// 尝试连接默认的地址和端口
					connFuture = connector.connect();
					connFuture.awaitUninterruptibly();
					setWhetherLinked(false);
				} catch (Exception e) {
					iLog.error("尝试连接默认地址和端口失败！" + e.toString());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			iLog.error("创建Mina连接失败！");
			e.printStackTrace();
		}
	}
}
