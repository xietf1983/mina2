package com.liveyc.mina.taizhou.server.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ServerFilterFactory implements ProtocolCodecFactory {
	private final ServerDecoderFilter serverDecoder;
	private final ServerEncoderFilter serverEncoder;
	
	public ServerFilterFactory() {
		this.serverDecoder = new ServerDecoderFilter();
		this.serverEncoder = new ServerEncoderFilter();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return serverDecoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return serverEncoder;
	}
}
