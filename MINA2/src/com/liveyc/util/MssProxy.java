package com.liveyc.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ice.ICECommunicatorHolder;
import com.ice.exception.NanwangException;

import GeMss.MssWebPrx;
import GeMss.MssWebPrxHelper;
import GeMss.TFdInfoKey;
import GeMss.TUrlAddressSeqHolder;

public class MssProxy {
	static private Logger iLog = Logger.getLogger(MssProxy.class);
	private static MssProxy inst = new MssProxy();

	private ConcurrentHashMap<String, MssWebPrx> prxContainer = new ConcurrentHashMap<String, MssWebPrx>();

	private MssProxy() {
	};

	public static MssProxy getInstance() {
		return inst;
	};

	private MssWebPrx getMssWebPrx(String domainId) {
		MssWebPrx prx = null;
		prx = (MssWebPrx) prxContainer.get(domainId);
		if (prx == null) {
			try {
				String proxyProperty = "MssWeb" + domainId + ".Proxy";
				Ice.Properties properties = ICECommunicatorHolder.getInstance().getICECommunicator().getProperties();
				String proxyString = properties.getProperty(proxyProperty);
				if (proxyString.length() == 0) {
					proxyProperty = "MssWeb.Proxy";
					proxyString = properties.getProperty(proxyProperty);
				}
				if (proxyString.length() == 0) {
					iLog.error("创建ice代理失败,property '" + proxyProperty + "' not set");
				}
				Ice.ObjectPrx base = ICECommunicatorHolder.getInstance().getICECommunicator().stringToProxy(proxyString);
				if (base == null) {
					iLog.error(proxyString + " 创建ice代理失败");
				}
				// 5秒超时
				prx = MssWebPrxHelper.checkedCast(base.ice_twoway().ice_timeout(5000));
				if (prx == null) {
					iLog.error(proxyString + " 创建ice代理失败");
				}
			} catch (Exception e) {
				iLog.error("mss服务器连接失败", e);
			}
		}
		return prx;
	}

	public String[] queryAlarmPicUrls(String fdId, short channelType, short channelId, String strAlarmGuid, String strStorageAreaId, Date alarmTime, HttpServletRequest request) {
		iLog.info("queryAlarmPicUrls参数： fdId:" + fdId + " channelType:" + channelType + " channelId:" + channelId + " strAlarmGuid:" + strAlarmGuid + ",strStorageAreaId:" + strStorageAreaId + " alarmTime:" + DateUtil.dateToString(alarmTime));
		String[] urls = new String[0];
		boolean bCenter = true;
		TUrlAddressSeqHolder seqUrlAddress = new TUrlAddressSeqHolder();
		TFdInfoKey tFdKey = new TFdInfoKey();
		tFdKey.strFdId = fdId;
		if (fdId.length() > 6) {
			tFdKey.strWorkDomainId = fdId.substring(0, 6);
		} else {
			tFdKey.strWorkDomainId = "";
		}
		tFdKey.nFdChannelId = (channelType << 16) + channelId;
		tFdKey.byQoS = 1;
		// String netWorkDouble =
		// XmlUtils.getInstance().getProperties("netWork_double");
		// String storeType =
		// XmlUtils.getInstance().getProperties("store_type");
		// iLog.info(" 存储使用 : " + storeType);
		String IP = "";
		// IP = request.getRemoteAddr();
		iLog.info("  客户端IP : " + IP);
		getMssWebPrx(tFdKey.strWorkDomainId).QueryAlarmStorageV3(bCenter, tFdKey, strAlarmGuid, alarmTime.getTime() / 1000, IP, strStorageAreaId, seqUrlAddress);
		if (seqUrlAddress.value == null) {
			urls = new String[0];
		} else {
			urls = new String[seqUrlAddress.value.length];
			for (int i = 0; i < urls.length; i++) {
				urls[i] = seqUrlAddress.value[i].strUrl;
			}
		}
		iLog.info(" 返回url大小 : " + urls.length + " urls:" + urls.toString());
		return urls;
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/*
	 * 这个函数的作用是为了css向ssu过渡用的
	 */
	private void queryCssUrl(boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, TUrlAddressSeqHolder seqUrlAddress) {
		if (storageAreaId == null) {
			// try {
			// List list = VehicleDAOUtil.getAllStoreAreaId();
			// for (int i = 0; i < list.size(); i++) {
			// Map map = (Map) list.get(i);
			// iLog.info("queryCssUrl: areaId:" + map.get("AREAID"));
			// getMssWebPrx(tFdKey.strWorkDomainId).QueryAlarmStorageV3(bCenter,
			// tFdKey, strAlarmGuid, nTime, strUserIP,
			// map.get("AREAID").toString(), seqUrlAddress);
			// if (seqUrlAddress.value.length == 0) {
			// continue;
			// } else {
			// break;
			// }
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
	}

}
