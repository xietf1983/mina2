package com.liveyc.mina.model;

import com.liveyc.mina.model.base.BaseModelHeader;

public class ReqRealLocation extends BaseModelHeader {
	private int vlength;
	public int getVlength() {
		return vlength;
	}
	public void setVlength(int vlength) {
		this.vlength = vlength;
	}
	private byte deviceType;
	private String deviceId;
	private String id;
	private String plateNo;
	private String platype;
	private String datetime;
	private String action;
	private Long value;
	private Long standard;
	private String sendTime;
	private byte picNumber;
	private String picUrl;
	
	public byte getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(byte deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getPlatype() {
		return platype;
	}
	public void setPlatype(String platype) {
		this.platype = platype;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public Long getStandard() {
		return standard;
	}
	public void setStandard(Long standard) {
		this.standard = standard;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public byte getPicNumber() {
		return picNumber;
	}
	public void setPicNumber(byte picNumber) {
		this.picNumber = picNumber;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
