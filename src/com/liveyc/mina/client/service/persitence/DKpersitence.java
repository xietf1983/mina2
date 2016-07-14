package com.liveyc.mina.client.service.persitence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.liveyc.mina.client.FailDataLast;
import com.liveyc.mina.client.model.PassVehicleModel;

public class DKpersitence extends SqlMapClientDaoSupport {
	public boolean deleteFailDataLast(String id) {
		getSqlMapClientTemplate().delete("failDataLast_delete", id);
		return true;
	}

	public boolean insertFailDataLast(FailDataLast failDataLast) {
		getSqlMapClientTemplate().insert("failDataLast_insert", failDataLast);
		return true;
	}

	public boolean updateFailDataLast(FailDataLast failDataLast) {
		getSqlMapClientTemplate().update("failDataLast_update", failDataLast);
		return true;
	}

	public FailDataLast findDataLastOne(int sendtype) {
		return (FailDataLast) getSqlMapClientTemplate().queryForObject("failDataLast_one", sendtype);

	}

	public List<PassVehicleModel> getPassVehicleList(long startId, long limit, int type) {
		Map map = new HashMap();
		map.put("STARTID", startId);
		map.put("ENDID", startId + limit);
		if (type > 0) {
			map.put("TYPE", type);
		}

		return getSqlMapClientTemplate().queryForList("vehicleAlarm_List", map);
	}

	/**
	 * 
	 * @param trackId
	 * @return
	 */
	public long getMaxVehicleAlarm() {
		long retvalue = 0;
		Long startLong = (Long) getSqlMapClientTemplate().queryForObject("querystartMaxAlarmId");
		if (startLong != null) {
			retvalue = startLong.longValue();
		}
		return retvalue;
	}

	/**
	 * 
	 * @param alarmId
	 * @return
	 */
	public long getMinVehicleAlarm(long alarmId) {
		Object object = getSqlMapClientTemplate().queryForObject("minAlarmId_vehicleAlarm", alarmId);
		if (object != null) {
			return (Long) object;
		} else {
			return 0l;
		}
	}

	public List<HashMap> getDeviceJcbk(int type) {
		return getSqlMapClientTemplate().queryForList("getdevice_bytype", type);
	}

}
