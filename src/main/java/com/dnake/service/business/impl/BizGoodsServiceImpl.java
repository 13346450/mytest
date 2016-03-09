package com.dnake.service.business.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.error.Validate;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCommentVO;
import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizGoodsImage;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.business.BizShop;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonReturnBizGoodsList;
import com.dnake.mapper.business.BizCategoryMapper;
import com.dnake.mapper.business.BizCommentMapper;
import com.dnake.mapper.business.BizGoodsImageMapper;
import com.dnake.mapper.business.BizGoodsMapper;
import com.dnake.mapper.business.BizOrderDetailsMapper;
import com.dnake.mapper.business.BizOrderMapper;
import com.dnake.mapper.business.BizShopMapper;
import com.dnake.service.business.BizGoodsService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ImageUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;

/**
 * 商品维护实现类 BizGoodsServiceImpl <br/>
 * 2014年9月11日 上午11:48:09 <br/>
 * 
 * @author chen qige
 * @version
 */
@Service
public class BizGoodsServiceImpl extends BaseServiceImpl implements
		BizGoodsService {

	@Resource
	private CommonService commonService;
	@Resource
	private BizGoodsMapper bizGoodsMapper;
	@Resource
	private BizGoodsImageMapper bizGoodsImageMapper;
	@Resource
	private BizCategoryMapper bizCategorymapper;
	@Resource
	private BizOrderDetailsMapper bizOrderDetailsMapper;
	@Resource
	private BizOrderMapper bizOrderMapper;
	@Resource
	private BizCommentMapper bizCommentMapper;
	@Resource
	private BizShopMapper bizShopMapper;

	@Override
	public BizGoods edit(Long idKey) {
		return bizGoodsMapper.edit(idKey);
	}

	@Override
	public String update(BizGoods bizGoods) {
		boolean haveOrder = (bizOrderDetailsMapper.selectByGoodsId(
				bizGoods.getIdKey()).size() != 0);
		BizCategory bizCategory = bizCategorymapper.selectById(bizGoods
				.getGoodsTypeId());
		String simages = bizGoodsMapper.edit(bizGoods.getIdKey()).getSimages();
		bizGoods.setSimages(bizGoods.getSimages() == null ? bizCategory
				.getSimages() : bizGoods.getSimages());
		bizGoods.setLimages(bizGoods.getLimages() == null ? bizCategory
				.getLimages() : bizGoods.getLimages());
		bizGoods.setShowSimages(bizGoods.getShowSimages() == null ? bizCategory
				.getSimages() : bizGoods.getShowSimages());
		bizGoods.setShowLimages(bizGoods.getShowLimages() == null ? bizCategory
				.getLimages() : bizGoods.getShowLimages());
		BizGoods bizGoods2 = bizGoodsMapper.edit(bizGoods.getIdKey());
		if ((!bizGoods.getSimages().equals(simages)) && (!haveOrder)) {// 图片更改，并且该商品没有订单，删除全部旧图片
			this.deleteImages(bizGoods2);
		}
		if ((!bizGoods.getSimages().equals(simages)) && (haveOrder)) {// 图片更改，并且该商品有订单，删除部分(大图)图片，订单详情有图片冗余
			bizGoods2.setShowSimages(null);
			bizGoods2.setSimages(null);
			this.deleteImages(bizGoods2);
		}
		SessionBean sessionBean = getUserBean();
		if (sessionBean.getRoleId() != 1) {
			bizGoods.setTelephone(bizGoods.getTelephone() == "" ? sessionBean
					.getTelephone() : bizGoods.getTelephone());
			bizGoods.setGoodsAddr(bizGoods.getGoodsAddr() == "" ? sessionBean
					.getAddr() : bizGoods.getGoodsAddr());
		}
		bizGoodsMapper.update(bizGoods);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String delete(Long idKey) {
		BizGoods bizGoods2 = bizGoodsMapper.edit(idKey);
		this.deleteImages(bizGoods2);
		bizGoodsMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	@Transactional
	public String insert(BizGoods bizGoods) {
		SessionBean sessionBean = getUserBean();
		bizGoods.setTelephone(bizGoods.getTelephone() == "" ? sessionBean
				.getTelephone() : bizGoods.getTelephone());
		bizGoods.setGoodsAddr(bizGoods.getGoodsAddr() == "" ? sessionBean
				.getAddr() : bizGoods.getGoodsAddr());
		bizGoods.setUploadDt(new Date());
		bizGoodsMapper.insert(bizGoods);
		BizGoodsImage bizGoodsImage = new BizGoodsImage();
		bizGoodsImage.setIsDefault(1);
		bizGoodsImage.setGoodsId(bizGoods.getIdKey());
		bizGoodsImage.setImgUrl(bizGoods.getShowSimages());
		bizGoodsImageMapper.insert(bizGoodsImage);
		return "{\"successMsg\":\"添加成功！\",\"flag\":true}";
	}

	@Override
	public Page<BizGoodsVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		Page<BizGoodsVO> page = new Page<BizGoodsVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		Map<String, Object> map = new HashMap<String, Object>();
		if (getUserBean().getRoleId() == Constants.SALES_ROLE_ID) {
			map.put("salesId", getUserBean().getUserId());
		}
		map.put("salesName", searchParam.getUserName());
		map.put("shopName", searchParam.getShopName());
		map.put("goodsStatus", searchParam.getStatus());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds()
					.split(","));// 增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		map.put("goodsType", searchParam.getGoodsType());
		if (searchParam.getGoodsTypeId() == null) {
			searchParam.setGoodsTypeId(1L);
		}
		// map.put("goodsTypeId", searchParam.getGoodsTypeId());
		page.setParams(map);
		page.setRows(bizGoodsMapper.queryAllPageFromParentCategory(page));
		return page;
	}

	@Override
	public String mobileQueryGoodsDetails(Long goodsid) {
		BizGoodsVO bv = bizGoodsMapper.mobileQueryGoodsDetails(goodsid);
		if (null != bv) {
			JsonResult jr = new JsonResult();
			BizCommentVO count = bizCommentMapper.queryCommentCount(bv
					.getIdKey());
			bv.setCommentCount(count.getCommentCount());
			bv.setGoodsAveScore(count.getGoodsAveScore());
			List<BizGoodsImage> list = bizGoodsImageMapper.queryByGoodsId(bv
					.getIdKey());
			bv.setListGoodsImage(list);
			BizShop bizShop = bizShopMapper.queryByIdkey(bv.getShopId());
			if (bizShop != null) {
				bv.setShopName(bizShop.getName());
				bv.setGoodsAddr(bizShop.getAddr());
				bv.setTelephone(bizShop.getTelephone());
			}
			jr.setData(bv);
			return ResultBuilderUtil.jsonBuild(jr);
		}
		return ResultBuilderUtil.statusMessage(2, "无此商品");
	}

	@Override
	public String mobileQueryGoodsList(MobileParms parms) {
		SessionBean sessionBean = getUserBean();
		Page<BizGoodsVO> page = new Page<BizGoodsVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("modelId", parms.getModelId());
		map.put("shopId", parms.getShopId());
		map.put("goodsStatus", parms.getGoodsStatus());
		if (sessionBean.getRoleId() == Constants.SALES_ROLE_ID) {
			map.put("salesId", sessionBean.getUserId());
			map.put("shopId", parms.getShopId());
		} else if (sessionBean.getRoleId() == Constants.USER_ROLE_ID) {// 住户版访问
			map.put("communityId", sessionBean.getDeptId());
			map.put("userId", sessionBean.getUserId());
			map.put("shopStatus", 2);// 店铺是开启状态
			if (parms.getGoodsStatus() == null || parms.getGoodsStatus() != 6) {// 不是活动上架商品，那么设置为普通的上架商品
				map.put("goodsStatus", 2);
			}
		}
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		page.setParams(map);
		List<BizGoodsVO> list = bizGoodsMapper.queryPage(page);
		List<BizGoodsVO> listTemp = new ArrayList<BizGoodsVO>();
		for (BizGoodsVO bizGoodsVO : list) {
			bizGoodsVO.setSalesCount(bizOrderMapper.mobileQuerySalesCount(
					bizGoodsVO.getIdKey()).getSalesCount());
			List<BizGoodsImage> list1 = bizGoodsImageMapper
					.queryByGoodsId(bizGoodsVO.getIdKey());
			bizGoodsVO.setListGoodsImage(list1);
			BizCommentVO count = bizCommentMapper.queryCommentCount(bizGoodsVO
					.getIdKey());
			bizGoodsVO.setCommentCount(count.getCommentCount());
			bizGoodsVO.setGoodsAveScore(count.getGoodsAveScore());
			listTemp.add(bizGoodsVO);
		}
		JsonReturnBizGoodsList jrb = new JsonReturnBizGoodsList();
		jrb.setTotal(page.getTotal());
		jrb.setTotalPage(page.getTotalPage());
		JsonResult jr = new JsonResult();
		jrb.setGoodsList(listTemp);
		jr.setData(jrb);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	public String deleteMulti(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < idArray.length; i++) {// 判断是否有销售记录
			if (bizOrderDetailsMapper.selectByGoodsId(Long.valueOf(idArray[i]))
					.size() == 0) {
				list.add(idArray[i]);
			}
		}
		map.put("deviceIdin", list);
		for (String idKey : list) {// 删除图片
			BizGoods bizGoods2 = bizGoodsMapper.edit(Long.valueOf(idKey));
			this.deleteImages(bizGoods2);
		}
		if (list.size() != 0) {
			bizGoodsMapper.deleteMulti(map);
		}
		if (idArray.length == list.size()) {
			return "{\"successMsg\":\"删除成功!\",\"flag\":true}";
		} else {
			return "{\"successMsg\":\"" + list.size() + "个商品删除成功,"
					+ (idArray.length - list.size())
					+ "个商品有销售记录无法删除\",\"flag\":false}";
		}

	}

	@Override
	public String pullOffMulti(String deleteIds, Integer goodsStatus,
			Timestamp autoPutOnTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "";
		if (goodsStatus.equals(3)) {
			msg = "强制下架成功";
		}else if (goodsStatus.equals(1)) {
			msg = "下架成功";
		} else if (goodsStatus.equals(2)) {
			msg = "恢复上架成功";
		} else if (goodsStatus.equals(6)) {
			msg = "设置活动成功";
		} else if (goodsStatus.equals(7) && autoPutOnTime != null) {
			// 将时间的秒数清零
			autoPutOnTime =  new Timestamp(DateTimeUtil.DateAddTime(autoPutOnTime,
					null, null, 0).getTime());
			map.put("autoPutOnTime", autoPutOnTime);
		} else {
			return "{\"successMsg\":\"未知操作\",\"flag\":false}";
		}
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		map.put("goodsStatus", goodsStatus);
		bizGoodsMapper.pullOffMulti(map);
		return "{\"successMsg\":\"" + msg + "\",\"flag\":true}";
	}

	@Override
	public String mobileInsertGoods(MobileParms parms) {
		BizCategory bizCategory = bizCategorymapper
				.selectById(parms.getIdKey());
		if (bizCategory == null) {
			return ResultBuilderUtil.statusMessage(2, "无此目录");
		}
		Validate.checkHaveNull(parms.getGoodsName(), parms.getQuantity(),
				parms.getShopId(), parms.getIdKey(), parms.getGoodsNote(),
				parms.getUnitPrice(), parms.getDiscountPrice());
		BizGoods bizGoods = new BizGoods();
		bizGoods.setSimages(bizCategory.getSimages());
		bizGoods.setLimages(bizCategory.getLimages());
		bizGoods.setGoodsName(parms.getGoodsName());
		bizGoods.setGoodsStatus(1);// 默认添加商品状态为销售中
									// 针对活动商品默认添加会上架问题，暂时改为商品添加后状态为未上架1
		bizGoods.setQuantity(parms.getQuantity());
		bizGoods.setShopId(parms.getShopId());
		bizGoods.setGoodsTypeId(parms.getIdKey());
		bizGoods.setGoodsNote(parms.getGoodsNote());
		bizGoods.setUnitPrice(parms.getUnitPrice());
		bizGoods.setDiscountPrice(parms.getDiscountPrice());
		bizGoods.setUploadDt(new Date());
		SessionBean sessionBean = getUserBean();
		bizGoods.setSalesId(sessionBean.getUserId());
		bizGoods.setGoodsAddr(sessionBean.getAddr());
		bizGoods.setTelephone(sessionBean.getTelephone());
		bizGoods.setCommunityId(sessionBean.getDeptId());
		bizGoodsMapper.insert(bizGoods);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", bizGoods.getIdKey());
		JsonResult jr = new JsonResult();
		jr.setData(map);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileUpdateGoods(MobileParms parms) {
		BizCategory bizCategory = bizCategorymapper.selectById(parms
				.getImagesId());// 根据内置图片的目录更新图片
		boolean haveOrder = (bizOrderDetailsMapper.selectByGoodsId(
				parms.getIdKey()).size() != 0);
		BizGoods bizGoods = new BizGoods();
		if (bizCategory != null) {
			bizGoods.setSimages(bizCategory.getSimages());
			bizGoods.setLimages(bizCategory.getLimages());
			bizGoods.setShowSimages(bizCategory.getShowSimages());
			bizGoods.setShowLimages(bizCategory.getShowLimages());
			BizGoods originalBizGoods = bizGoodsMapper.edit(parms.getIdKey());
			String simages = originalBizGoods.getSimages();
			if ((!bizGoods.getSimages().equals(simages)) && (!haveOrder)) {// 图片更改，并且该商品没有订单，删除全部旧图片
				this.deleteImages(originalBizGoods);
			}
			if ((!bizGoods.getSimages().equals(simages)) && (haveOrder)) {// 图片更改，并且该商品有订单，删除部分(大图)图片，订单详情有图片冗余
				originalBizGoods.setShowSimages(null);
				originalBizGoods.setSimages(null);
				this.deleteImages(originalBizGoods);
			}
		}
		bizGoods.setGoodsName(parms.getGoodsName());
		bizGoods.setIdKey(parms.getIdKey());
		bizGoods.setGoodsTypeId(parms.getGoodsTypeId());
		bizGoods.setGoodsStatus(parms.getGoodsStatus());
		bizGoods.setQuantity(parms.getQuantity());
		bizGoods.setGoodsNote(parms.getGoodsNote());
		bizGoods.setUnitPrice(parms.getUnitPrice());
		bizGoods.setDiscountPrice(parms.getDiscountPrice());
		bizGoods.setUploadDt(new Date());
		bizGoodsMapper.update(bizGoods);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", bizGoods.getIdKey());
		JsonResult jr = new JsonResult();
		jr.setData(map);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileDeleteGoods(MobileParms parms) {
		if (bizOrderDetailsMapper.selectByGoodsId(parms.getIdKey()).size() != 0) {
			return ResultBuilderUtil.statusMessage(2, "查无此类");
		}
		bizGoodsMapper.delete(parms.getIdKey());
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public void deleteImages(BizGoods bizGoods) {
		List<String> arrayList = new ArrayList<String>();
		if (bizGoods.getShowSimages() != null) {
			arrayList.add(bizGoods.getShowSimages());
		}
		if (bizGoods.getSimages() != null) {
			arrayList.add(bizGoods.getSimages());
		}
		arrayList.add(bizGoods.getLimages());
		arrayList.add(bizGoods.getShowLimages());
		File file = null;
		for (String path : arrayList) {
			file = new File(session.getServletContext().getRealPath("/") + path);
			file.delete();
		}
	}

	@Override
	@Transactional
	public String mobileUploadImage(HttpSession session, MobileParms parms) {
		String path = session.getServletContext().getRealPath("/")
				+ Constants.MOBILE_GOODS_UPLOAD_FILE;
		List<MultipartFile> listFile = ImageUtil.getMobileImageFiles(parms);
		List<BizGoodsImage> lists = bizGoodsImageMapper.queryByGoodsId(parms
				.getIdKey());
		if (lists.size() > 0) {
			String ids = "";
			for (BizGoodsImage bizGoodsImage : lists) {
				String imagePath = session.getServletContext().getRealPath("/")
						+ bizGoodsImage.getImgUrl();
				commonService.deleteImage(imagePath); // 删除图片文件
				ids += bizGoodsImage.getIdKey() + ",";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imagesIdin", Arrays.asList(ids.split(",")));
			bizGoodsImageMapper.deleteMulti(map); // 删除多图片
		}
		if (listFile.size() != 0) {
			int i = 1;
			for (MultipartFile mfile : listFile) {
				if (!mfile.isEmpty()) {
					String imageUrl = commonService.mobileUploadFile(mfile,
							path, "goods");
					BizGoodsImage biz = new BizGoodsImage();
					biz.setImgUrl(imageUrl);
					biz.setGoodsId(parms.getIdKey());
					if (i == 1) {
						biz.setIsDefault(1);
						/**
						 * 将默认图片地址设置为商品字段的showSimages
						 */
						BizGoods bizGoods = new BizGoods();
						bizGoods.setIdKey(biz.getGoodsId());
						bizGoods.setShowSimages(imageUrl);
						bizGoods.setSimages(imageUrl);
						bizGoods.setLimages(imageUrl);
						bizGoods.setShowLimages(imageUrl);
						bizGoodsMapper.update(bizGoods);
					}
					i++;
					bizGoodsImageMapper.insert(biz);
				}
			}
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String mobilePullOffMulti(MobileParms parms) {
		// 数据校验
		String ids = parms.getIds();
		Integer goodsStatus = parms.getGoodsStatus();
		Timestamp autoPutOnTime = parms.getAutoPutOnTime();
		Validate.checkHaveNull(parms.getIds(), parms.getGoodsStatus());
		Validate.regex(ids, "[\\d+,]*\\d+", null);
		if (goodsStatus == 7 && autoPutOnTime == null) {// 定时上架必须填写时间
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		// 校验结束

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(ids.split(",")));
		map.put("goodsStatus", goodsStatus);
		if (goodsStatus != 7) {// 只有活动商品定时上架时，该字段才有意义
			autoPutOnTime = null;
		}
		if (autoPutOnTime != null) {// 将秒数清0
			autoPutOnTime =  new Timestamp(DateTimeUtil.DateAddTime(autoPutOnTime,
					null, null, 0).getTime());
		}
		map.put("autoPutOnTime", autoPutOnTime);
		bizGoodsMapper.pullOffMulti(map);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

}
