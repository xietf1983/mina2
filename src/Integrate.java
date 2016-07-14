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
		// �������˵���Ҫ����
		IoAcceptor acceptor = new NioSocketAcceptor();

		// ����Filter��
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// Э�����������mina�ֳɵ�UTF-8�ַ�������ʽ
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("its", new ProtocolCodecFilter(new MessageCodecFactory(true)));

		// ������Ϣ�����ࣨ�������ر�Session���ɶ���д�ȵȣ��̳��Խӿ�IoHandler��
		acceptor.setHandler(new MinaServerHandler());
		// ���ý��ջ�������С
		acceptor.getSessionConfig().setReadBufferSize(2048);
		
		// acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		try {
			// ��������ʼ����
			acceptor.bind(new InetSocketAddress(PropsValues.ITS_PORT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// new Timer().schedule(new MainThread(), 100);

	}

}
