package com.dnake.controller.business;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.EasyUiTreeModel;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizCategoryService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 商品类目管理控制类 BizCategoryController <br/>
 * 2014年9月9日 下午4:51:28 <br/>
 * 
 * @author chen qige
 * @version
 */
@Controller
@RequestMapping("/business/BizCategory/*")
@Login
public class BizCategoryController extends BaseController {

	@Resource
	private BizCategoryService bizCategoryService;

	@Resource
	private CommonService commonService;

	/**
	 * 进入修改页面
	 * 
	 * @Title: edit
	 * @author ts 2013-10-14
	 * @Description:
	 * @param deptId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "edit/{deptId}")
	public String edit(@PathVariable Long deptId, Model model) {
		model.addAttribute("sysDept", bizCategoryService.edit(deptId));
		return "business/categoryEdit";
	}

	/**
	 * 进入新增页面
	 * 
	 * @Title: add
	 * @author ts 2013-10-14
	 * @Description:
	 * @param deptId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("add/{deptId}")
	public String add(@PathVariable Long deptId, Model model) {
		model.addAttribute("parentId", deptId);
		model.addAttribute("orderCd",
				bizCategoryService.queryMaxOrderCdByPId(deptId));
		return "business/categoryAdd";
	}

	/**
	 * 插入商品目录数据并且更新父节点的LastMark字段值为0
	 * 
	 * @Title: insert
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param sysDept
	 * @param @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(BizCategory sysDept, HttpServletResponse response) {
		this.outputString(response,
				bizCategoryService.insertDeptUpdateParentLaskMark(sysDept));
	}

	/**
	 * 更新商品目录数据
	 * 
	 * @Title: update
	 * @author ts 2013-10-14
	 * @Description:
	 * @param model
	 * @param sysDept
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(BizCategory sysDept, HttpServletResponse response) {
		outputString(response, bizCategoryService.update(sysDept));
	}

	/**
	 * 删除部门，并更新父节点的lastMark
	 * 
	 * @Title: delete
	 * @author tw 2013-10-30
	 * @Description:
	 * @param @param deptId
	 * @param @param parentId
	 * @param @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "delete")
	public void delete(@RequestParam Long deptId, @RequestParam Long parentId,
			HttpServletResponse response) {
		outputString(response,
				bizCategoryService.deleteDeptUpdateParentLastMark(deptId,
						parentId));
	}

	/**
	 * 构造目录
	 * 
	 * @Title: creatSysDeptTree
	 * @author tw 2013-10-24
	 * @Description:
	 * @param @param request
	 * @param @param nodeId
	 * @param @return
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	@RequestMapping(value = "creatSysDeptTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysDeptTree(@RequestParam("id") Long nodeId,Long communityId) {
		return bizCategoryService.creatSysDeptTree(nodeId,communityId);
	}

	/**
	 * 保存拖拽后的目录
	 * 
	 * @Title: saveDragAndDrop
	 * @author tw 2013-10-24
	 * @Description:
	 * @param @param response
	 * @param @param sourceId
	 * @param @param parentId
	 * @param @param targetId
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "saveDragAndDrop")
	public void saveDragAndDrop(HttpServletResponse response,
			@RequestParam Long sourceId, @RequestParam Long parentId,
			@RequestParam Long targetId) {
		outputString(response, bizCategoryService.updateParentIdByIdKey(
				sourceId, parentId, targetId));
	}

	/**
	 * 获取下拉菜单
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "loadSelectTagData")
	@ResponseBody
	public List<BizCategory> loadSelectTagData(String parentId, HttpServletRequest request) {
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		return bizCategoryService.queryChildrenByParentId(sessionBean.getDeptId(), parentId);
	}

	/**
	 * 获取下拉菜单
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "loadSelectTagDataByCommunityId")
	@ResponseBody
	public List<BizCategory> loadSelectTagDataByCommunityId(String parentId,Long communityId, HttpServletRequest request) {
		return bizCategoryService.queryChildrenByParentId(communityId, parentId);
	}
	
	@RequestMapping(value = "getCategoryTree/{communityId}")
	@ResponseBody
	public List<EasyUiTreeModel> getDeptTree(@PathVariable Long communityId) {
		List<EasyUiTreeModel> list = bizCategoryService.selectCategoryTree(communityId);
		return list;
	}

	/**
	 * 上传图标
	 * 
	 * @title uploadFile
	 * @author chen qige
	 * @date 2014年9月17日
	 * @param session
	 * @param response
	 * @param mfile
	 * @param link
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "upFile") MultipartFile mfile, String link) {
		outputString(response, commonService.uploadFile(mfile, session
				.getServletContext().getRealPath("/")
				+ Constants.UPLOAD_DIR
				+ File.separator + "icon_category", StringUtils
				.convertCharacter(link, Constants.CHARSET), "icon_category"));
	}
/**
 * 上传商户系统商品样例图片
* @title      uploadFileAndCompress 
* @author  chen qige     
* @date      2014年10月11日 
*  @param session
*  @param response
*  @param mfile
*  @param link
 */
	@RequestMapping(value = "uploadFileAndCompress")
	public void uploadFileAndCompress(HttpSession session,
			HttpServletResponse response,
			@RequestParam(value = "upFile") MultipartFile mfile, String link) {
		outputString(response, commonService.uploadFileAndCompress(mfile,
				session.getServletContext().getRealPath("/")
						+ Constants.UPLOAD_DIR + File.separator
						+ "goods_example_images", "goods_example_images"));
	}
	
	@RequestMapping(value="checkLeaf/{typeId}")
	public void delete(HttpServletResponse response, @PathVariable Long typeId){
		System.out.println(typeId+((bizCategoryService.haveChildren(typeId))?"{\"isLeaf\":\"0\"}":"{\"isLeaf\":\"1\"}"));
		outputString(response, (bizCategoryService.haveChildren(typeId))?"{\"isLeaf\":\"0\"}":"{\"isLeaf\":\"1\"}"); 
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizCategoryVO> listPage(@RequestParam("page") int pageIndex,
			int rows, SearchParam searchParam) {
		return bizCategoryService.listPage(pageIndex, rows, searchParam);
	}
}
