import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.liveyc.configuration.PropsValues;
import com.liveyc.mina.message.code.MessageCodecFactory;
import com.liveyc.mina.server.MinaServerHandler;

public class Integrate {
	public static void main(String[] args) throws Exception {
		// 服务器端的主要对象
		IoAcceptor acceptor = new NioSocketAcceptor();

		// 设置Filter链
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// 协议解析，采用mina现成的UTF-8字符串处理方式
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("its", new ProtocolCodecFilter(new MessageCodecFactory(true)));

		// 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
		acceptor.setHandler(new MinaServerHandler());
		// 设置接收缓存区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		
		// acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		try {
			// 服务器开始监听
			acceptor.bind(new InetSocketAddress(PropsValues.ITS_PORT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// new Timer().schedule(new MainThread(), 100);

	}

}
