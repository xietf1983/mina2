package com.liveyc.mina.model;

import com.liveyc.mina.model.base.BaseModelHeader;


public class ResConnetMode extends BaseModelHeader {
	private byte result;
	private String sysdateTime;
	public byte getResult() {
		return result;
	}
	public void setResult(byte result) {
		this.result = result;
	}
	public String getSysdateTime() {
		return sysdateTime;
	}
	public void setSysdateTime(String sysdateTime) {
		this.sysdateTime = sysdateTime;
	}
}
