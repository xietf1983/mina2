package com.ice;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.ice.exception.NanwangException;


import Ice.InitializationData;
import Ice.LocalException;
import Ice.Util;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: ȫ�ֹ���Communicator��Communicator���������ĵ�ʵ���
 * �������Ϊ�ͻ��˺ͷ���˹���һ��Communicator�� ע���ڳ����˳�ʱ����contextDestroyed�¼����ͷ���Դ
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: nanwang
 * </p>
 * 
 * @author yangj
 * @version 1.0
 */
public class ICECommunicatorHolder {
	private Ice.Communicator iCommunicator = null;
	private static ICECommunicatorHolder instance = new ICECommunicatorHolder();
	private static Logger iLog = Logger.getLogger(ICECommunicatorHolder.class);

	private ICECommunicatorHolder() {
	}

	public static ICECommunicatorHolder getInstance() {
		return instance;
	}

	/**
	 * �õ�ICEƽ̨Communicator�������û��ʼ�������ȳ�ʼ�� �������Ϊ�ͻ��˺ͷ���˹���һ��Communicator
	 * 
	 * @return Communicator
	 * @throws NanwangException
	 */
	public Ice.Communicator getICECommunicator() throws Exception {
		if (iCommunicator == null) {
			synchronized (this) {
				if (iCommunicator == null) {
					try {
						InitializationData initData = new InitializationData();
						try {
							initData.properties = IcePropertiesUtil.getInstance().initProeperties("ice.xml");
						} catch (LocalException ex) {
							iLog.error("��ice����ʧ��" + ": " + ex);
						} catch (java.lang.Exception ex) {
							iLog.error("��ice����ʧ��" + ": unknown exception");
						}
						// ����ͳһ��log4j��־
						initData.logger = new ICELogger(Logger.getLogger("ICE.Communicator"));
						iCommunicator = Ice.Util.initialize(initData);
					} catch (Ice.LocalException ex) {
						// ex.printStackTrace();
						if (iCommunicator != null) {
							iCommunicator.destroy();
						}
						throw new Exception("���ṩ����ķ�������ͨѶʧ�ܣ������Ի���ϵ�ͷ���", ex);
					}
				}
			}
		}
		return iCommunicator;
	}

	/**
	 * destroy�ͷ�ICE��Դ ע���ڳ����˳�ʱ����SPICEServer��contextDestroyed�¼����ͷ���Դ
	 */
	public synchronized void destroy() {
		if (iCommunicator != null) {
			iLog.info("�رշ�������iceƽ̨...");
			try {
				iCommunicator.destroy();
				iCommunicator = null;
				iLog.info("ice iCommunicator is stopped.");
			} catch (Exception ex) {
				iLog.error("�رշ�������iceƽ̨ʧ��", ex);
			}
		}
	}

}
