package com.liveyc.mina.client;

import java.util.Date;

import org.apache.log4j.Logger;

import com.liveyc.mina.sichun.ClientDataMsgProtocol;
import com.liveyc.mina.sichun.ClientIPMsgProtocol;
import com.liveyc.mina.sichun.NextIpAndPort;
import com.liveyc.util.DateUtil;

public class TcpLisenner extends Thread {
	private static Logger iLog = Logger.getLogger(TcpLisenner.class);
	public void run() {

		while (true)
			try {

				if (!DKMsgProtocol.getInstances().isIsconenected() || DKMsgProtocol.getInstances().getConnFuture() == null || !DKMsgProtocol.getInstances().getConnFuture().isConnected() || !DKMsgProtocol.getInstances().getConnFuture().getSession().isConnected()) {
					DKMsgStartProtocol.getInstances().clientStart();
					Thread.sleep(30000);
					if (DKMsgStartProtocol.getInstances().getNextIpAndPort().getIp() != null && !DKMsgStartProtocol.getInstances().getNextIpAndPort().getIp().equals("")) {
						//
						NextIpAndPort nextIpAndPort = new NextIpAndPort();
						nextIpAndPort.setIp(DKMsgStartProtocol.getInstances().getNextIpAndPort().getIp().replaceAll(" ", ""));
						nextIpAndPort.setPort(DKMsgStartProtocol.getInstances().getNextIpAndPort().getPort());
						DKMsgProtocol.getInstances().clientStart(nextIpAndPort);
					} else {

					}
				} else {
					if ((new Date()).getTime() - DKMsgProtocol.getInstances().getConnFuture().getSession().getLastReadTime() > DateUtil.MINUTE * 30) {
						DKMsgStartProtocol.getInstances().clientStart();
						Thread.sleep(30000);
						if (DKMsgStartProtocol.getInstances().getNextIpAndPort().getIp() != null && !DKMsgStartProtocol.getInstances().getNextIpAndPort().getIp().equals("")) {
							//
							NextIpAndPort nextIpAndPort = new NextIpAndPort();
							nextIpAndPort.setIp(DKMsgStartProtocol.getInstances().getNextIpAndPort().getIp().replaceAll(" ", ""));
							nextIpAndPort.setPort(DKMsgStartProtocol.getInstances().getNextIpAndPort().getPort());
							DKMsgProtocol.getInstances().clientStart(nextIpAndPort);
						}

					}
				}

				Thread.sleep(6000);

			} catch (Exception ex) {
				iLog.error(ex.toString());
				DKMsgProtocol.getInstances().setIsconenected(false);
			}
	}

}
