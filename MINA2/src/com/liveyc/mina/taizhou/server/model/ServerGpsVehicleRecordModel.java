package com.liveyc.mina.taizhou.server.model;

import java.util.Date;

public class ServerGpsVehicleRecordModel extends ServerBaseModel{
	private String vehicleNo;	//���ƺ�
	private String vehicleColor;	//������ɫ
	private Date alarmTime;
	private long lon;			//����
	private long lat;			//γ��
	private int vec1;           //�ٶ�
	private int vec2;			//��ʻ��¼�ٶ�
	private long vec3;   		//������ǰ��¼�ٶ�
	private int direction;		//����
	private int altitude;		//���θ߶�
	private long state;			//����״̬
	private long alarm;			//����״̬
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public Date getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	public long getLon() {
		return lon;
	}
	public void setLon(long lon) {
		this.lon = lon;
	}
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public int getVec1() {
		return vec1;
	}
	public void setVec1(int vec1) {
		this.vec1 = vec1;
	}
	public int getVec2() {
		return vec2;
	}
	public void setVec2(int vec2) {
		this.vec2 = vec2;
	}
	public long getVec3() {
		return vec3;
	}
	public void setVec3(long vec3) {
		this.vec3 = vec3;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	public long getAlarm() {
		return alarm;
	}
	public void setAlarm(long alarm) {
		this.alarm = alarm;
	}
}
