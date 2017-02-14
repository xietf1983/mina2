package com.liveyc.mina.taizhou.server.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.liveyc.data.service.GaVehViolationServiceUtil;
import com.liveyc.mina.taizhou.server.model.ServerBaseModel;
import com.liveyc.mina.taizhou.server.model.ServerGpsVehicleRecordModel;
import com.liveyc.mina.taizhou.server.model.ServerLoginRespModel;

public class ServerMainLinkHandler extends IoHandlerAdapter{
	private static Logger iLog = Logger.getLogger(ServerMainLinkHandler.class);
	private static long i = 0;
	
	// 当一个客户端关闭时
	public void sessionClosed(IoSession session) throws Exception {
		session.close();
	}
	
	// 发送消息异常
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close();
	}
	
	public void messageReceived(IoSession session, Object message) throws Exception {
		ServerBaseModel baseModel = (ServerBaseModel) message;
		if (baseModel.getId() == 4097) {
			ServerLoginRespModel respModel = new ServerLoginRespModel();
			respModel.setHeadFlag((byte) 0x5b);
			respModel.setLength(26 + 5);
			if (i <= 2147483648l * 2) {
				i++;
				respModel.setSn(i);
			} else {
				i = 0;
				respModel.setSn(i);
			}
			respModel.setId(0x1002);
			respModel.setGnssCenterId(baseModel.getGnssCenterId());
			respModel.setVersionFlag(baseModel.getVersionFlag());
			respModel.setEncryptFlag((byte) 0);
			respModel.setEncryptKey(0);
			respModel.setResult((byte) 0x00);
			respModel.setVerifyCode(0);
			respModel.setEndFlag((byte) 0x5d);
			session.write(respModel);
		} else if (baseModel.getId() == 4610) {
			ServerGpsVehicleRecordModel gpsVehicleRecord = (ServerGpsVehicleRecordModel) message;
			try {
				GaVehViolationServiceUtil.getService().addGpsVehicleRecord(gpsVehicleRecord);
			} catch (Exception e) {
				iLog.error("向数据库中添加车辆信息失败！" + e.toString());
			}
		}
	}
}
