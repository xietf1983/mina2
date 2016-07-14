package com.liveyc.mina.message.code;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.liveyc.configuration.PropsValues;
import com.liveyc.data.service.GaVehViolationServiceUtil;
import com.liveyc.mina.ClientMsgProtocol;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ResConnetMode;
import com.liveyc.util.DateUtil;

public class MessageResponseDecoder extends CumulativeProtocolDecoder {
	private static Logger iLog = Logger.getLogger(MessageResponseDecoder.class);
	protected boolean doDecode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
		if (buffer.remaining() >= 8) {
			buffer.mark();
			int length = buffer.getUnsignedShort();
			long sn = buffer.getUnsignedInt();
			int messageType = buffer.getUnsignedShort();
			buffer.reset();
			if (buffer.remaining() >= (length + 2)) {
				//
				switch (messageType) {
				// 链路登录应答消息
				case 0x8001: {
					ResConnetMode response = new ResConnetMode();
					response.setDataLength(buffer.getUnsignedShort());
					response.setMessageSn(buffer.getUnsignedInt());
					response.setMessageType(buffer.getUnsignedShort());
					response.setResult(buffer.get());
					byte[] neverread = new byte[14];
					buffer.get(neverread);
					buffer.get();
					buffer.get();
					System.out.print(response.getMessageSn() + "result" + response.getResult());
					if (response.getResult() == (byte) 0) {
						ClientMsgProtocol.getInstances().setIsconenected(true);
					}

					break;
				}

				case 0x2001: {
					ReqConnetModel request = new ReqConnetModel();
					request.setDataLength(buffer.getUnsignedShort());
					request.setMessageSn(buffer.getUnsignedInt());
					request.setMessageType(buffer.getUnsignedShort());
					byte[] neverread = new byte[20];
					buffer.get(neverread);
					request.setUserId(new String(neverread));
					byte[] connetTimeByte = new byte[14];
					buffer.get(connetTimeByte);
					request.setConnetTime(new String(connetTimeByte));
					byte[] mac = new byte[16];
					buffer.get(mac);

					byte[] mac3 = new byte[3];
					buffer.get(mac3);
					buffer.get();
					buffer.get();
					// request.setMac(mac);

					break;
				}
				/**
				 * 模拟服务端
				 */

				case 0x8101: {
					ResConnetMode response = new ResConnetMode();
					response.setDataLength(buffer.getUnsignedShort());
					response.setMessageSn(buffer.getUnsignedInt());
					response.setMessageType(buffer.getUnsignedShort());
					response.setResult(buffer.get());
					buffer.get();
					buffer.get();
					//System.out.print(response.getMessageSn() + "result" + response.getResult());
					iLog.error("MessageSn"+response.getMessageSn()+"result"+response.getResult());
					if (response.getResult() == (byte) 0 || response.getResult() == (byte) 1) {
						try {
							GaVehViolationServiceUtil.getService().deletegavehviolationupload(response.getMessageSn());
						} catch (Exception ex) {

						}
					}
					break;
				}
				default: {
					// 忽略的包
					//System.out.println(buffer.getHexDump());
					byte[] neverread = new byte[length + 2];
					buffer.get(neverread);
					break;
				}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
