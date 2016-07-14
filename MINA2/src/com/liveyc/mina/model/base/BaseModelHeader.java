package com.liveyc.mina.model.base;

public class BaseModelHeader extends BaseModel {
	protected int dataLength;

	protected int messageType;

	protected long messageSn;

	public long getMessageSn() {
		return messageSn;
	}

	public void setMessageSn(long messageSn) {
		this.messageSn = messageSn;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

}
