package com.liveyc.mina.taizhou.server.filter;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.SimpleDateFormat;

import oracle.net.aso.a;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.liveyc.mina.taizhou.server.model.ServerBaseModel;
import com.liveyc.mina.taizhou.server.model.ServerGpsVehicleRecordModel;

public class ServerDecoderFilter extends CumulativeProtocolDecoder{
	private static Logger iLog = Logger.getLogger(ServerDecoderFilter.class);
	CharsetDecoder charsetDecoder = Charset.forName("gbk").newDecoder();
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer buffer,
			ProtocolDecoderOutput out) throws Exception {
		IoBuffer in = null;
		byte b;
		while (buffer.hasRemaining()) {
			byte a = buffer.get();
			if (a == (byte) 0x5b) {
				buffer.mark();
				int length = buffer.getInt();
				in = IoBuffer.allocate(length - 2, true);
				buffer.reset();
			} else {
				if (in != null) {
					if (a == (byte) 0x5d) {
						in.flip();
						Object object = getObjectFromIoBuffer(in);
						out.write(object);
						in = null;
						return true;
					} else if (a == (byte) 0x5a) {
						b = buffer.get();
						if (b == (byte) 0x01) {
							in.put((byte) 0x5b);
						} else if (b == (byte) 0x02) {
							in.put((byte) 0x5a);
						} else {
							in.put(a);
							in.put(b);
						}
					} else if (a == (byte) 0x5e) {
						b = buffer.get();
						if (b == (byte) 0x01) {
							in.put((byte) 0x5d);
						} else if (b == (byte) 0x02) {
							in.put((byte) 0x5e);
						} else {
							in.put(a);
							in.put(b);
						}
					} else {
						in.put(a);
					}
				} else {
					buffer.get();
				}
			}
		}
		return false;
	}
	
	public Object getObjectFromIoBuffer(IoBuffer buffer) throws Exception {
		buffer.mark();
		buffer.getUnsignedInt();
		buffer.getUnsignedInt();
		int id = buffer.getUnsignedShort();
		buffer.reset();
		if (id == 4097) {
			ServerBaseModel baseModel = new ServerBaseModel();
			baseModel.setId(id);
			buffer.getUnsignedInt();
			buffer.getUnsignedInt();
			buffer.getUnsignedShort();
			baseModel.setGnssCenterId(buffer.getUnsignedInt());
			byte[] d = new byte[3];
			d[0] = buffer.get();
			d[1] = buffer.get();
			d[2] = buffer.get();
			baseModel.setVersionFlag(d);
			int encryptFlag = buffer.get() & 0xff;
			if (encryptFlag == 0) {
				buffer.getUnsignedInt();
				long userId = buffer.getUnsignedInt();
				String password = buffer.getString(8, charsetDecoder);
				String downLinkIp = buffer.getString(32, charsetDecoder);
				int downLinkPort = buffer.getUnsignedShort();
				buffer.getUnsignedShort();	
			} else {
				byte[] byte52 = new byte[52];
				buffer.get(byte52);
			}
			return baseModel;
		} else if (id == 4608) {
			ServerGpsVehicleRecordModel vehicleRecordModel = new ServerGpsVehicleRecordModel();
			buffer.getUnsignedInt();
			buffer.getUnsignedInt();
			buffer.getUnsignedShort();
			buffer.getUnsignedInt();
			buffer.get();
			buffer.get();
			buffer.get();
			int encryptFlag = buffer.get() & 0xff;
			if (encryptFlag == 0) {
				buffer.getUnsignedInt();
				vehicleRecordModel.setVehicleNo(buffer.getString(21, charsetDecoder));
				int colorNum = buffer.get() & 0xff;
				switch (colorNum) {
					case 1:
						vehicleRecordModel.setVehicleColor("蓝");
					break;
					case 2:	
						vehicleRecordModel.setVehicleColor("黄");
					break;
					case 3:	
						vehicleRecordModel.setVehicleColor("黑");
					break;
					case 4:	
						vehicleRecordModel.setVehicleColor("白");
					break;
					case 9:	
						vehicleRecordModel.setVehicleColor("其它");
					break;
					default: 
						vehicleRecordModel.setVehicleColor("其它");
					break;  
				}
				vehicleRecordModel.setId(buffer.getShort());
				buffer.getUnsignedInt();
				buffer.get();
				int dayNum = buffer.get() & 0xff;
				String day = dayNum < 10? "0" + String.valueOf(dayNum): String.valueOf(dayNum);
				int monthNum = buffer.get() & 0xff;
				String month = monthNum < 10? "0" + String.valueOf(monthNum): String.valueOf(monthNum);
				String year = Integer.toBinaryString(buffer.get() &0xff) + Integer.toBinaryString(buffer.get() &0xff);
				year = Integer.valueOf(year, 2).toString();
				int hourNum = buffer.get() & 0xff;
				String hour = hourNum < 10? "0" + String.valueOf(hourNum): String.valueOf(hourNum);
				int minuteNum = buffer.get() & 0xff;
				String minute = minuteNum < 10? "0" + String.valueOf(minuteNum): String.valueOf(minuteNum);
				int secondNum = buffer.get() & 0xff;
				String second = secondNum < 10? "0" + String.valueOf(secondNum): String.valueOf(secondNum);
				String alarmTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
				SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				vehicleRecordModel.setAlarmTime(sdft.parse(alarmTime));
				vehicleRecordModel.setLon(buffer.getUnsignedInt());
				vehicleRecordModel.setLat(buffer.getUnsignedInt());
				vehicleRecordModel.setVec1(buffer.getUnsignedShort());
				vehicleRecordModel.setVec2(buffer.getUnsignedShort());
				vehicleRecordModel.setVec3(buffer.getUnsignedInt());
				vehicleRecordModel.setDirection(buffer.getUnsignedShort());
				vehicleRecordModel.setAltitude(buffer.getUnsignedShort());
				vehicleRecordModel.setState(buffer.getUnsignedInt());
				vehicleRecordModel.setAlarm(buffer.getUnsignedInt());
				buffer.getUnsignedShort();	
			} else {
				byte[] byte70 = new byte[70];
				buffer.get(byte70);
			}
			return vehicleRecordModel;
		} else {
			return null;
		}
	}
}
