package com.dnake.service.business.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.business.BizCommentImage;
import com.dnake.domain.business.BizCommentVO;
import com.dnake.domain.business.BizComment;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.business.BizOrder;
import com.dnake.mapper.business.BizCommentImageMapper;
import com.dnake.mapper.business.BizCommentMapper;
import com.dnake.mapper.business.BizOrderDetailsMapper;
import com.dnake.mapper.business.BizOrderMapper;
import com.dnake.service.business.BizCommentService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonReturnBizComment;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.BeanCopy;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ImageUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

@Service
public class BizCommentServiceImpl extends BaseServiceImpl implements
		BizCommentService
{
	@Resource
	private BizCommentMapper bizCommentMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizCommentImageMapper bizCommentImageMapper;
	@Resource
	private BizOrderMapper bizOrderMapper;

	@Override
	public Page<BizCommentVO> listPage(int pageIndex, int rows,
			SearchParam searchParam)
	{
		if (StringUtils.isNull(searchParam.getStartDatetime()))
		{
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(),
							Constants.START_PERIOD), "yyyy-mm-dd")
					+ " 00:00:00");
		} else
		{
			searchParam.setStartDatetime(searchParam.getStartDatetime()
					+ " 00:00:00");
		}
		if (StringUtils.isNull(searchParam.getEndDatetime()))
		{
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(),
					"yyyy-mm-dd") + " 23:59:59");
		} else
		{
			searchParam.setEndDatetime(searchParam.getEndDatetime()
					+ " 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<BizCommentVO> page = new Page<BizCommentVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizCommentMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizComment bizComment)
	{
		bizComment.setPublishDt(new Date());
		bizCommentMapper.update(bizComment);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizComment bizComment)
	{
		bizComment.setPublishDt(new Date());
		bizCommentMapper.insert(bizComment);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizCommentMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileInsertComment(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizComment bizComment = new BizComment();
		bizComment.setContent(parms.getContent());
		bizComment.setGoodsScore(parms.getGoodsScore());
		bizComment.setParentId(parms.getParentId());
		bizComment.setGoodsId(parms.getGoodsId());
		bizComment.setOrderId(parms.getOrderId());
		bizComment.setType(parms.getCommentType());
		if(parms.getIsNullUser() == null)
		{
			bizComment.setIsNullUser(0);
		}
		else
		{
			bizComment.setIsNullUser(parms.getIsNullUser());
		}
		bizComment.setShopId(parms.getShopId());
		bizComment.setCommentLevel(parms.getCommentLevel());
		bizComment.setPublishDt(new Date());
		bizComment.setUserId(sessionBean.getUserId());
		if(parms.getCommentType() == 2)
		{
			BizComment biz = new BizComment();
			biz.setIdKey(parms.getParentId());
			biz.setIsReply(1);
			bizCommentMapper.update(biz);
		}
		
		bizCommentMapper.insert(bizComment);
		JsonResult jr = new JsonResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", bizComment.getIdKey());
		jr.setData(map);
		String json = ResultBuilderUtil.jsonBuild(jr);
		return json;
	}

	@Override
	public String mobileQueryCommentByGoodsId(MobileParms parms)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizCommentVO> page = new Page<BizCommentVO>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("goodsId", parms.getGoodsId());
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		page.setParams(map);
		List<BizCommentVO> list = bizCommentMapper.mobileQueryCommentByGoodsId(page);
		List<BizCommentVO> jsonList = new ArrayList<BizCommentVO>();
		BizCommentVO count = bizCommentMapper.queryCommentCount(parms.getGoodsId());
		JsonResult jr = new JsonResult();
		if(list.size() > 0)
		{
			for (BizCommentVO bizCommentVO : list)
			{
				if(bizCommentVO.getIsNullUser() != null)
				{
					if(bizCommentVO.getIsNullUser() == 0L)
					{
						if(bizCommentVO.getUserName() == null || bizCommentVO.getUserName().length() == 1)
						{
							bizCommentVO.setUserName("匿名用户");
						}
						else
						{
							String name = bizCommentVO.getUserName().substring(0, 1) + "*" + bizCommentVO.getUserName().substring(bizCommentVO.getUserName().length() - 1);
							bizCommentVO.setUserName(name);
						}
					}
				}
				List<BizCommentImage> imageList = bizCommentImageMapper.queryByCommentIdkey(bizCommentVO.getIdKey());
				bizCommentVO.setListImage(imageList);
				jsonList.add(bizCommentVO);
			}
			JsonReturnBizComment jsonComment = new JsonReturnBizComment();
			jsonComment.setCommentCount(count.getCommentCount());
			jsonComment.setGoodsAveScore(count.getGoodsAveScore());
			jsonComment.setList(jsonList);
			jr.setData(jsonComment);
		}
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileQueryCommentByShopId(MobileParms parms)
	{
		Map<String, Object> map = new HashMap<String, Object>(); 
		Page<BizCommentVO> page = new Page<BizCommentVO>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("shopId", parms.getShopId());
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		page.setParams(map);
		List<BizCommentVO> list = bizCommentMapper.mobileQueryCommentByShopId(page);
		List<BizCommentVO> jsonList = new ArrayList<BizCommentVO>();
		for (BizCommentVO bizCommentVO : list)
		{
			if(bizCommentVO.getIsNullUser() == null)
				bizCommentVO.setIsNullUser(0);
			if(bizCommentVO.getIsNullUser() == 0L)
			{
				if(bizCommentVO.getUserName() == null || bizCommentVO.getUserName().length() == 1)
				{
					bizCommentVO.setUserName("匿名用户");
				}
				else
				{
					String name = bizCommentVO.getUserName().substring(0, 1) + "*" + bizCommentVO.getUserName().substring(bizCommentVO.getUserName().length() - 1);
					bizCommentVO.setUserName(name);
				}
			}
			if(bizCommentVO.getOrderId() != null)
			{
				BizOrder biz = bizOrderMapper.queryByIdkey(bizCommentVO.getOrderId());
				if(biz != null)
				{
					if(biz.getOrderDt() != null)
						bizCommentVO.setOrderDate(biz.getOrderDt());
					if(biz.getOrderCost() != null)
						bizCommentVO.setOrderUnit(biz.getOrderCost());
				}
			}
			List<BizCommentImage> imageList = bizCommentImageMapper.queryByCommentIdkey(bizCommentVO.getIdKey());
			bizCommentVO.setListImage(imageList);
			jsonList.add(bizCommentVO);
		}
		JsonResult jr = new JsonResult();
		jr.setData(jsonList);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileUploadImage(HttpSession session, MobileParms parms)
	{
		String path = session.getServletContext().getRealPath("/")+Constants.MOBILE_COMMENT_UPLOAD_FILE;
		List<MultipartFile> listFile = ImageUtil.getMobileImageFiles(parms);
		if(listFile.size() != 0)
		{
			for (MultipartFile mfile : listFile)
			{
				if(!mfile.isEmpty())
				{
					String imageUrl = commonService.mobileUploadFile(mfile, path, "comment");
					BizCommentImage biz = new BizCommentImage();
					biz.setImageUrl(imageUrl);
					biz.setCommentId(parms.getIdKey());
					bizCommentImageMapper.insert(biz);
				}
			}
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

}
