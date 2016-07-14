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
	 * 字符串替换，将 source 中的 oldString 全部换成 newString
	 * 
	 * @param source
	 *            源字符串
	 * @param oldString
	 *            老的字符串
	 * @param newString
	 *            新的字符串
	 * @return 替换后的字符串
	 * @deprecated jdk1.5以后String已经有此方法
	 */
	public static String replace(String source, String oldString, String newString) {
		StringBuffer output = new StringBuffer();

		int lengthOfSource = source.length(); // 源字符串长度
		int lengthOfOld = oldString.length(); // 老字符串长度

		int posStart = 0; // 开始搜索位置
		int pos; // 搜索到老字符串的位置

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
	 * 将字符串格式化成 javascript字符串 输出
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 格式化后的字符串
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
	 * 将字符串格式化成 HTML 中的表单区域的值输出
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 格式化后的字符串
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
	 * 将字符串格式化成 HTML 代码输出 除普通特殊字符外，还对空格、制表符和换行进行转换， 以将内容格式化输出， 适合于 HTML 中的显示输出
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 格式化后的字符串
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
	 * 将普通字符串格式化成数据库认可的字符串格式
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 合法的数据库字符串
	 */
	public static String toSql(String str) {
		String sql = new String(str);
		return replace(sql, "'", "''");
	}

	/**
	 * 将录入字符串转为数据库可正常储存的字符串
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 数据库可正常储存的字符串
	 */
	public static String toDbString(String str) {
		return toCharset(str, "iso-8859-1");
	}

	/**
	 * 从默认字符集转到某字符集
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
	 * 将从数据库中取出的字符串转为页面上可正常显示的字符串
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 页面上可正常显示的字符串
	 */

	public static String toPageString(String str) {
		return fromCharset(str, "iso-8859-1");
	}

	/**
	 * 从某字符集转到默认字符集
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
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
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
