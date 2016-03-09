package com.dnake.utils;

import java.security.MessageDigest;
import java.util.Date;

/**
 * MD5加密工具类
 *  @author zgj
 *	日期：2015年7月3日下午3:00:59
 *  描述：
 */
public class MD5Util {
	public static String to16MD5(String sourceStr) {
		try {
			// 获得MD5摘要算法的 MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(sourceStr.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int tmp = md[i];
				if (tmp < 0)
					tmp += 256;
				if (tmp < 16)
					buf.append("0");
				buf.append(Integer.toHexString(tmp));
			}
			return change(buf.toString().substring(8, 24));// 16位加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String to32MD5(String sourceStr) {
		try {
			// 获得MD5摘要算法的 MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(sourceStr.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int tmp = md[i];
				if (tmp < 0)
					tmp += 256;
				if (tmp < 16)
					buf.append("0");
				buf.append(Integer.toHexString(tmp));
			}
			return change(buf.toString());// 32位加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取
	 * @return
	 */
	public static String getEncrypt()
	{
		Date d = new Date();
		String date = DateTimeUtil.formatDateToStr(d, "yyyy-MM-DD");
		date = date.replace("-", "");
		String parms = "re1256xvba13s1" + date;
		parms = to32MD5(parms);
		String result = "enterpriseID=086215836784&sign=" + parms;
		return result;
	}
	/**
	 * 获取LH接口调用权限的参数值
	 * @return
	 */
	public static String[] getLHEncrypt()
	{
		Date d = new Date();
		String date = DateTimeUtil.formatDateToStr(d, "yyyy-MM-DD");
		date = date.replace("-", "");
		String parms = "re1256xvba13s1" + date;
		parms = to32MD5(parms);
		return new String[]{"enterpriseID","086215836784","sign",parms};
	}

	/**
	 * 大小写转换
	 * @param b
	 * @return
	 */
	public static String change(String b)
	{
		char letters[] = new char[b.length()];
		for (int i = 0; i < b.length(); i++)
		{
			char letter = b.charAt(i);
			if (letter >= 'a' && letter <= 'z')
				letter = (char) (letter - 32);
			letters[i] = letter;
		}
		return new String(letters);
	}
}
