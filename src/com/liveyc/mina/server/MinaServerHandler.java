package com.liveyc.mina.server;

import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;


import com.liveyc.configuration.PropsValues;


public class MinaServerHandler extends IoHandlerAdapter {
	private static final Log log = LogFactory.getLog(MinaServerHandler.class);
	private Object mutex = new Object();

	/**
	 * 建立连结时候，发送验证信息
	 */
	public void sessionOpened(IoSession session) throws Exception {
		log.info("新连接已打开");
		// 发送注册消息
		//sendMsg(session);
		// MsgBlock msgBlock = new MsgBlock();
	}

	// 当一个客户端关闭时
	public void sessionClosed(IoSession session) throws Exception {
		//ItsClient.getInstances().setIsconenected(false);
		//session.close();
		super.sessionClosed(session);
		log.info("one server Disconnect !");
	}

	// 当服务器端发送的消息到达时:
	public void messageReceived(IoSession session, Object message) throws Exception {
		//int a=0;

	}

	// 发送消息给服务器
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	// 发送消息异常
	public void exceptionCaught(IoSession session, Throwable cause) {
		System.out.print(cause.toString());
		session.close();
	}

	
	public void sessionCreated(IoSession session) {
	}

}
