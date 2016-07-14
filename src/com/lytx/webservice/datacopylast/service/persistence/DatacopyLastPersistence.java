package com.lytx.webservice.datacopylast.service.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lytx.webservice.datacopylast.model.DatacopyLast;

public class DatacopyLastPersistence extends SqlMapClientDaoSupport {

	/**
	 * ����ID��ȡ��Ӧ�ļ�¼
	 * 
	 * @param domainId
	 * @return
	 */
	public DatacopyLast getDatacopyLastByDomainId(String domainId) {
		return (DatacopyLast) getSqlMapClientTemplate().queryForObject("querydatacopylastByDomainId", domainId);

	}
	
	public DatacopyLast getDatacopyLastByDomainIdAndaction(String domainId,String action) {
		Map map = new HashMap();
		map.put("domainId", domainId);
		if(action!= null && !action.equals("")){
			map.put("action", action);
		}
		return (DatacopyLast) getSqlMapClientTemplate().queryForObject("querydatacopylastByDomainIdAndAction", map);

	}

	/**
	 * ��ȡȫ����¼
	 * 
	 * @return
	 */
	public List<DatacopyLast> getAllDatacopyLast() {
		return (List<DatacopyLast>) getSqlMapClientTemplate().queryForList("queryAlldatacopylast");

	}

	/**
	 * �����¼
	 * 
	 * @param datalast
	 */
	public void insertDatacopyLast(DatacopyLast datalast) {
		getSqlMapClientTemplate().insert("datacopylast_insert", datalast);

	}

	/**
	 * ���¼�¼
	 * 
	 * @param datalast
	 */

	public void updateDatacopyLast(DatacopyLast datalast) {
		getSqlMapClientTemplate().update("datacopylast_update", datalast);

	}

}
