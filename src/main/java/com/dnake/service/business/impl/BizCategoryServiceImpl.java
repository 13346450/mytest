package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.EasyUiTreeModel;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizCategoryMapper;
import com.dnake.service.business.BizCategoryService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.utils.Constants;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;

/**
 * 类目管理实现类 BizCategoryServiceImpl <br/>
 * 2014年9月9日 上午11:13:55 <br/>
 * 
 * @author chen qige
 * @version
 */
@Service
public class BizCategoryServiceImpl extends BaseServiceImpl implements
		BizCategoryService {

	@Resource
	private BizCategoryMapper bizCategoryMapper;

	@Override
	public BizCategory selectById(Long id) {
		return bizCategoryMapper.selectById(id);
	}

	@Override
	public List<BizCategory> selectByParentId(Long parentId) {
		return bizCategoryMapper.selectByParentId(parentId);
	}

	@Override
	public String mobileQuery(MobileParms parms) {
		Long temp = null;
		String tempName=null;
		/* 对手机传入的参数对应到数据库的大类id进行处理 */
		if (parms.getModelId() != null) {
			temp = parms.getModelId();
			if (temp == 1l) {
				tempName="社区商城";
				temp = Constants.BIZ_CATEGORY_COMMUNITYMALL_ID;
			} else if (temp == 2l) {
				temp = Constants.BIZ_CATEGORY_FOODS_ID;
				tempName="餐饮美食";
			} else if (temp == 3l) {
				temp = Constants.BIZ_CATEGORY_LEISURE_ID;
				tempName="休闲健身";
			} else if (temp == 5l) {
				temp = Constants.BIZ_CATEGORY_NOTICE_ID;
				tempName="通知公告";
			} else if (temp == 6l) {
				temp = Constants.BIZ_CATEGORY_GOVEMMENTAFFAIRS_ID;
				tempName="政务之窗";
			} else if (temp == 7l) {
				temp = Constants.BIZ_CATEGORY_HOUSEKEEPING_ID;
				tempName="家政服务";
			} else if (temp == 8l) {
				temp = Constants.BIZ_CATEGORY_FORUM_ID;
				tempName="社区论坛";
			} else if (temp == 9l) {
				temp = Constants.BIZ_CATEGORY_PROPERTYSERVICES_ID;
				tempName="物业服务";
			}
		} else {
			temp = parms.getId();
		}
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizCategory.class, "idKey", "categoryName", "iconUrl", "simages",
				"limages", "showSimages", "showLimages");
		JsonResult jr = new JsonResult();
		List<BizCategory> list=bizCategoryMapper.queryByParentNameAndCommunity(getUserBean().getDeptId(),tempName);
		//List<BizCategory> list = selectByParentId(temp);
		jr.setData(list);
		String json = ResultBuilderUtil.jsonBuild(jr, filter);
		return json;
	}

	@Override
	public List<BizCategory> queryChildrenByParentId(Long deptId, String categoryName) {
		switch(categoryName)
		{
			case "sqsc":
				categoryName = "社区商城";
				break;
			case "cyms":
				categoryName = "餐饮美食";
				break;
			case "xxjs":
				categoryName = "休闲健身";
				break;
			case "tzgg":
				categoryName = "通知公告";
				break;
			case "zwzc":
				categoryName = "政务之窗";
				break;
			case "jzfw":
				categoryName = "家政服务";
				break;
			case "sqlt":
				categoryName = "社区论坛";
				break;
			case "wyfw":
				categoryName = "物业服务";
				break;
		}
		return bizCategoryMapper.queryByParentNameAndCommunity(deptId, categoryName);
	}

	@Override
	public BizCategory edit(Long deptId) {
		return bizCategoryMapper.edit(deptId);
	}

	@Override
	public String insert(BizCategory sysDept) {
		sysDept.setChgDt(new Date());
		sysDept.setLastMark(1);
		bizCategoryMapper.insert(sysDept);
		return "{\"result\":\"增加部门成功！\",\"idKey\":" + sysDept.getIdKey() + "}";
	}

	@Transactional
	public String insertDeptUpdateParentLaskMark(BizCategory bizCategory) {
		bizCategory.setChgDt(new Date());
 		BizCategory parentcate= bizCategoryMapper.selectById(bizCategory.getParentId());
		bizCategory.setLastMark(1);
		// sysDept.setIsBranch(0);
		bizCategory.setCommunityId(parentcate.getCommunityId());
		bizCategoryMapper.insert(bizCategory);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", bizCategory.getParentId());
		map.put("lastMark", 0);
		bizCategoryMapper.updateLastMark(map);

		writeLog(
				Constants.FUNC_MENU_NM_DEPT,
				Constants.FUNC_OPER_NM_CREATE,
				"idKey:" + bizCategory.getIdKey() + ",cdNm:"
						+ bizCategory.getCategoryName());
		return "{\"successMsg\":\"增加部门成功！\",\"idKey\":\"" + bizCategory.getIdKey()
				+ "\",\"parentId\":\"" + bizCategory.getParentId()+"\",\"communityId\":\""+bizCategory.getCommunityId()
				+ "\",\"cdNm\":\"" + bizCategory.getCategoryName() + "\"}";
	}

	@Transactional
	public String deleteDeptUpdateParentLastMark(Long deptId, Long parentId) {
		BizCategory sysDept = bizCategoryMapper.edit(deptId);
		bizCategoryMapper.delete(deptId);
		if (!haveChildren(parentId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			bizCategoryMapper.updateLastMark(map);
		}
		writeLog(
				Constants.FUNC_MENU_NM_DEPT,
				Constants.FUNC_OPER_NM_DELETE,
				"idKey:" + sysDept.getIdKey() + ",cdNm:"
						+ sysDept.getCategoryName());
		return "{\"successMsg\":\"删除目录成功！\",\"idKey\":\"" + deptId
				+ "\",\"flag\":true}";
	}

	@Transactional
	public String update(BizCategory sysDept) {
		sysDept.setChgDt(new Date());
		bizCategoryMapper.update(sysDept);
		writeLog(
				Constants.FUNC_MENU_NM_DEPT,
				Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + sysDept.getIdKey() + ",cdNm:"
						+ sysDept.getCategoryName());
		return "{\"successMsg\":\"商品目录更新成功！\",\"idKey\":\""
				+ sysDept.getIdKey() + "\",\"parentId\":\""
				+ sysDept.getParentId() +"\",\"communityId\":\""+sysDept.getCommunityId()+ "\",\"cdNm\":\""
				+ sysDept.getCategoryName() + "\"}";
	}

	@Transactional
	public String updateParentIdByIdKey(Long idKey, Long oldParentId,
			Long targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		bizCategoryMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", "0");
		bizCategoryMapper.updateLastMark(map);
		if (!haveChildren(oldParentId)) {
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", "1");
			bizCategoryMapper.updateLastMark(map);
		}
		BizCategory sysDept = bizCategoryMapper.edit(idKey);
		writeLog(
				Constants.FUNC_MENU_NM_DEPT,
				Constants.FUNC_OPER_NM_TREEDRAG,
				"idKey:" + sysDept.getIdKey() + ",cdNm:"
						+ sysDept.getCategoryName() + ",oldParentId:"
						+ oldParentId + ",newParentId:" + targetId);

		return "{\"successMsg\":\"移动成功！\"}";
	}

	public DhtmlxTreeModel creatSysDeptTree(Long nodeId,Long communityId) {
		SessionBean sessionBean = getUserBean();
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != nodeId && nodeId == -1) {
		//	BizCategory sd = bizCategoryMapper.edit(sessionBean.getDeptId());
			BizCategory sd = bizCategoryMapper.queryRootByCommunityId(communityId);
			if (null != sd) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sd.getIdKey().toString());
				node.setText(sd.getCategoryName());
				node.setItem(getChildren(sd.getIdKey()));
				node.setOpen("1");
				children.add(node);
			}
			tree.setItem(children);
		} else {
			tree.setItem(getChildren(nodeId));
		}
		return tree;
	}

	/**
	 * 获取dhtmlxtree子节点
	 * 
	 * @Title: getChildren
	 * @author tw 2013-10-17
	 * @Description:
	 * @param @param parentId
	 * @param @return
	 * @return List<DhtmlxTreeModel>
	 * @throws
	 */
	private List<DhtmlxTreeModel> getChildren(Long parentId) {
		List<BizCategory> list = bizCategoryMapper
				.queryChildrenByParentId(parentId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (BizCategory sysDept : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysDept.getIdKey().toString());
				node.setText(sysDept.getCategoryName());
				node.setChild((sysDept.getLastMark() ^ 1) + "");
				children.add(node);
			}
		}
		return children;
	}

	/**
	 * 判断是否有子节点
	 * 
	 * @Title: haveChildren
	 * @author tw 2013-10-15
	 * @Description:
	 * @param @param parentId
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean haveChildren(Long parentId) {
		List<BizCategory> list = bizCategoryMapper
				.queryChildrenByParentId(parentId);
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查找所属的二级单位
	 */
	public Long querySecondDeptId(Long deptId) {
		return bizCategoryMapper.querySecondDeptId(deptId);
	}

	@Override
	public Long queryMaxOrderCdByPId(Long parentId) {
		return bizCategoryMapper.queryMaxOrderCdByPId(parentId);
	}

	@Override
	public List<EasyUiTreeModel> selectCategoryTree(Long communityId) {
		List<EasyUiTreeModel> list = new ArrayList<EasyUiTreeModel>();
		BizCategory sd = bizCategoryMapper.queryRootByCommunityId(communityId);
		if (sd!=null){
		list = bizCategoryMapper.selectCategoryTree(Constants.BIZ_CATEGORY_ROOT_ID);}
		return list;
	}

	@Override
	public String salesQueryCategory() {
		Map<String,Object> returnMap= new HashMap<String,Object>();
		returnMap.put("communityMall",  (Constants.BIZ_CATEGORY_COMMUNITYMALL_ID));
		List<BizCategory> list=selectByParentId(Constants.BIZ_CATEGORY_FOODS_ID);
		list.addAll(selectByParentId(Constants.BIZ_CATEGORY_LEISURE_ID));
		returnMap.put("groupBuy",list);
		returnMap.put("Housekeeping",selectByParentId(Constants.BIZ_CATEGORY_HOUSEKEEPING_ID));
		JsonResult jr=new JsonResult();
		jr.setData(returnMap);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public Page<BizCategoryVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		Page<BizCategoryVO> page = new Page<BizCategoryVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		page.setParams(map);
		page.setRows(bizCategoryMapper.queryADCategory(page));
		return page;
	}
		

}
