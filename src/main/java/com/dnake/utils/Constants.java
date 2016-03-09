/**
 * @author Administrator
 *
 */
package com.dnake.utils;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.business.BizApp;

/**
 * 说明：常量类
 * 
 * @创建：作者:tw 创建时间：2013-10-28
 * @修改历史：
 */

final public class Constants {
	
	/***
	 * 根节点id
	 */
	public final static Long BST_SUBSTATION_TREE_ROOT_ID = 0L;
	/**
	 * 行政区域根节点id
	 */
	public final static Long SYS_DEPT_TREE_ROOT_ID = 1L;
	/***
	 * 菜单根节点id
	 */
	public final static Long SYS_MENU_TREE_ROOT_ID = 1L;
	
	/**
	 * 权限管理的角色根节点id
	 * */
	public final static Long SYS_POPEDOM_TREE_ROOT_ID = 0L;
	/**
	 * 目录管理根节点id
	 */
	public final static Long BIZ_CATEGORY_ROOT_ID=1L;
	
	/**
	 * 社区商城节点id
	 */
	public final static Long BIZ_CATEGORY_COMMUNITYMALL_ID = 2L;
	/**
	 * 餐饮美食节点id
	 */
	public final static Long BIZ_CATEGORY_FOODS_ID=3L;
	/**
	 * 休闲健身节点id
	 */
	public final static Long BIZ_CATEGORY_LEISURE_ID=4L;
	/**
	 * 通知公告及节点id
	 */
	public final static Long BIZ_CATEGORY_NOTICE_ID=52L;
	/**
	 * 政务之窗节点id
	 */
	public final static Long BIZ_CATEGORY_GOVEMMENTAFFAIRS_ID=63L;
	/**
	 * 家政服务节点id
	 */
	public final static Long BIZ_CATEGORY_HOUSEKEEPING_ID=67L;
	 /**
	  * 社区论坛节点id
	  */
	public final static Long BIZ_CATEGORY_FORUM_ID=75L;
	/**
	 * 物业服务节点id
	 */
	public final static Long BIZ_CATEGORY_PROPERTYSERVICES_ID=84L;
	
	/**
	 * 功能菜单名
	 */
	public final static String FUNC_MENU_NM_DEPT = "部门（门店）管理";
	public final static String FUNC_MENU_NM_USER = "人员管理";
	public final static String FUNC_MENU_NM_ROLE = "角色管理";
	public final static String FUNC_MENU_NM_POPEDOM = "权限管理";
	public final static String FUNC_MENU_NM_MENU = "菜单管理";
	public final static String FUNC_MENU_NM_PARMS = "参数管理";
	public final static String FUNC_MENU_NM_DICT = "数据字典";
	public final static String FUNC_MENU_NM_USERPWD = "修改密码";
	public final static String FUNC_MENU_NM_REGION = "归属地管理";
	public final static String FUNC_MENU_NM_COMMUNICATION = "通讯参数设置";
	
	public final static String FUNC_MENU_NM_SUBSTATION = "变电站管理";
	public final static String FUNC_MENU_NM_MONITOR = "监测设备管理";
	public final static String FUNC_MENU_NM_STATION = "监测点管理";
	public final static String FUNC_MENU_NM_GPRS = "GPRS参数管理";
	
	public final static String FUNC_MENU_NM_BIZRMIDMINUTE = "分钟统计数据临时表管理";
	public final static String FUNC_MENU_NM_BIZRMIDDAY = "日统计数据临时表管理";
	public final static String FUNC_MENU_NM_BIZRMIDDAYOVER = "日超限事件临时表管理";
	public final static String FUNC_MENU_NM_BIZRMIDMONTH = "月统计数据临时表管理";
	public final static String FUNC_MENU_NM_BIZRMIDSTATION = "站点临时数据";
	public final static String FUNC_MENU_NM_BIZRMIDPOWEROFF = "月来停电临时数据";
	
	public final static String FUNC_MENU_NM_BIZTSTATIONDAY = "日统计数据管理";
	public final static String FUNC_MENU_NM_BIZTSTATIONDAYOVER = "日超限事件管理";
	public final static String FUNC_MENU_NM_BIZTSTATIONMINUTE = "分钟统计数据管理";
	public final static String FUNC_MENU_NM_BIZTSTATIONMONTH = "月统计数据管理";
	public final static String FUNC_MENU_NM_BIZTSTATIONPOWEROFF = "月来停电数据";
	
	/**
	 * 操作功能(Create、Retrieve、Update、Delete、TreeDrag、Others)
	 */
	public final static String FUNC_OPER_NM_CREATE = "Create";		//增
	public final static String FUNC_OPER_NM_RETRIEVE = "Retrieve";	//恢复
	public final static String FUNC_OPER_NM_UPDATE = "Update";		//改
	public final static String FUNC_OPER_NM_DELETE = "Delete";		//删
	public final static String FUNC_OPER_NM_TREEDRAG = "TreeDrag";	//树节点拖拽
	public final static String FUNC_OPER_NM_OTHERS = "Others";		//其它
	
	
	/**
	 * 系统使用的字符集 UTF-8
	 */
	public final static String CHARSET = "UTF-8";

	/**
	 * 上传文件的存放目录
	 */
	public final static String UPLOAD_DIR = "upload_files";
	
	/**
	 * 上传apk的目录
	 */
	public final static String APK_DIR = "apk_files";
	/**
	 * 外链维护上传的图片
	 */
	public final static String CHAIN_DIR = "upload_files/chain_files";
	/**
	 * 默认的起始日期
	 */
	public final static int START_PERIOD = -30;
	
	/**
	 * 接受控制指令的等待次数
	 */
	public final static int RECEIVE_REPEAT_TIMES = 13;
	/**
	 * 商户的roleId
	 */
	public final static int SALES_ROLE_ID=8;
	/**
	 * 普通用户的roleId
	 */
	public final static int USER_ROLE_ID=7;
	/**
	 * 小区物业的roleId
	 */
	public final static int PROPERTY_ROLE_ID = 9;
	/**
	 * 物业管理人员roleid
	 */
	public final static int COMMUNITY_ADMIN_ROLE_ID=6;
	/**
	 * 系统管理员roleid
	 */
	public final static int SYSTEM_ADMIN_ROLE_ID=1;
	/**
	 * 手机消息推送服务器地址
	 */
	public final static String MOBILE_SEND_MESSAGE_URL="http://192.168.12.30:8070/notification.do?action=send";
	/**
	 * 手机端评论图片上传
	 */
	public final static String MOBILE_COMMENT_UPLOAD_FILE = "upload_files/comment_files";
	/**
	 * 手机端物业报修信息上传
	 */
	public final static String MOBILE_REPAIR_UPLOAD_FILE = "upload_files/proper_repair_files";
	/**
	 * 商品图片上传
	 */
	public final static String MOBILE_GOODS_UPLOAD_FILE = "upload_files/goods_files";
	/**
	 * 关于我们中企业荣誉图片
	 */
	public final static String ABOUTUS_CULTURE_FILE = "upload_files/aboutus_cultrue_files";
	/**
	 * 家政信息圖片
	 */
	public final static String HOUSEKEEPING_FILE = "upload_files/housekeeping_files";
	/**
	 * 新闻动态，图片，上传目录
	 */
	public final static String NEWS_DYNAMIC_FILE = "upload_files/new_dynamic_files";
	/**
	 * 用户版app版本控制信息版本号，放在内存里优化
	 */
	public static BizApp CUSTOMER_NEW_VERSION_APP=null;
	/**
	 * 商户版app版本控制信息版本号，放在内存里优化
	 */
	public static BizApp SALES_NEW_VERSION_APP=null;
	/**
	 * 分机版app版本控制信息版本号，放在内存里优化
	 */
	public static BizApp INSIDE_STATION_NEW_VERSION_APP=null;
	/**
	 * 权限表的目录树
	 */
	public static DhtmlxTreeModel POPEDOM_TREE=null;
	/**
	 * 退款回调接口指定URL
	 */
	public static String NOTIFY_URL = "http://211.162.38.19:16000/SmartCommunityWeb/business/biz-pay-callback/alipay-refund";
	/**
	 * 手机端上传退款图片照片
	 */
	public static String MOBILE_RFUND_UPLOAD_FILE = "upload_files/refund_files";
}
