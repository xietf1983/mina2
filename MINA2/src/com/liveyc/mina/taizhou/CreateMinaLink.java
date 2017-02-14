package com.liveyc.mina.taizhou;

import org.apache.log4j.Logger;

import com.liveyc.mina.taizhou.protocol.MainLinksClient;

public class CreateMinaLink extends Thread{
	private static Logger iLog = Logger.getLogger(CreateMinaLink.class);

	@Override
	public void run() {
		while (true) {
			try {
				if (!MainLinksClient.getInstances().isWhetherLinked()) {
					if (MainLinksClient.getInstances().getConnector() == null) {
						MainLinksClient.getInstances().createLink();
					} else {
						MainLinksClient.getInstances().recreateLink();
					}
					Thread.sleep(60000 * 5);
				} 
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
