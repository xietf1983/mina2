package com.liveyc.mina.sichun.hander;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.liveyc.mina.client.model.PassVehicleModel;
import com.liveyc.mina.client.model.TpObject;
import com.liveyc.mina.client.model.base.BaseModel;
import com.liveyc.mina.client.service.DKServiceUtil;
import com.liveyc.mina.sichun.ClientDataMsgProtocol;
import com.liveyc.mina.sichun.model.HeatReqDataModel;
import com.liveyc.mina.sichun.model.VehicleReqDataModel;
import com.liveyc.mina.util.CodecUtil;

public class ClientStartHandler extends IoHandlerAdapter {
	private static Logger iLog = Logger.getLogger(ClientStartHandler.class);
	private static Integer msgsn = 1;

	/**
	 * 建立连结时候，发送验证信息
	 */
	public void sessionOpened(IoSession session) throws Exception {
		iLog.info("新连接已打开");
		ClientDataMsgProtocol.getInstances().setIsconenected(true);
		// sendMsg(session);
	}

	// 当一个客户端关闭时
	public void sessionClosed(IoSession session) {
		// 连接异常处理
		//DKServiceUtil.getService().dealSessionClose();
		ClientDataMsgProtocol.getInstances().setIsconenected(false);
		session.close();
		iLog.error("连接失败");

	}

	// 当服务器端发送的消息到达时:
	public void messageReceived(IoSession session, Object message) throws Exception {
		BaseModel b = (BaseModel) message;
		if (message != null) {

		}

	}

	// 发送消息给服务器
	public void messageSent(IoSession session, Object message) throws Exception {

	}

	// 发送消息异常
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close();
		ClientDataMsgProtocol.getInstances().setIsconenected(false);
		iLog.error("连接失败"+cause.getMessage());
		//DKServiceUtil.getService().dealSessionClose();
		// DKMsgStartProtocol.getInstances().setIsconenected(false);
	}

	/**
	 * 发送注册请求
	 * 
	 * @param session
	 * @throws Exception
	 */

	public void sessionCreated(IoSession session) throws Exception {
		// sendMsg(session);
		// session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 50);
		
		DKMsgStartProtocol.getInstances().setIsconenected(true);
		//DKServiceUtil.getService().dealSessionCreat();
		//session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10 );
		//test(session);

	}
	public void test(IoSession session){
		VehicleReqDataModel veh = new VehicleReqDataModel();
		veh.setMessageType(3);
		veh.setSn(1l);
		veh.setXxbbh(PropsUtil.get(PropsKeys.SICHUN_VSERSION));
		veh.setJrbh(PropsUtil.get(PropsKeys.SICHUN_RZBH));
		veh.setKkbh("510300100096000000");
		veh.setCdbh(1 + " ");
		veh.setFxbh("5103000002");
		veh.setSbbh("510300100096000000");
		// 设置号牌号码
		CodecUtil.getFixStringBlank("川CW8005", 18);
		veh.setHphm(CodecUtil.getFixStringBlank("川CW8005", 18));
		veh.setHpys("2 ");
		// 设置号牌颜色
		veh.setHpzl("02");
		veh.setCwhphm(CodecUtil.getFixStringBlank("", 18));
		veh.setCwhpys(CodecUtil.getFixStringBlank("", 2));
		veh.setCwhpzy("  ");
		veh.setHpyz("3 ");
		veh.setCscd(0);
		veh.setClpp(CodecUtil.getFixStringBlank("", 10));
		veh.setClwx(CodecUtil.getFixStringBlank("", 10));
		veh.setCsys("Z ");
		veh.setCllx(CodecUtil.getFixStringBlank("", 4));
		veh.setWflx(CodecUtil.getFixStringBlank("", 4));
		veh.setClsd(0);
		veh.setClxs(0);
		veh.setJgsj(CodecUtil.getFixStringBlank("2015-11-19 15:36:46", 24));
		veh.setCpzbjq(0);
		veh.setCpxzb(0);
		veh.setCpyzb(0);
		veh.setCpkd(0);
		veh.setRljq(0);
		veh.setTxsl(1);
		veh.setTplj1(CodecUtil.getFixStringBlank("http://10.67.115.234:81/24-0-0-1-1.jpg?p=3f0b40e9007fe78c4fae21fe879c3a79", 300));
		veh.setTplj2(CodecUtil.getFixStringBlank("", 300));
		veh.setTplj3(CodecUtil.getFixStringBlank("", 300));
		veh.setTplj4(CodecUtil.getFixStringBlank("", 300));
		veh.setLxlj(CodecUtil.getFixStringBlank("", 300));
		veh.setEndtag(0x88aa99bb);
		veh.setTag(0x55aa66bb);
		veh.setLength(1746);
		session.write(veh);
	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		HeatReqDataModel model = new HeatReqDataModel();
		if (status == IdleStatus.BOTH_IDLE) {
			if (msgsn < Integer.MAX_VALUE - 1) {
				msgsn = msgsn + 1;
			} else {
				msgsn = msgsn - 1;
			}
			model.setEndtag(0x88aa99bb);
			model.setLength(16);
			model.setMessageType(5);
			model.setTag(0x55aa66bb);
			model.setXtxx(0x11aa22bb);
			model.setXxbbh(PropsUtil.get(PropsKeys.SICHUN_VSERSION));
			session.write(model);
		}
	}
}
