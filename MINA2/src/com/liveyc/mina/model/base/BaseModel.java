package com.liveyc.mina.model.base;

import java.nio.ByteBuffer;

public abstract class BaseModel {
	public static int headLength() {
		return 8;
	}

	public final static int packetLength(ByteBuffer buffer) {
		return buffer.getInt();
	}
}
