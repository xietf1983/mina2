package com.liveyc.mina.client.message;

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
import com.liveyc.mina.client.model.Model101Req;
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
import com.liveyc.util.DateUtil;

public class MessageResponseDKDecoder extends CumulativeProtocolDecoder {
	private static Logger iLog = Logger.getLogger(MessageResponseDKDecoder.class);

	protected boolean doDecode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder decoder = Charset.forName("gb2312").newDecoder();
		if (buffer.remaining() >= 8) {
			buffer.mark();
			buffer.get();
			String messageType = buffer.getString(3, decoder);
			buffer.reset();
			if (messageType.equals("601")) {
				if (buffer.remaining() >= 71) {
					Model101Res m = new Model101Res();
					m.setBt(buffer.get());
					m.setXyh(messageType);
					buffer.get();
					buffer.get();
					buffer.get();
					m.setRzbh(buffer.getString(15, decoder));
					m.setApply(buffer.getString(1, decoder));
					m.setIpdz(buffer.getString(15, decoder));
					m.setDk(buffer.getString(5, decoder));
					m.setRi(buffer.getString(10, decoder));
					m.setSj(buffer.getString(12, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setLjbw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("101")) {
				if (buffer.remaining() >= 48) {
					Model101Req m = new Model101Req();
					byte [] byte48 = new byte[48];
					buffer.put(byte48);
					m.setXyh(messageType);
					m.setIpdz("172.16.88.8");
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("602")) {
				if (buffer.remaining() >= 29) {
					Model102Res m = new Model102Res();
					m.setBt(buffer.get());
					m.setXyh(messageType);
					buffer.get();
					buffer.get();
					buffer.get();
					m.setRzbh(buffer.getString(15, decoder));
					m.setApply(buffer.getString(1, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setLjbw(buffer.get());
					out.write(m);
					return true;
				}
			}

			if (messageType.equals("603")) {
				if (buffer.remaining() >= 78) {
					Model103Res m = new Model103Res();
					m.setBt(buffer.get());
					m.setXyh(messageType);
					buffer.get();
					buffer.get();
					buffer.get();
					m.setRzbh(buffer.getString(15, decoder));
					m.setCjbh(buffer.getString(15, decoder));
					m.setTpid(buffer.getString(35, decoder));
					m.setSjcd(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("604")) {
				if (buffer.remaining() >= 78) {
					Model104Res m = new Model104Res();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();

					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setCjbh(buffer.getString(15, decoder));
					m.setTpid(buffer.getString(35, decoder));
					m.setSjcd(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("105")) {
				if (buffer.remaining() >= 50) {
					Model105Req m = new Model105Req();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();
					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setRi(buffer.getString(10, decoder));
					m.setSj(buffer.getString(12, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("106")) {
				if (buffer.remaining() >= 50) {
					Model106Req m = new Model106Req();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();
					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setRi(buffer.getString(10, decoder));
					m.setSj(buffer.getString(12, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("107")) {
				if (buffer.remaining() >= 28) {
					Model107Req m = new Model107Req();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();
					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("109")) {
				if (buffer.remaining() >= 92) {
					Model109Req m = new Model109Req();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();
					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setCjbh(buffer.getString(15, decoder));
					m.setRq1(buffer.getString(10, decoder));
					m.setSj1(buffer.getString(12, decoder));
					m.setRq2(buffer.getString(10, decoder));
					m.setSj2(buffer.getString(12, decoder));
					m.setWflx(buffer.getString(5, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("110")) {
				if (buffer.remaining() >= 88) {
					Model110Req m = new Model110Req();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();
					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setCjbh(buffer.getString(15, decoder));
					m.setRq1(buffer.getString(10, decoder));
					m.setSj1(buffer.getString(12, decoder));
					m.setRq2(buffer.getString(10, decoder));
					m.setSj2(buffer.getString(12, decoder));
					m.setCllx(buffer.getString(1, decoder));
					m.setSjjy(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
			if (messageType.equals("611")) {
				if (buffer.remaining() >= 28) {
					Model111Res m = new Model111Res();
					m.setBt(buffer.get());
					buffer.get();
					buffer.get();
					buffer.get();
					m.setXyh(messageType);
					m.setRzbh(buffer.getString(15, decoder));
					m.setSjcd(buffer.getString(8, decoder));
					m.setBw(buffer.get());
					out.write(m);
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}
}
