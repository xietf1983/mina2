package com.liveyc.mina.sichun.message;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
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
import com.liveyc.mina.client.model.Model101Res;
import com.liveyc.mina.client.model.Model102Res;
import com.liveyc.mina.client.model.Model103Res;
import com.liveyc.mina.client.model.Model104Res;
import com.liveyc.mina.client.model.Model105Req;
import com.liveyc.mina.client.model.Model105Res;
import com.liveyc.mina.client.model.Model106Req;
import com.liveyc.mina.client.model.Model106Res;
import com.liveyc.mina.client.model.Model107Req;
import com.liveyc.mina.client.model.Model109Req;
import com.liveyc.mina.client.model.Model110Req;
import com.liveyc.mina.client.model.Model111Res;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ResConnetMode;
import com.liveyc.mina.sichun.model.DeviceResStatusModel;
import com.liveyc.mina.sichun.model.RejResDataModel;
import com.liveyc.util.DateUtil;

public class MessageResponseDKDecoder extends CumulativeProtocolDecoder {
	private static Logger iLog = Logger.getLogger(MessageResponseDKDecoder.class);

	protected boolean doDecode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder decoder = Charset.forName("gb2312").newDecoder();
		if (buffer.remaining() >= 10) {
			buffer.mark();
			buffer.getInt();
			int length = buffer.getInt();
			int messageType = buffer.getUnsignedShort();
			buffer.reset();
			if (messageType == 1) {
				if (buffer.remaining() >= length + 8) {
					RejResDataModel m = new RejResDataModel();
					m.setTag(buffer.getUnsignedInt());
					m.setLength(buffer.getUnsignedInt());
					m.setMessageType(buffer.getUnsignedShort());
					m.setXxbbh(buffer.getString(6, decoder));
					m.setJrbh(buffer.getString(32, decoder));
					m.setZcbs(buffer.getUnsignedShort());
					m.setIp(buffer.getString(48, decoder));
					m.setPort(buffer.getInt());
					m.setSsyy(buffer.getUnsignedShort());
					m.setEndtag(buffer.getUnsignedInt());
					out.write(m);
					return true;
				}
			}else if (messageType == 9) {
				if (buffer.remaining() >= length + 8) {
					DeviceResStatusModel m = new DeviceResStatusModel();
					m.setTag(buffer.getUnsignedInt());
					m.setLength(buffer.getUnsignedInt());
					m.setMessageType(buffer.getUnsignedShort());
					m.setXxbbh(buffer.getString(6, decoder));
					m.setSn(buffer.getInt());;
					m.setSbjg(buffer.getShort());
					m.setEndtag(buffer.getUnsignedInt());
					out.write(m);
					return true;
				}
			} else {
				if (buffer.remaining() >= length + 8) {
					byte[] bytedata = new byte[length + 8];
					buffer.get(bytedata);
					return true;
				}
			}

		} else {
			return false;
		}
		return false;
	}
}
