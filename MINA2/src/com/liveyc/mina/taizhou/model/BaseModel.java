package com.liveyc.mina.taizhou.model;

import org.omg.CORBA.PRIVATE_MEMBER;

public class BaseModel {
	private byte headFlag = (byte) 0x5b;		//��Ϣͷ
	private long length;         //���ݳ���
	private long sn;				//�������к�
	private int id;				//ҵ����������
	private long gnssCenterId;	//�¼�ƽ̨������
	private byte[] versionFlag;	//Э��汾��
	private byte encryptFlag;	//���ļ��ܱ�ʶ
	private int encryptKey;		//���ݼ�����Կ
	private int crcCode;		//CRCУ����
	private byte endFlag = (byte) 0x5d;		//��Ϣβ
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
	public int getEncryptKey() {
		return encryptKey;
	}
	public void setEncryptKey(int encryptKey) {
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
