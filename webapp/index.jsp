<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="java.util.*"%>
<%@page import="com.liveyc.mina.model.*"%>
<%@page import="com.liveyc.configuration.PropsValues"%>
<%@page import="com.liveyc.util.*"%>
<%@page import="com.liveyc.mina.*"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<%
    ClientMsgProtocol.getInstances().clientStart();
    Thread.sleep(5000);
	ReqRealLocation r = new ReqRealLocation();
	r.setMessageSn(1234l);
	String PLATETYPE = "01";
	String PLATENO = "皖B18678";
	String ALARMTIME = "";
	String WFDD = "340204557";
	String WFXW = "1301";
	String record = "80";
	String speedMax = "";
	String alarmTimeStr = "20150529095441";
	String ALARMID = "123";
	String url = "ftp://liyuan@10.125.146.40/vio/liyuan/340204557/20150529/123_1.jpg;ftp://10.125.146.40/vio/liyuan/340204557/20150529/123_2.jpg;ftp://10.125.146.40/vio/liyuan/340204557/20150529/123_3.jpg";
	if (record == null || record.equals("") || record.equals("null")) {
		record = "0";
	}
	if (speedMax == null || speedMax.equals("") || speedMax.equals("null")) {
		speedMax = "0";
	}
	String picurl = "";

	if (url != null && !url.equals("")) {
		String[] urls = url.split(";");
		if (urls.length == 1) {
			String path = PropsValues.FTP_URL + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8);
			String fileName = ALARMID + ".jpg";
			picurl = picurl + "/" + ALARMID + ".jpg";
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
				String fileName = ALARMID + "_" + j + ".jpg";
				// picurl = path + "/" +
				// p.get("ALARMID") + "_" + j +
				// ".jpg";
				String path = PropsValues.FTP_PATH  + WFDD + "/" + (alarmTimeStr.replaceAll("-", "")).substring(0, 8) + "/";
				System.out.println(path );
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
		r.setMessageSn(Long.parseLong(ALARMID + ""));
		r.setMessageType(0x2101);
		r.setPicNumber((byte) url.split(";").length);
		r.setPicUrl(url);
		r.setSendTime("20150602125050");
		r.setPlateNo(PLATENO);
		r.setPlatype(PLATETYPE);
		r.setValue(Long.parseLong(record));
		r.setStandard(Long.parseLong(speedMax));
		r.setAction(WFXW);
		r.setVlength(106 + url.getBytes().length);
		r.setDataLength(115 + url.getBytes().length);
		// ClientMsgProtocol.getInstances().clientStart();
		if (ClientMsgProtocol.getInstances().isIsconenected()) {
			ClientMsgProtocol.getInstances().getConnFuture().getSession().write(r);
		}
	}
%>

<body>

	<br>
</body>
</html>
