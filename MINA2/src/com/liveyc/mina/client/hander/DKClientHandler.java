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
import com.liveyc.mina.sichun.NextIpAndPort;

public class DKClientHandler extends IoHandlerAdapter {
	private static Logger iLog = Logger.getLogger(DKClientHandler.class);
	private static Integer msgsn = 1;

	/**
	 * ��������ʱ�򣬷�����֤��Ϣ
	 */
	public void sessionOpened(IoSession session) throws Exception {
		iLog.info("�������Ѵ�");
		// sendMsg(session);
	}

	// ��һ���ͻ��˹ر�ʱ
	public void sessionClosed(IoSession session) {
		// �����쳣����
		// DKServiceUtil.getService().dealSessionClose();
		session.close();
		//DKMsgProtocol.getInstances().setIsconenected(false);
		// DKMsgProtocol.getInstances().setIsconenected(false);

	}

	// ���������˷��͵���Ϣ����ʱ:
	public void messageReceived(IoSession session, Object message) throws Exception {
		BaseModel b = (BaseModel) message;
		if (message != null) {
			if (b.getXyh().equals("601")) {
				// ���Ӵ���
				Model101Res res = (Model101Res) message;
				if (res.getApply().equals("1")) {
					String ip = res.getIpdz();
					String dk = res.getDk();
					NextIpAndPort nextIpAndPort = DKMsgProtocol.getInstances().getNextIpAndPort();
					nextIpAndPort.setIp(ip);
					nextIpAndPort.setPort(Integer.parseInt(dk));
					DKMsgStartProtocol.getInstances().setNextIpAndPort(nextIpAndPort);

				} else {
					iLog.error("����ʧ��,�ر����ӣ�5���Ӻ�����");
				}
			} else if (b.getXyh().equals("603")) {

			} else if (b.getXyh().equals("604")) {

			} else if (b.getXyh().equals("611")) {

			}else if (b.getXyh().equals("105")) {
				Model105Req req = (Model105Req) message;
				Model105Res res = new Model105Res();
				res.setPackageLength(50);
				res.setRq(req.getRi());
				res.setRzbh(req.getRzbh());
				res.setSj(req.getSj());
				res.setXyh("605");
				session.write(res);

			} else if (b.getXyh().equals("107")) {
				// Э��7
				DKMsgProtocol.getInstances().setIsconenected(true);
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
						device.setBh(CRC32Util.getFixString(String.valueOf(p.get("CODEINFO") == null ? "" : p.get("CODEINFO") == null), 15));// //�豸���
						length=length+15; 
						int cds =2;
						if(pp.get("CDSL")==null || String.valueOf(pp.get("CDSL")).equals("")){
							
						}else{
							cds= Integer.parseInt(String.valueOf(pp.get("CDSL")));
						}
						length=length+cds;
						device.setCdz(CRC32Util.getFixNumString(String.valueOf(cds),2));
						String tt ="";
						for(int i=0;i<cds;i++){
							tt=tt+"u";
						}
						device.setCdzt(tt);
						device.setCpuzyl("050");
						device.setIp(CRC32Util.getFixString(String.valueOf(p.get("SBBH") == null ? "" : p.get("SBBH") == null), 15));// IP��ַ
						device.setJxm("u");
						device.setJxwd("000");
						int sxjsl =2;
						if(pp.get("SXJSL")==null || String.valueOf(pp.get("SXJSL")).equals("")){
							
						}else{
							sxjsl= Integer.parseInt(String.valueOf(pp.get("SXJSL")));
						}
						length=length+sxjsl;
						device.setSxjzs(CRC32Util.getFixNumString(String.valueOf(sxjsl),2));
						String tt2 ="";
						for(int i=0;i<cds;i++){
							tt2=tt2+"u";
						}
						//device.setSxjzs("03");
						device.setSxjzt(tt2);
						device.setXhdzt("u");
						device.setYprl("1000000");
						device.setYpsyrl("0100000");
						list.add(device);
					}
				}
				res.setList(list);
				res.setSjcd(CRC32Util.getFixNumString(String.valueOf(length + 8 + 5), 7));
				res.setPackageLength(length+ 8 + 5 + 1 + 7 + 15 + 1 + 3);
				res.setSbgs(CRC32Util.getFixNumString(String.valueOf(list.size()), 5));
				session.write(res);
			}
		}
		session.close();
	}

	// ������Ϣ��������
	public void messageSent(IoSession session, Object message) throws Exception {

	}

	// ������Ϣ�쳣
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.close();
		DKMsgProtocol.getInstances().setIsconenected(false);
	}

	/**
	 * ����ע������
	 * 
	 * @param session
	 * @throws Exception
	 */
	public void sendMsg(IoSession session) throws Exception {
		Model101Req model = new Model101Req();
		model.setBt((byte) 0X02);
		model.setXyh("101");
		model.setLjlx("1");
		model.setRzbh(PropsUtil.get(PropsKeys.DK_RZBH));
		String vertion = " 2.2";
		StringBuffer vertionBuffer = new StringBuffer();
		if (vertion.getBytes().length < 4) {
			for (int i = 0; i < 4 - vertion.getBytes().length; i++) {
				vertionBuffer.append(" ");
			}
		}

		vertionBuffer.append(vertion);
		model.setXybb(vertionBuffer.toString());
		String hostIp = PropsUtil.get(PropsKeys.DK_HOST);
		StringBuffer hostIpBuffer = new StringBuffer();
		if (hostIp.getBytes().length < 15) {
			for (int i = 0; i < 15 - hostIp.getBytes().length; i++) {
				hostIpBuffer.append(" ");
			}
		}
		hostIpBuffer.append(hostIp);
		model.setIpdz(hostIpBuffer.toString());

		model.setPackageLength(48);
		session.write(model);
	}

	public void sessionCreated(IoSession session) throws Exception {
		sendMsg(session);
		DKMsgProtocol.getInstances().setIsconenected(true);
		// session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 50);
		 DKServiceUtil.getService().dealSessionCreat(5);
		// session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10*6);

	}

}
