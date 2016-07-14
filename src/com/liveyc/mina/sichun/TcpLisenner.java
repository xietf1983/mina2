package com.liveyc.mina.sichun;

import java.util.Date;

import org.apache.log4j.Logger;

import com.liveyc.mina.sichun.hander.ClientStartHandler;
import com.liveyc.util.DateUtil;

public class TcpLisenner extends Thread {
	private static Logger iLog = Logger.getLogger(TcpLisenner.class);
	public void run() {
		while (true)
			try {
				if (!ClientDataMsgProtocol.getInstances().isIsconenected() || ClientDataMsgProtocol.getInstances().getConnFuture() == null || !ClientDataMsgProtocol.getInstances().getConnFuture().isConnected() || !ClientDataMsgProtocol.getInstances().getConnFuture().getSession().isConnected()) {
					ClientIPMsgProtocol.getInstances().clientStart();
					Thread.sleep(30000);
					if (ClientIPMsgProtocol.getInstances().getNextIpAndPort().getIp() != null && !ClientIPMsgProtocol.getInstances().getNextIpAndPort().getIp().equals("")) {
						//
						NextIpAndPort nextIpAndPort = new NextIpAndPort();
						nextIpAndPort.setIp(ClientIPMsgProtocol.getInstances().getNextIpAndPort().getIp().replaceAll(" ", ""));
						nextIpAndPort.setPort(ClientIPMsgProtocol.getInstances().getNextIpAndPort().getPort());
						ClientDataMsgProtocol.getInstances().clientStart(nextIpAndPort);
					} else {

					}
				} else {
					if ((new Date()).getTime() - ClientDataMsgProtocol.getInstances().getConnFuture().getSession().getLastReadTime() > DateUtil.MINUTE * 5) {
						ClientIPMsgProtocol.getInstances().clientStart();
						Thread.sleep(30000);
						if (ClientIPMsgProtocol.getInstances().getNextIpAndPort().getIp() != null && !ClientIPMsgProtocol.getInstances().getNextIpAndPort().getIp().equals("")) {
							//
							NextIpAndPort nextIpAndPort = new NextIpAndPort();
							nextIpAndPort.setIp(ClientIPMsgProtocol.getInstances().getNextIpAndPort().getIp().replaceAll(" ", ""));
							nextIpAndPort.setPort(ClientIPMsgProtocol.getInstances().getNextIpAndPort().getPort());
							ClientDataMsgProtocol.getInstances().clientStart(nextIpAndPort);
						}

					}
				}
				Thread.sleep(60000);

			} catch (Exception ex) {
				ClientDataMsgProtocol.getInstances().setIsconenected(false);
			}
	}
}
