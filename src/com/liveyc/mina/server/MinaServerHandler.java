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
	 * ��������ʱ�򣬷�����֤��Ϣ
	 */
	public void sessionOpened(IoSession session) throws Exception {
		log.info("�������Ѵ�");
		// ����ע����Ϣ
		//sendMsg(session);
		// MsgBlock msgBlock = new MsgBlock();
	}

	// ��һ���ͻ��˹ر�ʱ
	public void sessionClosed(IoSession session) throws Exception {
		//ItsClient.getInstances().setIsconenected(false);
		//session.close();
		super.sessionClosed(session);
		log.info("one server Disconnect !");
	}

	// ���������˷��͵���Ϣ����ʱ:
	public void messageReceived(IoSession session, Object message) throws Exception {
		//int a=0;

	}

	// ������Ϣ��������
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	// ������Ϣ�쳣
	public void exceptionCaught(IoSession session, Throwable cause) {
		System.out.print(cause.toString());
		session.close();
	}

	
	public void sessionCreated(IoSession session) {
	}

}
