package com.liveyc.mina.sichun.model;

public class BaseModel {
	private long tag;
	private long length;
	private int messageType;
	private long endtag;
	public long getTag() {
		return tag;
	}
	public void setTag(long tag) {
		this.tag = tag;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public long getEndtag() {
		return endtag;
	}
	public void setEndtag(long endtag) {
		this.endtag = endtag;
	}

}
