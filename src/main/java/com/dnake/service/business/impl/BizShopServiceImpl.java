package com.dnake.service.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizShopVO;
import com.dnake.domain.business.BizShop;
import com.dnake.mapper.business.BizShopMapper;
import com.dnake.service.business.BizShopService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

@Service
public class BizShopServiceImpl extends BaseServiceImpl implements
		BizShopService {
	@Resource
	private BizShopMapper bizShopMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<BizShopVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		map.put("status", searchParam.getStatus());
		map.put("userName", searchParam.getUserName());
		Page<BizShopVO> page = new Page<BizShopVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizShopMapper.queryPage(page));
		return page;
	}

	
	
	@Override
	public String update(BizShop bizShop) {
		bizShop.setChgDt(new Date());
		bizShopMapper.update(bizShop);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizShop bizShop) {
		bizShop.setChgDt(new Date());
		bizShopMapper.insert(bizShop);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		//TODO 删除前需判断
		bizShopMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileInSert(MobileParms parms) {
		if (ValidationUtil.checkHaveNull(parms.getCommunityId(),
				parms.getAddr(), parms.getTelephone(),parms.getName())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		BizShop bizShop = new BizShop();
		bizShop.setAddr(parms.getAddr());
		bizShop.setName(parms.getName());
		bizShop.setStatus(1);//未审核
		bizShop.setTelephone(parms.getTelephone());
		bizShop.setCommunityId(parms.getCommunityId());
		bizShop.setUserId(getUserBean().getUserId());
		bizShop.setIsDefault(0);
		if(bizShopMapper.mobileQueryByUserId(getUserBean().getUserId()).size()==0){//第一家店，直接设为默认
			bizShop.setIsDefault(1);
		}
		bizShop.setChgDt(new Date());
		bizShopMapper.insert(bizShop);
		return ResultBuilderUtil.statusMessage(1, "插入成功");
	}

	@Override
	public String mobileQueryByUserId(MobileParms parms) {
		List<BizShopVO> list=bizShopMapper.mobileQueryByUserId(getUserBean().getUserId());
		JsonResult jr=new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileDelete(MobileParms parms) {
		if (ValidationUtil.checkHaveNull(parms.getIdKey())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		bizShopMapper.delete(parms.getIdKey());
		return ResultBuilderUtil.statusMessage(1, "删除成功！");
	}

	@Override
	public String mobileUpdate(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getIdKey())){
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		BizShop bizShop =new BizShop();
		bizShop.setIdKey(parms.getIdKey());
		bizShop.setName(parms.getName());
		bizShop.setStatus(parms.getStatus());
		bizShop.setTelephone(parms.getTelephone());
		bizShop.setCommunityId(parms.getCommunityId());
		bizShop.setAddr(parms.getAddr());
		bizShop.setChgDt(new Date());
		bizShop.setIsDefault(parms.getIsDefault());
		if(parms.getIsDefault()==1){//先清除原来的默认店铺，isdefault置0
			bizShopMapper.setAllNotDefault(getUserBean().getUserId());
		}
		bizShopMapper.update(bizShop);
		return ResultBuilderUtil.statusMessage(1, "修改成功！");
	}

	@Override
	public String updateMulti(String deleteIds, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idsList", Arrays.asList(deleteIds.split(",")));
		map.put("status", status);
		bizShopMapper.updateMulti(map);
		return "{\"successMsg\":\"操作成功！\",\"flag\":true}";
	}



	@Override
	public List<BizShopVO> queryShopByuserId(Long userId) {
		return 	bizShopMapper.mobileQueryByUserId(userId);
		
	}

}
