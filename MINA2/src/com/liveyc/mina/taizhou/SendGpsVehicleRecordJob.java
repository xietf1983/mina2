package com.liveyc.mina.taizhou;

import com.liveyc.mina.taizhou.protocol.MainLinksClient;

public class SendGpsVehicleRecordJob {
	public void run() {
		try {
			new CreateMinaLink().start();	
			Thread.sleep(5000);
			new SendGpsVehicleRecordThread().start();
		} catch (Exception e) {
			System.out.println("�ͻ�������ʧ�ܣ�");
		}
	}
//	public static void main() {
//		SendGpsVehicleRecordJob job = new SendGpsVehicleRecordJob();
//		job.run();
//	}
}
