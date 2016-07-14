package com.liveyc.mina.sichun;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.mina.client.DKMsgProtocol;
import com.liveyc.mina.client.DKMsgStartProtocol;
import com.liveyc.mina.client.FailDataLast;
import com.liveyc.mina.client.model.Model103Req;
import com.liveyc.mina.client.model.PassVehicleModel;
import com.liveyc.mina.client.model.TpObject;
import com.liveyc.mina.client.service.DKServiceUtil;
import com.liveyc.mina.sichun.model.VehicleReqDataModel;
import com.liveyc.mina.util.CodecUtil;
import com.liveyc.util.MssProxy;
import com.lytx.webservice.datacopylast.model.DatacopyLast;
import com.lytx.webservice.datacopylast.service.DatacopylastServiceUtil;

public class SendDataThread extends Thread {
	private static Logger iLog = Logger.getLogger(SendDataThread.class);
	public static long msgsn = 0;
	public static long rows = 200;
	public static long trackId = 0;
	public static long longrows = 200;
	public static long trytimes = 0;
	public String domainId = "sendsc";

	public void run() {
		try {
			trackId = DKServiceUtil.getService().getStartMaxVehicleTrackId();
			DatacopyLast datacopyLast = DatacopylastServiceUtil
					.getDatacopyLastByDomainId(domainId);
			if (datacopyLast != null && datacopyLast.getLastseq() > 0
					&& trackId > 0) {
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
				failLast.setSendType(6);
				DKServiceUtil.getService().insertFailDataLast(failLast);
			}

		} catch (Exception ex) {
			iLog.error("插入FailDataLast失败" + ex.toString());
		}
		while (true) {
			try {
				if (trackId > 0) {
					List<PassVehicleModel> list = DKServiceUtil.getService()
							.getVehicleAlarmByStartId(trackId, longrows, 6);
					if (list != null && list.size() > 0) {
						if ((list.size() + trackId) == list
								.get(list.size() - 1).getTrackId()
								|| trytimes > 5
								|| DKServiceUtil.getService()
										.getStartMaxVehicleTrackId() > (trackId + 5000)) {
							trytimes = 0;
							// 发送数据
							trackId = list.get(list.size() - 1).getTrackId();
							if (ClientDataMsgProtocol.getInstances()
									.isIsconenected()) {
								DatacopyLast datacopyLast = DatacopylastServiceUtil
										.getDatacopyLastByDomainId(domainId);
								if (datacopyLast == null) {
									// 获取当前值
									if (trackId > 0) {
										datacopyLast = new DatacopyLast();
										datacopyLast.setLastseq(trackId);
										datacopyLast.setDomainId(domainId);
										datacopyLast.setAction("copy"
												+ domainId);
										DatacopylastServiceUtil
												.insertDatacopyLast(datacopyLast);
									}

								} else {
									datacopyLast.setLastseq(trackId);
									DatacopylastServiceUtil
											.updateDatacopyLast(datacopyLast);
								}
								sendData(list);
							} else {

							}
						} else {
							trytimes = trytimes + 1;
							list = DKServiceUtil.getService()
									.getVehicleAlarmByStartId(trackId, rows, 6);
							if (list != null && list.size() > 0) {
								if ((list.size() + trackId) == list.get(
										list.size() - 1).getTrackId()
										|| trytimes > 5
										|| DKServiceUtil.getService()
												.getStartMaxVehicleTrackId() > (trackId + 5000)) {
									trytimes = 0;
									// 发送数据
									trackId = list.get(list.size() - 1)
											.getTrackId();
									if (ClientDataMsgProtocol.getInstances()
											.isIsconenected()) {
										DatacopyLast datacopyLast = DatacopylastServiceUtil
												.getDatacopyLastByDomainId(domainId);
										if (datacopyLast == null) {
											// 获取当前值
											if (trackId > 0) {
												datacopyLast = new DatacopyLast();
												datacopyLast
														.setLastseq(trackId);
												datacopyLast
														.setDomainId(domainId);
												datacopyLast.setAction("copy"
														+ domainId);
												DatacopylastServiceUtil
														.insertDatacopyLast(datacopyLast);
											}

										} else {
											datacopyLast.setLastseq(trackId);
											DatacopylastServiceUtil
													.updateDatacopyLast(datacopyLast);
										}
										sendData(list);
									} else {

									}
								}
							}
						}
					} else {
						long value = DKServiceUtil.getService()
								.getMinVehicleAlarmTrackId(trackId);
						if (value > 0 && value > (trackId + longrows)) {
							trackId = value - 1;
						}
					}
				}
				Thread.sleep(200);
			} catch (Exception ex) {
				try {
					Thread.sleep(2000);
				} catch (Exception ee) {

				}
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				ex.printStackTrace(pw);
				iLog.error("SendHisDataThread" + sw.toString());
			}

		}
	}

	public void sendData(List<PassVehicleModel> vehicleList) {
		if (vehicleList != null && vehicleList.size() > 0) {
			boolean ret = false;
			for (PassVehicleModel p : vehicleList) {
				if (p.getCjbh() != null && !p.getCjbh().equals("")) {
					try {
						VehicleReqDataModel veh = new VehicleReqDataModel();
						veh.setMessageType(3);
						if (p.getAlarmId() > Integer.MAX_VALUE) {
							veh.setSn(1l);
						}
						veh.setXxbbh(PropsUtil.get(PropsKeys.SICHUN_VSERSION));
						veh.setJrbh(PropsUtil.get(PropsKeys.SICHUN_RZBH));
						veh.setKkbh(p.getCjbh());
						veh.setCdbh(CodecUtil.getFixStringBlank(
								p.getCdh() + "", 2));
						veh.setFxbh(p.getFxbh());
						veh.setSbbh(p.getSbbh());
						// 设置号牌号码
						if (p.getPlateNo() == null || p.getPlateNo().equals("")) {
							veh.setHphm(CodecUtil.getFixStringBlank("noplate",
									18));
						} else {
							if (p.getPlateNo().length() > 6) {
								veh.setHphm(CodecUtil.getFixStringBlank(
										p.getPlateNo(), 18));
							} else {
								veh.setHphm(CodecUtil
										.getFixStringBlank("-", 18));
							}
						}
						// 设置号牌颜色
						if (p.getPlateColor() != null
								&& !p.getPlateColor().equals("")) {
							if (p.getPlateColor().indexOf("白") > -1) {
								veh.setHpys("0 ");
							} else if (p.getPlateColor().indexOf("黄") > -1) {
								veh.setHpys("1 ");
							} else if (p.getPlateColor().indexOf("蓝") > -1) {
								veh.setHpys("2 ");
							} else if (p.getPlateColor().indexOf("黑") > -1) {
								veh.setHpys("3 ");
							} else {
								veh.setHpys("4 ");
							}

						} else {
							veh.setHpys("4 ");
						}
						// 设置好泡种类
						if (p.getPlateType() != null
								&& !p.getPlateType().equals("")) {
							if (p.getPlateType().indexOf("小") > -1) {
								veh.setHpzl("02");
							} else if (p.getPlateType().indexOf("大") > -1) {
								veh.setHpzl("01");
							} else {
								veh.setHpzl("99");
							}
						} else {
							veh.setHpzl("99");
						}
						veh.setCwhphm(CodecUtil.getFixStringBlank("", 18));
						veh.setCwhpys(CodecUtil.getFixStringBlank("", 2));
						veh.setCwhpzy(CodecUtil.getFixStringBlank("", 2));
						veh.setHpyz("3 ");
						veh.setCscd(0);
						veh.setClpp(CodecUtil.getFixStringBlank("", 10));
						veh.setClwx(CodecUtil.getFixStringBlank("", 10));
						if (p.getVehicleColor() != null
								&& !p.getVehicleColor().equals("")) {
							if (p.getVehicleColor().indexOf("白") > -1) {
								veh.setCsys("A ");
							} else if (p.getVehicleColor().indexOf("灰") > -1) {
								veh.setCsys("B ");
							} else if (p.getVehicleColor().indexOf("黄") > -1) {
								veh.setCsys("C ");
							} else if (p.getVehicleColor().indexOf("粉") > -1) {
								veh.setCsys("D ");
							} else if (p.getVehicleColor().indexOf("红") > -1) {
								veh.setCsys("E ");
							} else if (p.getVehicleColor().indexOf("紫") > -1) {
								veh.setCsys("F ");
							} else if (p.getVehicleColor().indexOf("绿") > -1) {
								veh.setCsys("G ");
							} else if (p.getVehicleColor().indexOf("蓝") > -1) {
								veh.setCsys("H ");
							} else if (p.getVehicleColor().indexOf("棕") > -1) {
								veh.setCsys("I ");
							} else if (p.getVehicleColor().indexOf("黑") > -1) {
								veh.setCsys("J ");
							} else {
								veh.setCsys("Z ");
							}
						} else {
							veh.setCsys("Z ");
						}
						veh.setCllx(CodecUtil.getFixStringBlank("", 4));
						veh.setWflx(CodecUtil.getFixStringBlank("", 4));
						veh.setClsd(p.getRecordedSpeed()==null? 0:p.getRecordedSpeed());
						veh.setClxs(p.getSpeedMax()==null? 0:p.getSpeedMax());
						veh.setJgsj(CodecUtil.getFixStringBlank(
								p.getAlarmTimeStr(), 24));
						veh.setCpzbjq(0);
						veh.setCpxzb(0);
						veh.setCpyzb(0);
						veh.setCpkd(0);
						veh.setRljq(0);
						List<TpObject> list = getPhotourl(p);
						int size =0;
						for(TpObject t:list){
							if(!t.getUrl().trim().equals("")){
								size=size+1;
							}
						}
						veh.setTxsl(size);
						if (veh.getTplj1() == null
								|| veh.getTplj1().equals("")) {
							veh.setTplj1(CodecUtil.getFixStringBlank(
									list.get(0).getUrl(), 300));
						} else {
							veh.setTplj1(CodecUtil.getFixStringBlank("",
									300));
						}
						if (veh.getTplj2() == null
								|| veh.getTplj2().equals("")) {
							veh.setTplj2(CodecUtil.getFixStringBlank(
									list.get(1).getUrl(), 300));
						} else {
							veh.setTplj2(CodecUtil.getFixStringBlank("",
									300));
						}
						if (veh.getTplj3() == null
								|| veh.getTplj3().equals("")) {
							veh.setTplj3(CodecUtil.getFixStringBlank(
									list.get(2).getUrl(), 300));
						} else {
							veh.setTplj4(CodecUtil.getFixStringBlank("",
									300));
						}
						if (veh.getTplj4() == null
								|| veh.getTplj4().equals("")) {
							veh.setTplj4(CodecUtil.getFixStringBlank(
									list.get(3).getUrl(), 300));
						} else {
							veh.setTplj4(CodecUtil.getFixStringBlank("",
									300));
						}
						veh.setLxlj(CodecUtil.getFixStringBlank("", 300));
						veh.setEndtag(0x88aa99bb);
						veh.setTag(0x55aa66bb);
						veh.setLength(1746);
						veh.setSn(1l);
						ClientDataMsgProtocol.getInstances().getConnFuture()
								.getSession().write(veh);
					} catch (Exception ex) {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						ex.printStackTrace(pw);
						iLog.error("LostPassVehicleThread" + sw.toString());
					}
				}
			}
		}

	}

	public List<TpObject> getPhotourl(PassVehicleModel p) {
		List<TpObject> list = new ArrayList();
		if (p.getCenterStoreKey() != null && !p.getCenterStoreKey().equals("")) {
			if (p.getCenterStoreKey().toUpperCase().indexOf("HTTP") > -1
					|| p.getCenterStoreKey().toUpperCase().indexOf("FTP") > -1) {
				String[] temp = p.getCenterStoreKey().split(";");
				StringBuffer buffer = new StringBuffer();
				for (int j = 0; j < temp.length; j++) {
					if (temp[j] == null || temp[j].equals("")) {

					} else {
						TpObject tp = new TpObject();
						tp.setSize("0000000");
						if (temp[j].indexOf("pf=1") > -1) {
							tp.setType("01");
						} else {
							tp.setType("09");
						}
						StringBuffer bu = new StringBuffer();
						try {
							bu.append(temp[j]);
						} catch (Exception ex) {

						}
						tp.setUrl(bu.toString());
						list.add(tp);
					}
				}

			} else {
				StringBuffer buffer = new StringBuffer();
				try {
					iLog.error("SSU 查询图片信息");
					String[] pics = MssProxy.getInstance().queryAlarmPicUrls(
							p.getFdId(), (short) 1,
							p.getChannelId().shortValue(),
							p.getCenterStoreKey(), p.getStorageAreaId(),
							p.getAlarmTime(), null);
					if (pics != null && pics.length > 0) {
						for (int j = 0; j < pics.length; j++) {
							TpObject tp = new TpObject();
							tp.setSize("0000000");
							if (pics[j].indexOf("pf=1") > -1) {

							} else {
								if (buffer.toString().equals("")) {
									tp.setType("01");
								} else {
									tp.setType("09");
								}
								StringBuffer bu = new StringBuffer();
								try {
									bu.append(pics[j]);
								} catch (Exception ex) {

								}
								tp.setUrl(bu.toString());
								list.add(tp);
							}
						}
					}

				} catch (Exception e) {
					iLog.error(e.toString());
				}

			}
		}
		if (list.size() < 4) {
			int size =list.size();
			for (int i = 0; i <4 - size; i++) {
				TpObject tp = new TpObject();
				tp.setUrl("");
				list.add(tp);
			}
		}
		return list;
	}
}
