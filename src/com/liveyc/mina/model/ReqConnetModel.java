package com.liveyc.mina.model;

import com.liveyc.mina.model.base.BaseModelHeader;

/**
 * ÑéÖ¤°ü
 * 
 * @author dell
 * 
 */
public class ReqConnetModel extends BaseModelHeader {

	private String userId;
	private String connetTime;
	private String  mac;
	private byte[] version;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConnetTime() {
		return connetTime;
	}

	public void setConnetTime(String connetTime) {
		this.connetTime = connetTime;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public byte[] getVersion() {
		return version;
	}

	public void setVersion(byte[] version) {
		this.version = version;
	}

}
