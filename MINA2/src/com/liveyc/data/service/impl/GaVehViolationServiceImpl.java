package com.liveyc.data.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liveyc.data.persistence.GaVehViolationPersistence;
import com.liveyc.data.service.GaVehViolationService;
import com.liveyc.mina.taizhou.server.model.ServerGpsVehicleRecordModel;


public class GaVehViolationServiceImpl implements GaVehViolationService{
	private GaVehViolationPersistence persistence;

	public GaVehViolationPersistence getPersistence() {
		return persistence;
	}

	public void setPersistence(GaVehViolationPersistence persistence) {
		this.persistence = persistence;
	}

	public List<HashMap> getGaVehViolationCheck(Map parameter) {
		return getPersistence().getGaVehViolationCheck(parameter);
	}

	public List<HashMap> getGaVehViolationStart(Map parameter) {
		return getPersistence().getGaVehViolationStart(parameter);
	}

	public void deletegavehviolationupload(long alarmId) {
		getPersistence().deletegavehviolationupload(alarmId);
	}

	public void updateDatacopylast(long alarmid) {
		getPersistence().updateDatacopylast(alarmid);
	}

	public Map getDatacopylast(Map map1) {
		return getPersistence().getDatacopylast(map1);
	}

	public void insertDatacopylast(long maxseq) {
		getPersistence().insertDatacopylast(maxseq);
	}

	public Map getMaxgavehviolation(Map map1) {
		return getPersistence().getMaxgavehviolation(map1);
	}

	public Map getFromgavehviolation(Map map1) {
		return getPersistence().getFromgavehviolation(map1);
	}

	public void addGpsVehicleRecord(ServerGpsVehicleRecordModel model) {
		getPersistence().addGpsVehicleRecord(model);
	}
	

}
