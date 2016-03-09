package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.print.resources.serviceui;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysDept;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.mapper.system.SysDeptMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysDeptService;
import com.dnake.utils.Constants;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;

/**
 * 行政区域管理实现类 SysDeptServiceImpl <br/>
 * 2014年10月21日 上午10:13:53 <br/>
 * 
 * @author chen qige
 * @version
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl implements
		SysDeptService {

	@Resource
	private SysDeptMapper sysDeptMapper;
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysCommunityMapper sysCommunityMapper;
	@Override
	public List<SysDept> queryChildrenByParentId(Long deptId) {
		return sysDeptMapper.queryChildrenByParentId(deptId);
	}

	@Override
	public SysDept edit(Long deptId) {
		return sysDeptMapper.edit(deptId);
	}

	@Override
	public String insert(SysDept sysDept) {
		sysDept.setChgDt(new Date());
		sysDept.setLastMark(1);
		sysDeptMapper.insert(sysDept);
		return "{\"result\":\"增加部门成功！\",\"idKey\":" + sysDept.getIdKey() + "}";
	}

	@Transactional
	public String insertDeptUpdateParentLaskMark(SysDept sysDept) {
		sysDept.setChgDt(new Date());
		sysDept.setLastMark(1);
		// sysDept.setIsBranch(0);
		sysDept.setDeptCode("1111");
		sysDeptMapper.insert(sysDept);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", sysDept.getParentId());
		map.put("lastMark", 0);
		sysDeptMapper.updateLastMark(map);

		writeLog(Constants.FUNC_MENU_NM_DEPT, Constants.FUNC_OPER_NM_CREATE,
				"idKey:" + sysDept.getIdKey() + ",cdNm:" + sysDept.getCdNm());
		return "{\"successMsg\":\"增加部门成功！\",\"idKey\":\"" + sysDept.getIdKey()
				+ "\",\"parentId\":\"" + sysDept.getParentId()
				+ "\",\"cdNm\":\"" + sysDept.getCdNm() + "\"}";
	}

	@Transactional
	public String deleteDeptUpdateParentLastMark(Long deptId, Long parentId) {
		SysDept sysDept = sysDeptMapper.edit(deptId);
		sysDeptMapper.delete(deptId);
		if (!haveChildren(parentId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			sysDeptMapper.updateLastMark(map);
		}
		writeLog(Constants.FUNC_MENU_NM_DEPT, Constants.FUNC_OPER_NM_DELETE,
				"idKey:" + sysDept.getIdKey() + ",cdNm:" + sysDept.getCdNm());
		return "{\"successMsg\":\"删除部门成功！\",\"idKey\":\"" + deptId
				+ "\",\"flag\":true}";
	}

	@Transactional
	public String update(SysDept sysDept) {
		sysDept.setChgDt(new Date());
		sysDeptMapper.update(sysDept);
		writeLog(Constants.FUNC_MENU_NM_DEPT, Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + sysDept.getIdKey() + ",cdNm:" + sysDept.getCdNm());
		return "{\"successMsg\":\"更新部门成功！\",\"idKey\":\"" + sysDept.getIdKey()
				+ "\",\"parentId\":\"" + sysDept.getParentId()
				+ "\",\"cdNm\":\"" + sysDept.getCdNm() + "\"}";
	}

	@Transactional
	public String updateParentIdByIdKey(Long idKey, Long oldParentId,
			Long targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		sysDeptMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", "0");
		sysDeptMapper.updateLastMark(map);
		if (!haveChildren(oldParentId)) {
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", "1");
			sysDeptMapper.updateLastMark(map);
		}
		SysDept sysDept = sysDeptMapper.edit(idKey);
		writeLog(Constants.FUNC_MENU_NM_DEPT, Constants.FUNC_OPER_NM_TREEDRAG,
				"idKey:" + sysDept.getIdKey() + ",cdNm:" + sysDept.getCdNm()
						+ ",oldParentId:" + oldParentId + ",newParentId:"
						+ targetId);

		return "{\"successMsg\":\"移动成功！\"}";
	}

	public DhtmlxTreeModel creatSysDeptTree(Long nodeId) {
		SessionBean sessionBean = getUserBean();
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != nodeId && nodeId == -1) {
			SysDept sd = sysDeptMapper.edit(sessionBean.getDeptId());
			if (null != sd) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sd.getIdKey().toString());
				node.setText(sd.getCdNm());
				node.setItem(getChildren(sessionBean.getDeptId()));
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
		List<SysDept> list = sysDeptMapper.queryChildrenByParentId(parentId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysDept sysDept : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysDept.getIdKey().toString());
				node.setText(sysDept.getCdNm());
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
		List<SysDept> list = sysDeptMapper.queryChildrenByParentId(parentId);
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
		return sysDeptMapper.querySecondDeptId(deptId);
	}

	@Override
	public Long queryMaxOrderCdByPId(Long parentId) {
		return sysDeptMapper.queryMaxOrderCdByPId(parentId);
	}

	/**
	 * 手机客户端查询
	 */
	@Override
	public String mobileQueryByParentId(Long parentId) {
		List<SysDept> list = sysDeptMapper.mobileQueryByParentId(parentId);
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				SysDept.class, "idKey", "cdNm");
		JsonResult jr = new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public String queryByAllParentIdByCommunityIds(String communityIds) {
		List<Long> list= new ArrayList<Long>();
		for(String id:communityIds.split(",")){
			list.add(Long.valueOf(id));
			Long parentId=null;
			SysCommunity sysCommunity=sysCommunityMapper.queryById(Long.valueOf(id));
			if(sysCommunity!=null){//获取小区所在的部门
				parentId=sysCommunity.getDeptId();
			}
			if(parentId!=null){
				list.add(parentId);
				while(!parentId.equals(1L)&&parentId!=null){//递归获取部门的父节点，添加到list中
					parentId=sysDeptMapper.getParentIdByDeptId(parentId);
					if(parentId!=null){
							if(list.contains(parentId)){
								break;
							}
						list.add(parentId);
					}
				}
			}
		}
		Collections.sort(list);
		StringBuilder sb=new StringBuilder();
		for (Long string : list) {//拼接成字符串
			sb.append(string).append(",");
		}
		if(sb.length()!=0){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString()+"";
	}
}
