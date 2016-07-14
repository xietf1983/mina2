package com.liveyc.mina.sichun;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SendDataJob {
	private static boolean runing = false;

	public void run() {
		if (!runing) {
			runing = true;
			new TcpLisenner().start();
			new SendDataThread().start();
			new SendHisDataThread().start();
		}
	}
}
