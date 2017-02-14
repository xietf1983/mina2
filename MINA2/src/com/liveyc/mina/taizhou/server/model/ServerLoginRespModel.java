package com.liveyc.mina.taizhou.server.model;

public class ServerLoginRespModel extends ServerBaseModel{
	private byte result;
	private int verifyCode;
	public byte getResult() {
		return result;
	}
	public void setResult(byte result) {
		this.result = result;
	}
	public int getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}
}
