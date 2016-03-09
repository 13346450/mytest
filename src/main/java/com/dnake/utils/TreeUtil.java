package com.dnake.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dnake.domain.system.SysTree;

public class TreeUtil {

	/***********************      一次行全部加载    ***********************************/
	/**
	 * 将一个菜单列表组装成一个SysMenu,并且该SysMenu下有子节点,子节点下又有子节点,一直到最低子节点
	 */
	public static SysTree populateMenu(List SysTrees) {
		//创建一个List存放不是根节点的节点
		List removedSysTree = new ArrayList();
		for (int i = 0; i < SysTrees.size(); i++) {
			SysTree SysTree = (SysTree)SysTrees.get(i);
			//对SysTree对象进行过滤,如果是根节点,则不进入方法体
			if (SysTree.getParentId() != null && SysTree.getParentId() != -1l) { //start if1 
				removedSysTree.add(SysTree); // 每次循环 都把有具上级的数据放入removedSysTree
				SysTree parent = findParentSysTree(SysTrees, SysTree
						.getParentId()); // 返回该对象的上级单位数据
				//如果上级节点不为空,则为上级节点添加子节点
				if (parent != null) {//start if2 
					parent.getChildList().add(SysTree);// 把上级单位放入SysTree List
				}//end if2
			}//end if1
		}

		// 把有上级单位的数据 清除出来 找出根节点 也就是清楚所有的下级数据
		for (int i = 0; i < removedSysTree.size(); i++) {
			SysTree SysTree = (SysTree)removedSysTree.get(i);
			SysTrees.remove(SysTree);
		}
		
		if(SysTrees != null && SysTrees.size() > 0) return (SysTree)SysTrees.get(0);
		return null;
	}

	/**
	 * 描述：循环List集合进行对比 查出该上级单位数据
	 * @param SysTrees
	 *            树集合
	 * @param parentId
	 *            上级id
	 * @return 返回具有下级的对象
	 */
	private static SysTree findParentSysTree(List SysTrees, Long parentId) {
		for (int i = 0; i < SysTrees.size(); i++) {
			SysTree SysTree = (SysTree)SysTrees.get(i);
			if (parentId.equals(SysTree.getIdKey())) {
				return SysTree;
			}
		}
		return null;
	}

	/**
	 * 创造树
	 */
	private static void createSysTree(SysTree SysTree, Element element) {
		// 递归方法 循环自己的类
		for (Iterator i = SysTree.getChildList().iterator(); i.hasNext();) {
			SysTree child = (SysTree) i.next();
			
			Element item = element.addElement("item"); // 生成根节点
			item.addAttribute("id", child.getIdKey().toString()); // 添加属性
			item.addAttribute("text", child.getCdNm());
			
			createSysTree(child, item);
		}
	}
	
	/**
	 * 
	 *摘要：
	 *@说明：处理传进来的列表,生成Document(dom4j)并返回,上层使用流处理Document生成树形
	 *		 使用类似hql="from 实体名称"加载一个list,直接传进来即可
	 *@创建：作者:Qi		创建时间：Apr 20, 2009
	 *@param SysTrees : (内部放的SysTree)实体的列表 使用类似hql="from 实体名称"加载一个list,直接传进来即可
	 *@return Document
	 *@修改历史：
	 *		[序号](Qi	Apr 20, 2009)<修改说明>
	 */
	public static Document loadTreeAsXML(List SysTrees) {
		/*
		 * <tree id="0" > 
		 *   <item text="类别文件" id="-1" open="1" im0="books_close.gif" im1="tombs.gif" im2="tombs.gif"> 
		 *    <item text="法律法规" id="700" im0="book.gif" im1="books_open.gif" im2="books_close.gif"> 
		 *      <item text="公用物资管理手册" id="teens" im0="book.gif" im1="books_open.gif" im2="books_close.gif"> 
		 *      </item>
		 *    </item> 
		 *   </item>
		 * </tree>
		 */
		populateMenu(SysTrees);//过滤数据 把最上级的数据抽取出来
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("tree");
		root.addAttribute("id", SysTree.TREE_ID);//要和jsp中的根对应
		for (int i = 0; i < SysTrees.size(); i++) {
			SysTree tree = (SysTree)SysTrees.get(i);
			
			Element item = root.addElement("item"); // 生成根节点
			item.addAttribute("id", tree.getIdKey().toString()); // 添加属性
			item.addAttribute("text", tree.getCdNm());
			item.addAttribute("open", "1");//open=1表示打开该节点
			
			createSysTree(tree, item);
		}
		return doc;
	}
	
	
	/***********************      异步加载    ***********************************/
	/**
	 * 加载根节点及其下级节点,这个systree必须要先设置其childList
	 * 上层使用 systree.getChildList().add(SysTree);来设置其clildList
	 *摘要：
	 *@说明：
	 *@创建：作者:Qi		创建时间：Apr 20, 2009
	 *@param sysTree : 这个systree必须要先设置其childList
	 *@return 
	 *@修改历史：
	 *		[序号](Qi	Apr 20, 2009)<修改说明>
	 */
	public static StringBuffer loadRoot(SysTree sysTree) {
		StringBuffer json = new StringBuffer();
		json.append("{");
		 json.append("id:'"+SysTree.TREE_ID+"',");
		 json.append("item:[{");
		 		 //userdata:用户自定义的数据,在页面中JS取得该值判断节点是否是最低子节点
		 	     json.append("userdata:[{name:'isleaf',content:'"+(sysTree.getLastMark()==null?SysTree.LAST_MARK_YES:sysTree.getLastMark())+"'}],");
		 	     json.append("id:'"+sysTree.getIdKey()+"',");
		 	     json.append("text:'"+sysTree.getCdNm()+"'");
		 	     if(sysTree.getLastMark() != null && sysTree.getLastMark() != SysTree.LAST_MARK_YES){
		 	    	//如果不是子节点,设置其图片为文件夹与子节点区分
		 	     json.append(",im0:'folderClosed.gif'");
		 	     }
		 	    List list = sysTree.getChildList();
		 	    
		 	    if(list == null || list.size() == 0) {
					throw new RuntimeException("没有为其设置子节点");
				}
		 	    
		 	    if(list != null && list.size() > 0){
		 	      /***
		 	       * 这里我给他隐藏了,就是不让他展开节点,可以去除一个bug,
		 	       * bug为:展开根节点,如果一级子节点是最低子节点的话,会有一收缩图标
		 	       **/
		 	     //json.append(",open:'1'");//
		 	     json.append(",item:[");
		 	     load(json, list);
			 	 json.append("]");
		 	    }
		 	     
		      json.append("}]");
		json.append("}");
		return json;
	}
	
	/**
	 * 加载当前节点下的子节点,这个systree是当前的节点,必须要先设置其childList,
	 * 上层使用 systree.getChildList().add(SysTree);来设置其clildList
	 *摘要：
	 *@说明：
	 *@创建：作者:Qi		创建时间：Apr 20, 2009
	 *@param sysTree : 这个systree必须要先设置其childList
	 *@return 
	 *@修改历史：
	 *		[序号](Qi	Apr 20, 2009)<修改说明>
	 */
	public static StringBuffer loadChild(SysTree sysTree) {

		List list = sysTree.getChildList();
		
		if(list == null || list.size() == 0) {
			throw new RuntimeException("没有为其设置子节点");
		}
		
		StringBuffer json = new StringBuffer();
		
		if (list != null && list.size() > 0) {
			json.append("{");
			 json.append("id:'"+sysTree.getIdKey()+"',");
			 json.append("item:[");
			 load(json, list);
			 json.append("]");
			json.append("}");
		}
		return json;
	}
	
	private static void load(StringBuffer json, List list) {
		int size = 0;
		for (Iterator i = list.iterator(); i.hasNext();) {
			SysTree s = (SysTree)i.next();
			json.append("{");
			 //userdata:用户自定义的数据,在页面中JS取得该值判断节点是否是最低子节点
	 		 json.append("userdata:[{name:'isleaf',content:'"+(s.getLastMark()==null?SysTree.LAST_MARK_YES:s.getLastMark())+"'}],");
	 		 json.append("id:'"+s.getIdKey()+"',");
	 	     json.append("text:'"+s.getCdNm()+"'");
	 	     if(s.getLastMark() != null && s.getLastMark() != SysTree.LAST_MARK_YES) {
	 	    	 //如果不是子节点,设置其图片为文件夹与子节点区分
	 	    	json.append(",im0:'folderClosed.gif'");
	 	     }
	 	     json.append("}");
	 	     if(size != list.size()-1) {
	 	      json.append(",");
	 	     }
	 	    size ++;
		}
	}
}
