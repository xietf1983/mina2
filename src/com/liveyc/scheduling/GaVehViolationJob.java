package com.liveyc.scheduling;

import com.liveyc.mina.ClientMsgProtocol;

/*******************************************************************************
 * 获取过车记录，定时获取过车记录
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
