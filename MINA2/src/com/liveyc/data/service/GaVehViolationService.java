package com.liveyc.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liveyc.mina.taizhou.server.model.ServerGpsVehicleRecordModel;

public interface GaVehViolationService {
	public List<HashMap> getGaVehViolationCheck(Map parameter);

	public List<HashMap> getGaVehViolationStart(Map parameter);

	public void deletegavehviolationupload(long alarmId);

	public void updateDatacopylast(long alarmid);

	public Map getDatacopylast(Map map1);

	public void insertDatacopylast(long maxseq);

	public Map getMaxgavehviolation(Map map1);

	public Map getFromgavehviolation(Map map1);
	
	public void addGpsVehicleRecord(ServerGpsVehicleRecordModel model);
}
