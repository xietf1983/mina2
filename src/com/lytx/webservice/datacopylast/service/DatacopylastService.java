package com.lytx.webservice.datacopylast.service;

import java.util.List;

import com.lytx.webservice.datacopylast.model.DatacopyLast;

public interface DatacopylastService {
	/**
	 * ����ID��ȡ��Ӧ�ļ�¼
	 * 
	 * @param domainId
	 * @return
	 */
	public DatacopyLast getDatacopyLastByDomainId(String domainId);

	/**
	 * ��ȡȫ����¼
	 * 
	 * @return
	 */
	public List<DatacopyLast> getAllDatacopyLast();

	/**
	 * �����¼
	 * 
	 * @param datalast
	 */
	public void insertDatacopyLast(DatacopyLast datalast);

	/**
	 * ���¼�¼
	 * 
	 * @param datalast
	 */

	public void updateDatacopyLast(DatacopyLast datalast);
	
	
	public DatacopyLast getDatacopyLastByDomainIdAndaction(String domainId,String action) ;

}
