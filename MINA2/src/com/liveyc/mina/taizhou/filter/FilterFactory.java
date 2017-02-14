package com.liveyc.mina.taizhou.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class FilterFactory implements ProtocolCodecFactory{
	private final ProtocolEncoder encoder;
	private final ProtocolDecoder decoder;
	
	public FilterFactory() {
		this.encoder = new EncoderFilter();
		this.decoder = new DecoderFilter();
	}
	
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}
	
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}
}
