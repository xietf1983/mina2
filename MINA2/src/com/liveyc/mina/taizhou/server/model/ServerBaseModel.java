package com.liveyc.mina.taizhou.server.model;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ServerBaseModel {
	private byte headFlag;		//��Ϣͷ
	private long length;         //���ݳ���
	private long sn;				//�������к�
	private int id;				//ҵ����������               4097:����·��¼����4098:����·��¼Ӧ��4608:����·��̬��Ϣ����(��ҵ������)��4610:ʵʱ�ϴ�������λ��Ϣ(��ҵ������)
	private long gnssCenterId;	//�¼�ƽ̨������
	private byte[] versionFlag;	//Э��汾��
	private byte encryptFlag;	//���ļ��ܱ�ʶ
	private long encryptKey;		//���ݼ�����Կ
	private int crcCode;		//CRCУ����
	private byte endFlag;		//��Ϣβ
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
