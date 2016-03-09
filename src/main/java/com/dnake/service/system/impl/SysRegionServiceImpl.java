package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysRegion;
import com.dnake.mapper.system.SysRegionMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysRegionService;
import com.dnake.utils.Constants;

@Service
public class SysRegionServiceImpl extends BaseServiceImpl implements
		SysRegionService {
	@Resource
	private SysRegionMapper sysRegionMapper;

	@Override
	public List<SysRegion> queryChildrenByParentId(Long deptId) {
		return sysRegionMapper.queryChildrenByParentId(deptId);
	}

	@Override
	public SysRegion edit(Long deptId) {
		return sysRegionMapper.edit(deptId);
	}

	@Override
	public String insert(SysRegion SysRegion) {
		SysRegion.setLastMark(1);
		sysRegionMapper.insert(SysRegion);
		return "{\"result\":\"增加区域成功！\",\"idKey\":" + SysRegion.getIdKey()
				+ "}";
	}

	@Transactional
	public String insertRegionUpdateParentLaskMark(SysRegion SysRegion) {
		SysRegion.setLastMark(1);
		sysRegionMapper.insert(SysRegion);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", SysRegion.getParentId());
		map.put("lastMark", 0);
		sysRegionMapper.updateLastMark(map);

		writeLog(
				Constants.FUNC_MENU_NM_REGION,
				Constants.FUNC_OPER_NM_CREATE,
				"idKey:" + SysRegion.getIdKey() + ",cdNm:"+ SysRegion.getRegionName());
		return "{\"successMsg\":\"增加区域成功！\",\"idKey\":\""+ SysRegion.getIdKey() + "\",\"parentId\":\""
				+ SysRegion.getParentId() + "\",\"cdNm\":\""
				+ SysRegion.getRegionName() + "\"}";
	}

	@Override
	public void delete(Long deptId) {
		sysRegionMapper.delete(deptId);
	}

	@Transactional
	public String deleteRegionUpdateParentLastMark(Long regionId, Long parentId) {
		SysRegion SysRegion = sysRegionMapper.edit(regionId);
		sysRegionMapper.delete(regionId);
		if (!haveChildren(parentId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			sysRegionMapper.updateLastMark(map);
		}
		writeLog(
				Constants.FUNC_MENU_NM_REGION,
				Constants.FUNC_OPER_NM_DELETE,
				"idKey:" + SysRegion.getIdKey() + ",cdNm:"
						+ SysRegion.getRegionName());
		return "{\"successMsg\":\"删除区域成功！\",\"idKey\":\"" + regionId + "\"}";
	}

	@Transactional
	public String update(SysRegion SysRegion) {
		sysRegionMapper.update(SysRegion);
		writeLog(
				Constants.FUNC_MENU_NM_REGION,
				Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + SysRegion.getIdKey() + ",cdNm:"
						+ SysRegion.getRegionName());
		return "{\"successMsg\":\"更新区域成功！\",\"idKey\":\""
				+ SysRegion.getIdKey() + "\",\"parentId\":\""
				+ SysRegion.getParentId() + "\",\"cdNm\":\""
				+ SysRegion.getRegionName() + "\"}";
	}

	@Transactional
	public String updateParentIdByIdKey(Long idKey, Long oldParentId,
			Long targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		sysRegionMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", "0");
		sysRegionMapper.updateLastMark(map);
		if (!haveChildren(oldParentId)) {
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", "1");
			sysRegionMapper.updateLastMark(map);
		}
		SysRegion SysRegion = sysRegionMapper.edit(idKey);
		writeLog(
				Constants.FUNC_MENU_NM_REGION,
				Constants.FUNC_OPER_NM_TREEDRAG,
				"idKey:" + SysRegion.getIdKey() + ",cdNm:"
						+ SysRegion.getRegionName() + ",oldParentId:"
						+ oldParentId + ",newParentId:" + targetId);

		return "{\"successMsg\":\"移动成功！\"}";
	}

	public DhtmlxTreeModel creatSysRegionTree(Long nodeId) {
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != nodeId && nodeId == -1) {
			List<SysRegion> list = sysRegionMapper.queryChildrenByParentId(Long
					.parseLong("0"));
			if (null != list && list.size() > 0) {
				for (SysRegion SysRegion : list) {
					DhtmlxTreeModel node = new DhtmlxTreeModel();
					node.setId(SysRegion.getIdKey().toString());
					node.setText(SysRegion.getRegionName());
					node.setItem(getChildren(SysRegion.getIdKey()));
					children.add(node);
				}
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
	 * @author xzm 2013-11-14
	 * @Description:
	 * @param parentId
	 * @return
	 * @return List<DhtmlxTreeModel>
	 * @throws
	 */
	private List<DhtmlxTreeModel> getChildren(Long parentId) {
		List<SysRegion> list = sysRegionMapper
				.queryChildrenByParentId(parentId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysRegion SysRegion : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(SysRegion.getIdKey().toString());
				node.setText(SysRegion.getRegionName());
				node.setChild((SysRegion.getLastMark() ^ 1) + "");
				children.add(node);
			}
		}
		return children;
	}

	/**
	 * 判断是否有子节点
	 */
	public boolean haveChildren(Long parentId) {
		List<SysRegion> list = sysRegionMapper
				.queryChildrenByParentId(parentId);
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
