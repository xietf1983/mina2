package com.liveyc.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * company:
 * 
 * @author yangj
 * @version 1.0
 */

public class Format {

	/**
	 * �ַ����滻���� source �е� oldString ȫ������ newString
	 * 
	 * @param source
	 *            Դ�ַ���
	 * @param oldString
	 *            �ϵ��ַ���
	 * @param newString
	 *            �µ��ַ���
	 * @return �滻����ַ���
	 * @deprecated jdk1.5�Ժ�String�Ѿ��д˷���
	 */
	public static String replace(String source, String oldString, String newString) {
		StringBuffer output = new StringBuffer();

		int lengthOfSource = source.length(); // Դ�ַ�������
		int lengthOfOld = oldString.length(); // ���ַ�������

		int posStart = 0; // ��ʼ����λ��
		int pos; // ���������ַ�����λ��

		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));

			output.append(newString);
			posStart = pos + lengthOfOld;
		}

		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}

		return output.toString();
	}

	/**
	 * ���ַ�����ʽ���� javascript�ַ��� ���
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toJavascriptVar(String str) {
		if (str == null) {
			return null;
		}

		String html = replace(str, "\\", "\\\\");

		html = replace(html, "\r", "\\r");
		html = replace(html, "\n", "\\n");
		html = replace(html, "\'", "\\\'");
		html = replace(html, "\"", "\\\"");

		return html;
	}

	/**
	 * ���ַ�����ʽ���� HTML �еı������ֵ���
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toHtmlInput(String str) {
		if (str == null) {
			return null;
		}

		String html = new String(str);

		html = replace(html, "&", "&amp;");
		html = replace(html, "\"", "&quot;");
		html = replace(html, "<", "&lt;");
		html = replace(html, ">", "&gt;");

		return html;
	}

	/**
	 * ���ַ�����ʽ���� HTML ������� ����ͨ�����ַ��⣬���Կո��Ʊ���ͻ��н���ת���� �Խ����ݸ�ʽ������� �ʺ��� HTML �е���ʾ���
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toHtml(String str) {
		if (str == null) {
			return null;
		}

		String html = new String(str);

		html = toHtmlInput(html);
		html = replace(html, "\r\n", "<br>");
		html = replace(html, "\r", "<br>");
		html = replace(html, "\n", "<br>");
		html = replace(html, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		html = replace(html, " ", "&nbsp;");

		return html;
	}

	/**
	 * ����ͨ�ַ�����ʽ�������ݿ��Ͽɵ��ַ�����ʽ
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return �Ϸ������ݿ��ַ���
	 */
	public static String toSql(String str) {
		String sql = new String(str);
		return replace(sql, "'", "''");
	}

	/**
	 * ��¼���ַ���תΪ���ݿ������������ַ���
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return ���ݿ������������ַ���
	 */
	public static String toDbString(String str) {
		return toCharset(str, "iso-8859-1");
	}

	/**
	 * ��Ĭ���ַ���ת��ĳ�ַ���
	 * 
	 * @param str
	 *            String
	 * @param charSet
	 *            String
	 * @return String
	 */
	public static String toCharset(String str, String charSet) {
		if (str == null) {
			return null;
		}
		String newStr;
		try {
			newStr = new String(str.getBytes(), charSet);
		} catch (Exception e) {
			e.printStackTrace();
			newStr = str;
		}
		return newStr;
	}

	/**
	 * �������ݿ���ȡ�����ַ���תΪҳ���Ͽ�������ʾ���ַ���
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return ҳ���Ͽ�������ʾ���ַ���
	 */

	public static String toPageString(String str) {
		return fromCharset(str, "iso-8859-1");
	}

	/**
	 * ��ĳ�ַ���ת��Ĭ���ַ���
	 * 
	 * @param str
	 *            String
	 * @param charSet
	 *            String
	 * @return String
	 */
	public static String fromCharset(String str, String charSet) {
		if (str == null) {
			return null;
		}
		String newStr;
		try {
			newStr = new String(str.getBytes(charSet));
		} catch (Exception e) {
			e.printStackTrace();
			newStr = str;
		}
		return newStr;
	}

	public static String toDateTimeStr(Date time) {
		SimpleDateFormat sfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (time != null) {
			return sfDateTime.format(time);
		}
		return null;
	}

	public static String toDateStr(Date date) {
		SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
		if (date != null) {
			return sfDate.format(date);
		}
		return null;
	}

	public static Timestamp toDateTime(String date) throws ParseException {
		SimpleDateFormat sfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null && date.length() > 0) {
			return new Timestamp(sfDateTime.parse(date).getTime());
		}
		return null;
	}

	public static Date toDateDate(String time) throws ParseException {
		SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
		if (time != null && time.length() > 0) {
			return sfDate.parse(time);
		}
		return new Date();
	}

	public static Timestamp toDate(String time) throws ParseException {
		SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
		if (time != null && time.length() > 0) {
			return new Timestamp(sfDate.parse(time).getTime());
		}
		return null;
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static byte[] MD5(String strSrc) {
		byte[] returnByte = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			returnByte = md5.digest(strSrc.getBytes("GBK"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnByte;
	}
	
	public static byte[] MD55(String s) {
		byte[] md = null;
		try {
			byte[] btInput = s.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			md = mdInst.digest();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return md;
	}
	
	public static void main(String [] args){
		System.out.print(MD5("SDSDU01587"));
		System.out.print(MD55("SDSDU01587"));
	}
}
