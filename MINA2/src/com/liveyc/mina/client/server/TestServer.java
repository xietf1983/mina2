package com.liveyc.mina.client.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.liveyc.mina.client.message.MessageCodecDKFactory;


public class TestServer {
	public static void main(String[] args) throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecDKFactory(true)));
		acceptor.setHandler(new MinaServerHandler());
		acceptor.bind(new InetSocketAddress(5555));
	}
}
