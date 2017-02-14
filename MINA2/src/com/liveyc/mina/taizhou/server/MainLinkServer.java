package com.liveyc.mina.taizhou.server;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.taizhou.server.filter.ServerFilterFactory;
import com.liveyc.mina.taizhou.server.handler.ServerMainLinkHandler;

public class MainLinkServer extends Thread{
	private static Logger iLog = Logger.getLogger(MainLinkServer.class);

	@Override
	public void run() {
		try {
			String port = PropsUtil.get("taizhou.serverport");
			if (port != null && !port.equals("")) {
				NioSocketAcceptor acceptor = new NioSocketAcceptor();
				acceptor.getFilterChain().addLast("log", new LoggingFilter()); 
				acceptor.getFilterChain().addLast("objectFilter", new ProtocolCodecFilter(new ServerFilterFactory()));
				acceptor.setHandler(new ServerMainLinkHandler());
				acceptor.bind(new InetSocketAddress(Integer.parseInt(port)));
//				acceptor.setSessionRecycler(new ExpiringSessionRecycler(1000 * 60 * 3));   //会话超时时间3分钟
				iLog.info("Mina开启监听端口为：" + port);
			} else {
				throw new Exception("请配置监听端口！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			iLog.error("启动台州上传车辆定位信息服务失败！错误原因：" + e.toString());
		}
	}
}
