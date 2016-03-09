package com.dnake.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizChain;
import com.dnake.domain.business.BizChainVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.business.BizChainService;
import com.dnake.service.common.CommSqlService;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.StringUtils;


/**
 * 便民服务链接管理控制层
 * @ClassName BizChainController
 * @author zgj
 * 2014年9月4日 上午9:28:46
 */
@Controller
@RequestMapping("/business/BizChain/*")
@Login
public class BizChainController extends BaseController
{
	@Resource
	private BizChainService bizChainService;
	@Resource
	private CommonService commonService;
	@Resource
	private CommSqlService commSqlService;
	
	/**
	 * 按条件分页查询
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<BizChainVO> listPage(@RequestParam("page") int pageIndex, int rows, SearchParam searchParam){
		//int i = commSqlService.insertSql("insert into biz_chain (chain_name,links_url,images_url) values ('中国银行', 'sdf', 'dsfsd')");
		//System.out.println(i);
		/*List<Map> list = commSqlService.selectSql(("select chain_name from biz_chain where id_key = '12' "));
		for (Map map : list)
		{
			System.out.println(map.get("chain_name"));
		}		
		String ssssql = "";*/
		return bizChainService.listPage(pageIndex ,rows, searchParam);
	}
	/**
	 * 插入一条数据
	 * @param response
	 * @param bizHitRecord
	 */
	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, BizChain bizChain)
	{
		outputString(response ,bizChainService.insert(bizChain));
	}
	
	/**
	 * 更新一条数据
	 * @param response
	 * @param bizHitRecord
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, BizChain bizChain)
	{
		outputString(response,bizChainService.update(bizChain)); 
	}
	
	/**
	 * 删除一条数据
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "delete/{idKey}")
	public void delete(HttpServletResponse response, @PathVariable Long idKey)
	{
			outputString(response, bizChainService.delete(response, idKey)); 
	}
	
	/**
	 * 查找一条数据
	 * @param response
	 * @param idKey
	 */
	@RequestMapping(value = "queryById/{idKey}")
	@ResponseBody
	public BizChain queryById(@PathVariable Long idKey)
	{
		return bizChainService.queryById(idKey); 
	}
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "uploadFile")
	public void uploadFile(HttpSession session, HttpServletResponse response, 
						@RequestParam(value="upFile") MultipartFile mfile, String link)
	{
		outputString(response,commonService.uploadFile(mfile, 
				session.getServletContext().getRealPath("/")+Constants.CHAIN_DIR ,
				StringUtils.convertCharacter(link,Constants.CHARSET), "chain"));
	}
	
	
}
