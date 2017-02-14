package com.liveyc.data.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.liveyc.mina.taizhou.server.model.ServerGpsVehicleRecordModel;

public class GaVehViolationPersistence extends SqlMapClientDaoSupport {
	public List<HashMap> getGaVehViolationCheck(Map parameter) {
		return getSqlMapClientTemplate().queryForList("gavehviolation_list_checked", parameter);

	}

	public List<HashMap> getGaVehViolationStart(Map parameter) {
		return getSqlMapClientTemplate().queryForList("gavehviolation_list_alarmid", parameter);
	}

	public void deletegavehviolationupload(long alarmId) {
		getSqlMapClientTemplate().delete("gavehviolationupload_delete", alarmId);
	}

	public void updateDatacopylast(long alarmid) {
		Map m = new HashMap();
		m.put("domainid", "gavehviolation_wh");
		m.put("action", "gavehviolation_wh");
		m.put("detail", "gavehviolation_wh");
		m.put("lastseq", alarmid);
		getSqlMapClientTemplate().update("Datacopylast_update", m);
	}

	public Map getDatacopylast(Map map1) {
		return (Map) getSqlMapClientTemplate().queryForObject("Datacopylast_load", map1);
	}

	public void insertDatacopylast(long maxseq) {
		Map map1 = new HashMap();
		map1.put("domainid", "gavehviolation_wh");
		map1.put("action", "gavehviolation_wh");
		map1.put("detail", "gavehviolation_wh");
		map1.put("lastseq", maxseq);
		getSqlMapClientTemplate().insert("Datacopylast_insert", map1);
	}

	public Map getMaxgavehviolation(Map map1) {
		return (Map) getSqlMapClientTemplate().queryForObject("gavehviolation_max", map1);
	}

	public Map getFromgavehviolation(Map map1) {
		return (Map) getSqlMapClientTemplate().queryForObject("gavehviolation_start", map1);
	}
	
	public void addGpsVehicleRecord(ServerGpsVehicleRecordModel model) {
		getSqlMapClientTemplate().insert("insertGpsVehicleRecord", model);
	}
}
