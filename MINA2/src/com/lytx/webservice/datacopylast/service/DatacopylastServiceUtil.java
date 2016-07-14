package com.lytx.webservice.datacopylast.service;

import java.util.List;

import com.lytx.webservice.datacopylast.model.DatacopyLast;

public class DatacopylastServiceUtil {

	private static DatacopylastService _service;

	public static DatacopylastService getService() {
		if (_service == null) {
			throw new RuntimeException("DatacopylastService is not set");
		}

		return _service;
	}

	public void setService(DatacopylastService service) {
		_service = service;
	}

	/**
	 * 根据ID获取对应的记录
	 * 
	 * @param domainId
	 * @return
	 */
	public static DatacopyLast getDatacopyLastByDomainId(String domainId) {
		return getService().getDatacopyLastByDomainId(domainId);

	}
	
	public static DatacopyLast getDatacopyLastByDomainIdAndaction(String domainId,String action) {
		return getService().getDatacopyLastByDomainIdAndaction(domainId,action);
	}

	/**
	 * 获取全部记录
	 * 
	 * @return
	 */
	public static List<DatacopyLast> getAllDatacopyLast() {
		return getService().getAllDatacopyLast();

	}

	/**
	 * 插入记录
	 * 
	 * @param datalast
	 */
	public static void insertDatacopyLast(DatacopyLast datalast) {
		getService().insertDatacopyLast(datalast);
	}

	/**
	 * 更新记录
	 * 
	 * @param datalast
	 */

	public static void updateDatacopyLast(DatacopyLast datalast) {
		getService().updateDatacopyLast(datalast);
	}

}