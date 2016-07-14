package com.liveyc.mina.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liveyc.mina.client.FailDataLast;
import com.liveyc.mina.client.model.PassVehicleModel;

public interface DKService {
	public void dealSessionCreat(int type);

	public void dealSessionClose(int type);



	public boolean deleteFailDataLast(String id);

	public boolean insertFailDataLast(FailDataLast failDataLast);

	public boolean updateFailDataLast(FailDataLast failDataLast);

	public FailDataLast findDataLastOne(int sendtype);
	
	public List<PassVehicleModel> getVehicleAlarmByStartId(long startId, long rowsId,int type);
	
	public long getStartMaxVehicleTrackId() ;
	
	public long getMinVehicleAlarmTrackId(long trackId);
	
	public List<HashMap> getDeviceJcbk(int type);
}
