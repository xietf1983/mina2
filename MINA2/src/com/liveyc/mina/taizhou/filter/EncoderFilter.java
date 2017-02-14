package com.liveyc.mina.taizhou.filter;

import java.io.ByteArrayOutputStream;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.liveyc.mina.taizhou.model.BaseModel;
import com.liveyc.mina.taizhou.model.LoginReqModel;
import com.liveyc.mina.taizhou.model.RealLocationReqDataModel;
import com.liveyc.mina.util.CRCUtil;

public class EncoderFilter extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object object, ProtocolEncoderOutput out)
			throws Exception {
		BaseModel baseModel = (BaseModel) object;
//		IoBuffer ioBuffer = IoBuffer.allocate((int) baseModel.getLength(), true);   //创建IoBuffer实例，第一个参数指定初始化容量，第二个参数指定使用直接缓冲区还是JAVA 内存堆的缓存区，默认为false
		int modelType = baseModel.getModelType();
//		if (modelType == 1) {
//			LoginReqModel reqModel = (LoginReqModel) object;
//			ioBuffer.put(reqModel.getHeadFlag());   // Unsigned 无符号
//			ioBuffer.putUnsignedInt(reqModel.getLength());
//			ioBuffer.putUnsignedInt(reqModel.getSn());
//			ioBuffer.putUnsignedShort(reqModel.getId());
//			ioBuffer.putUnsignedInt(reqModel.getGnssCenterId());
//			ioBuffer.put(reqModel.getVersionFlag());
//			ioBuffer.put(reqModel.getEncryptFlag());
//			ioBuffer.putUnsignedInt(reqModel.getEncryptKey());
//			ioBuffer.putUnsignedInt(reqModel.getUserId());
//			byte[] byte8 = new byte[8];
//			byte[] passWordBytes = reqModel.getPassWord().getBytes("gbk");
//			for (int i = 0; i < 8; i++) {
//				if (i < passWordBytes.length) {
//					byte8[i] = passWordBytes[i];
//				} else {
//					byte8[i] = (byte) 0x00;
//				}
//			}
//			ioBuffer.put(byte8);
//			byte[] byte32 = new byte[32];
//			byte[] downLinkIpBytes = reqModel.getDownLinkIp().getBytes("gbk");
//			for (int i = 0; i < 32; i++) {
//				if (i < downLinkIpBytes.length) {
//					byte32[i] = downLinkIpBytes[i];
//				} else {
//					byte32[i] = (byte) 0x00;
//				}
//			}
//			ioBuffer.put(byte32);
//			ioBuffer.putUnsignedShort(reqModel.getDownLinkPort());
////			ByteArrayOutputStream outputStream = (ByteArrayOutputStream) ioBuffer.asOutputStream();
////			byte[] b = outputStream.toByteArray();
//			ioBuffer.putUnsignedShort(0x12);   //CRC校验码
//			ioBuffer.put(reqModel.getEndFlag());
//		} else if (modelType == 2) {
//			RealLocationReqDataModel reqDataModel = (RealLocationReqDataModel) object;
//			ioBuffer.put(reqDataModel.getHeadFlag());   // Unsigned 无符号
//			ioBuffer.putUnsignedInt(reqDataModel.getLength());
//			ioBuffer.putUnsignedInt(reqDataModel.getSn());
//			ioBuffer.putUnsignedShort(reqDataModel.getId());
//			ioBuffer.putUnsignedInt(reqDataModel.getGnssCenterId());
//			ioBuffer.put(reqDataModel.getVersionFlag());
//			ioBuffer.put(reqDataModel.getEncryptFlag());
//			ioBuffer.putUnsignedInt(reqDataModel.getEncryptKey());
//			byte[] byte21 = new byte[21];
//			byte[] vehicleNo = reqDataModel.getVehicleNo().getBytes("gbk");
//			for (int i = 0; i < 21; i++) {
//				if (i < vehicleNo.length) {
//					byte21[i] = vehicleNo[i];
//				} else {
//					byte21[i] = (byte) 0x00;
//				}
//			}
//			ioBuffer.put(byte21);
//			ioBuffer.put(reqDataModel.getVehicleColor());
//			ioBuffer.putUnsignedShort(reqDataModel.getDataType());
//			ioBuffer.putUnsignedInt(reqDataModel.getDataLength());
//			ioBuffer.put(reqDataModel.getEncrypt());
//			ioBuffer.put(reqDataModel.getDate());
//			ioBuffer.put(reqDataModel.getTime());
//			ioBuffer.putUnsignedInt(reqDataModel.getLon());
//			ioBuffer.putUnsignedInt(reqDataModel.getLat());
//			ioBuffer.putUnsignedShort(reqDataModel.getVec1());
//			ioBuffer.putUnsignedShort(reqDataModel.getVec2());
//			ioBuffer.putUnsignedInt(reqDataModel.getVec3());
//			ioBuffer.putUnsignedShort(reqDataModel.getDirection());
//			ioBuffer.putUnsignedShort(reqDataModel.getAltitude());
//			ioBuffer.putUnsignedInt(reqDataModel.getState());
//			ioBuffer.putUnsignedInt(reqDataModel.getAlarm());
////			byte[] b = ioBuffer.array();
//			ioBuffer.putUnsignedShort(0x12);
//			ioBuffer.put(reqDataModel.getEndFlag());
//		}
		IoBuffer ioBuffer = null;
		if (modelType == 1) {
			ioBuffer = IoBuffer.allocate(74, true);
			ioBuffer.put((byte) 0x5b);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x48);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x06);
			
			ioBuffer.put((byte) 0x37);
			ioBuffer.put((byte) 0x10);
			ioBuffer.put((byte) 0x01);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x7B);
			ioBuffer.put((byte) 0x01);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x7B);
			ioBuffer.put((byte) 0x31);
			ioBuffer.put((byte) 0x32);
			ioBuffer.put((byte) 0x33);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x31);
			ioBuffer.put((byte) 0x31);
			ioBuffer.put((byte) 0x35);
			ioBuffer.put((byte) 0x2E);
			ioBuffer.put((byte) 0x32);
			
			ioBuffer.put((byte) 0x39);
			ioBuffer.put((byte) 0x2E);
			ioBuffer.put((byte) 0x31);
			ioBuffer.put((byte) 0x38);
			ioBuffer.put((byte) 0x36);
			ioBuffer.put((byte) 0x2E);
			ioBuffer.put((byte) 0x37);
			ioBuffer.put((byte) 0x33);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x23);
			ioBuffer.put((byte) 0xF1);
			ioBuffer.put((byte) 0xF7);
			ioBuffer.put((byte) 0x97);
			ioBuffer.put((byte) 0x5D);
		} else if (modelType == 2) {
			ioBuffer = IoBuffer.allocate(94, true);
			ioBuffer.put((byte) 0x5b);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x5A);
			ioBuffer.put((byte) 0x02);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x06);
			
			ioBuffer.put((byte) 0x06);
			ioBuffer.put((byte) 0x38);
			ioBuffer.put((byte) 0x12);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x7B);
			
			ioBuffer.put((byte) 0x01);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0xD5);
			ioBuffer.put((byte) 0xE3);
			ioBuffer.put((byte) 0x41);
			ioBuffer.put((byte) 0x35);
			ioBuffer.put((byte) 0x41);
			ioBuffer.put((byte) 0x30);
			ioBuffer.put((byte) 0x37);
			ioBuffer.put((byte) 0x36);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x12);
			ioBuffer.put((byte) 0x02);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x24);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x04);
			ioBuffer.put((byte) 0x01);
			ioBuffer.put((byte) 0x07);
			
			ioBuffer.put((byte) 0xE1);
			ioBuffer.put((byte) 0x0E);
			ioBuffer.put((byte) 0x05);
			ioBuffer.put((byte) 0x0B);
			ioBuffer.put((byte) 0x07);
			ioBuffer.put((byte) 0x2A);
			ioBuffer.put((byte) 0x24);
			ioBuffer.put((byte) 0x30);
			
			ioBuffer.put((byte) 0x01);
			ioBuffer.put((byte) 0xCF);
			ioBuffer.put((byte) 0xA8);
			ioBuffer.put((byte) 0x7C);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x16);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x16);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x01);
			ioBuffer.put((byte) 0x23);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x03);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			ioBuffer.put((byte) 0x00);
			
			ioBuffer.put((byte) 0x5A);
			ioBuffer.put((byte) 0x01);
			ioBuffer.put((byte) 0x79);
			ioBuffer.put((byte) 0x5D);
		}
		if (ioBuffer != null) {
			ioBuffer.flip();
			out.write(ioBuffer);
		}
	}

}
