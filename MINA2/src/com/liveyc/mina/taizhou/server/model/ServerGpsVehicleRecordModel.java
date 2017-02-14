package com.liveyc.mina.taizhou.server.model;

import java.util.Date;

public class ServerGpsVehicleRecordModel extends ServerBaseModel{
	private String vehicleNo;	//车牌号
	private String vehicleColor;	//车牌颜色
	private Date alarmTime;
	private long lon;			//经度
	private long lat;			//纬度
	private int vec1;           //速度
	private int vec2;			//行驶记录速度
	private long vec3;   		//车辆当前记录速度
	private int direction;		//方向
	private int altitude;		//海拔高度
	private long state;			//车辆状态
	private long alarm;			//报警状态
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
