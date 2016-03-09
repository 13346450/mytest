package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizChain;
import com.dnake.domain.business.BizChainVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizChainMapper;
import com.dnake.service.business.BizChainService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.*;
/**
 * 实现类
 * @ClassName BizChainServiceImpl
 * @author zgj
 * 2014年9月2日 上午10:01:00
 */
@Service
public class BizChainServiceImpl extends BaseServiceImpl implements BizChainService
{

	@Resource
	private BizChainMapper bizChainMapper;
	@Resource
	private CommonService commonService;
	@Override
	public String insert(BizChain bizChain)
	{
		bizChainMapper.insert(bizChain);
		return "{\"successMsg\":\"添加成功！\",\"flag\":true}";
	}

	@Override
	public String update(BizChain bizChain)
	{
		bizChainMapper.update(bizChain);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public BizChain queryById(Long idKey)
	{
		BizChain bizChain = bizChainMapper.queryById(idKey);
		return bizChain;
	}

	@Override
	public String delete(HttpServletResponse response, Long idKey)
	{
		BizChain bizChain = bizChainMapper.queryById(idKey);
		if(bizChain != null)
		{
		String[] imageArray = bizChain.getImagesUrl().split("@@");
		for(String imageAddr : imageArray){
			commonService.deleteFile(response, 
					imageAddr.substring(imageAddr.indexOf("/")+1) , 
					session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR);
		}
		bizChainMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
		}
		else
		{
			return "{\"successMsg\":\"没有数据，删除失败！\"}";
		}
	}

	@Override
	public Page<BizChainVO> listPage(int pageIndex, int rows, SearchParam searchParam)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort",searchParam.getSort());
		map.put("order",searchParam.getOrder());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		Page<BizChainVO> page = new Page<BizChainVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizChainMapper.queryPage(page));
		return page;
	}

	@Override
	public String queryMobileChainInfoList(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		List<BizChain> list = bizChainMapper.queryMobileChainInfoList(sessionBean.getDeptId());
		List<BizChain> listCopy = new ArrayList<BizChain>();
		JsonResult jr = new JsonResult();
		if (null != list) {
			for (BizChain bizChain : list)
			{
				bizChain.setLinksUrl("/appservice/viewChain/" + bizChain.getIdKey());
				listCopy.add(bizChain);
			}
		}
		jr.setData(listCopy);
		return ResultBuilderUtil.jsonBuild(jr);
	}
	
}
