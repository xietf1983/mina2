package com.liveyc.mina.taizhou.model;

public class LoginReqModel extends BaseModel{
	private long userId;
	private String passWord;
	private String downLinkIp;    	//����·IP��ַ
	private int downLinkPort;		//����·�˿ں�
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
