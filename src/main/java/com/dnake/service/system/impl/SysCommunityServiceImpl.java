package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.domain.system.SysDept;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.mapper.business.BizCategoryMapper;
import com.dnake.mapper.business.BizChainMapper;
import com.dnake.mapper.system.SysDeptMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysCommunityService;
import com.dnake.service.system.SysDeptService;
import com.dnake.utils.Constants;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;

/**
 * 小区管理实现类 SysCommunityServiceImpl <br/>
 * 2014年10月21日 上午10:11:29 <br/>
 * 
 * @author chen qige
 * @version
 */
@Service
public class SysCommunityServiceImpl extends BaseServiceImpl implements
		SysCommunityService {

	@Resource
	private SysCommunityMapper sysCommunityMapper;
	@Resource
	private SysDeptMapper sysDeptMapper;
	@Resource
	private SysDeptService sysDeptService;
	@Resource
	private BizCategoryMapper bizCategoryMapper;
	@Resource
	private BizChainMapper bizChainMapper;

	public List<SysCommunity> listAll() {
		return sysCommunityMapper.queryAll();
	}

	public SysCommunity edit(Long userId) {
		return sysCommunityMapper.queryById(userId);
	}

	public SysCommunity queryList(Long userId) {
		return sysCommunityMapper.queryList(userId);
	}

	@Transactional
	public String update(SysCommunity sysCommunity) {
		sysCommunityMapper.update(sysCommunity);
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + sysCommunity.getIdKey() + ",communityName:"
						+ sysCommunity.getCommunityName());
		return "{\"Msg\":\"更新人员信息成功！\",\"idKey\":\"" + sysCommunity.getIdKey()
				+ "\",\"DeptId\":\"" + sysCommunity.getDeptId()
				+ "\",\"communityName\":\"" + sysCommunity.getCommunityName()
				+ "\",\"ty\":\"update\",\"flag\":true}";
	}

	@Transactional
	public String delete(Long idKey) {
		if (null == idKey) {
			return "{\"errorMsg\":\"请选中需要删除的行！\",\"flag\":false}";
		}
		SysCommunity sysCommunity = sysCommunityMapper.queryList(idKey);
		sysCommunityMapper.delete(idKey);
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_DELETE,
				"idKey:" + sysCommunity.getIdKey() + ",communityName:"
						+ sysCommunity.getDeptId());
		return "{\"Msg\":\"删除人员成功！\",\"idKey\":\"" + sysCommunity.getIdKey()
				+ "\",\"flag\":true}";
	}

	public Page<SysCommunity> listPage(int pageIndex, int rows, Long deptID) {
		Page<SysCommunity> page = new Page<SysCommunity>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptID", deptID);
		map.put("communityIds",
				getUserBean().getPopedomCommunityIds().split(","));// 增加管理员是否可以查看该小区权限判断
		page.setParams(map);
		page.setRows(sysCommunityMapper.queryPage(page));
		return page;

	}

	public List<SysCommunity> queryBydeptID(Long deptID) {
		return sysCommunityMapper.queryBydeptID(deptID);
	}

	@Transactional
	public String updateDeptIDByIdKey(Long idKey, Long oldDeptId, Long targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("deptID", targetId);
		sysCommunityMapper.updateDeptIDByIdKey(map);
		SysCommunity sysCommunity = sysCommunityMapper.edit(idKey);
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_TREEDRAG,
				"idKey:" + sysCommunity.getIdKey() + ",communityName:"
						+ sysCommunity.getDeptId() + ",oldParentId:"
						+ oldDeptId + ",newDeptId:" + targetId);
		return "{\"successMsg\":\"移动成功！\",\"flg\":true}";
	}

	@Transactional
	public String insert(SysCommunity sysCommunity) {
		SysDept sysdept = sysDeptMapper.edit(sysCommunity.getDeptId());// 区
		sysdept = sysDeptMapper.edit(sysdept.getParentId());// 市
		sysCommunity.setCityId(sysdept.getIdKey());
		sysdept = sysDeptMapper.edit(sysdept.getParentId());// 省
		sysCommunity.setProvinceId(sysdept.getIdKey());
		sysCommunityMapper.insert(sysCommunity);
		// 插入默认类目，以万科湖心岛为默认设置，communityId为10028
		bizCategoryMapper.insertDefaultRoot(sysCommunity.getIdKey());// 插入根类目
		bizCategoryMapper.insertDefault(sysCommunity.getIdKey());// 插入其他类目

		// 插入默认便民服务链接
		bizChainMapper.insertDefault(sysCommunity.getIdKey());
		// 更新session里的权限小区ids
		getUserBean().setPopedomCommunityIds(
				getUserBean().getPopedomCommunityIds() + ","
						+ sysCommunity.getIdKey());

		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_CREATE,
				"idKey:" + sysCommunity.getIdKey() + ",communityName:"
						+ sysCommunity.getCommunityName());
		return "{\"Msg\":\"增加用户信息成功！\",\"idKey\":\"" + sysCommunity.getIdKey()
				+ "\",\"DeptId\":\"" + sysCommunity.getDeptId()
				+ "\",\"communityName\":\"" + sysCommunity.getCommunityName()
				+ "\",\"ty\":\"add\",\"flag\":true}";

	}

	public DhtmlxTreeModel creatSysDeptOrCommunityTree(Long nodeId) {
		SessionBean sessionBean = getUserBean();
		/*
		 * if (sessionBean.getRoleId() == Long.parseLong(String
		 * .valueOf(Constants.SYSTEM_ADMIN_ROLE_ID))) {
		 */
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != nodeId && nodeId == -1) {
			SysDept sd = sysDeptMapper.edit(1L);
			if (null != sd) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sd.getIdKey().toString());
				node.setText(sd.getCdNm());
				node.setItem(getFirstOneChildren(1L));
				node.setOpen("1");
				children.add(node);
			}
			tree.setItem(children);
		} else {
			if (!sysDeptService.haveChildren(nodeId)) {// 部门没有子节点
				tree.setItem(getFirstThreeChildren(nodeId, false));
			} else {// 部门有子节点
				tree.setItem(getFirstTwoChildren(nodeId));
			}
		}
		return tree;
	}

	private List<DhtmlxTreeModel> getFirstOneChildren(Long parentId) {
		List<SysDept> list = sysDeptMapper.queryChildrenByParentId(parentId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		String Marks = "";
		if (null != list && list.size() > 0) {
			for (SysDept sysDept : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysDept.getIdKey().toString());
				node.setText(sysDept.getCdNm());
				Marks = (sysDept.getLastMark() ^ 1) + "";
				if (Marks.equals("0")) {
					List<SysCommunity> list1 = sysCommunityMapper
							.queryBydeptID(sysDept.getIdKey());
					if (null != list1 && list1.size() > 0) {
						node.setChild("1");
					} else {
						node.setChild("0");
						node.setIm0("folderClosed.gif");
					}
				} else {
					node.setChild("1");
				}
				children.add(node);
			}
		} else {
			return children = getFirstThreeChildren(parentId, false);
		}
		return children;
	}

	/**
	 * 获取部门的子节点部门
	 * 
	 * @param parentId
	 * @return
	 * @author cqg 2015年8月4日
	 */
	private List<DhtmlxTreeModel> getFirstTwoChildren(Long parentId) {
		List<SysDept> list = sysDeptMapper.queryChildrenByParentId(parentId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysDept sysDept : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysDept.getIdKey().toString());
				node.setText(sysDept.getCdNm());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptId", sysDept.getIdKey());
				map.put("communityIds",
						getUserBean().getPopedomCommunityIds().split(","));// 增加管理员是否可以查看该小区权限判断
				List<SysCommunity> list1 = sysCommunityMapper
						.queryBydeptIdAndPopedomIds(map);//查看是否有子节点小区
				Boolean b=sysDeptService.haveChildren(sysDept.getIdKey());//参看是否有子部门
				if (b||list1.size() > 0) {
					node.setChild("1");
				} else {
					 node.setChild("0");
					 node.setIm0("folderClosed.gif");
				}
				children.add(node);
			}
		}
		return children;
	}

	/**
	 * 根据部门id获取子节点的小区
	 * 
	 * @param deptId
	 * @param flg
	 * @return
	 * @author cqg 2015年8月4日
	 */
	private List<DhtmlxTreeModel> getFirstThreeChildren(Long deptId, Boolean flg) {
		// List<SysCommunity> list = sysCommunityMapper.queryBydeptID(deptId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", deptId);
		map.put("communityIds",
				getUserBean().getPopedomCommunityIds().split(","));// 增加管理员是否可以查看该小区权限判断
		List<SysCommunity> list = sysCommunityMapper
				.queryBydeptIdAndPopedomIds(map);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysCommunity sysCommunity : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(0 + sysCommunity.getIdKey().toString());
				node.setText(sysCommunity.getCommunityName());
				if (flg) {
					node.setChild("1");
				}
				node.setIm0("community.png");
				children.add(node);
			}
		}
		return children;
	}

	@Override
	public Long queryMaxOrderCdByPId(Long parentId) {
		return sysCommunityMapper.queryMaxOrderCdByPId(parentId);
	}

	public boolean haveChildren(Long parentId) {
		List<SysCommunity> list = sysCommunityMapper.queryBydeptID(parentId);
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String mobileQueryByDeptId(Long parentId) {
		List<SysCommunity> list = sysCommunityMapper.queryBydeptID(parentId);
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				SysCommunity.class, "idKey", "communityName");
		JsonResult jr = new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public SysCommunityVO queryCommunityVOById(Long id) {
		SysCommunityVO sysCommunityVO = sysCommunityMapper
				.queryCommunityVOById(id);
		return sysCommunityVO;
	}

	@Override
	public DhtmlxTreeModel creatSysPopedomTree(Long nodeId) {
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> childrenRoot = new ArrayList<DhtmlxTreeModel>();
		DhtmlxTreeModel node = new DhtmlxTreeModel();
		SysDept sd = sysDeptMapper.edit(1l);
		if (null != sd) {
			node.setId(sd.getIdKey().toString());
			node.setText(sd.getCdNm());
			// List<SysDept> list1=sysDeptMapper.queryChildrenByParentId(1l);
			// List<DhtmlxTreeModel> children1 = new
			// ArrayList<DhtmlxTreeModel>();
			// for (SysDept sysDept1 : list1) {//第一子级
			// DhtmlxTreeModel node1 = new DhtmlxTreeModel();
			// node1.setId(sysDept1.getIdKey()+"");
			// node1.setText(sysDept1.getCdNm());
			// List<DhtmlxTreeModel> children2 = new
			// ArrayList<DhtmlxTreeModel>();
			// List<SysDept>
			// list2=sysDeptMapper.queryChildrenByParentId(sysDept1.getIdKey());
			// for (SysDept sysDept2 : list2) {
			// DhtmlxTreeModel node2 = new DhtmlxTreeModel();
			// node2.setId(sysDept2.getIdKey()+"");
			// node2.setText(sysDept2.getCdNm());
			// children2.add(node2);
			// }
			// node1.setItem(children2);
			// //node1.setItem(item);
			// children1.add(node1);
			// }
			// node.setItem(children1);
			node.setItem(getSubTree(1L));
			node.setOpen("1");
		}
		childrenRoot.add(node);
		tree.setItem(childrenRoot);
		return tree;
	}

	/**
	 * 递归调用获取全部子树
	 * 
	 * @param id
	 * @return
	 * @author cqg 2015年7月3日
	 */
	private List<DhtmlxTreeModel> getSubTree(Long id) {
		List<DhtmlxTreeModel> children1 = new ArrayList<DhtmlxTreeModel>();
		List<SysDept> list1 = sysDeptMapper.queryChildrenByParentId(id);
		for (SysDept sysDept1 : list1) {
			DhtmlxTreeModel node1 = new DhtmlxTreeModel();
			node1.setId(sysDept1.getIdKey() + "");
			node1.setText(sysDept1.getCdNm());
			node1.setItem(getSubTree(sysDept1.getIdKey()));
			children1.add(node1);
		}
		if (list1.size() == 0) {
			List<SysCommunity> list = sysCommunityMapper.queryBydeptID(id);
			for (SysCommunity sysCommunity : list) {
				DhtmlxTreeModel node1 = new DhtmlxTreeModel();
				node1.setId(sysCommunity.getIdKey() + "");
				node1.setText(sysCommunity.getCommunityName());
				node1.setIm0("community.png");
				children1.add(node1);
			}
		}
		return children1;
	}

	@Override
	public List<SysCommunity> queryPopedomCommunity() {
		return sysCommunityMapper.queryPopedomCommunityByUserId(getUserBean()
				.getUserId());
	}

	@Override
	public String queryPopedomCommunityString(Long userId) {
		if (userId == null) {
			userId = getUserBean().getUserId();
		}
		StringBuilder sb = new StringBuilder();
		List<SysCommunity> list = sysCommunityMapper
				.queryPopedomCommunityByUserId(userId);
		for (SysCommunity sysCommunity : list) {
			sb.append(sysCommunity.getIdKey()).append(",");
		}
		if (sb.length() == 0) {// 没查找到小区，随便给一个小区id，防止sql语句where…in(…)报错
			sb.append("-2");
		} else {
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个逗号
		}
		return sb.toString();
	}
}
