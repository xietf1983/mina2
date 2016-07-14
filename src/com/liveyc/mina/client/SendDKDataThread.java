package com.liveyc.mina.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.liveyc.configuration.PropsKeys;
import com.liveyc.configuration.PropsUtil;
import com.liveyc.configuration.PropsValues;
import com.liveyc.data.service.GaVehViolationServiceUtil;
import com.liveyc.mina.ClientMsgProtocol;
import com.liveyc.mina.client.model.Model103Req;
import com.liveyc.mina.client.model.PassVehicleModel;
import com.liveyc.mina.client.model.TpObject;
import com.liveyc.mina.client.service.DKServiceUtil;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.util.DateUtil;
import com.liveyc.util.FtpUtil;
import com.liveyc.util.MssProxy;
import com.lytx.webservice.datacopylast.model.DatacopyLast;
import com.lytx.webservice.datacopylast.service.DatacopylastServiceUtil;

public class SendDKDataThread extends Thread {
	private static Logger iLog = Logger.getLogger(SendDKDataThread.class);
	public static long msgsn = 0;
	public static long rows = 200;
	public static long trackId = 0;
	public static long longrows = 200;
	public static long trytimes = 0;
	public String domainId = "senddk";

	public void run() {
		try {
			trackId = DKServiceUtil.getService().getStartMaxVehicleTrackId();
			DatacopyLast datacopyLast = DatacopylastServiceUtil.getDatacopyLastByDomainId(domainId);
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
				failLast.setSendType(5);
				DKServiceUtil.getService().insertFailDataLast(failLast);

			}

		} catch (Exception ex) {
			iLog.error("插入FailDataLast失败" + ex.toString());
		}
		// 发送实时数据
		while (true) {
			try {
				iLog.error("当前trackid值" + trackId);
				if (trackId > 0) {
					List<PassVehicleModel> list = DKServiceUtil.getService().getVehicleAlarmByStartId(trackId, longrows, 5);
					if (list != null && list.size() > 0) {
						if ((list.size() + trackId) == list.get(list.size() - 1).getTrackId() || trytimes > 5 || DKServiceUtil.getService().getStartMaxVehicleTrackId() > (trackId + 5000)) {
							trytimes = 0;
							// 发送数据
							trackId = list.get(list.size() - 1).getTrackId();
							if (DKMsgProtocol.getInstances().isIsconenected()) {
								DatacopyLast datacopyLast = DatacopylastServiceUtil.getDatacopyLastByDomainId(domainId);
								if (datacopyLast == null) {
									// 获取当前值
									if (trackId > 0) {
										datacopyLast = new DatacopyLast();
										datacopyLast.setLastseq(trackId);
										datacopyLast.setDomainId(domainId);
										datacopyLast.setAction("copy" + domainId);
										DatacopylastServiceUtil.insertDatacopyLast(datacopyLast);
									}

								} else {
									datacopyLast.setLastseq(trackId);
									DatacopylastServiceUtil.updateDatacopyLast(datacopyLast);
								}
								sendData(list);
							} else {

							}
						} else {
							trytimes = trytimes + 1;
							list = DKServiceUtil.getService().getVehicleAlarmByStartId(trackId, rows, 5);
							if (list != null && list.size() > 0) {
								if ((list.size() + trackId) == list.get(list.size() - 1).getTrackId() || trytimes > 5 || DKServiceUtil.getService().getStartMaxVehicleTrackId() > (trackId + 5000)) {
									trytimes = 0;
									// 发送数据
									trackId = list.get(list.size() - 1).getTrackId();
									if (DKMsgProtocol.getInstances().isIsconenected()) {
										DatacopyLast datacopyLast = DatacopylastServiceUtil.getDatacopyLastByDomainId(domainId);
										if (datacopyLast == null) {
											// 获取当前值
											if (trackId > 0) {
												datacopyLast = new DatacopyLast();
												datacopyLast.setLastseq(trackId);
												datacopyLast.setDomainId(domainId);
												datacopyLast.setAction("copy" + domainId);
												DatacopylastServiceUtil.insertDatacopyLast(datacopyLast);
											}

										} else {
											datacopyLast.setLastseq(trackId);
											DatacopylastServiceUtil.updateDatacopyLast(datacopyLast);
										}
										sendData(list);
									} else {

									}
								}
							}
						}
					} else {
						long value = DKServiceUtil.getService().getMinVehicleAlarmTrackId(trackId);
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
				// p.setCjbh("020000000000001");
				try {
					if (p.getCjbh() != null) {
						if (p.getCjbh().length() < 15) {
							StringBuffer buffer = new StringBuffer();
							for (int j = 0; j < 15 - p.getCjbh().length(); j++) {
								buffer.append(" ");
							}
							p.setCjbh(buffer.toString() + p.getCjbh());
						}
						Model103Req req = new Model103Req();
						req.setBt((byte) 0x02);
						req.setBw((byte) 0x03);
						req.setCblx("99");
						req.setCcm("00");
						req.setCcyy("00");
						req.setCjbh(p.getCjbh());
						if (p.getPlateNo() == null || p.getPlateNo().equals("") || p.getPlateNo().equals("00000000") || p.getPlateNo().length() < 7) {
							req.setClsd("999");
						} else {
							if (p.getPlateNo().substring(0, 2).getBytes().length < 3) {
								p.setPlateNo(" " + p.getPlateNo().substring(0, 2));
							} else {
								req.setClsd(p.getPlateNo().substring(0, 2));
							}
						}
						req.setCpzbx("99999");
						req.setCpzby("99999");
						if (p.getVehicleColor() != null) {
							if (p.getVehicleColor().indexOf("白") > -1) {
								req.setCsys("A");

							} else if (p.getVehicleColor().indexOf("灰") > -1) {
								req.setCsys("B");

							} else if (p.getVehicleColor().indexOf("白") > -1) {
								req.setCsys("A");

							} else if (p.getVehicleColor().indexOf("黄") > -1) {
								req.setCsys("C");

							} else if (p.getVehicleColor().indexOf("粉") > -1) {
								req.setCsys("D");

							} else if (p.getVehicleColor().indexOf("红") > -1) {
								req.setCsys("E");

							} else if (p.getVehicleColor().indexOf("紫") > -1) {
								req.setCsys("F");

							} else if (p.getVehicleColor().indexOf("绿") > -1) {
								req.setCsys("G");

							} else if (p.getVehicleColor().indexOf("蓝") > -1) {
								req.setCsys("H");

							} else if (p.getVehicleColor().indexOf("棕") > -1) {
								req.setCsys("I");

							} else if (p.getVehicleColor().indexOf("黑") > -1) {
								req.setCsys("J");

							} else {
								req.setCsys("Z");
							}
						} else {
							req.setCsys("Z");
						}
						req.setCx("9");
						if (p.getPlateType() != null) {
							if (p.getPlateType().equals("小型汽车")) {
								req.setCx("1");
							} else if (p.getPlateType().equals("大型汽车")) {
								req.setCx("3");
							}
						}
						req.setXs("000");
						req.setHdsj("000000");
						if (p.getPlateNo() == null || p.getPlateNo().equals("") || p.getPlateNo().equals("00000000") || p.getPlateNo().length() < 7) {
							req.setClsd("999");
						} else {
							if (p.getPlateNo().substring(0, 2).getBytes().length < 3) {
								p.setPlateNo(" " + p.getPlateNo().substring(0, 2));
							} else {
								req.setClsd(p.getPlateNo().substring(0, 2));
							}
						}
						if (p.getPlateNo() == null || p.getPlateNo().length() < 7 || p.getPlateNo().equals("00000000")) {
							req.setHphm("       00000000");
						} else {
							if (p.getPlateNo().getBytes().length < 15) {
								StringBuffer buffer = new StringBuffer();
								for (int j = 0; j < 15 - p.getPlateNo().getBytes().length; j++) {
									buffer.append(" ");
								}
								req.setHphm(buffer.toString() + p.getPlateNo());
							} else {
								// req.setHphm(new String());
							}

						}
						req.setHpjg("1");
						if (p.getPlateNo() != null && p.getPlateNo().indexOf("警") > -1) {
							req.setHpjg("3");
						} else if (p.getPlateType() != null && p.getPlateType().equals("小型汽车")) {

						} else {
							req.setHpjg("5");
						}
						req.setHpsl("1");
						if (p.getPlateNo() == null || p.getPlateNo().length() < 7 || p.getPlateNo().equals("00000000")) {
							req.setHpsl("0");
						} else {

						}
						req.setJgsj(p.getAlarmTimeStr());
						req.setLxfl("");
						req.setLxsl("0");
						req.setRzbh(PropsUtil.get(PropsKeys.DK_RZBH));
						req.setSbsj("0000000010");
						if (p.getRecordedSpeed() != null && p.getRecordedSpeed() < 250) {
							String str = p.getRecordedSpeed() + "";
							StringBuffer bu = new StringBuffer();
							if (str.length() < 3) {

								for (int j = 0; j < 3 - str.length(); j++) {
									bu.append("0");
								}
							}
							bu.append(str);
							req.setSpd(bu.toString());
						} else {
							req.setSpd("255");
						}
						String cdh = "01";
						if (p.getVehicleChannel() != null && !p.getVehicleChannel().equals("")) {
							try {
								if (Integer.parseInt(p.getVehicleChannel()) <= p.getCdsl()) {
									p.setCdh(Integer.parseInt(p.getVehicleChannel()));
								}
							} catch (Exception ex) {

							}
						}
						if (p.getCdh() != null && p.getCdh() > 0) {
							if (p.getCdh() < 10) {
								cdh = "0" + p.getCdh();
							} else if (p.getCdh() < 100 && p.getCdh() >= 10) {
								cdh = cdh + "";
							}
						}
						String str = "1";
						if (p.getFxbh()!= null && !p.getFxbh().equals("") && p.getFxbh().length() == 1) {
							str = p.getFxbh();
						}
						req.setTpid(p.getCjbh() + str + cdh + (p.getAlarmTimeStr().replaceAll(":", "").replaceAll(" ", "").replaceAll("-", "") + "000"));

						req.setTpList(getPhotourl(p));
						req.setTpsl(CRC32Util.getFixNumString(req.getTpList().size() + "", 1));
						if(p.getUpload()!=null && p.getUpload().equals("2") && p.getViolationCode()!=null){
							req.setWflx(CRC32Util.getFixString(p.getViolationCode(), 5));
							iLog.error("违法代码violationCode：" + p.getViolationCode());
							iLog.error("trackId："+p.getTrackId()+"上传违法");
						}else {
							req.setWflx("00000");
						}
						req.setXs("000");
						req.setXyh("103");
						req.setHpys("99");
						if (p.getPlateColor() != null) {
							if (p.getPlateColor().indexOf("白") > -1) {
								req.setHpys("00");
							}
							else  if (p.getPlateColor().indexOf("黄") > -1) {
								req.setHpys("01");
							}
							else if (p.getPlateColor().indexOf("蓝") > -1) {
								req.setHpys("02");
							}
							else if (p.getPlateColor().indexOf("黑") > -1) {
								req.setHpys("03");
							}else{
								req.setHpys("04");
							}
						} else {

						}
						//iLog.error("setTpid="+req.getTpid() +"   setHpys="+req.getHpys()+"hpyz"+p.getPlateColor() +"tPlateColor" +"VehicleChannel"+p.getVehicleChannel()+"p.getCdsl()"+p.getCdsl());
						int lenth = 0;
						if (req.getTpList() != null) {
							for (TpObject tp : req.getTpList()) {
								lenth = lenth + 256 + 7 + 2;
							}
						}
						// lenth = lenth + 146;
						req.setPackageLength(lenth + 173 + 1);
						String strlenth = (lenth + 173 + 1 - 27) + "";
						StringBuffer bu = new StringBuffer();
						if (strlenth.length() < 7) {

							for (int j = 0; j < 7 - strlenth.length(); j++) {
								bu.append("0");
							}
						}
						bu.append(strlenth);
						req.setSjcd(bu.toString());
						DKMsgProtocol.getInstances().getConnFuture().getSession().write(req);
					}

				} catch (Exception ex) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					ex.printStackTrace(pw);
					iLog.error("LostPassVehicleThread" + sw.toString());
				}
			}
		}

	}

	public byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}

	public List<TpObject> getPhotourl(PassVehicleModel p) {
		List<TpObject> list = new ArrayList();
		if (p.getCenterStoreKey() != null && !p.getCenterStoreKey().equals("")) {
			if (p.getCenterStoreKey().toUpperCase().indexOf("HTTP") > -1 || p.getCenterStoreKey().toUpperCase().indexOf("FTP") > -1) {
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
							if (temp[j].getBytes("gb2312").length > 256) {
								temp[j] = new String(subBytes(temp[j].getBytes("gb2312"), 0, 256));
								bu.append(temp[j]);

							} else {
								for (int k = 0; k < 256 - temp[j].getBytes("gb2312").length; k++) {
									bu.append(" ");
								}
								bu.append(temp[j]);
							}
						} catch (Exception ex) {

						}
						if (bu.toString().length() == 256) {
							tp.setUrl(bu.toString());
							list.add(tp);
						}
					}
				}

			} else {
				StringBuffer buffer = new StringBuffer();
				try {
					iLog.error("SSU 查询图片信息");
					// iLog.error("queryAlarmPicUrls参数： fdId:" +
					// map.get("FDID").toString() + " channelType:" + 1 +
					// " channelId:" +
					// Short.parseShort(String.valueOf(map.get("CHANNELID"))) +
					// " strAlarmGuid:" + map.get("CENTERSTOREKEY").toString() +
					// ",strStorageAreaId:" +
					// map.get("STORAGEAREAID").toString() + " alarmTime:" +
					// map.get("ALARMTIMESTR"));
					String[] pics = MssProxy.getInstance().queryAlarmPicUrls(p.getFdId(), (short) 1, p.getChannelId().shortValue(), p.getCenterStoreKey(), p.getStorageAreaId(), p.getAlarmTime(), null);
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
									if (pics[j].getBytes("gb2312").length > 256) {
										pics[j] = new String(subBytes(pics[j].getBytes("gb2312"), 0, 256));
										bu.append(pics[j]);

									} else {
										for (int k = 0; k < 256 - pics[j].getBytes("gb2312").length; k++) {
											bu.append(" ");
										}
										bu.append(pics[j]);
									}
								} catch (Exception ex) {

								}
								if (bu.toString().length() == 256) {
									tp.setUrl(bu.toString());
									list.add(tp);
								}

							}
						}
					}

				} catch (Exception e) {
					iLog.error(e.toString());
				}

			}
		}
		return list;
	}
}
