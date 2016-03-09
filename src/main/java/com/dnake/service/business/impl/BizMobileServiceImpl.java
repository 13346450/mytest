package com.dnake.service.business.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.MobileParms;
import com.dnake.service.business.BizAdImageService;
import com.dnake.service.business.BizAppService;
import com.dnake.service.business.BizCategoryService;
import com.dnake.service.business.BizChainService;
import com.dnake.service.business.BizCouponService;
import com.dnake.service.business.BizGoodsService;
import com.dnake.service.business.BizMobileService;
import com.dnake.service.business.BizOrderDetailsService;
import com.dnake.service.business.BizOrderService;
import com.dnake.service.business.BizNoteService;
import com.dnake.service.business.BizPromotionGiftService;
import com.dnake.service.business.BizPromotionService;
import com.dnake.service.business.BizProperInfoService;
import com.dnake.service.business.BizPropertiesRepairService;
import com.dnake.service.business.BizRefundService;
import com.dnake.service.business.BizReportFormsService;
import com.dnake.service.business.BizShopService;
import com.dnake.service.business.BizShoppingcartService;
import com.dnake.service.business.BizUinfoCollectService;
import com.dnake.service.business.BizUserGiftService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.common.GetCommunityCodeService;
import com.dnake.service.system.SysCommunityInfoService;
import com.dnake.service.system.SysCommunityService;
import com.dnake.service.system.SysDeptService;
import com.dnake.service.system.SysLocalTime;
import com.dnake.service.system.SysLogoSettingsService;
import com.dnake.service.system.SysUserService;
import com.dnake.utils.BeanUtil;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.ValidationUtil;

/**
 * 设备控制业务实现类 <br/>
 * BizDeviceControlServiceImpl <br/>
 * 2014年3月4日 上午9:16:42 <br/>
 * 
 * @author ts
 * @version
 */
@Service
public class BizMobileServiceImpl extends BaseServiceImpl implements
		BizMobileService {

	@Resource
	private CommonService commonService;

	@Resource
	private BizCategoryService bizCategoryService;

	@Resource
	private SysDeptService sysDeptService;

	@Resource
	private SysCommunityService sysCommunityService;

	@Resource
	private BizProperInfoService bizProperInfoService;

	@Resource
	private BizUinfoCollectService bizUinfoCollectService;

	@Resource
	private BizNoteService bizNoteService;

	@Resource
	private BizChainService bizChainService;

	@Resource
	private SysUserService sysUserService;

	@Resource
	private BizGoodsService bizGoodsService;

	@Resource
	private BizOrderService bizOrderService;

	@Resource
	private BizOrderDetailsService bizOrderServiceDetailsService;
	@Resource
	private BizAppService bizAppService;
	@Resource
	private BizCommentServiceImpl bizCommentService;
	@Resource
	private BizAddrServiceImpl bizAddrService;
	@Resource
	private BizShopService bizShopService;
	@Resource
	private BizPromotionService bizPromotionService;
	@Resource
	private BizUserGiftService bizUserGiftService;
	@Resource
	private BizShoppingcartService bizShoppingcartService;
	@Resource
	private BizPromotionGiftService bizPromotionGiftService;
	@Resource
	private BizCouponService bizCouponService;
	@Resource
	private BizAdImageService bizAdImageService;
	@Resource
	private BizReportFormsService bizReportFormsService;
	@Resource
	private SysLogoSettingsService sysLogoSettingsService;
	@Resource
	private BizPropertiesRepairService bizPropertiesRepairService;
	@Resource
	private GetCommunityCodeService getCommunityCodeService;
	@Resource
	private SysCommunityInfoService sysCommunityInfoService;
	@Resource
	private BizRefundService bizRefundService;
	@Resource
	private SysLocalTime sysLocalTime;

	@Override
	public String mobileRequest(HttpServletRequest request, MobileParms parms) {
		if (ValidationUtil.checkHaveNull(parms.getCmd())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		String json = "";
		log.info("logged in > " + BeanUtil.getJavaBeanNotNullProperty(parms));
		int cmd = parms.getCmd();
		// log.info("received smart phone : cmd="+cmd+", user="+
		// getUserBean().getLoginNm()+ "："+getUserBean().getDeptName() );
		switch (cmd) {
		case 2002:
			json = bizProperInfoService.queryOtherPlatformNoticeList(parms);
			break;
		case 2003:
			json = bizProperInfoService.queryOtherPlatformNoticeDetail(parms);
			break;
		/**
		 * 32店铺管理模块
		 */
		case 3201:
			json = bizShopService.mobileInSert(parms);
			break;
		case 3202:
			json = bizShopService.mobileDelete(parms);
			break;
		case 3203:
			json = bizShopService.mobileUpdate(parms);
			break;
		case 3204:
			json = bizShopService.mobileQueryByUserId(parms);
			break;

		/**
		 * 评论管理
		 */
		case 3301:
			json = bizCommentService.mobileInsertComment(parms);
			break;
		case 3302:
			json = bizCommentService.mobileQueryCommentByGoodsId(parms);
			break;
		case 3303:
			json = bizCommentService.mobileQueryCommentByShopId(parms);
			break;
		case 3304:
			json = bizCommentService.mobileUploadImage(session, parms);
			break;

		/**
		 * 34活动促销模块
		 */
		case 3401:
			json = bizUserGiftService.mobileQuery(parms);
			break;
		case 3402:
			json = bizPromotionService.mobileInsert(parms);
			break;
		case 3403:
			json = bizPromotionService.mobileQueryByuserId(parms);
			break;
		case 3406:
			json = bizPromotionService.mobileUpdate(parms);
			break;
		case 3407:// 查询是否有红包可领
			json = bizPromotionGiftService.mobileUserQuery(parms);
			break;
		case 3408:// 用户领取红包
			json = bizUserGiftService.mobileInsert(parms);
			break;
		case 3409:// 提交订单时，查询优惠
			json = bizUserGiftService.mobileQueryIsHaveGift(parms);
			break;
		case 3410:
			json = bizGoodsService.mobilePullOffMulti(parms);
			break;
		/**
		 * 购物车管理
		 */
		case 3501:
			json = bizShoppingcartService.mobileInsertShoppingcart(parms);
			break;
		case 3502:
			json = bizShoppingcartService.mobileQueryShoppingcart(parms);
			break;
		case 3503:
			json = bizShoppingcartService.mobileDeleteByIdKey(parms);
			break;
		case 3504:
			json = bizShoppingcartService.mobileUpdateShoooingcart(parms);
			break;
		case 3505:
			json = bizShoppingcartService.mobileMultiDelete(parms);
			break;

		/**
		 * 密码管理-修改密码
		 */
		case 3603:
			// 手机端修改密码接口
			json = sysUserService.mobileModifyPassWord(parms.getNewPassWord(),
					parms.getOldPassWord());
			break;

		/**
		 * 商品管理
		 */
		case 3701:
			// 手机端查询商品列表
			json = bizGoodsService.mobileQueryGoodsList(parms);
			break;
		case 3702:
			// 手机端上传图片
			json = bizGoodsService.mobileUploadImage(session, parms);
			break;
		case 3703:
			// 手机端插入商品
			json = bizGoodsService.mobileInsertGoods(parms);
			break;
		case 3704:
			// 手机端更新商品
			json = bizGoodsService.mobileUpdateGoods(parms);
			break;
		case 3705:
			// 手机端查询商品详情
			json = bizGoodsService.mobileQueryGoodsDetails(parms.getIdKey());
			break;
		case 3706:
			// 手机端删除商品
			json = bizGoodsService.mobileDeleteGoods(parms);
			break;
		case 3707:
			// 商铺端查询商品
			json = bizGoodsService.mobileQueryGoodsList(parms);
			break;

		/**
		 * 订单模块
		 */
		case 3801:// 提交订单
			json = bizOrderService.mobileInsertOrder(parms);
			break;
		case 3802:// 查询订单
			json = bizOrderService.mobileQueryOrderAndDetails(parms);
			break;
		/*
		 * case 3803: // 手机商户端首页查询订单 json =
		 * bizOrderService.mobileSalesQueryOrder(parms); break;
		 */
		case 3804:
			// 修改订单状态
			json = bizOrderService.mobileUpdateOrder(parms);
			break;
		case 3805:
			// 分页获取更多订单
			json = bizOrderService.mobileQueryOrderPage(parms);
			break;
		case 3806:
			// 查询订单详情
			json = bizOrderServiceDetailsService.mobileQueryPage(parms);
			break;
		case 3807:
			// 提交申请退款
			json = bizRefundService.mobileInsert(parms);
			break;
		case 3808:
			// 上传申请退款图片
			json = bizRefundService.mobileUploadImage(parms);
			break;
		case 3809:
			// 获取提交给支付宝的订单号
			json = bizOrderService.mobileGetAlipayOrderNum(parms);
			break;
		/**
		 * 团购模块
		 */
		case 4001:// 用户查询团购券
			json = bizCouponService.mobileUserQuery(parms);
			break;
		case 4002:// 商家修改团购券变成已使用
			json = bizCouponService.mobileSalesUpdateToUsed(parms);
			break;
		case 4003:// 绑定手机号码
			json = sysUserService.mobileBindTel(parms);
			break;

		/**
		 * 广告图片管理模块
		 */
		case 4101:
			json = bizAdImageService.mobileQuery(parms);
			break;

		/**
		 * 目录模块
		 */
		case 4201:// 商铺端查询目录,已删除 2015-06-15 10:49:00
			json = bizCategoryService.salesQueryCategory();
			break;
		case 4202:
			json = bizCategoryService.mobileQuery(parms);
			break;

		/**
		 * 地址管理
		 * */
		case 4401:
			// 手机插入一条地址信息
			json = bizAddrService.mobileInsert(parms);
			break;
		case 4402:
			// 手机端编辑地址
			json = bizAddrService.mobileUpdate(parms);
			break;
		case 4403:
			// 手机端删除地址
			json = bizAddrService.delete(parms.getId());
			break;
		case 4404:
			// 手机端查询用户所有地址
			json = bizAddrService.mobileQueryAddr();
			break;
		case 4405:
			// 手机端更新默认地址
			json = bizAddrService.mobileUpdateDefalutAddr(parms);
			break;

		/**
		 * 手机访问网页接口
		 */
		case 4503:

			break;

		/**
		 * 小区管理
		 */
		case 4605:
			// 修改用户小区
			json = sysUserService.mobileUpdateUser(parms);
			break;

		/**
		 * 报表操作
		 */
		case 4701:
			json = bizReportFormsService.mobileSearchDailySales(parms);
			break;

		/**
		 * 便民服务链接接口
		 */
		case 4901:
			// 便民服务链接获取
			json = bizChainService.queryMobileChainInfoList(parms);
			break;
		/**
		 * 物业报修
		 */
		case 5001:
			// 查询列表
			json = bizPropertiesRepairService.mobileQueryList(parms);
			break;
		case 5002:
			// 查询详情
			json = bizPropertiesRepairService.mobileQueryDetails(parms);
			break;
		case 5003:
			// 删除
			json = bizPropertiesRepairService.mobileDeleteMulti(parms);
			break;
		case 5004:
			// 插入
			json = bizPropertiesRepairService.mobileInsert(parms);
			break;
		case 5005:
			// 插入
			json = bizPropertiesRepairService.mobileUploadImage(parms);
			break;
		/**
		 * 通知公告接口
		 */
		case 5101:
			// 手机端返回物业信息列表
			json = bizProperInfoService.queryForMobile(parms);
			break;
		case 5102:
			json = bizProperInfoService.mobileQueryProperDetail(parms);
			break;
		/**
		 * 投诉建议接口
		 */
		case 5201:
			json = bizUinfoCollectService.mobileInsert(parms);
			break;
		case 5202:
			json = bizUinfoCollectService.mobileQueryList(parms);
			break;
		case 5203:
			json = bizUinfoCollectService.mobileQueryDetail(parms);
			break;
		case 5204:
			json = bizUinfoCollectService.mobileUploadImage(parms);
			break;
		/**
		 * 物业客服接口
		 */
		case 5301:
			json = sysCommunityInfoService.mobileQueryCommuntiyInfo(parms);
			break;
		case 5302:
			json = sysCommunityInfoService.mobileQueryCommunityDetail(parms);
			break;
		/**
		 * 社区论坛接口
		 */
		case 5401:
			// 手机端返回帖子列表
			json = bizNoteService.queryForMobile(parms);
			break;
		case 5402:
			json = bizNoteService.mobileInsertReplyNote(parms);
			break;
		case 5403:
			json = bizNoteService.mobileQueryNoteDetail(parms);
			break;
		case 5404:
			json = bizNoteService.mobileDeleteNote(parms);
			break;
		case 5405:
			json = bizNoteService.mobileInsertNote(parms);
			break;
		case 5406:
			// c查询我的帖子列表
			json = bizNoteService.mobileQueryNoteList(parms);
			break;

		case 7001:
			json = bizUinfoCollectService.queryTPP(parms);
			break;

		case 7002:
			json = getCommunityCodeService.getCommunityInfo("35020601990001");
			break;
		default: {
			json = ResultBuilderUtil.statusMessage(4, "无此指令");
			log.info("invalid mobile protocol !");
		}
		}
		if (parms.getCallback() != null) {
			json = ResultBuilderUtil.jsonTojsonp(parms.getCallback(), json);
		}
		log.info(json);
		return json;
	}

	@Override
	public String mobileRequestNoLogin(HttpServletRequest request,
			MobileParms parms) {
		System.err.println(" not logged in > "
				+ BeanUtil.getJavaBeanNotNullProperty(parms));
		String json = "";
		int cmd = parms.getCmd();
		log.info("received smart phone (not log in): cmd=" + cmd);
		switch (cmd) {

		case 3101:
			// 用户注册信息提交
			json = sysUserService.mobileInsertUser(parms);
			break;
		case 3102:
			// 用户登录
			json = commonService.checkMobileLogin(request,
					parms.getLoginName(), parms.getUserPasswd(),
					parms.getRoleId(), parms.getUserCd());
			break;
		case 3103:
			// 手机通过token登录
			json = commonService.checkMobileLoginBytoken(parms);
			break;
		// 手机端验证验证码是否正确
		case 3601:
			json = commonService.mobileVerifyCode(parms);
			break;
		// 手机端找回密码
		case 3602:
			json = sysUserService.mobileGetPassword(parms);
			break;
		//网页端获取验证码
		case 3603:
			json = commonService.webRequestSmsCode(parms);
			break;
		//网页端验证验证码是否正确
		case 3604:
			json = commonService.webRequestSmsCodeVerify(parms);
			break;

		// 获取系统logo和图片
		case 4301:
			json = sysLogoSettingsService.mobileQueryLogo(parms);
			break;

		case 4601:
			// 获取省
			json = sysDeptService.mobileQueryByParentId(1L);
			break;
		case 4602:
			// 根据返回的省id获取市
			json = sysDeptService.mobileQueryByParentId(parms.getId());
			break;
		case 4603:
			// 根据返回的市id获取县(区)
			json = sysDeptService.mobileQueryByParentId(parms.getId());
			break;
		case 4604:
			// 根据返回的县区id获取小区
			json = sysCommunityService.mobileQueryByDeptId(parms.getId());
			break;

		case 4801:// 获取版本信息
			json = bizAppService.getAppVersion(parms);
			break;
		case 4802:// 获取系统当前时间
			json = sysLocalTime.getLocaltime(parms);
			break;
		default: {
			json = ResultBuilderUtil.statusMessage(4, "cmd无法识别");
			log.info("invalid mobile protocol !");
		}
		}
		// json转换成jsonp
		if (parms.getCallback() != null) {
			json = ResultBuilderUtil.jsonTojsonp(parms.getCallback(), json);
		}
		log.info(json);
		return json;
	}
}
