package com.liveyc.mina.sichun.hander;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.client.model.Model101Req;
import com.liveyc.mina.sichun.ClientIPMsgProtocol;
import com.liveyc.mina.sichun.NextIpAndPort;
import com.liveyc.mina.sichun.model.BaseModel;
import com.liveyc.mina.sichun.model.RejReqDataModel;
import com.liveyc.mina.sichun.model.RejResDataModel;

public class ClientHandler extends IoHandlerAdapter {
	private static Logger iLog = Logger.getLogger(ClientHandler.class);
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
		// �����쳣����
		// DKServiceUtil.getService().dealSessionClose();
		session.close();
		// DKMsgProtocol.getInstances().setIsconenected(false);

	}

	// ���������˷��͵���Ϣ����ʱ:
	public void messageReceived(IoSession session, Object message) throws Exception {
		BaseModel b = (BaseModel) message;
		if (message != null) {
			if (b.getMessageType() == 1) {
				RejResDataModel m = (RejResDataModel) message;
				if (m.getZcbs() == 1) {
					NextIpAndPort nextIpAndPort = ClientIPMsgProtocol.getInstances().getNextIpAndPort();
					nextIpAndPort.setIp(m.getIp());
					nextIpAndPort.setPort(m.getPort());
				}
			} else {
				iLog.error("����ʧ��,�ر����ӣ�5���Ӻ�����");
			}
		}
		session.close();

	}

	// ������Ϣ��������
	public void messageSent(IoSession session, Object message) throws Exception {

	}

	// ������Ϣ�쳣
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close();
	}

	/**
	 * ����ע������
	 * 
	 * @param session
	 * @throws Exception
	 */
	public void sendMsg(IoSession session) throws Exception {
		RejReqDataModel model = new RejReqDataModel();
		model.setTag(0x55aa66bb);
		model.setJrbh(PropsUtil.get(PropsKeys.SICHUN_RZBH));
		model.setXxbbh(PropsUtil.get(PropsKeys.SICHUN_VSERSION));
		model.setEndtag(0x88aa99bb);
		model.setMessageType(1);
		model.setLength(44);
		session.write(model);
	}

	public void sessionCreated(IoSession session) throws Exception {
		sendMsg(session);
	}

}
