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
	 * ����ID��ȡ��Ӧ�ļ�¼
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
	 * ��ȡȫ����¼
	 * 
	 * @return
	 */
	public List<DatacopyLast> getAllDatacopyLast() {
		return getPersistence().getAllDatacopyLast();
	}

	/**
	 * �����¼
	 * 
	 * @param datalast
	 */
	public void insertDatacopyLast(DatacopyLast datalast) {
		if (getDatacopyLastByDomainIdAndaction(datalast.getDomainId(),datalast.getAction()) == null) {
			getPersistence().insertDatacopyLast(datalast);
		}
	}

	/**
	 * ���¼�¼
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
