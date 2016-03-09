/**
 * Project Name:SmartHomeWeb
 * File Name:BizAppServiceImpl.java
 * Package Name:com.dnake.service.business.impl
 * Date:2014年4月9日上午11:26:43
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.
 *
 */

package com.dnake.service.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.business.BizApp;
import com.dnake.domain.business.BizAppVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizAppMapper;
import com.dnake.service.business.BizAppService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 手机app版本管理业务 ClassName:BizAppServiceImpl <br/>
 * Date: 2014年4月9日 上午11:26:43 <br/>
 * 
 * @author ts
 * @version
 * @see
 */
@Service
public class BizAppServiceImpl extends BaseServiceImpl implements BizAppService {
	@Resource
	private BizAppMapper bizAppMapper;
	@Resource
	private CommonService commonService;

	@Override
	public String update(BizApp bizApp) {
		bizApp.setMakerId(((SessionBean) session.getAttribute("sessionBean"))
				.getUserId());
		bizApp.setMakerDt(new Date());
		bizAppMapper.update(bizApp);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String delete(HttpServletResponse response, Long idKey) {
		BizApp bizApp = bizAppMapper.queryByIdkey(idKey);
		String apkAddr = bizApp.getDownloadUrl();
		commonService.deleteFile(response, apkAddr.substring(apkAddr
				.indexOf("/") + 1), session.getServletContext()
				.getRealPath("/") + Constants.APK_DIR);
		bizAppMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String insert(BizApp bizApp) {
		String maxVersionStr = bizAppMapper.getMaxVersionNo(bizApp.getType());
		if (maxVersionStr == null) {// 版本号至少从1开始
			maxVersionStr = "0";
		}
		int maxVersionInt = Integer.valueOf(maxVersionStr);
		bizApp.setAppVersion(maxVersionInt + 1 + "");// 版本序号自动加1
		bizApp.setMakerId(((SessionBean) session.getAttribute("sessionBean"))
				.getUserId());
		bizApp.setMakerDt(new Date());
		bizApp.setAppStatus(0);
		bizAppMapper.insert(bizApp);
		return "{\"successMsg\":\"添加成功！\",\"flag\":true}";
	}

	@Override
	public Page<BizAppVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		if (StringUtils.isNull(searchParam.getStartDatetime())) {
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(),
							Constants.START_PERIOD), "yyyy-mm-dd")
					+ " 00:00:00");
		} else {
			searchParam.setStartDatetime(searchParam.getStartDatetime()
					+ " 00:00:00");
		}
		if (StringUtils.isNull(searchParam.getEndDatetime())) {
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(),
					"yyyy-mm-dd") + " 23:59:59");
		} else {
			searchParam.setEndDatetime(searchParam.getEndDatetime()
					+ " 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appStatus", searchParam.getAppStatus());
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());

		Page<BizAppVO> page = new Page<BizAppVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizAppMapper.queryPage(page));
		return page;
	}

	@Override
	public String updateStatus(Long idKey, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type.equals("audit")) {
			map.put("auditId", ((SessionBean) session
					.getAttribute("sessionBean")).getUserId());
			map.put("auditDt", new Date());
			map.put("appStatus", 1);
			map.put("oper", "audit");
		} else if (type.equals("cancelAudit")) {
			map.put("auditId", null);
			map.put("auditDt", null);
			map.put("appStatus", 0);
			map.put("oper", "audit");
		} else if (type.equals("publish")) {
			map.put("publishId", ((SessionBean) session
					.getAttribute("sessionBean")).getUserId());
			map.put("publishDt", new Date());
			map.put("appStatus", 2);
			map.put("oper", "publish");
		} else if (type.equals("cancelPublish")) {
			map.put("publish", null);
			map.put("publish", null);
			map.put("appStatus", 1);
			map.put("oper", "publish");
		}
		map.put("idKey", idKey);
		bizAppMapper.updateStatus(map);
		Constants.CUSTOMER_NEW_VERSION_APP = bizAppMapper
				.queryMaxPublishedVersion("customer");// 更新内存里的商户端最新版本信息
		Constants.SALES_NEW_VERSION_APP = bizAppMapper
				.queryMaxPublishedVersion("sales");// 更新内存里的商户端最新版本信息
		Constants.INSIDE_STATION_NEW_VERSION_APP = bizAppMapper
				.queryMaxPublishedVersion("insideStation");// 更新内存里的分机端最新版本信息

		return "{\"successMsg\":\"操作成功！\"}";
	}

	@Override
	public String getAppVersion(MobileParms parms) {
		Long roleId = parms.getRoleId();
		JsonResult jr = new JsonResult();
		if (roleId == null
				|| !(roleId.equals(7L) || roleId.equals(8L) || roleId
						.equals(9L))) {// 住户版7，商户版8，分机版9
			jr.setStatus(3);
			jr.setMessages("未识别客户端");
			return ResultBuilderUtil.jsonBuild(jr);// 未识别客户端
		}

		BizApp newApp = new BizApp();
		newApp.setAppVersion("0");
		if (Constants.CUSTOMER_NEW_VERSION_APP == null) {// 第一次查数据库，之后就在内存里查
			BizApp bizApp = bizAppMapper.queryMaxPublishedVersion("customer");
			if (bizApp == null) {// 如果没查到，将版本0，赋值给内存里的版本号
				bizApp = newApp;
			}
			Constants.CUSTOMER_NEW_VERSION_APP = bizApp;
		}
		if (Constants.SALES_NEW_VERSION_APP == null) {// 第一次查数据库，之后就在内存里查
			BizApp bizApp = bizAppMapper.queryMaxPublishedVersion("sales");
			if (bizApp == null) {// 如果没查到，将版本0，赋值给内存里的版本号
				bizApp = newApp;
			}
			Constants.SALES_NEW_VERSION_APP = bizApp;
		}
		if (Constants.INSIDE_STATION_NEW_VERSION_APP == null) {// 第一次查数据库，之后就在内存里查
			BizApp bizApp = bizAppMapper
					.queryMaxPublishedVersion("insideStation");
			if (bizApp == null) {
				bizApp = newApp;
			}
			Constants.INSIDE_STATION_NEW_VERSION_APP = bizApp;
		}

		if (roleId.equals(7L)) {
			newApp = Constants.CUSTOMER_NEW_VERSION_APP;
		} else if (roleId.equals(8L)) {
			newApp = Constants.SALES_NEW_VERSION_APP;
		} else if (roleId.equals(9L)) {
			newApp = Constants.INSIDE_STATION_NEW_VERSION_APP;
		}
		jr.setData(newApp);

		String returnString = ResultBuilderUtil.jsonBuild(jr);
		return returnString;
	}

	@Override
	public String uploadFile(MultipartFile mfile, String path) {
		String s = commonService.uploadFile(mfile, path, false, null);
		if (s.indexOf("error") != -1) {
			return "{\"successMsg\" : \"上传失败。\" , \"addr\":\"\"}";
		} else {
			return "{\"successMsg\" : \"上传成功。\" , \"addr\":\"" + s + "\"}";
		}
	}

	@Override
	public String downLoadLatestApp(String type) {
		MobileParms mp = new MobileParms();
		if ("sp".equals(type)) {
			if (Constants.SALES_NEW_VERSION_APP == null) {
				mp.setRoleId(8L);
				this.getAppVersion(mp);
			}
			return Constants.SALES_NEW_VERSION_APP.getDownloadUrl();
		} else if ("zh".equals(type)) {
			if (Constants.CUSTOMER_NEW_VERSION_APP == null) {
				mp.setRoleId(7L);
				this.getAppVersion(mp);
			}
			return Constants.CUSTOMER_NEW_VERSION_APP.getDownloadUrl();
		} else if ("pad".equals(type)) {
			if (Constants.INSIDE_STATION_NEW_VERSION_APP == null) {
				mp.setRoleId(9L);
				this.getAppVersion(mp);
			}
			return Constants.INSIDE_STATION_NEW_VERSION_APP.getDownloadUrl();
		} else {// 云家庭、摇摇停车、智能家居不做内存优化以及其他，因为只做下载用，type为app简拼
			BizApp bizApp = bizAppMapper.queryMaxPublishedVersion(type);
			if (bizApp != null) {
				return bizApp.getDownloadUrl();
			}
		}
		return null;
	}

}
