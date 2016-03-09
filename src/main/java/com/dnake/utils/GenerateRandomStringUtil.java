package com.dnake.utils;

import java.util.Date;
import java.util.Random;
/**
 * 产生随机订单号
*  GenerateOrderNumberUtil <br/> 
*  2014年9月17日 下午4:14:59 <br/> 
* @author chen qige 
* @version
 */
public class GenerateRandomStringUtil {
	private static Random random=new Random();
	/**
	 * 产生随机订单
	 * @return
	 *@author cqg 
	 *2015年6月9日
	 */
	public static String getOrderNumber() {
		Date date = new Date();
		StringBuilder sb=new StringBuilder();
		sb.append(DateTimeUtil.formatDateToStr(date, "number"));
		for(int i=0;i<4;i++){
			sb.append((int) (Math.random() * 10));
		}
		return sb.toString();
	}
	/**
	 * 获取验证码
	 * @return
	 *@author cqg 
	 *2015年6月9日
	 */
	public static String getValidateCode(int num){
		String str="";
		 String[] codeSequence = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
					"K", "L", "M", "N",  "P", "Q", "R", "S", "T", "U", "V", "W",
					"X", "Y", "Z",  "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		 for(int i=0;i<num;i++){
			 str+=codeSequence[random.nextInt(codeSequence.length)];
		 }
		return str;
	}
	/**
	 * 获取订单号，统一支付订单号
	 *  @author zgj
	 *	日期：2015年9月22日上午11:43:55
	 *  描述：@return
	 *  格式：yyyymmddHHmmss+6位随机数
	 */
	public static String getAlipayOrderNum()
	{
		String date = DateTimeUtil.formatDateToString(new Date(), "yyyyMMddHHmmss");
		String orderNum = date + Integer.toString((int)((Math.random()*9+1)*100000));
		return orderNum;
	}
	/**
	 *  格式：当天日期[8位]+序列号[3至24位]，如：201008010000001
	 *  @author zgj
	 *	日期：2015年9月21日下午7:10:18
	 *  描述：@return
	 */
	public static String getBatchNo()
	{
		String date = DateTimeUtil.formatDateToString(new Date(), "yyyymmdd");
		String batchNo = date + Integer.toString((int)((Math.random()*9+1)*100000));
		return batchNo;
	}
}
