package com.liveyc.mina.hander;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.ClientMsgProtocol;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.util.DateUtil;
import com.liveyc.util.Format;

public class ItsClientHandler extends IoHandlerAdapter {
	private static Logger iLog = Logger.getLogger(ItsClientHandler.class);
	private Object mutex = new Object();
	private static Integer msgsn = 1;

	/**
	 * ��������ʱ�򣬷�����֤��Ϣ
	 */
	public void sessionOpened(IoSession session) throws Exception {
		iLog.info("�������Ѵ�");
		// sendMsg(session);
	}

	// ��һ���ͻ��˹ر�ʱ
	public void sessionClosed(IoSession session) {
		ClientMsgProtocol.getInstances().setIsconenected(false);
	}

	// ���������˷��͵���Ϣ����ʱ:
	public void messageReceived(IoSession session, Object message) throws Exception {

	}

	// ������Ϣ��������
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	// ������Ϣ�쳣
	public void exceptionCaught(IoSession session, Throwable cause) {
		// System.out.print(2222222);
		session.close();
		ClientMsgProtocol.getInstances().setIsconenected(false);
	}

	/**
	 * ����ע������
	 * 
	 * @param session
	 * @throws Exception
	 */
	public void sendMsg(IoSession session) throws Exception {
		ReqConnetModel model = new ReqConnetModel();
		model.setDataLength(61);
		model.setMessageSn(1);
		model.setMessageType(0x2001);
		model.setConnetTime(DateUtil.getCurrentDateHHMMSS().replaceAll("-", "").replaceAll(":", "").trim().replaceAll(" ", ""));
		model.setUserId(PropsValues.ITS_USERID);
		model.setMac(PropsValues.ITS_PASSWORD);
		String version = PropsValues.ITS_VSERSION;
		String[] vv = version.split("\\.");
		byte[] versiobbyte = new byte[3];
		for (int i = 0; i < vv.length; i++) {
			versiobbyte[i] = Byte.parseByte(vv[i]);
		}
		model.setVersion(versiobbyte);
		session.write(model);
	}

	public void sessionCreated(IoSession session) throws Exception {
		sendMsg(session);
		//session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10*6);

	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// ���IoSession���ã���ر�����
		if (status == IdleStatus.BOTH_IDLE) {
			if (msgsn < Integer.MAX_VALUE - 1) {
				msgsn = msgsn + 1;
			} else {
				msgsn = msgsn - 1;
			}
			ReqLinkTestModel linkTest = new ReqLinkTestModel();
			linkTest.setDataLength(8);
			linkTest.setMessageSn(msgsn);
			linkTest.setMessageType(PropsValues.UP_LINKTEST_REQ);
			session.write(linkTest);
		}
	}
}
