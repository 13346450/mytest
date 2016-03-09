package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizAdImageVO;
import com.dnake.domain.business.BizAdImage;
import com.dnake.domain.business.BizAdvert;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.mapper.business.BizAdImageMapper;
import com.dnake.mapper.business.BizCategoryMapper;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.service.business.BizAdImageService;
import com.dnake.service.business.BizCategoryService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysCommunity;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

@Service
public class BizAdImageServiceImpl extends BaseServiceImpl implements
		BizAdImageService {
	@Resource
	private BizAdImageMapper bizAdImageMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizCategoryMapper bizCategoryMapper;
	@Resource
	private SysCommunityMapper sysCommunityMapper;

	@Override
	public Page<BizCategoryVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		return null;
	}

	@Override
	public String update(BizAdImage bizAdImage) {
		// bizAdImage.setChgDt(new Date());
		bizAdImageMapper.update(bizAdImage);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(String categoryId, String dictKey, String imageUrl,String communityId) {
		/**
		 * 获取插入的排序号，
		 */

		BizAdImage bizAdImage = new BizAdImage();
		bizAdImage.setCategoryId(Long.valueOf(categoryId));
		bizAdImage.setDictKey(Long.valueOf(dictKey));
		bizAdImage.setImageUrl(imageUrl);
		bizAdImage.setCommunityId(Long.valueOf(communityId));
		Long order = bizAdImageMapper.queryMaxOrderByType(categoryId,communityId);
		if (order == null) {
			order = 0L;
		}
		bizAdImage.setOrder(order+1);
		bizAdImage.setStatus(1);// 启用
		bizAdImage.setChgDt(new Date());
		bizAdImageMapper.insert(bizAdImage);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizAdImageMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileQuery(MobileParms parms) {
		if (ValidationUtil.checkHaveNull(parms.getType())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizAdImageVO> page = new Page<BizAdImageVO>();
		if(parms.getType()==1){
			Long id = getUserBean().getDeptId();
			int val = bizCategoryMapper.queryRootByCommunityId(id).getIdKey().intValue();
			parms.setType(val);
		}
		map.put("categoryId", parms.getType());
		map.put("imageSize", parms.getImageSize());
		map.put("communityId",getUserBean().getDeptId());
		page.setPageNo(1);
		page.setPageSize(999);
		page.setParams(map);
		List<BizAdImageVO> list = bizAdImageMapper.queryPage(page);
		JsonResult jr = new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public List<BizAdImageVO> queryByType(String categoryId,String communityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizAdImageVO> page = new Page<BizAdImageVO>();
		map.put("categoryId", categoryId);
		map.put("communityId", communityId);
		page.setPageNo(1);
		page.setPageSize(999);
		page.setParams(map);
		List<BizAdImageVO> list = bizAdImageMapper.queryPage(page);
		return list;
	}

	@Override
	public String delete(HttpServletResponse response, Long idKey) {
		BizAdImage bizAdvert = bizAdImageMapper.queryByIdkey(idKey);
		commonService.deleteImage(session.getServletContext().getRealPath("/")
				+ bizAdvert.getImageUrl());
		bizAdImageMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
	}
}
