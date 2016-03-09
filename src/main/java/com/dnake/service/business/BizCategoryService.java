package com.dnake.service.business;

import java.util.List;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.EasyUiTreeModel;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 商品类目管理 BizCatrgory <br/>
 * 2014年9月9日 上午10:45:56 <br/>
 * 
 * @author chen qige
 * @version
 */
public interface BizCategoryService {
	/**
	 * 根据id查询类别
	 * 
	 * @title selectById
	 * @author chen qige
	 * @date 2014年9月9日
	 * @param id
	 * @return
	 */
	BizCategory selectById(Long id);

	/**
	 * 根据父节点ID查询
	 * 
	 * @title selectByParentId
	 * @author chen qige
	 * @date 2014年9月9日
	 * @param parentId
	 * @return
	 */
	List<BizCategory> selectByParentId(Long parentId);

	/**
	 * 手机端查询接口
	 * 
	 * @title mobileQuery
	 * @author chen qige
	 * @date 2014年9月10日
	 * @param parentId
	 * @return
	 */
	String mobileQuery(MobileParms parms);

	/**
	 * 根据id查询下级节点
	 * 
	 * @Title: queryChildrenByParentId
	 * @author ts 2013-10-14
	 * @Description:
	 * @param deptId
	 * @return
	 * @return List<BizCategory>
	 * @throws
	 */
	List<BizCategory> queryChildrenByParentId(Long deptId, String categoryName);

	/**
	 * 打开编辑页面
	 * 
	 * @Title: edit
	 * @author ts 2013-10-14
	 * @Description:
	 * @param deptId
	 * @return
	 * @return BizCategory
	 * @throws
	 */
	BizCategory edit(Long deptId);

	/**
	 * 保存新部门
	 * 
	 * @Title: insert
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param sysDept
	 * @param @return
	 * @return String
	 * @throws
	 */
	String insert(BizCategory sysDept);

	/**
	 * 删除部门并判断更新父节点lastMark
	 * 
	 * @Title: deleteDeptUpdateParentLastMark
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param deptId
	 * @param @param parentId
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String deleteDeptUpdateParentLastMark(Long deptId, Long parentId);

	/**
	 * 更新一个部门
	 * 
	 * @Title: update
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param sysDept
	 * @param @return
	 * @return String
	 * @throws
	 */
	String update(BizCategory sysDept);

	/**
	 * 更新指定idKey部门的parentId值
	 * 
	 * @Title: updateParentIdByIdKey
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param idKey
	 * @param @param oldParentId
	 * @param @param targetId
	 * @param @return
	 * @return String
	 * @throws
	 */
	String updateParentIdByIdKey(Long idKey, Long oldParentId, Long targetId);

	/**
	 * 插入部门数据并且更新父节点的LastMark字段值为0
	 * 
	 * @Title: insertDeptUpdateParentLaskMark
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param sysDept
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String insertDeptUpdateParentLaskMark(BizCategory sysDept);

	/**
	 * 构造部门树
	 * 
	 * @Title: creatSysDeptTree
	 * @author tw 2013-10-17
	 * @Description:
	 * @param @param nodeId
	 * @param @return
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	DhtmlxTreeModel creatSysDeptTree(Long nodeId, Long communityId);

	public boolean haveChildren(Long parentId);

	/**
	 * 查询所属的二级单位
	 * 
	 * @Title: querySecondDeptId
	 * @author ts 2013-12-4
	 * @Description:
	 * @param deptId
	 * @return
	 * @return Long
	 * @throws
	 */
	Long querySecondDeptId(Long deptId);

	/**
	 * 查询同级中排序码最大值加10
	 * 
	 * @Title: queryMaxOrderCdByPId
	 * @author xzm 2013-12-31
	 * @Description:
	 * @param parentId
	 * @return
	 * @return Long
	 * @throws
	 */
	Long queryMaxOrderCdByPId(Long parentId);

	/**
	 * 获取下拉菜单的目录树
	 * 
	 * @title selectDeptTree
	 * @author chen qige
	 * @date 2014年9月12日
	 * @return
	 */
	List<EasyUiTreeModel> selectCategoryTree(Long communityId);

	/**
	 * 商户端添加商品时,查询类别
	 * 
	 * @return
	 * @author cqg 2015年5月5日
	 */
	String salesQueryCategory();

	/**
	 * 分页查询用于广告图片的左侧栏
	 * 
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 * @author cqg 2015年7月17日
	 */
	Page<BizCategoryVO> listPage(int pageIndex, int rows,
			SearchParam searchParam);
}
