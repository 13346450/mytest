package com.dnake.service.business.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizNote;
import com.dnake.domain.business.BizNoteReply;
import com.dnake.domain.business.BizNoteReplyVO;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.mapper.business.BizNoteMapper;
import com.dnake.mapper.business.BizNoteReplyMapper;
import com.dnake.service.business.BizNoteReplyService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.SessionBean;

/**
 * 回复实现层
 * @ClassName BizNoteReplyServiceImpl
 * @author zgj
 * 2014年9月16日 下午6:30:22
 */
@Service
public class BizNoteReplyServiceImpl extends BaseServiceImpl implements BizNoteReplyService
{
	@Resource
	private BizNoteReplyMapper bizNoteReplyMapper;
	@Resource
	private BizNoteMapper bizNoteMapper;

	@Override
	public String insert(BizNoteReply bizNoteReply)
	{
		StringBuilder sb = new StringBuilder();
		//插入评论人Id  sessionbean
		bizNoteReply.setReplyUserId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		//增加一条评论数
		//评论条数+1
		BizNoteVO bizNoteVO = bizNoteMapper.queryById(bizNoteReply.getNoteId());
		int i = bizNoteVO.getReplyCount();
		i = i + 1;
		bizNoteVO.setReplyCount(i);
		
		bizNoteReply.setReplyDt(new Date());
		bizNoteReplyMapper.insert(bizNoteReply);
		BizNoteReplyVO biz = bizNoteReplyMapper.queryById(bizNoteReply.getIdKey());
		if(biz.getReplyUserName() == null)
		{
			biz.setReplyUserName(" ");
		}
		String replyDt = DateTimeUtil.formatDateToStr(biz.getReplyDt(), "yyyy-MM-dd HH:mm:ss");
		sb.append("{\"successMsg\":\"评论成功!\",");
		sb.append("\"replyUserName\":\"").append( biz.getReplyUserName());
		sb.append("\",\"replyContent\":\"").append( biz.getReplyContent());
		sb.append("\",\"replyDt\":\"").append( replyDt);
		sb.append("\"}");
		return  sb.toString();
		/*List<BizNoteReplyVO>  list = bizNoteReplyMapper.queryPage(bizNoteReply.getNoteId());
		sb.append("{\"successMsg\":\"评论成功!\", ");
		for (BizNoteReplyVO biz: list)
		{
			sb.append("\"userName\":\"" + biz.getReplyUserName() +
					  "\" ,\"replyDt\":\"" + biz.getReplyDt() + 
					  "\" ,\"replyContent\":\"" + biz.getReplyContent() +
					  "\" ,\"flag\":true}");
		}
		sb.append("");
		return sb.toString();*/
	}

	@Override
	public String delete(Long idKey)
	{
		bizNoteReplyMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功!\",\"flag\":true}";
	}

	@Override
	public List<BizNoteReplyVO> listPage(Long noteId)
	{
		List<BizNoteReplyVO> list = bizNoteReplyMapper.queryPage(noteId);
		return list;
	}

}
