package com.liveyc.mina.util;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;

public class CRCUtil {

	public static int getCRC(byte[] bytes) {
		int crc = 0xFFFF; // initial value
		int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

		for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 1) == 1);
				boolean c15 = ((crc >> 15 & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit)
					crc ^= polynomial;
			}
		}

		crc &= 0xffff;
		// System.out.println("CRC16-CCITT = " + Integer.toHexString(crc));
		return crc;
	}

	public static int getCRC32(IoBuffer b, int byteCount) {
		byte[] dst = new byte[byteCount];
		b.position(0);
		b.get(dst);
		return getCRC(dst);
	}
	
	public static int getCRC16_CCITT(byte[] bytes) {
		int crc = 0x00; // initial value
		int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

		for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 1) == 1);
				boolean c15 = ((crc >> 15 & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit)
					crc ^= polynomial;
			}
		}

		crc &= 0xffff;
		// System.out.println("CRC16-CCITT = " + Integer.toHexString(crc));
		return crc;
	}
	
	//CRC16_CCITT ²âÊÔ
	public static void main(String args[]) {
		byte[] b = new byte[] {  
	            (byte) 0x2C, (byte) 0x00, (byte) 0xFF, (byte) 0xFE,  
	            (byte) 0xFE, (byte) 0x04, (byte) 0x00, (byte) 0x00,  
	            (byte) 0x00, (byte) 0x00 };  
		
		int a = getCRC16_CCITT(b);
		System.out.println(a);
	}
}
