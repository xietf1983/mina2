package com.lytx.webservice.datacopylast.service;

import java.util.List;

import com.lytx.webservice.datacopylast.model.DatacopyLast;

public interface DatacopylastService {
	/**
	 * 根据ID获取对应的记录
	 * 
	 * @param domainId
	 * @return
	 */
	public DatacopyLast getDatacopyLastByDomainId(String domainId);

	/**
	 * 获取全部记录
	 * 
	 * @return
	 */
	public List<DatacopyLast> getAllDatacopyLast();

	/**
	 * 插入记录
	 * 
	 * @param datalast
	 */
	public void insertDatacopyLast(DatacopyLast datalast);

	/**
	 * 更新记录
	 * 
	 * @param datalast
	 */

	public void updateDatacopyLast(DatacopyLast datalast);
	
	
	public DatacopyLast getDatacopyLastByDomainIdAndaction(String domainId,String action) ;

}
