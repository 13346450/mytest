package com.dnake.tasks.lhApi;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dnake.tasks.lhApi.bean.AppendFeedbackForm;
import com.dnake.tasks.lhApi.bean.CreateFeedbackForm;
import com.dnake.tasks.lhApi.bean.GetCommunityList;
import com.dnake.tasks.lhApi.bean.GetFeedbackDetails;
import com.dnake.tasks.lhApi.bean.GetNotice;
import com.dnake.tasks.lhApi.bean.GetNoticeList;
import com.dnake.tasks.lhApi.bean.GetUserAdvices;
import com.dnake.tasks.lhApi.bean.GetUserCodes;
import com.dnake.tasks.lhApi.bean.GetUserFeedback;

/**
 * 数字物业云平台对外接口
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月3日 下午3:53:41
 * @version 1.0
 */
public class LhApi {
	public static final String URL_BASE = "http://app.pmsaas.net/Api_Sqt_v3/";
	/**
	 * URL:绑定手机号(绑定用户号)
	 */
	public static final String URL_1_BINDING = URL_BASE + "Binding";
	/**
	 * URL:返回绑定的用户号列表
	 */
	public static final String URL_3_GET_USER_CODES = URL_BASE + "GetUserCodes";
	/**
	 * URL:绑定小区
	 */
	public static final String URL_4_BINDING_COMMUNITY = URL_BASE + "BindingCommunity";
	/**
	 * URL:返回小区列表(搜索小区)
	 */
	public static final String URL_6_GET_COMMUNITY_LIST = URL_BASE + "GetCommunityList";
	/**
	 * URL:返回通知公告列表
	 */
	public static final String URL_8_GET_NOTICE_LIST = URL_BASE + "GetNoticeList";
	/**
	 * URL:返回通知公告明细
	 */
	public static final String URL_9_GET_NOTICE = URL_BASE + "GetNotice";
	/**
	 * URL:发布故障申告或者咨询建议
	 */
	public static final String URL_13_CREATE_FEEDBACK = URL_BASE + "CreateFeedback";
	/**
	 * URL:发布故障申告或者咨询建议
	 */
	public static final String URL_14_APPEND_FEEDBACK = URL_BASE + "AppendFeedback";
	/**
	 * URL:返回用户提交的故障申告列表
	 */
	public static final String URL_15_GET_USER_FEEDBACK = URL_BASE + "GetUserFeedback";
	/**
	 * URL:返回故障申告帖子明细
	 */
	public static final String URL_16_GET_FEEDBACK_DETAILS = URL_BASE + "GetFeedbackDetails";
	/**
	 * URL:返回用户提交的咨询建议列表
	 */
	public static final String URL_17_GET_USER_ADVICES = URL_BASE + "GetUserAdvices";
	/**
	 * 返回小区列表(搜索小区)
	 */
	public static void getCommunityList(String phoneNumber,LhListItemHandler<GetCommunityList> handler) {
		new LhApiConnection(URL_6_GET_COMMUNITY_LIST)
		.data("phoneNumber", phoneNumber,"pageSize", 10)
		.doPage(GetCommunityList.class,handler);
	}
	/**
	 * 返回通知公告列表
	 */
	public static void getNoticeList(String communityCode,LhListItemHandler<GetNoticeList> handler) {
		new LhApiConnection(URL_8_GET_NOTICE_LIST)
		.data("communityCode", communityCode,"type",1,"Index",1,"pageSize", 10)
		.doPage(GetNoticeList.class,handler);
	}
	/**
	 * 返回通知公告明细
	 */
	public static GetNotice getNotice(String id) {
		LhApiConnection connection = new LhApiConnection(URL_9_GET_NOTICE);
		connection.data("ID",id);
		LhListResult<GetNotice> result = connection.getListResult(GetNotice.class);
		return result.getData().get(0);
		
	}
	/**
	 * 返回用户提交的故障申告列表
	 */
	public static void getUserFeedback(String userName,LhListItemHandler<GetUserFeedback> handler) {
		new LhApiConnection(URL_15_GET_USER_FEEDBACK)
		.data("userID", userName,"type","故障申告","Index",1,"pageSize", 10)
		.doPage(GetUserFeedback.class,handler);
	}
	/**
	 * 返回通知公告明细
	 * @param lhListItemHandler 
	 */
	public static void getFeedbackDetails(String id, LhListItemHandler<GetFeedbackDetails> handler) {
		new LhApiConnection(URL_16_GET_FEEDBACK_DETAILS)
		.data("id", id,"Index",1,"pageSize",10)
		.doPage(GetFeedbackDetails.class,handler);
	}
	/**
	 * 返回绑定的用户号列表
	 */
	public static List<GetUserCodes> getUserCodes(String phone) {
		LhApiConnection connection = new LhApiConnection(URL_3_GET_USER_CODES);
		connection.data("phoneNumber",phone);
		LhListResult<GetUserCodes> result = connection.getListResult(GetUserCodes.class);
		return result.getData();
		
	}
	/**
	 * 绑定小区(关注小区)
	 */
	public static boolean bindingCommunity(String phone,String code) {
		LhApiConnection connection = new LhApiConnection(URL_4_BINDING_COMMUNITY);
//		name	是	string	绑定的参数值(可能是用户号、二维码唯一值、小区代码)
//		type	是	int	1、用户号绑定2、二维码绑定、3小区代码绑定
//		phoneNumber	是	string	手机号码
//		flag	是	string	1为默认小区0为普通小区
		connection.data("name",code);
		connection.data("type",3);
		connection.data("phoneNumber",phone);
		connection.data("flag",1);
		LhListResult<String> result = connection.getListResult(String.class);
		return result.getInfo().getCode()==1;
		
	}
	
	/**
	 * 发布故障申告或者咨询建议
	 */
	public static String createFeedback(CreateFeedbackForm form) {
		System.out.println("createFeedback："+JSON.toJSON(form));
		LhApiConnection connection = new LhApiConnection(URL_13_CREATE_FEEDBACK);
		connection.doPost();
		connection.data(form);
		LhListResult<JSONObject> result = connection.getListResult(JSONObject.class);
		return result.getData().get(0).getString("ID");
		
	}
	/**
	 * 绑定手机号
	 */
	public static boolean binding(String phoneNumber,String userCode,String machineKey,String uuid) {
		LhApiConnection connection = new LhApiConnection(URL_1_BINDING);
//		phoneNumber	是	string	手机号码(自动获取,不能由用户输入绑定)
//		userCode	是	string	用户号
//		machineKey	是	string	机器码
//		uuid	是	string	通用唯一识别码
		connection.data("phoneNumber",phoneNumber);
		connection.data("userCode",userCode);
		connection.data("machineKey",machineKey);
		connection.data("uuid",uuid);
		LhListResult<String> result = connection.getListResult(String.class);
		return result.getInfo().getCode()==1;
	}
	/**
	 * 返回用户提交的咨询建议列表
	 */
	public static void getUserAdvices(String userID,LhListItemHandler<GetUserAdvices> handler) {
		new LhApiConnection(URL_17_GET_USER_ADVICES)
		.data("userID", userID,"Index",1,"pageSize",10,"type","故障申告")
		.doPage(GetUserAdvices.class,handler);
	}
	/**
	 * 返回用户提交的咨询建议列表
	 */
	public static String appendFeedback(AppendFeedbackForm form) {
		System.out.println("appendFeedback："+JSON.toJSON(form));
		LhApiConnection connection = new LhApiConnection(URL_14_APPEND_FEEDBACK);
		connection.doPost();
		connection.data(form);
		LhListResult<String> result = connection.getListResult(String.class);
		System.out.println(JSON.toJSON(result));
		return result.getInfo().getName();
	}
}
