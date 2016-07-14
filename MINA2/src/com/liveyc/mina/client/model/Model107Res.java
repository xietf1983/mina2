package com.liveyc.mina.client.model;

import com.liveyc.mina.client.model.base.BaseModel;
import java.util.List;
public class Model107Res extends BaseModel {
	private String rzbh;
	private String sjjy;
	private byte ljbw;
	private String sjcd;
	private  String  sbgs;
	public String getSbgs() {
		return sbgs;
	}
	public void setSbgs(String sbgs) {
		this.sbgs = sbgs;
	}
	public String getSjcd() {
		return sjcd;
	}
	public void setSjcd(String sjcd) {
		this.sjcd = sjcd;
	}
	private List<DeviceObject> List;
	public List<DeviceObject> getList() {
		return List;
	}
	public void setList(List<DeviceObject> list) {
		List = list;
	}
	public String getRzbh() {
		return rzbh;
	}
	public void setRzbh(String rzbh) {
		this.rzbh = rzbh;
	}
	public String getSjjy() {
		return sjjy;
	}
	public void setSjjy(String sjjy) {
		this.sjjy = sjjy;
	}
	public byte getLjbw() {
		return ljbw;
	}
	public void setLjbw(byte ljbw) {
		this.ljbw = ljbw;
	}

}
