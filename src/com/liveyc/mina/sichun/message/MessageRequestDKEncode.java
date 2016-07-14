package com.liveyc.mina.sichun.message;

import java.util.Date;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.client.CRC32Util;
import com.liveyc.mina.client.model.DeviceObject;
import com.liveyc.mina.client.model.Model101Req;
import com.liveyc.mina.client.model.Model103Req;
import com.liveyc.mina.client.model.Model105Res;
import com.liveyc.mina.client.model.Model107Res;
import com.liveyc.mina.client.model.Model111Req;
import com.liveyc.mina.client.model.TpObject;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.mina.model.ResConnetMode;
import com.liveyc.mina.model.base.BaseModelHeader;
import com.liveyc.mina.sichun.model.DeviceReqStatusModel;
import com.liveyc.mina.sichun.model.HeatReqDataModel;
import com.liveyc.mina.sichun.model.RejReqDataModel;
import com.liveyc.mina.sichun.model.VehicleReqDataModel;
import com.liveyc.mina.util.CRCUtil;
import com.liveyc.util.DateUtil;
import com.liveyc.util.Format;

public class MessageRequestDKEncode implements ProtocolEncoder {
	/**
	 * 打包发送
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		com.liveyc.mina.sichun.model.BaseModel model = (com.liveyc.mina.sichun.model.BaseModel) message;
		IoBuffer buffer = IoBuffer.allocate((int) model.getLength() + 8, true);   //创建IoBuffer实例，第一个参数指定初始化容量，第二个参数指定使用直接缓冲区还是JAVA 内存堆的缓存区，默认为false
		int messagetType = model.getMessageType();
		if (messagetType == 1) {
			RejReqDataModel request = (RejReqDataModel) message;
			buffer.putUnsignedInt(request.getTag());   //putUnsignedInt（），设置无符号的数据
			buffer.putUnsignedInt(request.getLength());
			buffer.putShort((short) 1);
			byte[] byte6 = new byte[6];
			if (request.getXxbbh() == null) {
				request.setXxbbh("");
			}
			byte[] userNameBytetemp = request.getXxbbh().getBytes();
			for (int j = 0; j < userNameBytetemp.length; j++) {
				if (j < 6) {
					byte6[j] = userNameBytetemp[j];
				}
			}
			buffer.put(byte6);
			byte[] byte32 = new byte[32];
			if (request.getJrbh() == null) {
				request.setJrbh("");
			}
			byte[] byte32temp = request.getJrbh().getBytes();
			for (int j = 0; j < byte32temp.length; j++) {
				if (j < 32) {
					byte32[j] = byte32temp[j];
				}
			}
			buffer.put(byte32);
			buffer.putUnsignedInt(0x88aa99bb);

		}
		if (messagetType == 3) {
			VehicleReqDataModel request = (VehicleReqDataModel) message;
			buffer.putUnsignedInt(request.getTag());
			buffer.putUnsignedInt(request.getLength());
			buffer.putShort((short) 3);
			byte[] byte6 = new byte[6];
			if (request.getXxbbh() == null) {
				request.setXxbbh("");
			}
			byte[] userNameBytetemp = request.getXxbbh().getBytes();
			for (int j = 0; j < userNameBytetemp.length; j++) {
				if (j < 6) {
					byte6[j] = userNameBytetemp[j];
				}
			}
			buffer.put(byte6);// 16
			byte[] byte32 = new byte[32];
			if (request.getJrbh() == null) {
				request.setJrbh("");
			}
			byte[] byte32temp = request.getJrbh().getBytes();
			for (int j = 0; j < byte32temp.length; j++) {
				if (j < 32) {
					byte32[j] = byte32temp[j];
				}
			}
			buffer.put(byte32); // 48
			buffer.putUnsignedInt(request.getSn());
			byte[] byte18kkbh = new byte[18];
			if (request.getKkbh() == null) {
				request.setKkbh("");
			}
			byte[] byte18kkbhtemp = request.getKkbh().getBytes();
			for (int j = 0; j < byte18kkbhtemp.length; j++) {
				if (j < 18) {
					byte18kkbh[j] = byte18kkbhtemp[j];
				}
			}
			buffer.put(byte18kkbh);// 70
			byte[] byte2cdh = new byte[2];
			if (request.getCdbh() == null || request.getCdbh().equals("")) {
				request.setCdbh("1");
			}
			byte[] byte2cdhtemp = request.getCdbh().getBytes();
			for (int j = 0; j < byte2cdhtemp.length; j++) {
				if (j < 2) {
					byte2cdh[j] = byte2cdhtemp[j];
				}
			}
			buffer.put(byte2cdh);
			byte[] byte10fxbh = new byte[10];
			if (request.getFxbh() == null || request.getFxbh().equals("")) {
				request.setFxbh("");
			}
			byte[] byte10fxbhtemp = request.getFxbh().getBytes();
			for (int j = 0; j < byte10fxbhtemp.length; j++) {
				if (j < 10) {
					byte10fxbh[j] = byte10fxbhtemp[j];
				}
			}
			buffer.put(byte10fxbh);// 82
			// 设备编号
			byte[] byte18sbbh = new byte[18];
			if (request.getSbbh() == null || request.getSbbh().equals("")) {
				request.setSbbh("");
			}
			byte[] byte18sbbhtemp = request.getSbbh().getBytes();
			for (int j = 0; j < byte18sbbhtemp.length; j++) {
				if (j < 18) {
					byte18sbbh[j] = byte18sbbhtemp[j];
				}
			}
			buffer.put(byte18sbbh);// 100

			// 车头号牌
			byte[] byte18hphm = new byte[18];
			if (request.getHphm() == null || request.getHphm().equals("")) {
				request.setHphm("");
			}
			byte[] byte18hphmtemp = request.getHphm().getBytes("utf-8");
			for (int j = 0; j < byte18hphmtemp.length; j++) {
				if (j < 18) {
					byte18hphm[j] = byte18hphmtemp[j];
				}
			}
			buffer.put(byte18hphm);// 118

			// 车头号牌颜色
			byte[] byte2platecolor = new byte[2];
			if (request.getHpys() == null || request.getHpys().equals("")) {
				request.setHpys("");
			}
			byte[] byte2platecolortemp = request.getHpys().getBytes();
			for (int j = 0; j < byte2platecolortemp.length; j++) {
				if (j < 2) {
					byte2platecolor[j] = byte2platecolortemp[j];
				}
			}
			buffer.put(byte2platecolor);
			//buffer.putShort((short)02);

			// 车头号牌种类
			byte[] byte2plateType = new byte[2];
			if (request.getHpzl() == null || request.getHpzl().equals("")) {
				request.setHpzl("");
			}
			byte[] byte2plateTypetemp = request.getHpzl().getBytes();
			for (int j = 0; j < byte2plateTypetemp.length; j++) {
				if (j < 2) {
					byte2plateType[j] = byte2plateTypetemp[j];
				}
			}
			buffer.put(byte2plateType);// 122

			// 车尾
			byte[] byte18cwhphm = new byte[18];
			if (request.getCwhphm() == null || request.getCwhphm().equals("")) {
				request.setCwhphm("");
			}
			byte[] byte18cwhphmtemp = request.getCwhphm().getBytes("utf-8");
			for (int j = 0; j < byte18cwhphmtemp.length; j++) {
				if (j < 18) {
					byte18cwhphm[j] = byte18cwhphmtemp[j];
				}
			}
			buffer.put(byte18cwhphm);// 140

			// 车尾号牌颜色
			byte[] byte2cwplatecolor = new byte[2];
			if (request.getCwhpys() == null || request.getCwhpys().equals("")) {
				request.setCwhpys("");
			}
			byte[] byte2cwplatecolortemp = request.getCwhpys().getBytes();
			for (int j = 0; j < byte2cwplatecolortemp.length; j++) {
				if (j < 2) {
					byte2cwplatecolor[j] = byte2cwplatecolortemp[j];
				}
			}
			buffer.put(byte2cwplatecolor);
			//buffer.putShort((short)02);
			// 车头号牌种类
			byte[] byte2cwplateType = new byte[2];
			if (request.getCwhpzy() == null || request.getCwhpzy().equals("")) {
				request.setCwhpzy("");
			}
			byte[] byte2cwplateTypetemp = request.getCwhpzy().getBytes();
			for (int j = 0; j < byte2cwplateTypetemp.length; j++) {
				if (j < 2) {
					byte2cwplateType[j] = byte2cwplateTypetemp[j];
				}
			}
			buffer.put(byte2cwplateType);// 144
			// 号牌一致性
			byte[] byte2hpyz = new byte[2];
			if (request.getHpyz() == null || request.getHpyz().equals("")) {
				request.setHpyz("");
			}
			byte[] byte2hpyztemp = request.getHpyz().getBytes();
			for (int j = 0; j < byte2hpyztemp.length; j++) {
				if (j < 2) {
					byte2hpyz[j] = byte2hpyztemp[j];
				}
			}
			//buffer.putShort((short)1);
			buffer.put(byte2hpyz);
			buffer.putInt((int) request.getCscd());

			byte[] byte10clpp = new byte[10];
			if (request.getClpp() == null || request.getClpp().equals("")) {
				request.setClpp("");
			}
			byte[] byte10clpptemp = request.getClpp().getBytes();
			for (int j = 0; j < byte10clpptemp.length; j++) {
				if (j < 10) {
					byte10clpp[j] = byte10clpptemp[j];
				}
			}
			buffer.put(byte10clpp);// 车辆品牌

			byte[] byte10clwx = new byte[10];
			if (request.getClwx() == null || request.getClwx().equals("")) {
				request.setClwx("");
			}
			byte[] byte10clwxtemp = request.getClwx().getBytes();
			for (int j = 0; j < byte10clwxtemp.length; j++) {
				if (j < 10) {
					byte10clwx[j] = byte10clwxtemp[j];
				}
			}
			buffer.put(byte10clwx);// 车外
			// 车身颜色

			byte[] byte2csys = new byte[2];
			if (request.getCsys() == null || request.getCsys().equals("")) {
				request.setCsys("");
			}
			byte[] byte2csystemp = request.getCsys().getBytes();
			for (int j = 0; j < byte2csystemp.length; j++) {
				if (j < 2) {
					byte2csys[j] = byte2csystemp[j];
				}
			}
			buffer.put(byte2csys);

			// 车辆类型
			byte[] byte4cllx = new byte[4];
			if (request.getCllx() == null || request.getCllx().equals("")) {
				request.setCllx("");
			}
			byte[] byte4cllxtemp = request.getCllx().getBytes();
			for (int j = 0; j < byte4cllxtemp.length; j++) {
				if (j < 4) {
					byte4cllx[j] = byte4cllxtemp[j];
				}
			}
			buffer.put(byte4cllx);
			// 违法类型
			byte[] byte4wflx = new byte[4];
			if (request.getWflx() == null || request.getWflx().equals("")) {
				request.setWflx("");
			}
			byte[] byte4wflxtemp = request.getWflx().getBytes();
			for (int j = 0; j < byte4wflxtemp.length; j++) {
				if (j < 4) {
					byte4wflx[j] = byte4wflxtemp[j];
				}
			}
			buffer.put(byte4wflx);
			buffer.putLong(request.getClsd());
			buffer.putLong(request.getClsd());

			// 过车时间
			byte[]bytegcsj = new byte[24];
			if (request.getJgsj() == null || request.getJgsj().equals("")) {
				request.setJgsj("");
			}
			byte[] bytegcsjtemp = request.getJgsj().getBytes();
			for (int j = 0; j < bytegcsjtemp.length; j++) {
				if (j < 24) {
					bytegcsj[j] = bytegcsjtemp[j];
				}
			}
			buffer.put(bytegcsj);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) 0);
			buffer.putShort((short) request.getTxsl());
			byte[] tplj1 = new byte[300];
			if (request.getTplj1() == null || request.getTplj1().equals("")) {
				request.setTplj1("");
			}
			byte[] tplj1temp = request.getTplj1().getBytes();
			for (int j = 0; j < tplj1temp.length; j++) {
				if (j < 300) {
					tplj1[j] = tplj1temp[j];
				}
			}
			buffer.put(tplj1);
			byte[] tplj2 = new byte[300];
			if (request.getTplj2() == null || request.getTplj2().equals("")) {
				request.setTplj2("");
			}
			byte[] tplj2temp = request.getTplj2().getBytes();
			for (int j = 0; j < tplj2temp.length; j++) {
				if (j < 300) {
					tplj2[j] = tplj2temp[j];
				}
			}
			buffer.put(tplj2);

			byte[] tplj3 = new byte[300];
			if (request.getTplj3() == null || request.getTplj3().equals("")) {
				request.setTplj3("");
			}
			byte[] tplj3temp = request.getTplj3().getBytes();
			for (int j = 0; j < tplj3temp.length; j++) {
				if (j < 300) {
					tplj3[j] = tplj3temp[j];
				}
			}
			buffer.put(tplj3);

			byte[] tplj4 = new byte[300];
			if (request.getTplj4() == null || request.getTplj4().equals("")) {
				request.setTplj4("");
			}
			byte[] tplj4temp = request.getTplj4().getBytes();
			for (int j = 0; j < tplj4temp.length; j++) {
				if (j < 300) {
					tplj4[j] = tplj4temp[j];
				}
			}
			buffer.put(tplj4);

			byte[] tplj5 = new byte[300];
			if (request.getLxlj() == null || request.getLxlj().equals("")) {
				request.setLxlj("");
			}
			byte[] tplj5temp = request.getLxlj().getBytes();
			for (int j = 0; j < tplj5temp.length; j++) {
				if (j < 300) {
					tplj5[j] = tplj5temp[j];
				}
			}
			buffer.put(tplj5);
			buffer.putInt(0x88aa99bb);

		}
		if (messagetType == 5) {
			HeatReqDataModel request = (HeatReqDataModel) message;
			buffer.putUnsignedInt(request.getTag());
			buffer.putUnsignedInt(request.getLength());
			buffer.putShort((short) 5);
			byte[] byte6 = new byte[6];
			if (request.getXxbbh() == null) {
				request.setXxbbh("");
			}
			byte[] userNameBytetemp = request.getXxbbh().getBytes();
			for (int j = 0; j < userNameBytetemp.length; j++) {
				if (j < 6) {
					byte6[j] = userNameBytetemp[j];
				}
			}
			buffer.put(byte6);
			buffer.putInt(0x11aa22bb);
			buffer.putInt(0x88aa99bb);

		}
		if (messagetType == 9) {
			DeviceReqStatusModel request = (DeviceReqStatusModel) message;
			buffer.putUnsignedInt(request.getTag());
			buffer.putUnsignedInt(request.getLength());
			buffer.putShort((short) 9);
			byte[] byte6 = new byte[6];
			if (request.getXxbbh() == null) {
				request.setXxbbh("");
			}
			byte[] userNameBytetemp = request.getXxbbh().getBytes();
			for (int j = 0; j < userNameBytetemp.length; j++) {
				if (j < 6) {
					byte6[j] = userNameBytetemp[j];
				}
			}
			buffer.put(byte6);
			buffer.putInt(10);
			byte[] byte18sbbh = new byte[18];
			if (request.getSbbh() == null || request.getSbbh().equals("")) {
				request.setSbbh("");
			}
			byte[] byte18sbbhtemp = request.getSbbh().getBytes();
			for (int j = 0; j < byte18sbbhtemp.length; j++) {
				if (j < 18) {
					byte18sbbh[j] = byte18sbbhtemp[j];
				}
			}
			buffer.put(byte18sbbh);// 100
			String date =DateUtil.formatDateTime(new Date())+"     ";
			byte[] bytegcsj = new byte[24];
			
			byte[] bytegcsjtemp = date.getBytes();
			for (int j = 0; j < bytegcsjtemp.length; j++) {
				if (j < 24) {
					bytegcsj[j] = bytegcsjtemp[j];
				}
			}
			buffer.put(bytegcsj);
			buffer.putShort((short)01);
			buffer.put(new byte[24]);
			buffer.putShort((short)01);
			buffer.putInt(0x88aa99bb);

		}
		buffer.flip();
		out.write(buffer);
	}

	public void dispose(IoSession session) throws Exception {
		// nothing to dispose
	}

}
