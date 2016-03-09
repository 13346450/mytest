package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysDict;
import com.dnake.mapper.system.SysDictMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysDictService;
import com.dnake.utils.Constants;

@Service
public class SysDictServiceImpl extends BaseServiceImpl implements
		SysDictService {

	@Resource
	private SysDictMapper sysDictMapper;

	@Override
	public List<SysDict> queryListSysDict() {
		return sysDictMapper.queryListSysDict();
	}

	/**
	 * 构造数据字典树
	 * 
	 * @Title: creatSysDeptTree
	 * @author cqx 2013-10-28
	 * @Description:
	 * @param @param nodeId
	 * @param @return
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	@Override
	public DhtmlxTreeModel creatSysDeptTree(Long nodeId) {
		// 构造一棵树
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		// 给树设置节点
		tree.setId(nodeId + "");
		// 构造子树
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		// 如果nodeId==-1为第一次加载树
		if (null != nodeId && nodeId == -1) {
			// 查询所有的数据字典信息
			List<SysDict> sysDictList = sysDictMapper.queryListSysDict();
			if (null != sysDictList && sysDictList.size() > 0) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId("01");
				node.setText("数据字典");
				// 加载子节点
				node.setItem(getChildren(nodeId));
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
	 * @author cqx 2013-10-28
	 * @Description:
	 * @param @param parentId
	 * @param @return
	 * @return List<DhtmlxTreeModel>
	 * @throws
	 */
	private List<DhtmlxTreeModel> getChildren(Long parentId) {
		List<SysDict> list = sysDictMapper.queryListSysDict();
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysDict sysDict : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysDict.getDictTypeId());
				node.setText(sysDict.getDictTypeNm());
				node.setChild(null);
				children.add(node);
			}
		}
		return children;
	}

	/**
	 * 根据dictTypeId查询List<SysDict>信息
	 * 
	 * @Title: editList
	 * @author cqx 2013-10-29
	 * @Description:
	 * @param @param dictTypeId
	 * @param @return
	 * @return List<SysDict>
	 * @throws
	 */
	@Override
	public List<SysDict> editList(String dictTypeId) {
		return sysDictMapper.queryListSysDictByDictTypeId(dictTypeId);
	}

	/**
	 * 根据idKey删除dict信息
	 * 
	 * @Title: delete
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param @param idKey
	 * @return void
	 * @throws
	 */
	@Transactional
	public String delete(String idKey) {
		// 把传进来的String型截取成一段一段
		SysDict sysDict = new SysDict();
		String idKeys[] = idKey.split(",");
		if (null != idKey && idKey.length() > 0) {
			for (int i = 0; i < idKeys.length; i++) {
				// String强制转换为Long型
				sysDict = sysDictMapper.queryDictByKeyID(Long
						.parseLong(idKeys[i]));
				sysDictMapper.delete(Long.parseLong(idKeys[i]));
				writeLog(
						Constants.FUNC_MENU_NM_DICT,
						Constants.FUNC_OPER_NM_DELETE,
						"idKey:" + sysDict.getIdKey() + ",cdNm:"
								+ sysDict.getDictValue());
			}
		}
		return "{\"successMsg\":\"删除成功！\"}";
	}

	/**
	 * 根据类别ID获取文本值
	 * 
	 * @Title: update
	 * @author xzm 2013-11-12
	 * @Description:
	 * @param @param sysDict
	 * @return void
	 * @throws
	 */
	@Override
	public SysDict queryIdNmByTypeID(String dictTypeId) {
		return sysDictMapper.queryIdNmByTypeID(dictTypeId);
	}

	/**
	 * 新增数据字典
	 * 
	 * @Title: insert
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param @param response
	 * @param @param dictTypeId
	 * @return String
	 * @throws
	 */
	@Transactional
	public String insert(SysDict sysDict) {
		SysDict sysDictTwo = sysDictMapper.queryIdNmByTypeID(sysDict
				.getDictTypeId());
		sysDict.setChgDt(new Date());
		sysDict.setDictTypeNm(sysDictTwo.getDictTypeNm());
		sysDictMapper.insert(sysDict);
		writeLog(
				Constants.FUNC_MENU_NM_DICT,
				Constants.FUNC_OPER_NM_CREATE,
				"idKey:" + sysDict.getIdKey() + ",cdNm:"
						+ sysDict.getDictValue());
		return "{\"successMsg\":\"新增数据成功！\"}";
	}

	/**
	 * 更新数据信息
	 * 
	 * @Title: update
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param @param sysDict
	 * @param @param response
	 * @return void
	 * @throws
	 */
	@Transactional
	public String update(SysDict sysDict) {
		sysDict.setChgDt(new Date());
		sysDictMapper.update(sysDict);
		writeLog(
				Constants.FUNC_MENU_NM_DICT,
				Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + sysDict.getIdKey() + ",cdNm:"
						+ sysDict.getDictValue());
		return "{\"successMsg\":\"更新数据成功！\"}";
	}
	
	public List<SysDict> querySysDictByDictTypeId(String dictTypeId){
		return sysDictMapper.queryListSysDictByDictTypeId(dictTypeId);
	}
}
