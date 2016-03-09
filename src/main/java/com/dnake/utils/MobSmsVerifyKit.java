package com.dnake.utils;

public class MobSmsVerifyKit
{

	public static String appkey = "68b249ba97b1";		//appkey
	public static String address = "https://web.sms.mob.com/sms/verify";
	public static String webRequestAddress = "https://web.sms.mob.com/sms/sendmsg";
	public static String webVerifyAddress = "https://web.sms.mob.com/sms/checkcode";
	/**
	 * 随机码验证函数
	 * @param phone	电话号码
	 * @param zone	区号
	 * @param code	4位验证码
	 * @return
	 * @throws Exception
	 */
	public String smsVerify(String phone, String zone, String code) throws Exception{
		MobClient client = null;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			String result = client.post();
			return result;
		} finally {
			client.release();
		}
	}
	/**
	 * 网页端请求发送短信验证码
	 *  @author zgj
	 *	日期：2015年10月14日下午7:14:04
	 *  描述：@param phone
	 *  描述：@return
	 *  描述：@throws Exception
	 */
	public String webRequestSmsPost(String phone)
	{
		MobClient client = null;
		String result = "";
		try {
			client = new MobClient(webRequestAddress);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", "86");
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			result = client.post();
			return result;
		}catch(Exception e){
			return result;
		}finally{
			client.release();
		}
	}
	/**
	 * 网页端校验获得的短信验证码
	 *  @author zgj
	 *	日期：2015年10月14日下午7:19:08
	 *  描述：@param phone 电话
	 *  描述：@param zone  区号86
	 *  描述：@param code  短信验证码
	 *  描述：@return
	 *  描述：@throws Exception
	 */
	public String webRequestSmsVerify(String phone, String zone, String code)
	{
		MobClient client = null;
		String result = "";
		try {
			client = new MobClient(webVerifyAddress);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			result = client.post();
			return result;
		}catch(Exception e){
			return result;
		}
		finally{
			client.release();
		}
	}
}
