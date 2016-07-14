package com.liveyc.mina.client.message;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.client.CRC32Util;
import com.liveyc.mina.client.model.DeviceObject;
import com.liveyc.mina.client.model.Model101Req;
import com.liveyc.mina.client.model.Model101Res;
import com.liveyc.mina.client.model.Model103Req;
import com.liveyc.mina.client.model.Model105Res;
import com.liveyc.mina.client.model.Model107Req;
import com.liveyc.mina.client.model.Model107Res;
import com.liveyc.mina.client.model.Model111Req;
import com.liveyc.mina.client.model.TpObject;
import com.liveyc.mina.model.ReqConnetModel;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.mina.model.ResConnetMode;
import com.liveyc.mina.model.base.BaseModelHeader;
import com.liveyc.mina.util.CRCUtil;
import com.liveyc.util.Format;

public class MessageRequestDKEncode implements ProtocolEncoder {
	/**
	 * 打包发送
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		com.liveyc.mina.client.model.base.BaseModel model = (com.liveyc.mina.client.model.base.BaseModel) message;
		IoBuffer buffer = IoBuffer.allocate(model.getPackageLength(), true);
		String messagetType = model.getXyh();
		if (messagetType.equals("101")) {
			Model101Req request = (Model101Req) message;
			buffer.put((byte) 0x2);
			buffer.put("101".getBytes());
			buffer.put("1".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(request.getIpdz().getBytes());
			buffer.put(request.getXybb().getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), 39).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("601")) {
			Model101Res request = (Model101Res) message;
			buffer.put((byte) 0x2);
			buffer.put("601".getBytes());
			buffer.put("123456789012345".getBytes());
			buffer.put("1".getBytes());
			buffer.put("    172.16.66.8".getBytes());
			buffer.put("06666".getBytes());
			buffer.put("1234567890".getBytes());
			buffer.put("123456789012".getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), request.getPackageLength() - 9).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("103")) {
			Model103Req request = (Model103Req) message;
			buffer.put((byte) 0x2);
			buffer.put("103".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(request.getSjcd().getBytes());
			buffer.put(request.getCjbh().getBytes());
			buffer.put(request.getTpid().getBytes());
			buffer.put(request.getHphm().getBytes("gb2312"));
			buffer.put(request.getHpys().getBytes());
			buffer.put(request.getJgsj().getBytes());
			buffer.put(request.getSpd().getBytes());// 车速
			buffer.put(request.getClsd().getBytes());// 车速
			buffer.put(request.getCsys().getBytes());// 车身颜色
			buffer.put(request.getCcm().getBytes());
			buffer.put(request.getCcyy().getBytes());
			buffer.put(request.getSbsj().getBytes());// 识别时间
			buffer.put(request.getHpjg().getBytes());
			buffer.put(request.getHpsl().getBytes());
			buffer.put(request.getWflx().getBytes());
			buffer.put(request.getCpzbx().getBytes());
			buffer.put(request.getCpzby().getBytes());
			buffer.put(request.getCblx().getBytes());
			buffer.put(request.getCx().getBytes());
			buffer.put(request.getXs().getBytes());
			buffer.put(request.getHdsj().getBytes());
			buffer.put(request.getTpsl().getBytes());
			if (request.getTpList().size() > 0) {
				for (TpObject tp : request.getTpList()) {
					buffer.put(tp.getType().getBytes());
					buffer.put(tp.getSize().getBytes());
					buffer.put(tp.getUrl().getBytes());
				}
			}
			buffer.put("1".getBytes());
			buffer.put("0".getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), request.getPackageLength() - 9).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("104")) {
			Model103Req request = (Model103Req) message;
			buffer.put((byte) 0x2);
			buffer.put("104".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(request.getSjcd().getBytes());
			buffer.put(request.getCjbh().getBytes());
			buffer.put(request.getTpid().getBytes());
			buffer.put(request.getHphm().getBytes("gb2312"));
			buffer.put(request.getHpys().getBytes());
			buffer.put(request.getJgsj().getBytes());
			buffer.put(request.getSpd().getBytes());// 车速
			buffer.put(request.getClsd().getBytes());// 车速
			buffer.put(request.getCsys().getBytes());// 车身颜色
			buffer.put(request.getCcm().getBytes());
			buffer.put(request.getCcyy().getBytes());
			buffer.put(request.getSbsj().getBytes());// 识别时间
			buffer.put(request.getHpjg().getBytes());
			buffer.put(request.getHpsl().getBytes());
			buffer.put(request.getWflx().getBytes());
			buffer.put(request.getCpzbx().getBytes());
			buffer.put(request.getCpzby().getBytes());
			buffer.put(request.getCblx().getBytes());
			buffer.put(request.getCx().getBytes());
			buffer.put(request.getXs().getBytes());
			buffer.put(request.getHdsj().getBytes());
			buffer.put(request.getTpsl().getBytes());
			if (request.getTpList().size() > 0) {
				for (TpObject tp : request.getTpList()) {
					buffer.put(tp.getType().getBytes());
					buffer.put(tp.getSize().getBytes());
					buffer.put(tp.getUrl().getBytes());
				}
			}
			buffer.put("1".getBytes());
			buffer.put("0".getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), request.getPackageLength() - 9).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("111")) {
			Model111Req request = (Model111Req) message;
			buffer.put((byte) 0x2);
			buffer.put("101".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), 19).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("107")) {
			Model107Req request = (Model107Req) message;
			buffer.put((byte) 0x2);
			buffer.put("107".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), 19).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("607")) {
			Model107Res request = (Model107Res) message;
			buffer.put((byte) 0x2);
			buffer.put("607".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(request.getSjcd().getBytes());
			buffer.put(request.getSbgs().getBytes());
			if (request.getList().size() > 0) {
				for (DeviceObject tp : request.getList()) {
					buffer.put(tp.getBh().getBytes());
					buffer.put(tp.getIp().getBytes());
					buffer.put(tp.getCdz().getBytes());
					buffer.put(tp.getCdzt().getBytes());
					buffer.put(tp.getSxjzs().getBytes());
					buffer.put(tp.getSxjzt().getBytes());
					buffer.put(tp.getJxwd().getBytes());
					buffer.put(tp.getJxm().getBytes());
					buffer.put(tp.getYprl().getBytes());
					buffer.put(tp.getYpsyrl().getBytes());
					buffer.put(tp.getCpuzyl().getBytes());
					buffer.put(tp.getXhdzt().getBytes());
				}
			}
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), request.getPackageLength() - 9).getBytes());
			buffer.put((byte) 0x3);

		}
		if (messagetType.equals("605")) {
			Model105Res request = (Model105Res) message;
			buffer.put((byte) 0x2);
			buffer.put("605".getBytes());
			buffer.put(request.getRzbh().getBytes());
			buffer.put(request.getRq().getBytes());
			buffer.put(request.getSj().getBytes());
			buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), request.getPackageLength() - 9).getBytes());
			buffer.put((byte) 0x3);

		}
		buffer.flip();
		out.write(buffer);
	}

	public void dispose(IoSession session) throws Exception {
		// nothing to dispose
	}

}
