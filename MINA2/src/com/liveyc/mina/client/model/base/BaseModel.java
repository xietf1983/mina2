package com.liveyc.mina.client.model.base;

public class BaseModel {
	private byte bt;
	private byte bw;
	private int packageLength;
	public int getPackageLength() {
		return packageLength;
	}
	public void setPackageLength(int packageLength) {
		this.packageLength = packageLength;
	}
	public byte getBw() {
		return bw;
	}
	public void setBw(byte bw) {
		this.bw = bw;
	}
	private String xyh;//三个字符的协议号
	public byte getBt() {
		return bt;
	}
	public void setBt(byte bt) {
		this.bt = bt;
	}
	public String getXyh() {
		return xyh;
	}
	public void setXyh(String xyh) {
		this.xyh = xyh;
	}

}
