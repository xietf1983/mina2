package com.liveyc.mina.client.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.liveyc.mina.client.FailDataLast;
import com.liveyc.mina.client.SendDKDataThread;
import com.liveyc.mina.client.model.PassVehicleModel;
import com.liveyc.mina.client.service.DKService;
import com.liveyc.mina.client.service.DKServiceUtil;
import com.liveyc.mina.client.service.persitence.DKpersitence;
import com.lytx.webservice.datacopylast.model.DatacopyLast;
import com.lytx.webservice.datacopylast.service.DatacopylastServiceUtil;

public class DKServiceImpl implements DKService {
	protected DKpersitence persistence;

	public DKpersitence getPersistence() {
		return persistence;
	}

	public void setPersistence(DKpersitence persistence) {
		this.persistence = persistence;
	}

	@Override
	public void dealSessionCreat(int type) {
		long trackId = DKServiceUtil.getService().getStartMaxVehicleTrackId();
		DatacopyLast datacopyLast = DatacopylastServiceUtil.getDatacopyLastByDomainId("");
		if (datacopyLast != null && datacopyLast.getLastseq() > 0 && trackId > 0) {
			// 插入需要补传的过车记录
			long starttrackId = datacopyLast.getLastseq();
			long endtrackId = trackId;
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			// 保存
			FailDataLast failLast = new FailDataLast();
			failLast.setCreateTime(new Date());
			failLast.setId(uuid);
			failLast.setStartId(starttrackId);
			failLast.setEndId(endtrackId);
			failLast.setSendType(type);
			DKServiceUtil.getService().insertFailDataLast(failLast);

		}

	}

	@Override
	public void dealSessionClose(int type) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteFailDataLast(String id) {
		return getPersistence().deleteFailDataLast(id);
	}

	@Override
	public boolean insertFailDataLast(FailDataLast failDataLast) {
		// TODO Auto-generated method stub
		return getPersistence().insertFailDataLast(failDataLast);
	}

	@Override
	public boolean updateFailDataLast(FailDataLast failDataLast) {
		return getPersistence().updateFailDataLast(failDataLast);
	}

	@Override
	public FailDataLast findDataLastOne(int sendtype) {
		return getPersistence().findDataLastOne(sendtype);
	}

	@Override
	public List<PassVehicleModel> getVehicleAlarmByStartId(long startId, long rowsId, int type) {
		return getPersistence().getPassVehicleList(startId, rowsId,type);
	}

	@Override
	public long getStartMaxVehicleTrackId() {
		// TODO Auto-generated method stub
		return getPersistence().getMaxVehicleAlarm();
	}

	@Override
	public long getMinVehicleAlarmTrackId(long trackId) {
		// TODO Auto-generated method stub
		return getPersistence().getMinVehicleAlarm(trackId);
	}

	public List<HashMap> getDeviceJcbk(int type) {
		return getPersistence().getDeviceJcbk(type);
	}

}
