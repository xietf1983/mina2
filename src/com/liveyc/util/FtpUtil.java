package com.liveyc.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.liveyc.scheduling.SendDataThread;

public class FtpUtil {
	private static Logger iLog = Logger.getLogger(FtpUtil.class);

	public static boolean uploadFile2(String url, int port, String username, String password, String path, String filename, String httpresource) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setConnectTimeout(3000);
		ftp.setDataTimeout(60000);
		
		InputStream input = null;
		iLog.error("ͼƬ��ʼ����:" + httpresource);
		try {
			int reply;
			//����ģʽ
			iLog.error("��ʼ����" );
			ftp.connect(url, port);// ����FTP������
			iLog.error("���" );
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			// ftp.set
			ftp.setBufferSize(1024*1024);
			ftp.enterLocalPassiveMode();
			ftp.login(username, password);// ��¼
			reply = ftp.getReplyCode();
			iLog.error("���뷵��ֵ"+ reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				//ftp.quit();
				ftp.disconnect();
				return success;
			}
			// ftp.set
			// System.out.println("path:" + path);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			URL urlsource = new URL(httpresource);
			URLConnection urlConnection = null;
			urlConnection = urlsource.openConnection();
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(10000);
			input = new URL(httpresource).openStream();
			// ftp.changeToParentDirectory();
			// iLog.error("ͼƬ����·��:" + path);
			ftp.changeWorkingDirectory("/");
			if (!ftp.changeWorkingDirectory(path)) {
				String directory = path.substring(0, path.lastIndexOf("/") + 1);
				// System.out.println("directory:"+directory);
				// iLog.error("directory:" + directory);
				int start = 0;
				int end = 0;
				if (path.startsWith("/")) {
					start = 1;
				} else {
					start = 0;
				}
				end = directory.indexOf("/", start);
				while (true) {
					String subDirectory = new String(path.substring(start, end));
					boolean ret = ftp.changeWorkingDirectory(subDirectory);
					if (!ret) {
						if (ftp.makeDirectory(subDirectory)) {
							ftp.changeWorkingDirectory(subDirectory);
							iLog.error("����Ŀ¼�� " + subDirectory);
							//System.out.println("����Ŀ¼�� " + subDirectory);
						} else {
							iLog.error("����Ŀ¼ʧ�ܣ� " + subDirectory);
							//System.out.println("����Ŀ¼ʧ��");
							success = false;
							return success;
						}
					}
					start = end + 1;
					end = directory.indexOf("/", start);
					// �������Ŀ¼�Ƿ񴴽����
					if (end <= start) {
						break;
					}
				}
			}
			ftp.deleteFile(filename);
			success = ftp.storeFile(filename, input);
			iLog.error("ͼƬ����:" + success);
			// success = true;
		} catch (IOException e) {
			iLog.error(e.toString());
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ioe) {

				}
			}
			try {
				ftp.logout();
			} catch (IOException ioe) {
				iLog.error(ioe.toString());
			}
			if (ftp.isConnected()) {
				try {
					// ftp.quit()
					ftp.disconnect();
				} catch (IOException ioe) {
					iLog.error(ioe.toString());
				}
			}
		}
		return success;
	}
}
