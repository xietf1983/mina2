package com.liveyc.mina.taizhou.server.filter;

import com.liveyc.mina.taizhou.server.model.ServerBaseModel;
import com.liveyc.mina.taizhou.server.model.ServerLoginRespModel;
import com.liveyc.mina.util.CRCUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ServerEncoderFilter implements ProtocolEncoder {

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(IoSession session, Object object, ProtocolEncoderOutput out)
			throws Exception {
		ServerBaseModel baseModel = (ServerBaseModel) object;
		IoBuffer ioBuffer = IoBuffer.allocate((int) baseModel.getLength(), true);
		int id = baseModel.getId();
		if (id == 4098) {
			ServerLoginRespModel loginRespModel = (ServerLoginRespModel) object;
			ioBuffer.put(loginRespModel.getHeadFlag());
			ioBuffer.putUnsignedInt(loginRespModel.getLength());
			ioBuffer.putUnsignedInt(loginRespModel.getSn());
			ioBuffer.putUnsignedShort(loginRespModel.getId());
			ioBuffer.putUnsignedInt(loginRespModel.getGnssCenterId());
			ioBuffer.put(loginRespModel.getVersionFlag());
			ioBuffer.put(loginRespModel.getEncryptFlag());
			ioBuffer.putUnsignedInt(loginRespModel.getEncryptKey());
			ioBuffer.put(loginRespModel.getResult());
			ioBuffer.putUnsignedInt(loginRespModel.getVerifyCode());
			//byte[] b = ioBuffer.array();
			ioBuffer.putUnsignedShort(0x12);
			ioBuffer.put(loginRespModel.getEndFlag());
		}
		ioBuffer.flip();
		out.write(ioBuffer);
	}

}
