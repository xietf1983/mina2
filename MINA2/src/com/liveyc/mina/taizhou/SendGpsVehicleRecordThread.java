package com.liveyc.mina.taizhou;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.mina.taizhou.model.RealLocationReqDataModel;
import com.liveyc.mina.taizhou.protocol.MainLinksClient;

public class SendGpsVehicleRecordThread extends Thread {
	private static Logger iLog = Logger.getLogger(SendGpsVehicleRecordThread.class);
	private static long j = 0;
	private static long gnssCenterId = Long.parseLong(PropsUtil.get("taizhou.gnssCenterId"));
	
	public void run() {
		while(true) {
			try {
				iLog.error("进入发送车辆线程");			
				if (MainLinksClient.getInstances().isWhetherLinked()) {
					iLog.error("开始发送车辆位置信息！");
					for (int i = 0; i < 500; i++) {
						RealLocationReqDataModel reqDataModel = new RealLocationReqDataModel();
						reqDataModel.setModelType(2);
						reqDataModel.setLength(26 + 64);
						if (j <= 2147483648l * 2) {
							j++;
							reqDataModel.setSn(j);
						} else {
							j = 0;
							reqDataModel.setSn(j);
						}
						reqDataModel.setId(0x1200);
						reqDataModel.setGnssCenterId(gnssCenterId);
						reqDataModel.setVersionFlag(new byte[] {1, 2, 15});
						reqDataModel.setEncryptFlag((byte) 0);
						reqDataModel.setEncryptKey(0);
						reqDataModel.setVehicleNo("浙A12345");
						reqDataModel.setVehicleColor((byte) 1);
						reqDataModel.setDataType(0x1202);
						reqDataModel.setDataLength(36);
						reqDataModel.setEncrypt((byte) 0);
						Date date = new Date();
						SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
						String[] dateArray = sdft.format(date).split("-");
						String year = dateArray[0];
						String a = Integer.toBinaryString(Integer.parseInt(year));
						int year1 = Integer.valueOf(a.substring(0, a.length() - 8), 2).intValue();
						int year2 = Integer.valueOf(a.substring(a.length() - 8), 2).intValue();
						int month = Integer.parseInt(dateArray[1]);
						int day = Integer.parseInt(dateArray[2]);
						int hour = Integer.parseInt(dateArray[3]);
						int min = Integer.parseInt(dateArray[4]);
						int second = Integer.parseInt(dateArray[5]);
						reqDataModel.setDate(new byte[] {(byte) day,(byte) month,(byte) year1,(byte) year2});
						reqDataModel.setTime(new byte[] {(byte) hour,(byte) min,(byte) second});
						reqDataModel.setLon(121389239);
						reqDataModel.setLat(28734286);
						reqDataModel.setVec1(72);
						reqDataModel.setVec2(72);
						reqDataModel.setVec3(1250);
						reqDataModel.setDirection(245);
						reqDataModel.setAltitude(156);
						reqDataModel.setState(122);   //随便填的
						reqDataModel.setAlarm(551);   //随便填的
						MainLinksClient.getInstances().getConnFuture().getSession().write(reqDataModel);
					}
				} else {
					try {
						iLog.error("Mina未连接成功，线程休眠1分钟");
						Thread.sleep(6000000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				Thread.sleep(500000);
			} catch (Exception e) {
				iLog.error("测试段发送");
			}
		}
	}
}
