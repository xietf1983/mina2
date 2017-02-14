package com.liveyc.mina.taizhou.handler;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.junit.Test;

import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.taizhou.model.BaseModel;
import com.liveyc.mina.taizhou.model.LoginReqModel;
import com.liveyc.mina.taizhou.model.LoginRespModel;
import com.liveyc.mina.taizhou.protocol.MainLinksClient;

public class MainLinksHandler extends IoHandlerAdapter {
	private static Logger iLog = Logger.getLogger(MainLinksHandler.class);
	
	public void sessionOpened(IoSession session) throws Exception {
		
	}
	
	public void messageReceived(IoSession session, Object message) throws Exception {
		BaseModel baseModel = (BaseModel) message;
		if (baseModel != null) {
			if (baseModel.getModelType() == 3) {
				LoginRespModel respModel = (LoginRespModel) message;
				int v = respModel.getResult() & 0xFF;
				if (v == 0) {
					iLog.error("登录成功！");
					MainLinksClient.getInstances().setWhetherLinked(true);
				} else {
					iLog.error("登录失败，关闭Mina连接！错误原因：");
					if (v == 1) {
						iLog.error("IP地址不正确！");
					} else if (v == 2) {
						iLog.error("接入码不正确！");
					} else if (v == 3) {
						iLog.error("用户没有注册！");
					} else if (v == 4) {
						iLog.error("密码错误！");
					} else if (v == 5) {
						iLog.error("资源紧张，稍后再连接！");
					} else if (v == 6) {
						iLog.error("其他错误！");
					}
					session.close();
				}
			}
		}
	}
	
	public void exceptionCaught(IoSession session, Throwable cause) {
		iLog.error("错误异常！");
		session.close();
	}
	
	public void sessionCreated(IoSession session) throws Exception {
		LoginReqModel loginReqModel = new LoginReqModel();
		loginReqModel.setLength(26 + 46);
		loginReqModel.setSn(0);
		loginReqModel.setId(0x1001);
		loginReqModel.setGnssCenterId(Long.parseLong(PropsUtil.get("taizhou.gnssCenterId")));
		loginReqModel.setVersionFlag(new byte[] {1, 2, 15});
		loginReqModel.setEncryptFlag((byte) 0);
		loginReqModel.setEncryptKey(0);
		loginReqModel.setModelType(1);
		loginReqModel.setUserId(Integer.parseInt(PropsUtil.get("taizhou.userId")));
		loginReqModel.setPassWord(PropsUtil.get("taizhou.password"));
		loginReqModel.setDownLinkIp("");
		loginReqModel.setDownLinkPort(0);
		session.write(loginReqModel);
		iLog.error("发送登录信息完成！");
	}
	
	@Test
	public void test1(){
//		int v = 0xD9;
//		int hv = 0x07;
//		System.out.println("0xD9:" + v + ";0x07:" + hv);
//		byte b = (byte) 0x50;
//		int v = b & 0xff;
//		System.out.println(b);
		
//		byte[] b = new byte[] {(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
//		System.out.println(new String(b));
		try {
//			byte[] c = new byte[8];
//			String s = "1234";
//			byte[] a = s.getBytes("gbk");
//			for (int i = 0; i < 8; i++) {
//				if (i < a.length) {
//					c[i] = a[i];
//				} else {
//					c[i] = (byte) 0x00;
//				}
//			}
//			System.out.println(new String(c, "gbk"));
//			byte a = (byte) 0x07;
//			byte b = (byte) 0xD9;
//			String yearStr = Integer.toBinaryString(a & 0xff) + Integer.toBinaryString(b & 0xff);
//			System.out.println(Integer.valueOf(yearStr, 2).toString());    //二进制转十进制 
//			Date date = new Date();
//			SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
//			String[] dateArray = sdft.format(date).split("-");
//			String year = dateArray[0];
//			String a = Integer.toBinaryString(Integer.parseInt(year));
//			System.out.println(a);
//			System.out.println(a.substring(a.length() - 8));
//			System.out.println(a.substring(0, a.length() - 8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
