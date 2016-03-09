package com.dnake.service.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.business.BizOrder;
import com.dnake.domain.business.BizRefundVO;
import com.dnake.domain.business.BizRefund;
import com.dnake.domain.business.BizRepairImage;
import com.dnake.mapper.business.BizOrderMapper;
import com.dnake.mapper.business.BizRefundMapper;
import com.dnake.service.business.BizRefundService;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ImageUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;

/**
 * 退货款业务实现类
 *  @author zgj
 *	日期：2015年9月19日上午11:46:39
 *  描述：
 */
@Service
public class BizRefundServiceImpl extends BaseServiceImpl implements
		BizRefundService
{
	@Resource
	private BizRefundMapper bizRefundMapper;
	@Resource
	private BizOrderMapper bizOrderMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<BizRefundVO> listPage(int pageIndex, int rows,
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
		Page<BizRefundVO> page = new Page<BizRefundVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizRefundMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizRefund bizRefund)
	{
		// bizRefund.setChgDt(new Date());
		bizRefundMapper.update(bizRefund);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizRefund bizRefund)
	{
		// bizRefund.setChgDt(new Date());
		bizRefundMapper.insert(bizRefund);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizRefundMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileInsert(MobileParms parms)
	{
		BizRefund bizRefundTemp = bizRefundMapper.queryByOrderId(parms.getOrderId());
		if(bizRefundTemp == null)
		{
			BizRefund biz = new BizRefund();
			biz.setOrderId(parms.getOrderId());
			biz.setRefundDt(new Date());
			biz.setRefundExplain(parms.getRefundExplain());
			biz.setRefundMoney(parms.getRefundMoney());
			biz.setRefundReason(parms.getRefundReason());
			biz.setRefundType(parms.getRefundType());
			bizRefundMapper.insert(biz);
			BizOrder bizOrder = bizOrderMapper.queryByIdkey(parms.getOrderId());
			if(bizOrder != null)
			{
				if(bizOrder.getOrderStatus() == 4)
				{
					BizOrder bizOrderTemp = new BizOrder();
					bizOrderTemp.setIdKey(bizOrder.getIdKey());
					bizOrderTemp.setOrderStatus(9);
					bizOrderMapper.update(bizOrderTemp);
				}
			}
			return ResultBuilderUtil.resultIncludeValue("idKey", biz.getIdKey());
		}
		else
		{
			return ResultBuilderUtil.resultIncludeValue("idKey", bizRefundTemp.getIdKey());
		}
	}

	@Override
	public String mobileUploadImage(MobileParms parms)
	{
		String path = session.getServletContext().getRealPath("/")+Constants.MOBILE_REPAIR_UPLOAD_FILE;
		List<MultipartFile> listFile = ImageUtil.getMobileImageFiles(parms);
		BizRefund bizRefund = new BizRefund();
		bizRefund.setIdKey(parms.getIdKey());
		int i = 1;
		if(listFile.size() != 0)
		{
			for (MultipartFile mfile : listFile)
			{
				if(!mfile.isEmpty())
				{
					String imageUrl = commonService.mobileUploadFile(mfile, path, "refund");
					switch(i)
					{
					case 1:
						bizRefund.setImageOne(imageUrl);
						break;
					case 2:
						bizRefund.setImageTwo(imageUrl);
						break;
					case 3:
						bizRefund.setImageThree(imageUrl);
						break;
					case 4:
						bizRefund.setImageFour(imageUrl);
						break;
					}
					i++;
				}
			}
			bizRefundMapper.update(bizRefund);
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
}
