package com.dnake.service.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizAdvert;
import com.dnake.domain.business.BizAdvertVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizAdvertMapper;
import com.dnake.service.business.BizAdvertService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 广告业务实现类 <br/>
 * ClassName:BizAdvertServiceImpl <br/>
 * Date:     2014年3月19日 下午4:42:32 <br/>
 * @author   ts
 * @version  
 * @see 	 
 */
@Service
public class BizAdvertServiceImpl extends BaseServiceImpl implements BizAdvertService {
	
	@Resource
	private BizAdvertMapper bizAdvertMapper;
	@Resource
	private CommonService commonService;

	@Override
	public String update(BizAdvert bizAdvert) {
		bizAdvert.setMakerId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		bizAdvert.setMakerDt(new Date());
		String img = bizAdvert.getImagesUrl();
		String url = bizAdvert.getLinksUrl();
		if( !StringUtils.isNull(img) ){
			img = bizAdvert.getImagesUrl().substring(0, bizAdvert.getImagesUrl().length()-2);
		}
		if( !StringUtils.isNull(url) ){
			url = bizAdvert.getLinksUrl().substring(0, bizAdvert.getLinksUrl().length()-2);
		}
		bizAdvert.setImagesUrl(img);
		bizAdvert.setLinksUrl(url);
		bizAdvertMapper.update(bizAdvert);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String delete(HttpServletResponse response, Long idKey) {
		BizAdvert bizAdvert = bizAdvertMapper.queryByIdkey(idKey);
		String[] imageArray = bizAdvert.getImagesUrl().split("@@");
		for(String imageAddr : imageArray){
			commonService.deleteFile(response, 
					imageAddr.substring(imageAddr.indexOf("/")+1) , 
					session.getServletContext().getRealPath("/")+Constants.UPLOAD_DIR);
		}
		bizAdvertMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String insert(BizAdvert bizAdvert) {
		//先判断版本号是否比最大值大
		if(Float.parseFloat(bizAdvert.getAdvVersion()) <= Float.parseFloat(bizAdvertMapper.getMaxVersionNo())){
			return "{\"successMsg\":\"版本号必须大于现有版本（"+
						bizAdvertMapper.getMaxVersionNo()+"）\",\"flag\":false}";
		}
		bizAdvert.setMakerId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		bizAdvert.setMakerDt(new Date());
		bizAdvert.setImagesUrl(bizAdvert.getImagesUrl().substring(0, bizAdvert.getImagesUrl().length()-2));
		bizAdvert.setLinksUrl(bizAdvert.getLinksUrl().substring(0, bizAdvert.getLinksUrl().length()-2));
		bizAdvert.setAdvStatus(0);
		bizAdvertMapper.insert(bizAdvert);
		return "{\"successMsg\":\"添加成功！\",\"flag\":true}";
	}

	@Override
	public Page<BizAdvertVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
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
		map.put("advStatus", searchParam.getAdvStatus());
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		map.put("sort",searchParam.getSort());
		map.put("order",searchParam.getOrder());
		
		Page<BizAdvertVO> page = new Page<BizAdvertVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizAdvertMapper.queryPage(page));
		return page;
	}

	@Override
	public String updateStatus(Long idKey, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(type.equals("audit")){
			map.put("auditId", ((SessionBean) session.getAttribute("sessionBean")).getUserId());
			map.put("auditDt", new Date());
			map.put("advStatus", 1);
			map.put("oper", "audit");
		}else if(type.equals("cancelAudit")){
			map.put("auditId",  null);
			map.put("auditDt",  null);
			map.put("advStatus", 0);
			map.put("oper", "audit");
		}else if(type.equals("publish")){
			map.put("publishId", ((SessionBean) session.getAttribute("sessionBean")).getUserId());
			map.put("publishDt", new Date());
			map.put("advStatus", 2);
			map.put("oper", "publish");
		}else if(type.equals("cancelPublish")){
			map.put("publish",  null);
			map.put("publish",  null);
			map.put("advStatus", 1);
			map.put("oper", "publish");
		}
		map.put("idKey", idKey);
		bizAdvertMapper.updateStatus(map);
		return "{\"successMsg\":\"操作成功！\"}";
	}

}
