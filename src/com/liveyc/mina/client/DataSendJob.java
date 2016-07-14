package com.liveyc.mina.client;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DataSendJob {
	private static boolean runing = false;

	public void run() {
		if (!runing) {
			runing = true;
			new TcpLisenner().start();
			//new SendHisDataThread().start();
			//new SendDKDataThread().start();
		}
	}
}
