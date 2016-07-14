package com.liveyc.scheduling;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.liveyc.configuration.PropsValues;
import com.liveyc.data.service.GaVehViolationServiceUtil;
import com.liveyc.mina.ClientMsgProtocol;
import com.liveyc.mina.model.ReqLinkTestModel;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.util.DateUtil;
import com.liveyc.util.FtpUtil;
import com.liveyc.util.MssProxy;

public class SendDataThread extends Thread {
	private static Logger iLog = Logger.getLogger(SendDataThread.class);
	public static long msgsn = 0;

	public void run() {
		while (true) {
			try {
				// 获取过车记录
				//Thread.sleep(2000);
				iLog.error("我再运行");
				List<HashMap> list = GaVehViolationServiceUtil.getService().getGaVehViolationCheck(null);
				if (list != null && list.size() > 0) {
					for (HashMap p : list) {
						if (p.get("PLATENOWHIT") == null || String.valueOf(p.get("PLATENOWHIT")).equals("0")) {
							String alarmTimeStr = (String) p.get("ALARMTIMESTR");
							if (alarmTimeStr != null) {
								if (((new Date()).getTime() - DateUtil.StringToDate(alarmTimeStr).getTime()) > DateUtil.DAY * 10) {
									GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(String.valueOf(p.get("ALARMID"))));
								} else {
									ReqRealLocation r = new ReqRealLocation();
									r.setMessageSn(Long.parseLong(String.valueOf(p.get("ALARMID"))));
									String PLATETYPE = (String) (p.get("PLATETYPE"));
									String PLATENO = (String) (p.get("PLATENO"));
									String ALARMTIME = (String) (p.get("ALARMTIMESTR"));
									String WFDD = (String) (p.get("DEVICEID"));
									String WFXW = (String) (p.get("VIOLATIONCODE"));
									if (WFXW == null || WFXW.equals("")) {
										WFXW = "00000";
									}
									String record = String.valueOf(p.get("RECORDEDSPEED"));
									String deviceType = String.valueOf(p.get("DEVICE_TYPE") == null ? "2" : p.get("DEVICE_TYPE"));
									String speedMax = String.valueOf(p.get("SPEEDMAX"));
									if (record == null || record.equals("") || record.equals("null")) {
										record = "0";
									}
									if (speedMax == null || speedMax.equals("") || speedMax.equals("null")) {
										speedMax = "0";
									}
									String picurl = "";
									if (PLATENO == null || WFDD == null) {
										GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(String.valueOf(p.get("ALARMID"))));
										continue;
									} else {
										String url = getPhotourl(p);
										iLog.error("获取图片地址:" + url);
										if (url != null && !url.equals("")) {
											String[] urls = url.split(";");
											if (urls.length == 1) {
												String fileName = p.get("ALARMID") + ".jpg";
												iLog.error("开始上传FTP文件"+"ftp://" + PropsValues.FTP_URL + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + ".jpg");
												if (FtpUtil.uploadFile2(PropsValues.FTP_URL, Integer.parseInt(PropsValues.FTP_PORT), PropsValues.FTP_USERID, PropsValues.FTP_PWD, WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/", fileName, urls[0])) {

													picurl = "ftp://" + PropsValues.FTP_URL2 + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + ".jpg";
												}
												iLog.error("结束上传上传FTP文件"+"ftp://" + PropsValues.FTP_URL + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + ".jpg");

											} else {
												for (int j = 0; j < urls.length; j++) {
													String fileName = p.get("ALARMID") + "_" + j + ".jpg";
													iLog.error("开始上传FTP文件"+"ftp://" + PropsValues.FTP_URL + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + ".jpg");
													if (FtpUtil.uploadFile2(PropsValues.FTP_URL, Integer.parseInt(PropsValues.FTP_PORT), PropsValues.FTP_USERID, PropsValues.FTP_PWD, WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/", fileName, urls[j])) {
														if (picurl.equals("")) {
															picurl += "ftp://" + PropsValues.FTP_URL2 + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + "_" + j + ".jpg";
														} else {
															picurl += ";" + "ftp://" + PropsValues.FTP_URL2 + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + "_" + j + ".jpg";
														}
													}
													iLog.error("结束上传上传FTP文件"+"ftp://" + PropsValues.FTP_URL + "/" + PropsValues.FTP_PATH + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/" + p.get("ALARMID") + ".jpg");
												}

											}
											// 违法地点
											iLog.error("发送数据picurl"+picurl);
											if (picurl != null && !picurl.equals("")) {
												r.setDeviceId(WFDD);
												r.setDeviceType((byte) Byte.parseByte(deviceType));
												r.setId(String.valueOf(System.currentTimeMillis()) + String.valueOf(System.currentTimeMillis()).substring(6, 13));
												r.setDatetime(alarmTimeStr.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
												r.setMessageSn(Long.parseLong(p.get("ALARMID") + ""));
												r.setMessageType(PropsValues.REAL_LOCATION_REQ);
												r.setPicNumber((byte) picurl.split(";").length);
												r.setPicUrl(picurl);
												r.setSendTime((DateUtil.getCurrentDateHHMMSS().replaceAll("-", "").replace(":", "")).replaceAll(" ", ""));
												r.setPlateNo(PLATENO);
												r.setPlatype(PLATETYPE);
												r.setValue(Long.parseLong(record));
												r.setStandard(Long.parseLong(speedMax));
												r.setAction(WFXW);
												r.setVlength(106 + picurl.getBytes().length);
												r.setDataLength(115 + picurl.getBytes().length);
												if (ClientMsgProtocol.getInstances().isIsconenected()) {
													iLog.error("发送数据");
													ClientMsgProtocol.getInstances().getConnFuture().getSession().write(r);
												}
											}else{
												GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(String.valueOf(p.get("ALARMID"))));
												continue;
											}
										} else {
											GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(String.valueOf(p.get("ALARMID"))));
											continue;
										}

									}

								}
							}

						} else {
							// 白名单直接删除
							GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(String.valueOf(p.get("ALARMID"))));
							continue;
						}

					}
				}
			} catch (Exception ex) {
				iLog.error("传输出错" + ex.toString());
			}
		}
	}

	public String getPhotourl(Map map) {
		String url = "";
		if (map.get("CENTERSTOREKEY") != null && !map.get("CENTERSTOREKEY").toString().equals("")) {
			if (map.get("CENTERSTOREKEY").toString().toUpperCase().indexOf("HTTP") > -1 || map.get("CENTERSTOREKEY").toString().toUpperCase().indexOf("FTP") > -1) {
				String[] temp = map.get("CENTERSTOREKEY").toString().split(";");
				StringBuffer buffer = new StringBuffer();
				for (int j = 0; j < temp.length; j++) {
					// buffer.append(picUrl);
					// buffer.append("/showfile?fileType=picUrlProxy");
					// buffer.append("&xwPicUrl=");
					if (temp[j].indexOf("pf=1") > -1) {

					} else {
						if (buffer.toString().equals("")) {

						} else {
							buffer.append(";");
						}
						buffer.append(temp[j]);
					}
				}
				url = buffer.toString();// StringUtils.getChineseEncoding(buffer.toString());
			} else {
				StringBuffer buffer = new StringBuffer();
				try {
					iLog.error("SSU 查询图片信息");
					iLog.error("queryAlarmPicUrls参数： fdId:" + map.get("FDID").toString() + " channelType:" + 1 + " channelId:" + Short.parseShort(String.valueOf(map.get("CHANNELID"))) + " strAlarmGuid:" + map.get("CENTERSTOREKEY").toString() + ",strStorageAreaId:" + map.get("STORAGEAREAID").toString() + " alarmTime:" + map.get("ALARMTIMESTR"));
					String[] pics = MssProxy.getInstance().queryAlarmPicUrls(map.get("FDID").toString(), (short) 1, Short.parseShort(String.valueOf(map.get("CHANNELID"))), map.get("CENTERSTOREKEY").toString(), map.get("STORAGEAREAID").toString(), DateUtil.StringToDate((map.get("ALARMTIMESTR")).toString()), null);
					if (pics != null && pics.length > 0) {
						for (int j = 0; j < pics.length; j++) {
							// buffer.append(picUrl);
							// buffer.append("/showfile?fileType=picUrlProxy");
							// buffer.append("&xwPicUrl=");
							if (pics[j].indexOf("pf=1") > -1) {

							} else {
								if (buffer.toString().equals("")) {

								} else {
									buffer.append(";");
								}
								buffer.append(pics[j]);

							}
						}
					}

				} catch (Exception e) {
					iLog.error(e.toString());
				}
				url = buffer.toString();// StringUtils.getChineseEncoding(buffer.toString());
				// url = "ftp://10.123.93.14/LHG/vehiclepic/test.jpg";
			}
		}
		iLog.error("上传图片地址=[" + url + "]==");
		return url;
	}

	public boolean getFtpOrHttpPicIsExit(String photoUrl) {
		boolean flag = false;
		URLConnection urlConnection = null;
		InputStream inputStream = null;
		try {
			URL url = new URL(photoUrl);
			urlConnection = url.openConnection();
			urlConnection.setReadTimeout(5000);
			urlConnection.setConnectTimeout(5000);
			inputStream = urlConnection.getInputStream();
			inputStream.close();
			flag = true;
		} catch (Exception e) {
			iLog.error("getFtpOrHttpPicIsAvailable", e);
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					//iLog.error("getFtpOrHttpPicIsAvailable", ex);
					e.printStackTrace();
					return flag;
				}
			}
			return flag;
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					//iLog.error("getFtpOrHttpPicIsAvailable", e);
					e.printStackTrace();
					return flag;
				}
			}
		}
		return flag;
	}
}
