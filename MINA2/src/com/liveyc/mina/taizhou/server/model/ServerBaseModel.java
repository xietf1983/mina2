package com.liveyc.mina.taizhou.server.model;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ServerBaseModel {
	private byte headFlag;		//消息头
	private long length;         //数据长度
	private long sn;				//报文序列号
	private int id;				//业务数据类型               4097:主链路登录请求，4098:主链路登录应答，4608:主链路动态信息交换(主业务类型)，4610:实时上传车辆定位信息(子业务类型)
	private long gnssCenterId;	//下级平台接入码
	private byte[] versionFlag;	//协议版本号
	private byte encryptFlag;	//报文加密标识
	private long encryptKey;		//数据加密密钥
	private int crcCode;		//CRC校验码
	private byte endFlag;		//消息尾
	private int modelType;		// 1-LoginReqModel; 2-RealLocationReqDataModel; 3-LoginRespModel
	public byte getHeadFlag() {
		return headFlag;
	}
	public void setHeadFlag(byte headFlag) {
		this.headFlag = headFlag;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public long getSn() {
		return sn;
	}
	public void setSn(long sn) {
		this.sn = sn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getGnssCenterId() {
		return gnssCenterId;
	}
	public void setGnssCenterId(long gnssCenterId) {
		this.gnssCenterId = gnssCenterId;
	}
	public byte[] getVersionFlag() {
		return versionFlag;
	}
	public void setVersionFlag(byte[] versionFlag) {
		this.versionFlag = versionFlag;
	}
	public byte getEncryptFlag() {
		return encryptFlag;
	}
	public void setEncryptFlag(byte encryptFlag) {
		this.encryptFlag = encryptFlag;
	}
	public long getEncryptKey() {
		return encryptKey;
	}
	public void setEncryptKey(long encryptKey) {
		this.encryptKey = encryptKey;
	}
	public int getCrcCode() {
		return crcCode;
	}
	public void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}
	public byte getEndFlag() {
		return endFlag;
	}
	public void setEndFlag(byte endFlag) {
		this.endFlag = endFlag;
	}
	public int getModelType() {
		return modelType;
	}
	public void setModelType(int modelType) {
		this.modelType = modelType;
	}
}
