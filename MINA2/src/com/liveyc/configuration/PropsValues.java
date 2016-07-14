package com.liveyc.configuration;

public class PropsValues {
	/** ************************SCATS******************************** */
	public static final String ITS_SERVERIP = PropsUtil.get(PropsKeys.ITS_SERVERIP);
	public static final int ITS_PORT = Integer.parseInt(PropsUtil.get(PropsKeys.ITS_PORT));
	public static final String ITS_PASSWORD = PropsUtil.get(PropsKeys.ITS_PASSWORD);
	public static final String ITS_USERID = PropsUtil.get(PropsKeys.ITS_USERID);
	public static final String ITS_VSERSION = PropsUtil.get(PropsKeys.ITS_VSERSION);
	public static final String FTP_USERID = PropsUtil.get(PropsKeys.FTP_USERID);
	public static final String FTP_PWD = PropsUtil.get(PropsKeys.FTP_PWD);
	public static final String FTP_URL = PropsUtil.get(PropsKeys.FTP_URL);
	public static final String FTP_PORT = PropsUtil.get(PropsKeys.FTP_PORT);
	public static final String FTP_PATH = PropsUtil.get(PropsKeys.FTP_PATH);
	public static final String FTP_URL2 = PropsUtil.get(PropsKeys.FTP_URL2);
	public static final int CONNECT_REQ = 0x2001;
	public static final int CONNECT_RSP = 0x8001;
	public static final int UP_LINKTEST_REQ = 0x2002;
	public static final int DOWN_LINKTEST_RSP = 0x8002;
	public static final int CLOSELINK_INFORM = 0x8003;
	public static final int REAL_LOCATION_REQ = 0x2101;
	public static final int REAL_LOCATION_RESP = 0x8101;
	public static final int HISTORY_LOCATION_REQ = 0x2102;
	public static final int HISTORY_LOCATION_RESP = 0x8102;
	public static final int REAL_RUN_REQ = 0x2103;
	public static final int REAL_RUN_RESP = 0x8103;
	public static final int HISTORY_RUN_REQ = 0x2104;
	public static final int HISTORY_RUN_RESP = 0x8104;
	public static final int ROAD_CODE_REQ = 0x2105;
	public static final int ROAD_CODE_RESP = 0x8105;

	public static final int CROSS_CODE_REQ = 0x2106;
	public static final int CROSS_CODE_RESP = 0x8106;
	public static final int VIO_CODE_REQ = 0x2107;
	public static final int VIO_CODE_ = 0x8107;
	public static final int VIO_FAULTCODE_REQ = 0x2108;
	public static final int VIO_FAULTCODE__RESP = 0x8108;

}
