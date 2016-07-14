package com.liveyc.nio.shdk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.client.CRC32Util;
import com.liveyc.mina.client.model.Model101Req;
import com.liveyc.mina.client.model.base.BaseModel;

public class ClientLink {
	private SocketChannel clientChannel;
	private static ClientLink inst = new ClientLink();
	private InetSocketAddress inetSocketAddress;
	private Selector selector;
	private boolean sendconnected = false;
	private List<Byte> listbyte = new ArrayList();

	public static ClientLink getInstance() {
		return inst;
	}

	private ClientLink() {
		inetSocketAddress = new InetSocketAddress(PropsUtil.get(PropsKeys.DK_SERVERIP), Integer.parseInt(PropsUtil.get(PropsKeys.DK_PORT)));
	}

	public SocketChannel getClientChannel() {
		return clientChannel;
	}

	public void setClientChannel(SocketChannel clientChannel) {
		this.clientChannel = clientChannel;
	}

	public ClientLinkResponse getClientLinkRes() {
		ClientLinkResponse res = new ClientLinkResponse();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(PropsUtil.get(PropsKeys.DK_SERVERIP), Integer.parseInt(PropsUtil.get(PropsKeys.DK_PORT)));
		try {
			selector = Selector.open();
			clientChannel = SocketChannel.open();
			clientChannel.configureBlocking(true);
			sendRegister(clientChannel);
			ByteBuffer buf = ByteBuffer.allocate(71); 
			while(true){
                 clientChannel.read(buf);
			}
		} catch (Exception ex) {

		} finally {

		}
		return res;

	}

	public ClientLinkResponse handread(SelectionKey key) throws Exception {
		ByteBuffer bbObj = ByteBuffer.allocate(128);
		ClientLinkResponse c = new ClientLinkResponse();
		SocketChannel channel = (SocketChannel) key.channel();
		return null;
	}

	private void sendRegister(SocketChannel clientChannel) throws Exception {
		Model101Req model = new Model101Req();
		model.setBt((byte) 0X02);
		model.setXyh("101");
		model.setLjlx("1");
		model.setRzbh(PropsUtil.get(PropsKeys.DK_RZBH));
		String vertion = PropsUtil.get(PropsKeys.DK_VSERSION);
		StringBuffer vertionBuffer = new StringBuffer();
		if (vertion.getBytes().length < 4) {
			for (int i = 0; i < 4 - vertion.getBytes().length; i++) {
				vertionBuffer.append(" ");
			}
		}

		vertionBuffer.append(vertion);
		model.setXybb(vertionBuffer.toString());
		String hostIp = PropsUtil.get(PropsKeys.DK_HOST);
		StringBuffer hostIpBuffer = new StringBuffer();
		if (hostIp.getBytes().length < 15) {
			for (int i = 0; i < 15 - hostIp.getBytes().length; i++) {
				hostIpBuffer.append(" ");
			}
		}
		hostIpBuffer.append(hostIp);
		model.setIpdz(hostIpBuffer.toString());

		model.setPackageLength(48);
		ByteBuffer buffer = ByteBuffer.allocate(48);
		buffer.put((byte) 0x2);
		buffer.put("101".getBytes());
		buffer.put("1".getBytes());
		buffer.put(model.getRzbh().getBytes());
		buffer.put(model.getIpdz().getBytes());
		buffer.put(model.getXybb().getBytes());
		buffer.put(CRC32Util.getCRC32(buffer.asReadOnlyBuffer(), 39).getBytes());
		buffer.put((byte) 0x3);
		clientChannel.write(buffer);
	}
}
