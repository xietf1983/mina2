package com.liveyc.mina.client.hander;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.client.CRC32Util;
import com.liveyc.mina.client.DKMsgProtocol;
import com.liveyc.mina.client.DKMsgStartProtocol;
import com.liveyc.mina.client.model.DeviceObject;
import com.liveyc.mina.client.model.Model101Req;
import com.liveyc.mina.client.model.Model101Res;
import com.liveyc.mina.client.model.Model105Req;
import com.liveyc.mina.client.model.Model105Res;
import com.liveyc.mina.client.model.Model107Res;
import com.liveyc.mina.client.model.Model111Req;
import com.liveyc.mina.client.model.base.BaseModel;
import com.liveyc.mina.client.service.DKServiceUtil;

public class DKClientStartHandler extends IoHandlerAdapter {
	private static Logger iLog = Logger.getLogger(DKClientStartHandler.class);
	private static Integer msgsn = 1;

	/**
	 * 建立连结时候，发送验证信息
	 */
	public void sessionOpened(IoSession session) throws Exception {
		iLog.info("新连接已打开");
		//DKMsgStartProtocol.getInstances().setIsconenected(true);
		// sendMsg(session);
	}

	// 当一个客户端关闭时
	public void sessionClosed(IoSession session) {
		// 连接异常处理
		// DKServiceUtil.getService().dealSessionClose();
		session.close();
		DKMsgProtocol.getInstances().setIsconenected(false);
		if (DKMsgProtocol.getInstances().getConnFuture().getSession() != null) {
			DKMsgProtocol.getInstances().getConnFuture().getSession().close();
		}

	}

	// 当服务器端发送的消息到达时:
	public void messageReceived(IoSession session, Object message) throws Exception {
		BaseModel b = (BaseModel) message;
		if (message != null) {
			if (b.getXyh().equals("601")) {
				// 连接处理
				Model101Res res = (Model101Res) message;
				if (res.getApply().equals("1")) {
					// DKMsgProtocol.getInstances().setIsconenected(true);

				} else {
					iLog.error("连接失败,关闭连接，5秒钟后重试");
				}
			} else if (b.getXyh().equals("603")) {

			} else if (b.getXyh().equals("604")) {

			} else if (b.getXyh().equals("611")) {

			} else if (b.getXyh().equals("105")) {
				Model105Req req = (Model105Req) message;
				Model105Res res = new Model105Res();
				res.setPackageLength(50);
				res.setRq(req.getRi());
				res.setRzbh(req.getRzbh());
				res.setSj(req.getSj());
				res.setXyh("605");
				session.write(res);

			} else if (b.getXyh().equals("107")) {
				// 协议7
				Model107Res res = new Model107Res();
				res.setBt((byte) (0x02));
				res.setBw((byte) (0x03));
				res.setRzbh(PropsUtil.get(PropsKeys.DK_RZBH));
				res.setXyh("607");
				List<DeviceObject> list = new ArrayList();
				List<HashMap> dataList = DKServiceUtil.getService().getDeviceJcbk(5);
				if (dataList == null) {
					dataList = new ArrayList();
				}
				Map pp = new HashMap();
				int length =0;
				for (HashMap p : dataList) {
					if (pp.get(String.valueOf(p.get("CODEINFO"))) == null) {
						pp.put(String.valueOf(p.get("CODEINFO")), String.valueOf(p.get("CODEINFO")));
						DeviceObject device = new DeviceObject();
						device.setBh(CRC32Util.getFixString(String.valueOf(p.get("CODEINFO") == null ? "" : p.get("CODEINFO")), 15));// //设备编号
						length=length+15; 
						int cds =2;
						if(p.get("CDSL")==null || String.valueOf(p.get("CDSL")).equals("")){
							
						}else{
							cds= Integer.parseInt(String.valueOf(p.get("CDSL")));
						}
						length=length+2;
						length=length+cds;
						device.setCdz(CRC32Util.getFixNumString(String.valueOf(cds),2));
						String tt ="";
						for(int i=0;i<cds;i++){
							tt=tt+"u";
						}
						device.setCdzt(tt);
						device.setCpuzyl("050");
						device.setIp(CRC32Util.getFixString(String.valueOf(p.get("SBBH") == null ? "" : p.get("SBBH")), 15));// IP地址
						length=length+15;
						device.setJxm("u");
						device.setJxwd("000");
						length=length+7;
						int sxjsl =2;
						if(p.get("SXJSL")==null || String.valueOf(p.get("SXJSL")).equals("")){
							
						}else{
							sxjsl= Integer.parseInt(String.valueOf(p.get("SXJSL")));
						}
						length=length+2;
						length=length+sxjsl;
						device.setSxjzs(CRC32Util.getFixNumString(String.valueOf(sxjsl),2));
						String tt2 ="";
						for(int i=0;i<sxjsl;i++){
							tt2=tt2+"u";
						}
						//device.setSxjzs("03");
						device.setSxjzt(tt2);
						device.setXhdzt("u");
						device.setYprl("1000000");
						device.setYpsyrl("0100000");
						length=length+15;
						list.add(device);
						iLog.error("CDSL="+device.getCdz() );
					}
				}
				res.setList(list);
				res.setSjcd(CRC32Util.getFixNumString(String.valueOf(length + 8 + 5), 7));
				res.setPackageLength(length+ 8 + 5 + 1 + 7 + 15 + 1 + 3);
				res.setSbgs(CRC32Util.getFixNumString(String.valueOf(list.size()), 5));
				session.write(res);
				DKMsgProtocol.getInstances().setIsconenected(true);
			}

		}

	}

	// 发送消息给服务器
	public void messageSent(IoSession session, Object message) throws Exception {

	}

	// 发送消息异常
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close();
	}

	/**
	 * 发送注册请求
	 * 
	 * @param session
	 * @throws Exception
	 */
	public void sendMsg(IoSession session) throws Exception {
		
	}

	public void sessionCreated(IoSession session) throws Exception {
		// sendMsg(session);
		// session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 50);
		DKServiceUtil.getService().dealSessionCreat(5);
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
		//DKMsgProtocol.getInstances().setIsconenected(true);

	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		Model111Req model = new Model111Req();
		if (status == IdleStatus.BOTH_IDLE) {
			if (msgsn < Integer.MAX_VALUE - 1) {
				msgsn = msgsn + 1;
			} else {
				msgsn = msgsn - 1;
			}
			model.setBt((byte) 0x02);
			model.setBw((byte) 0x03);
			model.setPackageLength(28);
			model.setRzbh(PropsUtil.get(PropsKeys.DK_RZBH));
			model.setXyh("111");
			session.write(model);
		}
	}
}
