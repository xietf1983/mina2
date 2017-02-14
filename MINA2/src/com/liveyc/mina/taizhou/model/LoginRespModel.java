package com.liveyc.mina.taizhou.model;

public class LoginRespModel extends BaseModel{
	private byte result;
	private long verifyCode;
	public byte getResult() {
		return result;
	}
	public void setResult(byte result) {
		this.result = result;
	}
	public long getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(long verifyCode) {
		this.verifyCode = verifyCode;
	}
}
