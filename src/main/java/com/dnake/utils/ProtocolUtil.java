package com.dnake.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import com.dnake.utils.Constants;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 协议工具类
 * 
 * @ClassName: ProtocolUtil
 * @Description:
 * @author ts
 * @date 2013-11-14 上午08:35
 * 
 */
public class ProtocolUtil {
	/*public static void main(String[] args) throws UnsupportedEncodingException {		
	}*/

	/**
	 * 计算检验和函数
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte checkSum(byte[] bytes) {
		int mySum = 0;
		for (int i = 0; i < bytes.length; i++) {
			mySum += bytes[i];
		}
		mySum = mySum % 256;
		return (byte) mySum;
	}
	
	/**
	 * 时间转换6字节byte数组
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] dateTo6Bytes(Date date) {
		SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyMMddHHmmss");
		try {
			String dateTimeString = bartDateFormat1.format(date);
			return hexStringToBytes(dateTimeString);
		} catch (Exception e) {
			return hexStringToBytes("000101000000");
		}
	}

	/**
	 * 时间转换2字节年月byte数组
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] dateToYearMonthBytes(Date date) {
		SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyMM");
		try {
			String dateTimeString = bartDateFormat1.format(date);
			return hexStringToBytes(dateTimeString);
		} catch (Exception e) {
			return hexStringToBytes("0001");
		}
	}

	/**
	 * 时间转换3字节年月日byte数组
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] dateToYearMonthDayBytes(Date date) {
		SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyMMdd");
		try {
			String dateTimeString = bartDateFormat1.format(date);
			return hexStringToBytes(dateTimeString);
		} catch (Exception e) {
			return hexStringToBytes("000101");
		}
	}

	/**
	 * 时间转换6字节年月日时分秒byte数组
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] dateToyyMMddHHmmssBytes(Date date) {
		SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyMMddHHmmss");
		try {
			String dateTimeString = bartDateFormat1.format(date);
			return hexStringToBytes(dateTimeString);
		} catch (Exception e) {
			return hexStringToBytes("000101000000");
		}
	}
	
	/**
	 * 时间转换5字节年月日时分byte数组
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] dateToyyMMddHHmmBytes(Date date) {
		SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyMMddHHmm");
		try {
			String dateTimeString = bartDateFormat1.format(date);
			return hexStringToBytes(dateTimeString);
		} catch (Exception e) {
			return hexStringToBytes("0001010000");
		}
	}
	

	/**
	 * 6字节byte数组转换成时间
	 * 
	 * @param bytes
	 * @return 使用方法：SimpleDateFormat bartDateFormat = new SimpleDateFormat(
	 *         "yyyy-MM-dd HH:mm:ss"); bartDateFormat.format(date);
	 */
	public static Date bytesToDate(byte[] bytes) {
		String errorTimeString = "2000-01-01 00:00:00";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String time = "20" + byteToHexString(bytes[0]) + "-"
				+ byteToHexString(bytes[1]) + "-" + byteToHexString(bytes[2])
				+ " " + byteToHexString(bytes[3]) + ":"
				+ byteToHexString(bytes[4]) + ":" + byteToHexString(bytes[5]);

		try {
			Date returnDate = bartDateFormat.parse(time);
			return returnDate;
		} catch (Exception e) {
			try {
				return bartDateFormat.parse(errorTimeString);
			} catch (ParseException e1) {
				return new Date();
			}
		}

	}

	/**
	 * 5字节byte数组转换成时间
	 * 
	 * @param bytes
	 * @return 使用方法：SimpleDateFormat bartDateFormat = new SimpleDateFormat(
	 *         "yyyy-MM-dd HH:mm:ss"); bartDateFormat.format(date);
	 */
	public static Date fiveBytesToDate(byte[] bytes) {
		String errorTimeString = "2000-01-01 00:00:00";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String time = "20" + byteToHexString(bytes[0]) + "-"
				+ byteToHexString(bytes[1]) + "-" + byteToHexString(bytes[2])
				+ " " + byteToHexString(bytes[3]) + ":"
				+ byteToHexString(bytes[4]) + ":" + "00";

		try {
			Date returnDate = bartDateFormat.parse(time);
			return returnDate;
		} catch (Exception e) {
			try {
				return bartDateFormat.parse(errorTimeString);
			} catch (ParseException e1) {
				return new Date();
			}
		}

	}

	/**
	 * 3字节byte数组转换成时间
	 * 
	 * @param bytes
	 * @return 使用方法：SimpleDateFormat bartDateFormat = new SimpleDateFormat(
	 *         "yyyy-MM-dd HH:mm:ss"); bartDateFormat.format(date);
	 */
	public static Date threeBytesToDate(byte[] bytes) {
		String errorTimeString = "2000-01-01 00:00:00";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String time = "20" + byteToHexString(bytes[0]) + "-"
				+ byteToHexString(bytes[1]) + "-" + byteToHexString(bytes[2])
				+ " " + "00" + ":" + "00" + ":" + "00";

		try {
			Date returnDate = bartDateFormat.parse(time);
			return returnDate;
		} catch (Exception e) {
			try {
				return bartDateFormat.parse(errorTimeString);
			} catch (ParseException e1) {
				return new Date();
			}
		}
	}

	/**
	 * 2字节byte数组转换成时间
	 * 
	 * @param bytes
	 * @return 使用方法：SimpleDateFormat bartDateFormat = new SimpleDateFormat(
	 *         "yyyy-MM-dd HH:mm:ss"); bartDateFormat.format(date);
	 */
	public static Date twoBytesToDate(byte[] bytes) {
		String errorTimeString = "2000-01-01 00:00:00";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String time = "20" + byteToHexString(bytes[0]) + "-"
				+ byteToHexString(bytes[1]) + "-" + "01" + " " + "00" + ":"
				+ "00" + ":" + "00";

		try {
			Date returnDate = bartDateFormat.parse(time);
			return returnDate;
		} catch (Exception e) {
			try {
				return bartDateFormat.parse(errorTimeString);
			} catch (ParseException e1) {
				return new Date();
			}
		}
	}

	/**
	 * 将long值转成低前高后4个字节(因为int范围不够)
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] lowHighLongTo4Bytes(long n) {
		byte[] b = new byte[8];
		for (int i = 0; i < 8; i++) {
			b[i] = (byte) n;
			n = (n >> 8);
		}
		byte[] tmp = new byte[4];
		tmp[0] = b[0];
		tmp[1] = b[1];
		tmp[2] = b[2];
		tmp[3] = b[3];
		return tmp;
	}

	/**
	 * 将低前高后4个字节转成long值(因为int范围不够)
	 * 
	 * @param b
	 * @return
	 */
	public static long lowHigh4BytesToLong(byte[] bytes) {
		// 都是FFFFFFFF则直接返回0
		String string = bytesToHexString(bytes);
		if (string.equals("FFFFFFFF")) {
			return 0l;
		}

		byte[] newBytes = new byte[4];
		for (int i = 0; i < newBytes.length; i++) {
			newBytes[i] = bytes[3 - i];
		}

		long ivalue = 0;
		for (int i = 0; i < newBytes.length; i++) {
			ivalue <<= 8;
			ivalue += newBytes[i] & 0xFF;
		}
		return ivalue;
	}

	/**
	 * 将long值转成高前低后4个字节
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] highLowLongTo4Bytes(long n) {
		byte[] b = new byte[8];
		for (int i = 0; i < 8; i++) {
			b[i] = (byte) n;
			n = (n >> 8);
		}
		byte[] tmp = new byte[4];
		tmp[0] = b[3];
		tmp[1] = b[2];
		tmp[2] = b[1];
		tmp[3] = b[0];
		return tmp;
	}

	/**
	 * 将高前低后4个字节转成long值(因为int范围不够)
	 * 
	 * @param bytes
	 * @return
	 */
	public static long highLow4BytesToLong(byte[] bytes) {
		// 都是FFFFFFFF则直接返回0
		String string = bytesToHexString(bytes);
		if (string.equals("FFFFFFFF")) {
			return 0l;
		}

		long ivalue = 0;
		for (int i = 0; i < bytes.length; i++) {
			ivalue <<= 8;
			ivalue += bytes[i] & 0xFF;
		}
		return ivalue;
	}

	/**
	 * 将int值转成低前高后2个字节
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] lowHighIntTo2Bytes(int n) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) n;
			n = (n >> 8);
		}
		byte[] tmp = new byte[2];
		tmp[0] = b[0];
		tmp[1] = b[1];
		return tmp;
	}

	/**
	 * 将低前高后2个字节转成int值
	 * 
	 * @param bytes
	 * @return
	 */
	public static int lowHigh2BytesToInt(byte[] bytes) {
		// 都是FFFF则直接返回0
		String string = bytesToHexString(bytes);
		if (string.equals("FFFF")) {
			return 0;
		}

		int ivalue = ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
		return ivalue;
	}

	/**
	 * 将1个字节转成int值
	 * 
	 * @param oneByte
	 * @return
	 */
	public static int oneByteToInt(byte oneByte) {
		int ivalue = (oneByte & 0xFF);
		return ivalue;
	}

	/**
	 * 将int值转成高前低后2个字节
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] highLowIntTo2Bytes(int n) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) n;
			n = (n >> 8);
		}
		byte[] tmp = new byte[2];
		tmp[0] = b[1];
		tmp[1] = b[0];
		return tmp;
	}

	/**
	 * 将高前低后2个字节转成int值
	 * 
	 * @param bytes
	 * @return
	 */
	public static int highLow2BytesToInt(byte[] bytes) {
		// 都是FFFF则直接返回0
		String string = bytesToHexString(bytes);
		if (string.equals("FFFF")) {
			return 0;
		}

		byte[] newBytes = new byte[4];
		newBytes[0] = bytes[1];
		newBytes[1] = bytes[0];

		int ivalue = ((newBytes[1] & 0xFF) << 8) | (newBytes[0] & 0xFF);
		return ivalue;
	}

	/**
	 * 判断字符串是否全部为数字
	 * 
	 * @param string
	 * @return true-都是数字；false-包含非数字
	 */
	public static boolean checkStringDigital(String string) {
		return string.matches("-?\\d+\\.?\\d*");
	}

	/**
	 * BCD码串转换为byte数组 注意：输入的bcd串必须为纯数字，且长度为2的倍数 例如“1234”，不能是“1F”或者“123”
	 * 
	 * @param bcd
	 * @return "1234"输出byte数组0x12 0x34
	 */
	public static byte[] bcdToBytes(String bcd) {
		if (!checkStringDigital(bcd)) {
			return new byte[] { 0 };
		}
		try {
			byte[] b = new byte[bcd.length() / 2];
			int j = 0;
			for (int i = 0; i < bcd.length(); i += 2) {
				b[j] = Byte.parseByte(bcd.substring(i, i + 2), 16);
				j++;
			}
			return b;
		} catch (Exception e) {
			return new byte[] { 0 };
		}
	}

	/**
	 * 将bytes数组转成BCD码字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToBCD(byte[] bytes) {
		return bytesToHexString(bytes);
	}

	/**
	 * 将ASCII码字符串转换成byte数组
	 * 
	 * @param ascii
	 * @return
	 */
	public static byte[] asciiToBytes(String ascii) {
		byte[] tBytes;
		try {
			tBytes = ascii.getBytes("ASCII");
		} catch (UnsupportedEncodingException e) {
			return new byte[] { 0 };
		}
		return tBytes;
	}

	/**
	 * 将byte数组转换成ASCII码字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToAscii(byte[] bytes) {
		return new String(bytes);
	}

	/**
	 * 将float浮点数转换成4字节byte数组
	 * 原理：先将float浮点数转换成2进制位表示的int值，然后将int数据中的4个byte取出，windows系统需要倒序取出
	 * 
	 * @param f
	 *            float数据
	 * @return 生成的byte数组
	 */
	public static byte[] floatToBytes(float f) {
		// 判断f是否为非法值
		if (Float.isNaN(f)) {
			return new byte[] { 0, 0, 0, 0 };
		}
		int n = Float.floatToIntBits(f);
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) n;
			n = (n >> 8);
		}
		return b;
	}

	/**
	 * 将4字节byte数组转换成float浮点数<br>
	 * 原理：先将4字节byte数组转换成2进制位int值，然后将int转换成float值，windows系统需要倒序取出
	 * 
	 * @param bytes
	 *            需要转换的4字节byte数组
	 * @return 生成float浮点数
	 */
	public static float bytesTofloat(byte[] bytes) {
		int ivalue = ((bytes[3] & 0xFF) << 24) | ((bytes[2] & 0xFF) << 16)
				| ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
		float f = Float.intBitsToFloat(ivalue);
		// 判断f是否为非法值
		if (Float.isNaN(f)) {
			return 0.0f;
		}
		return f;
	}

	/**
	 * 字节数组转成十六进制字符串 <br>
	 * 如：byte[]{0x2B, 0x44, 0xEF, 0xD9} -->> "2B44EFD9"
	 * 
	 * @Title: Bytes2HexString
	 * @author ts 2013-1-30
	 * @param @param bArray
	 * @param @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);// 因为byte范围 -128~127
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 单个字节转成十六进制
	 * 
	 * @Title: Byte2HexString
	 * @author ts 2013-6-17
	 * @Description:
	 * @param bArray
	 * @return
	 * @return String
	 * @throws
	 */
	public static String byteToHexString(byte bArray) {
		String sTemp;
		StringBuffer sb = new StringBuffer("");
		sTemp = Integer.toHexString(0xFF & bArray);
		if (sTemp.length() < 2) {
			sb.append(0);
		}
		sb.append(sTemp.toUpperCase());
		return sb.toString();
	}

	
	
	
	/**
	 * 字符串转成一个16进制的字节byte
	 * 
	 * @param string
	 * @return
	 */
	public static byte hexStringToByte(String string) {
		byte[] b = hexStringToBytes(string);
		return b[0];
	}

	/**
	 * 16进制字符串转换成byte数组
	 * 
	 * @param hexstr
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	/**
	 * 字符串补0函数
	 * 
	 * @param str
	 *            需要补0的字符串
	 * @param strLength
	 *            补完0后的总长度
	 * @param bLeft
	 *            是否从左侧开始补0 true-左侧补0；false-右侧补0
	 * @return String 根据总长度补完0后的新字符串
	 */
	public static String padZero(String str, int strLength, boolean bLeft) {
		if (str == null)
			str = "";
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				if (bLeft)
					sb.append("0").append(str);// 左补0
				else
					sb.append(str).append("0");// 右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}

	/**
	 * 字节转二进制(补足8位)
	 * 
	 * @param bArray
	 * @return
	 */
	public static String byteToBinString(byte bArray) {
		return padZero(Integer.toBinaryString(0xFF & bArray), 8, true);
	}

	/**
	 * 二进制转十进制字符串
	 * 
	 * @param bin
	 * @return
	 */
	public static String binToDecString(String bin) {
		int i = Integer.parseInt(bin, 2);
		return Integer.toString(i);
	}

	/**
	 * 二进制转16进制字符串
	* @Title: binaryString2hexString 
	* @author xql  2014-1-20
	* @Description: 
	* @param @param bString
	* @param @return    
	* @return String    
	* @throws
	 */
	public static String binaryString2hexString(String bString) {
		if (bString == null || bString.equals("") || bString.length() % 8 != 0)
			return null;
		StringBuffer tmp = new StringBuffer();
		int iTmp = 0;
		for (int i = 0; i < bString.length(); i += 4) {
			iTmp = 0;
			for (int j = 0; j < 4; j++) {
				iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
			}
			tmp.append(Integer.toHexString(iTmp));
		}
		return tmp.toString().toUpperCase();
	}
	
	
	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 * @param src1
	 * @return
	 */
	private static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将字符串的指定位置替换成新的字符
	 * 
	 * @param str
	 *            原字符串
	 * @param n
	 *            指定要替换的位数(从0开始)
	 * @param newChar
	 *            要替换的字符
	 * @return String 替换后的字符串
	 * @throws Throwable
	 */
	public static String replaceString(String srcString, int n, String newChar) {
		String s1 = "";
		String s2 = "";
		try {
			s1 = srcString.substring(0, n);
			s2 = srcString.substring(n + 1, srcString.length());
		} catch (Exception ex) {
			return srcString;
		}
		return s1 + newChar + s2;
	}

	private static String padRightUser(String source, int length) {
		String retValue = source;
		for (int i = 0; i < length - source.length(); i++) {
			retValue += " ";
		}
		return retValue;
	}

	private static String stringAndString(String strSource1, String strSource2) {
		String result = "";
		String str1;
		String str2;
		for (int i = 0; i < strSource1.length(); i++) {
			str1 = strSource1.substring(i, i + 1);
			str2 = strSource2.substring(i, i + 1);
			if ((str1.equals("1") && str2.equals("1")))
				result += "1";
			else
				result += "0";
		}
		return result;
	}

	private static String leftShift(String str) {
		String value = str.substring(1, 16) + "0";
		return value;
	}

	private static String xor(String strSource1, String strSource2) {
		String result = "";
		String str1;
		String str2;
		for (int i = 0; i < strSource1.length(); i++) {
			str1 = strSource1.substring(i, i + 1);
			str2 = strSource2.substring(i, i + 1);
			if ((str1.equals("0") && str2.equals("1"))
					|| (str1.equals("1") && str2.equals("0")))
				result += "1";
			else
				result += "0";
		}
		return result;
	}

}
