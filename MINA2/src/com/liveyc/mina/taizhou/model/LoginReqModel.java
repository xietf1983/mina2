package com.liveyc.mina.taizhou.model;

public class LoginReqModel extends BaseModel{
	private long userId;
	private String passWord;
	private String downLinkIp;    	//从链路IP地址
	private int downLinkPort;		//从链路端口号
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getDownLinkIp() {
		return downLinkIp;
	}
	public void setDownLinkIp(String downLinkIp) {
		this.downLinkIp = downLinkIp;
	}
	public int getDownLinkPort() {
		return downLinkPort;
	}
	public void setDownLinkPort(int downLinkPort) {
		this.downLinkPort = downLinkPort;
	}
}
