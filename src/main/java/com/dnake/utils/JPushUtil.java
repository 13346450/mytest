package com.dnake.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnake.common.GlobalLoader;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类 参考地址：http://docs.jpush.io/server/java_sdk/ JPushUtil <br/>
 * 2015年1月21日 下午8:14:32 <br/>
 * 
 * @author chen qige
 * @version
 */
public class JPushUtil {
	protected static final Logger LOG = LoggerFactory
			.getLogger(JPushUtil.class);
	private static final String customerAppKey = "29ca77e54d7bf4b108618051";
	private static final String customerMasterSecret = "5b1202485c9256ddb91fe2a8";
	private static JPushClient customerClient;

	private static final String salesAppKey = "7f8d8346af47ed13d74aa27f";
	private static final String salesMasterSecret = "f199a96aa5b4fab46fac83d4";
	private static JPushClient salesClient;

	/**
	 * 根据别名的set集合发送消息
	 * 
	 * @title sendPushByRegisterIds
	 * @author chen qige
	 * @date 2015年1月22日
	 * @param appType
	 *            customer sales
	 * @param registrationIds
	 * @param notifyString
	 */
	public static void sendPushByRegisterIds(String appType,
			Set<String> registrationIds, String notifyString) {
		Iterator<String> it = registrationIds.iterator();
		while (it.hasNext()) {
			String str = it.next();
			if (str == null) {
				it.remove();
			}
		}
		if (registrationIds.size() == 0) {
			return;
		}
		System.out.println(registrationIds);
		Audience audience = Audience.registrationId(registrationIds);
		JPushClient jpushClient = null;
		if ("customer".equals(appType)) {
			jpushClient = JPushUtil.getCustomerClient();
		} else if ("sales".equals(appType)) {
			jpushClient = JPushUtil.getSalesClient();
		} else {
			LOG.info("JPush: 发送的app类型不对，appType 应为 customer 或者 sales");
			return;
		}

		try {
			PushResult result = jpushClient.sendPush(PushPayload.newBuilder()
					.setPlatform(Platform.all()).setAudience(audience)
					.setNotification(Notification.alert(notifyString)).build());
			LOG.info("JPush: Got result - " + result);
		} catch (APIConnectionException e) {
			// Connection error, should retry later
			e.printStackTrace();
			LOG.error("JPush: Connection error, should retry later", e);
		} catch (APIRequestException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// Should review the error, and fix the request
			LOG.error("JPush: Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
		}
	}

	/**
	 * app用户版推送
	 * 
	 * @title getInstance
	 * @author chen qige
	 * @date 2015年1月22日
	 * @return
	 */
	public static JPushClient getCustomerClient() {
		if (null == customerClient) {
			customerClient = new JPushClient(customerMasterSecret,
					customerAppKey, 3);
		}
		return customerClient;
	}

	/**
	 * app商户版推送
	 * 
	 * @title getSalesClient
	 * @author chen qige
	 * @date 2015年1月22日
	 * @return
	 */
	public static JPushClient getSalesClient() {
		if (null == salesClient) {
			synchronized (JPushUtil.class) {
				salesClient = new JPushClient(salesMasterSecret, salesAppKey, 3);
			}
		}
		return salesClient;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("08101d4ef8a");
		try {
			PushResult result = new JPushClient(salesMasterSecret, salesAppKey,
					3).sendPush(PushPayload
					.newBuilder()
					.setPlatform(Platform.all())
					.setAudience(Audience.registrationId(list))
					.setNotification(
							Notification.alert("测试--from cqg java test "
									+ new Date())).build());
		} catch (APIConnectionException | APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
