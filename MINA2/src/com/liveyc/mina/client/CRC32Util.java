package com.liveyc.mina.client;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

import org.apache.mina.core.buffer.IoBuffer;

import com.liveyc.mina.util.CRCUtil;

public class CRC32Util {
	public static String getCRC32(byte[] data) {
		CRC32 crc32 = new CRC32();
		crc32.update(data);
		String tt = Long.toHexString(crc32.getValue());
		StringBuffer bufer = new StringBuffer();
		if (tt.length() < 8) {
			for (int j = 0; j < 8 - tt.length(); j++) {
				bufer.append(" ");
			}
		}
		bufer.append(tt);
		return bufer.toString();
	}

	public static String getCRC32(IoBuffer b, int byteCount) {
		byte[] dst = new byte[byteCount];
		b.position(0);
		b.get(dst);
		return getCRC32(dst);
	}
	public static String getCRC32(ByteBuffer b, int byteCount) {
		byte[] dst = new byte[byteCount];
		b.position(0);
		b.get(dst);
		return getCRC32(dst);
	}

	public static String getFixString(String data, int lenth) {
		if (data == null) {
			data = "";
		}
		StringBuffer buffer = new StringBuffer();
		if (data.getBytes().length > lenth) {
			try {
				data = new String(subBytes(data.getBytes("gb2312"), 0, lenth), "gb2312");
			} catch (Exception ex) {

			}
		}
		for (int i = 0; i < lenth - data.length(); i++) {
			buffer.append(" ");
		}
		buffer.append(data);
		return buffer.toString();

	}
	
	
	public static String getFixNumString(String data, int lenth) {
		if (data == null) {
			data = "";
		}
		StringBuffer buffer = new StringBuffer();
		if (data.getBytes().length > lenth) {
			try {
				data = new String(subBytes(data.getBytes("gb2312"), 0, lenth), "gb2312");
			} catch (Exception ex) {

			}
		}
		for (int i = 0; i < lenth - data.length(); i++) {
			buffer.append("0");
		}
		buffer.append(data);
		return buffer.toString();

	}

	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}
}
