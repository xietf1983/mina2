package com.liveyc.scheduling;

import com.liveyc.mina.ClientMsgProtocol;

/*******************************************************************************
 * ��ȡ������¼����ʱ��ȡ������¼
 * 
 * @author Administrator
 * 
 */
public class GaVehViolationJob {
	private static boolean runing = false;

	public void run() {
		if (!runing) {
			runing = true;
			ClientMsgProtocol.getInstances().clientStart();
			new SendDataThread().start();
		}
	}

}
