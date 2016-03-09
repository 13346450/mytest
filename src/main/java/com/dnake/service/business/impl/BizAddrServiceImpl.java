package com.dnake.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizAddrVO;
import com.dnake.domain.business.BizAddr;
import com.dnake.mapper.business.BizAddrMapper;
import com.dnake.service.business.BizAddrService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;

@Service
public class BizAddrServiceImpl extends BaseServiceImpl implements
		BizAddrService
{
	@Resource
	private BizAddrMapper bizAddrMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<BizAddrVO> listPage(int pageIndex, int rows,
			SearchParam searchParam)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<BizAddrVO> page = new Page<BizAddrVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizAddrMapper.queryPage(page));
		return page;
	}

	@Override
	public String mobileUpdate(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizAddr bizAddr = new BizAddr();
		bizAddr.setIdKey(parms.getIdKey());
		bizAddr.setProvince(parms.getProvinceId());
		bizAddr.setCity(parms.getCityId());
		bizAddr.setDistrict(parms.getDistrictId());
		bizAddr.setAddressDetail(parms.getAddrDetail());
		bizAddr.setPhoneNum(parms.getPhone());
		bizAddr.setPostalcode(parms.getPostalcode());
		bizAddr.setUserId(sessionBean.getUserId());
		bizAddr.setUserName(parms.getUserName());
		bizAddr.setCommunityId(sessionBean.getDeptId());
		
		
		bizAddrMapper.update(bizAddr);
		return ResultBuilderUtil.statusMessage(1, "更新成功");
	}

	@Override
	public String insert(BizAddr bizAddr)
	{
		bizAddrMapper.insert(bizAddr);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String delete(Long idKey)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizAddrVO addr = bizAddrMapper.queryByIdkey(idKey);
		bizAddrMapper.delete(idKey);
		List<BizAddrVO> list = bizAddrMapper.queryByUserId(sessionBean.getUserId());	
		if(addr.getIsDefault() == 1)
		{
			if(list.size() > 0)
			{
				for (BizAddrVO bizAddrVO : list)
				{
					BizAddr biz1 = new BizAddr();
					biz1.setIdKey(bizAddrVO.getIdKey());
					biz1.setIsDefault(1);
					bizAddrMapper.updateDefault(biz1);
					break;
				}
			}
		}
		return ResultBuilderUtil.statusMessage(1, "删除成功");
	}

	@Override
	public String mobileInsert(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizAddr bizAddr = new BizAddr();
		List<BizAddrVO> list = bizAddrMapper.queryByUserId(sessionBean.getUserId());
		if(list.size() == 0)
		{
			bizAddr.setIsDefault(1);
		}
		else
		{
			bizAddr.setIsDefault(0);
		}
		bizAddr.setProvince(parms.getProvinceId());
		bizAddr.setCity(parms.getCityId());
		bizAddr.setDistrict(parms.getDistrictId());
		bizAddr.setAddressDetail(parms.getAddrDetail());
		bizAddr.setPhoneNum(parms.getPhone());
		bizAddr.setPostalcode(parms.getPostalcode());
		bizAddr.setUserId(sessionBean.getUserId());
		bizAddr.setUserName(parms.getUserName());
		bizAddr.setCommunityId(sessionBean.getDeptId());
		
		bizAddrMapper.insert(bizAddr);
		return ResultBuilderUtil.statusMessage(1, "插入成功");
	}

	@Override
	public String mobileQueryAddr()
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		List<BizAddrVO> list = bizAddrMapper.queryByUserId(sessionBean.getUserId());
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizAddrVO.class, "idKey", "userName", "province", "provinceNm",
				"city", "cityNm", "district", "districtNm", "communityNm",
				"addressDetail", "phoneNum", "postalCode", "isDefault");
		JsonResult jr = new JsonResult();
		jr.setData(list);
		String json = ResultBuilderUtil.jsonBuild(jr, filter);
		return json;
	}

	@Override
	public String mobileUpdateDefalutAddr(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizAddr biz = new BizAddr();
		biz.setUserId(sessionBean.getUserId());
		biz.setIsDefault(1);
		List<BizAddrVO> list = bizAddrMapper.queryByDefault(biz);
		if(list.size() > 0)
		{
			for (BizAddrVO bizAddrVO : list)
			{
				if(bizAddrVO.getIdKey() != parms.getIdKey())
				{
					biz.setIsDefault(0);
					biz.setIdKey(bizAddrVO.getIdKey());
					bizAddrMapper.updateDefault(biz);
					biz.setIsDefault(1);
					biz.setIdKey(parms.getIdKey());
					bizAddrMapper.updateDefault(biz);
					return ResultBuilderUtil.statusMessage(1, "更新成功，将原来的默认地址设置为0");
				}
			}
		}
		else
		{
			biz.setIsDefault(1);
			biz.setIdKey(parms.getIdKey());
			bizAddrMapper.updateDefault(biz);
			return ResultBuilderUtil.statusMessage(1, "更新成功，将选择的地址设置为默认地址");
		}
		return ResultBuilderUtil.statusMessage(2, "更新失败，没有地址");
		
	}

}
