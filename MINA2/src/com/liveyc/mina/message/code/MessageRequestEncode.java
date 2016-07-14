package com.liveyc.mina.message.code;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.mina.model.ResConnetMode;
import com.liveyc.mina.model.base.BaseModelHeader;
import com.liveyc.mina.util.CRCUtil;
import com.liveyc.util.Format;

public class MessageRequestEncode implements ProtocolEncoder {
	/**
	 * 打包发送
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		BaseModelHeader model = (BaseModelHeader) message;
		IoBuffer buffer = IoBuffer.allocate(model.getDataLength() + 2, true);
		switch (model.getMessageType()) {
		case 0x2001: {
			ReqConnetModel request = (ReqConnetModel) message;
			try {
				buffer.putUnsignedShort(request.getDataLength());
				buffer.putUnsignedInt(request.getMessageSn());
				buffer.putUnsignedShort(request.getMessageType());
				byte[] userNameByte = new byte[20];
				byte[] userNameBytetemp = request.getUserId().getBytes();
				for (int j = 0; j < userNameBytetemp.length; j++) {
					if (j < 20) {
						userNameByte[j] = userNameBytetemp[j];
					}
				}
				buffer.put(userNameByte);
				byte[] connetTimeByte =  new byte[14]; 
				byte[] connetTimeBytetemp = request.getConnetTime().getBytes();
				for (int j = 0; j < connetTimeBytetemp.length; j++) {
					if (j < 14) {
						connetTimeByte[j] = connetTimeBytetemp[j];
					}
				}
				buffer.put(connetTimeByte);
				System.out.print("PWD"+request.getMac());
				System.out.print("USERID"+request.getUserId());
				byte[] macByte = Format.MD5(request.getMac()); 
				buffer.put(macByte);
				buffer.put(request.getVersion());
				//System.out.println(buffer.getHexDump(0));
				buffer.putUnsignedShort(CRCUtil.getCRC32(buffer.asReadOnlyBuffer(), model.getDataLength()));

			} catch (Exception ex) {
				System.out.print(ex);
			}
			break;

		}
		case 0x8001: {
			ResConnetMode request = (ResConnetMode) message;
			try {
				buffer.putUnsignedShort(request.getDataLength());
				buffer.putUnsignedInt(request.getMessageSn());
				buffer.putUnsignedShort(request.getMessageType());
				buffer.put(request.getResult());
				byte[] connetTimeByte =  new byte[14]; 
				byte[] connetTimeBytetemp = request.getSysdateTime().getBytes();
				for (int j = 0; j < connetTimeBytetemp.length; j++) {
					if (j < 14) {
						connetTimeByte[j] = connetTimeBytetemp[j];
					}
				}
				buffer.put(connetTimeByte);
				buffer.putUnsignedShort(CRCUtil.getCRC32(buffer.asReadOnlyBuffer(), model.getDataLength()));

			} catch (Exception ex) {
				System.out.print(ex);
			}
			break;

		}
		case 0x2002: {
			ReqLinkTestModel request = (ReqLinkTestModel) message;
			try {
				buffer.putUnsignedShort(request.getDataLength());
				buffer.putUnsignedInt(request.getMessageSn());
				buffer.putUnsignedShort(request.getMessageType());
				buffer.putUnsignedShort(CRCUtil.getCRC32(buffer.asReadOnlyBuffer(), model.getDataLength()));

			} catch (Exception ex) {
				System.out.print(ex);
			}
			break;

		}
		case 0x2101: {
			ReqRealLocation request = (ReqRealLocation) message;
			try {
				buffer.putUnsignedShort(request.getDataLength());
				buffer.putUnsignedInt(request.getMessageSn());
				buffer.putUnsignedShort(request.getMessageType());
				buffer.put((byte)1);
				buffer.putUnsignedShort(request.getVlength());
			
				buffer.put(request.getDeviceType());
				//
				byte[] deviceByte = new byte[24];
				byte[] deviceBytetetemp = request.getDeviceId().getBytes();
				for (int j = 0; j < deviceBytetetemp.length; j++) {
					if (j < 24) {
						deviceByte[j] = deviceBytetetemp[j];
					}
				}
				buffer.put(deviceByte);
				byte[] id = new byte[20];
				byte[] idBytetetemp = request.getId().getBytes();
				for (int j = 0; j < idBytetetemp.length; j++) {
					if (j < 20) {
						id[j] = idBytetetemp[j];
					}
				}
				buffer.put(id);
				byte[] plateNo = new byte[15];
				byte[] plateNoTypeTemp = request.getPlateNo().getBytes("GBK");
				for (int j = 0; j < plateNoTypeTemp.length; j++) {
					if (j < 15) {
						plateNo[j] = plateNoTypeTemp[j];
					}
				}
				buffer.put(plateNo);
				byte[] plateType = new byte[2];
				byte[] plateTypeTemp = request.getPlatype().getBytes();
				for (int j = 0; j < plateTypeTemp.length; j++) {
					if (j < 2) {
						plateType[j] = plateTypeTemp[j];
					}
				}
				buffer.put(plateType);
				byte[] datetimeByte = new byte[14];
				byte[] datetimeByteTemp = request.getDatetime().getBytes();
				for (int j = 0; j < datetimeByteTemp.length; j++) {
					if (j < 14) {
						datetimeByte[j] = datetimeByteTemp[j];
					}
				}
				buffer.put(datetimeByte);// 14字节

				byte[] action = new byte[5];
				byte[] actionByteTemp = request.getAction().getBytes();
				for (int j = 0; j < actionByteTemp.length; j++) {
					if (j < 5) {
						action[j] = actionByteTemp[j];
					}
				}
				buffer.put(action);// 5字节
				buffer.putUnsignedInt(request.getValue());
				buffer.putUnsignedInt(request.getStandard());

				byte[] sendtimeByte = new byte[14];
				byte[] sendtimeByteTemp = request.getSendTime().getBytes();
				for (int j = 0; j < sendtimeByteTemp.length; j++) {
					if (j < 14) {
						sendtimeByte[j] = sendtimeByteTemp[j];
					}
				}
				buffer.put(sendtimeByte);// 14字节

				buffer.put(request.getPicNumber());

				buffer.put(request.getPicUrl().getBytes());

				buffer.putUnsignedShort(CRCUtil.getCRC32(buffer.asReadOnlyBuffer(), model.getDataLength()));

			} catch (Exception ex) {
				System.out.print(ex);
			}
			break;

		}
		case 0x8101: {
			ResConnetMode request = (ResConnetMode) message;
			try {
				buffer.putUnsignedShort(request.getDataLength());
				buffer.putUnsignedInt(request.getMessageSn());
				buffer.putUnsignedShort(request.getMessageType());
				buffer.put(request.getResult());
				buffer.putUnsignedShort(CRCUtil.getCRC32(buffer.asReadOnlyBuffer(), model.getDataLength()));

			} catch (Exception ex) {
				System.out.print(ex);
			}
			break;

		}
		}
		buffer.flip();
		out.write(buffer);
	}

	public void dispose(IoSession session) throws Exception {
		// nothing to dispose
	}

}
