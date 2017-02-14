package com.liveyc.mina.taizhou.filter;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.liveyc.mina.taizhou.model.LoginRespModel;

import IceInternal.Buffer;

public class DecoderFilter extends CumulativeProtocolDecoder{
	CharsetDecoder decoder = Charset.forName("gbk").newDecoder();
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer buffer,
			ProtocolDecoderOutput out) throws Exception {
		if (buffer.remaining() >= 11) {
			buffer.mark();
			buffer.get();
			int length = buffer.getInt();
			buffer.getUnsignedInt();
			int id = buffer.getUnsignedShort();
			buffer.reset();
			if (id == 4098) {
				if (buffer.remaining() >= length) {
					LoginRespModel respModel = new LoginRespModel();
					respModel.setModelType(3);
					buffer.get();
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
						respModel.setResult(buffer.get());
						respModel.setVerifyCode(buffer.getUnsignedInt());
						buffer.getUnsignedShort();
						buffer.get();	
					}
					out.write(respModel);
					return true;
				}
			} else {
				if (buffer.remaining() >= length) {
					byte[] b = new byte[length];
					buffer.get(b);
					return true;
				}
			}
			
		}
		return false;
	}

}
