import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.liveyc.configuration.PropsValues;
import com.liveyc.data.service.GaVehViolationServiceUtil;
import com.liveyc.mina.ClientMsgProtocol;
import com.liveyc.mina.message.code.MessageCodecFactory;
import com.liveyc.mina.model.ReqRealLocation;
import com.liveyc.mina.server.MinaServerHandler;
import com.liveyc.scheduling.SendDataThread;
import com.liveyc.util.DateUtil;
import com.liveyc.util.FtpUtil;

public class Integrate2 {
	public static void main(String[] args) throws Exception {
		ClientMsgProtocol.getInstances().clientStart();
		while (true) {
			List<HashMap> list = new ArrayList();
			HashMap ppp = new HashMap();
			ppp.put("PLATENOWHIT", "0");
			ppp.put("ALARMTIMESTR", DateUtil.getCurrentDateHHMMSS());
			ppp.put("ALARMID", 99);
			ppp.put("PLATETYPE", "02");
			ppp.put("PLATENO", "浙A12345");
			ppp.put("JJLOCATION", "340200");
			ppp.put("VIOLATIONCODE", "1727A");
			list.add(ppp);
			Thread.sleep(10*10000);
			if (list != null && list.size() > 0) {
				for (HashMap p : list) {
					if (p.get("PLATENOWHIT") == null || p.get("PLATENOWHIT").equals("0")) {
						String alarmTimeStr = (String) p.get("ALARMTIMESTR");
						if (alarmTimeStr != null) {
							if (((new Date()).getTime() - DateUtil.StringToDate(alarmTimeStr).getTime()) > DateUtil.DAY * 10) {
								GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(p.get("ALARMID") + ""));
							} else {
								ReqRealLocation r = new ReqRealLocation();
								r.setMessageSn(Long.parseLong(String.valueOf(p.get("ALARMID"))));
								String PLATETYPE = (String) (p.get("PLATETYPE"));
								String PLATENO = (String) (p.get("PLATENO"));
								String ALARMTIME = (String) (p.get("ALARMTIMESTR"));
								String WFDD = (String) (p.get("JJLOCATION"));
								String WFXW = (String) (p.get("VIOLATIONCODE"));
								String record = String.valueOf(p.get("RECORDEDSPEED"));
								String speedMax = String.valueOf(p.get("SPEEDMAX"));
								if (record == null || record.equals("") || record.equals("null")) {
									record = "0";
								}
								if (speedMax == null || speedMax.equals("") || speedMax.equals("null")) {
									speedMax = "0";
								}
								String picurl = "";
								if (PLATENO == null || WFDD == null) {
									GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(p.get("ALARMID") + ""));
									continue;
								} else {
									String url = "ftp://172.16.65.51/incoming/chenlin/images/1.jpg;ftp://172.16.65.51/incoming/chenlin/images/2.jpg;";
									if (url != null && !url.equals("")) {
										String[] urls = url.split(";");
										if (urls.length == 1) {
											String path = PropsValues.FTP_URL + "/2" + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8);
											String fileName = p.get("ALARMID") + ".jpg";
											picurl = picurl + "/" + p.get("ALARMID") + ".jpg";
											if (FtpUtil.uploadFile2(url, Integer.parseInt(PropsValues.FTP_PORT), PropsValues.FTP_USERID, PropsValues.FTP_PWD, path, fileName, urls[0])) {
												// iLog.error("上传图片成功，上传地址:" +
												// picurl);
											}

										} else {
											for (int j = 1; j <= urls.length; j++) {
												// String path =
												// PropsValues.FTP_URL
												// +"/2"+WFDD+"/"+
												// (alarmTimeStr.replaceAll("-",
												// "")).substring(0, 8);
												String fileName = p.get("ALARMID") + "_" + j + ".jpg";
												// picurl = path + "/" +
												// p.get("ALARMID") + "_" + j +
												// ".jpg";
												String path = PropsValues.FTP_PATH + "2" + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/";
												if (FtpUtil.uploadFile2(PropsValues.FTP_URL, Integer.parseInt(PropsValues.FTP_PORT), PropsValues.FTP_USERID, PropsValues.FTP_PWD, path, fileName, urls[j - 1])) {
													// iLog.error("上传图片成功，上传地址:"
													// +
													// picurl);
												}
												if (picurl.equals("")) {

												} else {
													picurl = ";" + picurl;
												}
											}

										}
										// 违法地点
										r.setDeviceId(WFDD);
										r.setDeviceType((byte) 2);
										r.setId(String.valueOf(System.currentTimeMillis()) + String.valueOf(System.currentTimeMillis()).substring(6, 13));
										r.setDatetime(alarmTimeStr.replaceAll("-", "").trim());
										r.setMessageSn(Long.parseLong(p.get("ALARMID") + ""));
										r.setMessageType(0x2101);
										r.setPicNumber((byte) url.split(";").length);
										r.setPicUrl(url);
										r.setSendTime(DateUtil.getCurrentDateHHMMSS().replace("-", "").trim());
										r.setPlateNo(PLATENO);
										r.setPlatype(PLATETYPE);
										r.setValue(Long.parseLong(record));
										r.setStandard(Long.parseLong(speedMax));
										r.setAction(WFXW);
										r.setVlength(106 + url.getBytes().length);
										r.setDataLength(114 + url.getBytes().length);
										// ClientMsgProtocol.getInstances().clientStart();
										if (ClientMsgProtocol.getInstances().isIsconenected()) {
											ClientMsgProtocol.getInstances().getConnFuture().getSession().write(r);
										}
									} else {
										GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong("ALARMID"));
										continue;
									}

								}

							}
						}

					} else {
						// 白名单直接删除
						GaVehViolationServiceUtil.getService().deletegavehviolationupload(Long.parseLong(String.valueOf(p.get("ALARMID"))));
					}

				}
			}
		}

	}
}
