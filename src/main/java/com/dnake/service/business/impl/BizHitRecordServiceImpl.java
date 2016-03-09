package com.dnake.service.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizHitRecord;
import com.dnake.domain.business.BizHitRecordVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizHitRecordMapper;
import com.dnake.service.business.BizHitRecordService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;
/**
 * 业务接口实现类
 * @ClassName BizHitRecordServiceImpl
 * @author zgj
 * 2014年9月3日 下午2:30:58
 */
@Service
public class BizHitRecordServiceImpl extends BaseServiceImpl implements BizHitRecordService
{
	@Resource
	private BizHitRecordMapper bizHitRecordMapper;
	@Resource
	private CommonService commonService;
	
	@Override
	public Page<BizHitRecordVO> listPage(int pageIndex, int rows,
			SearchParam searchParam)
	{
		if( StringUtils.isNull(searchParam.getStartDatetime()) ){
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(), Constants.START_PERIOD) ,"yyyy-mm-dd")+" 00:00:00");
		}else{
			searchParam.setStartDatetime(searchParam.getStartDatetime()+" 00:00:00");
		}
		if( StringUtils.isNull(searchParam.getEndDatetime()) ){
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(), "yyyy-mm-dd")+" 23:59:59");
		}else{
			searchParam.setEndDatetime(searchParam.getEndDatetime()+" 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("sort",searchParam.getIdKey());
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		
		Page<BizHitRecordVO> page = new Page<BizHitRecordVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizHitRecordMapper.queryPage(page));
		return page;
	}

	@Override
	public String insert(BizHitRecord bizHitRecord)
	{
		bizHitRecord.setUserId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		bizHitRecord.setHitDate(new Date());
		bizHitRecordMapper.insert(bizHitRecord);
		return "{\"successMsg\":\"添加成功！\",\"flag\":true}";
	}

	@Override
	public String update(BizHitRecord bizHitRecord)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(HttpServletResponse response,Long idKey)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryById(Long idKey)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMobileClicksInfo(Long chainId)
	{
		BizHitRecord bizHitRecord = new BizHitRecord();
		bizHitRecord.setUserId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		bizHitRecord.setChainId(chainId);
		bizHitRecord.setHitDate(new Date());
		bizHitRecordMapper.insert(bizHitRecord);
	}

}
