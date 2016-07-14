package com.lytx.webservice.datacopylast.service.impl;

import java.util.List;

import com.lytx.webservice.datacopylast.model.DatacopyLast;
import com.lytx.webservice.datacopylast.service.DatacopylastService;
import com.lytx.webservice.datacopylast.service.persistence.DatacopyLastPersistence;

public class DatacopylastServiceImpl implements DatacopylastService {

	private DatacopyLastPersistence persistence;

	public DatacopyLastPersistence getPersistence() {
		return persistence;
	}

	public void setPersistence(DatacopyLastPersistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * 根据ID获取对应的记录
	 * 
	 * @param domainId
	 * @return
	 */
	public DatacopyLast getDatacopyLastByDomainId(String domainId) {
		return getPersistence().getDatacopyLastByDomainId(domainId);

	}
	
	public DatacopyLast getDatacopyLastByDomainIdAndaction(String domainId,String action) {
		return  getPersistence().getDatacopyLastByDomainIdAndaction(domainId, action);
	}

	/**
	 * 获取全部记录
	 * 
	 * @return
	 */
	public List<DatacopyLast> getAllDatacopyLast() {
		return getPersistence().getAllDatacopyLast();
	}

	/**
	 * 插入记录
	 * 
	 * @param datalast
	 */
	public void insertDatacopyLast(DatacopyLast datalast) {
		if (getDatacopyLastByDomainIdAndaction(datalast.getDomainId(),datalast.getAction()) == null) {
			getPersistence().insertDatacopyLast(datalast);
		}
	}

	/**
	 * 更新记录
	 * 
	 * @param datalast
	 */

	public void updateDatacopyLast(DatacopyLast datalast) {
		DatacopyLast beforfor = getDatacopyLastByDomainIdAndaction(datalast.getDomainId(),datalast.getAction());
		if (beforfor == null) {
			return;
		} else {
			if (beforfor.getLastseq() >= datalast.getLastseq()) {
				return;
			} else {
				datalast.setDatalasttime(beforfor.getActtime());
				getPersistence().updateDatacopyLast(datalast);
			}
		}

	}
}
