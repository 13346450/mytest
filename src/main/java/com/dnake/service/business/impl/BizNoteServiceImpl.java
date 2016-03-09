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

import com.dnake.domain.business.BizAddrVO;
import com.dnake.domain.business.BizNote;
import com.dnake.domain.business.BizNoteReply;
import com.dnake.domain.business.BizNoteReplyVO;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysDept;
import com.dnake.mapper.business.BizNoteMapper;
import com.dnake.service.business.BizHitRecordService;
import com.dnake.service.business.BizNoteReplyService;
import com.dnake.service.business.BizNoteService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;


/**
 * 帖子管理实现类
 * @ClassName BizNoteServiceImpl
 * @author zgj
 * 2014年9月16日 下午3:57:45
 */
@Service
public class BizNoteServiceImpl extends BaseServiceImpl implements BizNoteService
{
	@Resource
	private BizNoteMapper bizNoteMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizHitRecordService bizHitRecordService;
	@Resource
	private BizNoteReplyService bizNoteReplyService;
	
	@Override
	public void insert(BizNote bizNote)
	{
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		bizNote.setUserId(sessionBean.getUserId());
		bizNote.setCommunityId(sessionBean.getDeptId());
		bizNote.setPublishDt(new Date());
		bizNoteMapper.insert(bizNote);
	}

	@Override
	public String update(BizNote bizNote)
	{
		bizNoteMapper.update(bizNote);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public BizNoteVO queryById(Long idKey)
	{
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		BizNoteVO bizNoteVO = bizNoteMapper.queryById(idKey);
		if(bizNoteVO != null)
		{
			if(sessionBean.getUserId() != bizNoteVO.getUserId())
			{
				//增加点击
				BizNoteVO bizNote = bizNoteMapper.queryById(idKey);
				int status = bizNote.getClicksCount();
				status = status +1;
				updateStatus(status, "addClicks", idKey);
			}
			return bizNoteVO;
		}
		return new BizNoteVO();
	}

	@Override
	public String delete(Long idKey)
	{
		bizNoteMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public Page<BizNoteVO> listPage(int pageIndex, int rows,
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
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		
		Page<BizNoteVO> page = new Page<BizNoteVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		List<BizNoteVO> bizNoteVOList = bizNoteMapper.queryPage(page);
		for (BizNoteVO bizNoteVO : bizNoteVOList)
		{
			//调用公共类对数据进行转义
			String s = StringUtils.replaceNewline(bizNoteVO.getNoteContent());
			bizNoteVO.setNoteContent(s);
		}
		page.setRows(bizNoteVOList);
		return page;
	}

	@Override
	public String queryForMobile(MobileParms parms)
	{
		String sContent = "";
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		Page<BizNoteVO> page = new Page<BizNoteVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("noteType", parms.getNoteType());
		map.put("communityId", sessionBean.getDeptId());
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		page.setParams(map);
		List<BizNoteVO> list = bizNoteMapper.queryForMobile(page);
		List<BizNoteVO> list1 = new ArrayList<BizNoteVO>();
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizNoteVO.class, "idKey", "noteTitle", "noteContent",
				"publishDt", "userName", "communityName", "replyCount",
				"clicksCount");
		JsonResult jr = new JsonResult();
		for (BizNoteVO bn : list) 
		{
			sContent = (bn.getNoteContent().length() >= 30)?bn.getNoteContent().substring(0, 30):bn.getNoteContent();
			sContent = StringUtils.replaceNewline(sContent);
			bn.setNoteContent(sContent);
			if(bn.getUserName() == null)
			{
				bn.setUserName("匿名者");
			}
			if(bn.getNoteContent() == null)
			{
				bn.setNoteContent(" ");
			}
			if(bn.getNoteTitle() == null)
			{
				bn.setNoteContent(" ");
			}
			list1.add(bn);
		}
		jr.setData(list1);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public BizNoteVO queryForDetail(Long idKey)
	{
		BizNoteVO bizNoteVO = bizNoteMapper.queryById(idKey);
		if(bizNoteVO != null)
		{
			return bizNoteVO;
		}
		return new BizNoteVO();
	}

	@Override
	public String deleteMulti(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(ids.split(",")));
		bizNoteMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public List<BizNoteVO> queryForUserNote(Long idKey)
	{
		List<BizNoteVO> list = bizNoteMapper.queryForUserNote(idKey);
		return list;
	}

	@Override
	public void updateStatus(int status, String type, Long idKey)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(type.equals("addClicks"))
		{
			map.put("clicksCount", status);
			map.put("oper", "clicks");
		}
		else if(type.equals("addReplys"))
		{
			map.put("replysCount", status);
			map.put("lastReplyDt", new Date());
			map.put("oper", "replys");
		}
		map.put("idKey", idKey);
		bizNoteMapper.updateStatus(map);
	}

	@Override
	public String mobileQueryNoteList(MobileParms parms)
	{
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		List<BizNoteVO> list = queryForUserNote(sessionBean.getUserId());
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizNoteVO.class, "idKey", "noteTitle", "noteTypeName", "noteContent",
				"lastReplyDt", "publishDt", "replyCount", "clicksCount", "communityName",
				"userName", "userId");
		JsonResult jr = new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr,filter);
	}

	@Override
	public String mobileDeleteNote(MobileParms parms)
	{
		delete(parms.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String mobileInsertNote(MobileParms parms)
	{
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		
		BizNote bizNote = new BizNote();
		bizNote.setNoteTitle(parms.getNoteTitle());
		bizNote.setNoteType(parms.getNoteType());
		bizNote.setNoteContent(parms.getNoteContent());
		bizNote.setPublishDt(new Date());
		bizNote.setReplyCount(0);
		bizNote.setClicksCount(0);
		bizNote.setCommunityId(sessionBean.getDeptId());
		bizNote.setUserId(sessionBean.getUserId());
		insert(bizNote);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String mobileInsertReplyNote(MobileParms parms)
	{
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		BizNoteReply bizNoteReply = new BizNoteReply();
		int status = queryForDetail(parms.getIdKey()).getReplyCount();
		bizNoteReply.setNoteId(parms.getIdKey());
		bizNoteReply.setReplyContent(parms.getReplyContent());
		bizNoteReply.setReplyUserId(sessionBean.getUserId());
		bizNoteReply.setReplyDt(new Date());
		status = status + 1;
		updateStatus(status, "addReplys", parms.getIdKey());
		bizNoteReplyService.insert(bizNoteReply);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String mobileQueryNoteDetail(MobileParms parms)
	{
		BizNoteVO biz =  queryById(parms.getIdKey());
		List<BizNoteReplyVO> listReply = bizNoteReplyService.listPage(parms.getIdKey());
		biz.setListReply(listReply);
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizNoteVO.class, "idKey", "noteTitle", "noteTypeName", "noteContent",
				"lastReplyDt", "publishDt", "replyCount", "clicksCount", "communityName",
				"userName", "userId", "listReply");
		JsonResult jr = new JsonResult();
		jr.setData(biz);
		return ResultBuilderUtil.jsonBuild(jr,filter);
	}
}
