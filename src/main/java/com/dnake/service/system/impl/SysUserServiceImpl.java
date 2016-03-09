package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.domain.system.SysDept;
import com.dnake.domain.system.SysRole;
import com.dnake.domain.system.SysUser;
import com.dnake.domain.system.SysUserPopedom;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.mapper.system.SysDeptMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.mapper.system.SysUserPopedomMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysCommunityService;
import com.dnake.service.system.SysDeptService;
import com.dnake.service.system.SysRoleService;
import com.dnake.service.system.SysUserService;
import com.dnake.utils.Constants;
import com.dnake.utils.MobClient;
import com.dnake.utils.MobSmsVerifyKit;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

@Service
public class SysUserServiceImpl extends BaseServiceImpl implements
		SysUserService {
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private CommonService commonService;
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysDeptMapper sysDeptMapper;
	@Resource
	private SysDeptService sysDeptService;
	@Resource
	private SysCommunityMapper sysCommunityMapper;
	@Resource
	private SysCommunityService sysCommunityService;
	@Resource
	private SysUserPopedomMapper sysUserPopedomMapper;

	public List<SysUser> listAll() {
		return sysUserMapper.queryAll();
	}

	public SysUser checkLogin(String loginNm) {
		return sysUserMapper.queryByLoginNm(loginNm);
	}

	public SysUser mobileCheckLogin(String loginNm) {
		return sysUserMapper.mobileQueryByLoginNm(loginNm);
	}

	public SysUser edit(Long userId) {
		return sysUserMapper.queryById(userId);
	}

	public SysUser queryList(Long userId) {
		return sysUserMapper.queryList(userId);
	}

	@Transactional
	public String update(SysUser sysUser) {
		List<SysUser> list = sysUserMapper.queryByLoginNmUp(sysUser);
		if (null != list && list.size() > 0) {
			return "{\"Msg\":\"该登录名已存在，请重新输入！\",\"flag\":false}";
		}

		if ((!(sysUser.getRoleId().equals(7l) || sysUser.getRoleId().equals(8l)))// 添加的角色不是住户和商户，并且账号的roleId比之前的靠前
				&& sysUser.getRoleId() < (getUserBean().getRoleId())) {
			return "{\"Msg\":\"您无法创建比您帐号等级高的信息,操作失败!\",\"flag\":false}";
		}
		/*
		 * List<SysUser> list2=sysUserMapper.queryByUserCdUp(sysUser); if(null
		 * != list2 && list2.size() >0) { return
		 * "{\"Msg\":\"该用户编号已存在，请重新输入！\",\"flag\":false}"; }
		 */
		sysUser.setChgDt(new Date());
		sysUserMapper.update(sysUser);
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + sysUser.getIdKey() + ",cdNm:" + sysUser.getCdNm());
		return "{\"Msg\":\"更新人员信息成功！\",\"idKey\":\"" + sysUser.getIdKey()
				+ "\",\"DeptId\":\"" + sysUser.getDeptId() + "\",\"cdNm\":\""
				+ sysUser.getCdNm() + "\",\"ty\":\"update\",\"flag\":true}";
	}

	@Transactional
	public String delete(Long idKey) {
		if (null == idKey) {
			return "{\"errorMsg\":\"请选中需要删除的行！\",\"flag\":false}";
		}
		SysUser sysUser = sysUserMapper.queryList(idKey);
		SessionBean sessionBean = getUserBean();
		if (Long.parseLong(sysUser.getRoleType()) < Long.parseLong(sessionBean
				.getRoleType())) {
			return "{\"Msg\":\"您无法删除比您帐号等级高的信息,操作失败!\",\"flag\":false}";
		}
		sysUserMapper.delete(idKey);
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_DELETE,
				"idKey:" + sysUser.getIdKey() + ",cdNm:" + sysUser.getCdNm());
		return "{\"Msg\":\"删除人员成功！\",\"idKey\":\"" + sysUser.getIdKey()
				+ "\",\"flag\":true}";
	}

	public Page<SysUser> listPage(int pageIndex, int rows, Long deptID) {
		Page<SysUser> page = new Page<SysUser>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptID", deptID);
		page.setParams(map);
		List<SysUser> list = sysUserMapper.queryPage(page);
		page.setRows(list);
		return page;
	}

	public List<SysUser> queryBydeptID(Long deptID) {
		return sysUserMapper.queryBydeptID(deptID);
	}
	
	@Override
	public List<SysUser> querySalesBydeptID(Long communityId) {
		Page<SysUser> page = new Page<SysUser>();
		page.setPageNo(1);
		page.setPageSize(99999);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptID", communityId);
		map.put("roleId", Constants.SALES_ROLE_ID);
		page.setParams(map);
		List<SysUser> list = sysUserMapper.queryPage(page);
		return list;
	}

	

	@Transactional
	public String updateDeptIDByIdKey(Long idKey, Long oldDeptId, Long targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("deptID", targetId);
		sysUserMapper.updateDeptIDByIdKey(map);
		SysUser sysUser = sysUserMapper.edit(idKey);
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_TREEDRAG,
				"idKey:" + sysUser.getIdKey() + ",cdNm:" + sysUser.getCdNm()
						+ ",oldParentId:" + oldDeptId + ",newDeptId:"
						+ targetId);
		return "{\"successMsg\":\"移动成功！\",\"flg\":true}";
	}

	@Transactional
	public String insert(SysUser sysUser) {
		List<SysUser> list = sysUserMapper.qByLoginNm(sysUser.getLoginNm());
		if (null != list && list.size() > 0) {
			return "{\"Msg\":\"该登录名已存在，请重新输入！\",\"flag\":false}";
		}
		if (!(sysUser.getRoleId().equals(7L) || sysUser.getRoleId()//不能添加比自己权限高的用户，除了商户和住户
				.equals(8L)) && sysUser.getRoleId() < getUserBean().getRoleId()) {
			return "{\"Msg\":\"您无法创建比您帐号等级高的信息,操作失败!\",\"flag\":false}";
		}
		
		
		sysUser.setGender("m");// 男
		sysUser.setUserStatus(1L);// 在职
		sysUser.setChgDt(new Date());
		sysUser.setUserPwd(StringUtils.encrypt("123456"));
		sysUserMapper.insert(sysUser);
		if(sysUser.getRoleId()==9){//社区管理员,增加默认权限
			SysUserPopedom sysUserPopedom =new SysUserPopedom();
			sysUserPopedom.setType(5);//小区级
			sysUserPopedom.setDeptId(sysUser.getDeptId());
			sysUserPopedom.setUserId(sysUser.getIdKey());
			sysUserPopedomMapper.insert(sysUserPopedom);
		}
		writeLog(Constants.FUNC_MENU_NM_USER, Constants.FUNC_OPER_NM_CREATE,
				"idKey:" + sysUser.getIdKey() + ",cdNm:" + sysUser.getCdNm());
		return "{\"Msg\":\"增加用户信息成功！\",\"idKey\":\"" + sysUser.getIdKey()
				+ "\",\"DeptId\":\"" + sysUser.getDeptId() + "\",\"cdNm\":\""
				+ sysUser.getCdNm() + "\",\"ty\":\"add\",\"flag\":true}";

	}

	/**
	 * 修改密码
	 * 
	 * @Title: modifyPwd
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param userPwd
	 * @param newPwd
	 * @param surePwd
	 * @return
	 * @return String
	 * @throws
	 */
	@Transactional
	public String modifyPwd(String userPwd, String newPwd, String surePwd,
			String types) {
		SysUser userInfo = new SysUser();
		SessionBean sessionBean = getUserBean();
		String msg = "";
		if (!newPwd.equals(surePwd)) {
			msg = "{\"Msg\":\"两次密码输入不一样,请重新输入！\",\"flag\":false}";
			return msg;
		}
		if (sessionBean != null) {
			String loginNm = sessionBean.getLoginNm();
			userInfo = sysUserMapper.queryByLoginNm(loginNm);
			if (userInfo == null) {
				userInfo = sysUserMapper.queryByLoginName2Community(loginNm);
			}
			if (null != userInfo) {
				userInfo.setIsModify(1);
				if (types != null && types.equals("1")) {
					userInfo.setUserPwd(StringUtils.encrypt(newPwd));
					sysUserMapper.modifyPwd(userInfo);
					msg = "{\"Msg\":\"修改用户密码成功！\",\"flag\":true}";
				} else {
					if (StringUtils.encrypt(userPwd).equals(
							userInfo.getUserPwd())) {
						userInfo.setUserPwd(StringUtils.encrypt(newPwd));
						sysUserMapper.modifyPwd(userInfo);
						msg = "{\"Msg\":\"修改用户密码成功！\",\"flag\":true}";
					} else {
						msg = "{\"Msg\":\"原密码输入错误,请重新输入！\",\"flag\":false}";
					}
				}
			}
		} else {
			msg = "{\"Msg\":\"请重新登录！\",\"flag\":false}";
		}
		writeLog(Constants.FUNC_MENU_NM_USERPWD, Constants.FUNC_OPER_NM_UPDATE,
				"idKey:" + userInfo.getIdKey() + ",cdNm:" + userInfo.getCdNm());
		return msg;
	}

	@Override
	public String mobileModifyPassWord(String newPassWord, String oldPassWord) {
		SessionBean sessionBean = getUserBean();
		SysUser userInfo = sysUserMapper.mobileQueryByLoginNm(sessionBean
				.getLoginNm());
		if (null != userInfo) {
			userInfo.setIsModify(1);
			if (StringUtils.encrypt(oldPassWord).equals(userInfo.getUserPwd())) {
				userInfo.setUserPwd(StringUtils.encrypt(newPassWord));
				sysUserMapper.modifyPwd(userInfo);
				return ResultBuilderUtil.RESULT_SUCCESS;
			} else {
				return ResultBuilderUtil.statusMessage(2, "密码不一致！");
			}
		}

		return ResultBuilderUtil.statusMessage(3, "查无此账户！");
	}

	/**
	 * 创建树，当部门树末端时，创建小区树，小区数末端时创建人员树
	 * 
	 * @title creatSysDeptOrUserTree
	 * @author chen qige
	 * @date 2014年9月4日
	 * @param nodeId
	 * @return
	 */
	public DhtmlxTreeModel creatSysDeptOrUserTree(Long nodeId) {
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
				if (!sysDeptService.haveChildren(nodeId)) {//部门子节点没了，开始展开小区
					if (!sysCommunityService.haveChildren(nodeId)) {//小区子节点没了。开始展开用户
						tree.setItem(getFirstFourChildren(nodeId, false));
					} else {//还有小区子节点
						tree.setItem(getFirstThreeChildren(nodeId, false));
					}
				} else {//还有部门子节点
					tree.setItem(getFirstTwoChildren(nodeId));
				}
			}
			return tree;
	}

	/**
	 * 第一次获取构造树
	 * 
	 * @title getFirstOneChildren
	 * @author chen qige
	 * @date 2014年9月4日
	 * @param parentId
	 * @return
	 */
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
					List<SysUser> list1 = sysUserMapper.queryBydeptID(sysDept
							.getIdKey());
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
	 * 部门构造树末端
	 * 
	 * @title getFirstTwoChildren
	 * @author chen qige
	 * @date 2014年9月4日
	 * @param parentId
	 * @return
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
	 * 获取小区构造树
	 * 
	 * @title getFirstThreeChildren
	 * @author chen qige
	 * @date 2014年9月4日
	 * @param deptId
	 * @param flg
	 * @return
	 */
	private List<DhtmlxTreeModel> getFirstThreeChildren(Long deptId, Boolean flg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", deptId);
		map.put("communityIds",
				getUserBean().getPopedomCommunityIds().split(","));// 增加管理员是否可以查看该小区权限判断
		List<SysCommunity> list = sysCommunityMapper
				.queryBydeptIdAndPopedomIds(map);//查看是否有子节点小区
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysCommunity sysCommunity : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysCommunity.getIdKey().toString());
				node.setText(sysCommunity.getCommunityName());
				List<SysUser> list1 = sysUserMapper.queryBydeptID(sysCommunity
						.getIdKey());
				if (null != list1 && list1.size() > 0) {
					node.setChild("1");
					node.setIm1("community.png");
					node.setIm2("community.png");
				} else {
					node.setChild("0");
					node.setIm0("community.png");
				}
				// node.setIm0("people.png");
				children.add(node);
			}
		}
		return children;
	}

	/**
	 * 获取用户构造树
	 * 
	 * @title getFirstFourChildren
	 * @author chen qige
	 * @date 2014年9月4日
	 * @param deptId
	 * @param flg
	 * @return
	 */
	private List<DhtmlxTreeModel> getFirstFourChildren(Long deptId, Boolean flg) {
		List<SysUser> list = sysUserMapper.queryBydeptID(deptId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if (null != list && list.size() > 0) {
			for (SysUser sysUser : list) {
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(00 + sysUser.getIdKey().toString());
				node.setText(sysUser.getCdNm());
				if (flg) {
					node.setChild("1");
				}
				node.setIm0("people.png");
				children.add(node);
			}
		}
		return children;
	}

	@Transactional
	public String initPwd(Long userId) {
		SysUser sysUser = sysUserMapper.queryList(userId);
		if (sysUser != null) {
			SessionBean sessionBean = getUserBean();
			if (Long.parseLong(sysUser.getRoleType()) < Long
					.parseLong(sessionBean.getRoleType())) {
				return "{\"Msg\":\"您无法修改比您帐号等级高的信息,密码重置失败!\",\"flag\":false}";
			}
			sysUser.setUserPwd(StringUtils.encrypt("123456"));
			sysUserMapper.modifyPwd(sysUser);
			writeLog(
					Constants.FUNC_MENU_NM_USER,
					Constants.FUNC_OPER_NM_UPDATE,
					"idKey:" + sysUser.getIdKey() + ",cdNm:初始化用户"
							+ sysUser.getCdNm() + "密码成功");
			return "{\"Msg\":\"初始化密码成功！\",\"flag\":true}";
		} else {
			return "{\"Msg\":\"初始化密码失败！\",\"flag\":false}";
		}
	}

	@Override
	public Long queryMaxOrderCdByPId(Long parentId) {
		return sysUserMapper.queryMaxOrderCdByPId(parentId);
	}

	@Override
	public String mobileInsertUser(MobileParms parms) {
		SysUser sysUser = new SysUser();
		sysUser.setLoginNm(parms.getLoginName());
		sysUser.setUserPwd(StringUtils.encrypt(parms.getUserPasswd()));
		sysUser.setDeptId(parms.getCommunityId());
		sysUser.setRoleId(parms.getRoleId());
		sysUser.setUserCd(parms.getUserCd());
		sysUser.setBuilding(parms.getBuilding());
		sysUser.setUnit(parms.getUnit());
		sysUser.setFloor(parms.getFloor());
		sysUser.setRoom(parms.getRoom());
		
		if (parms.getLoginName().matches("^[1][3,4,5,8][0-9]{9}$"))
			sysUser.setBindTel(parms.getLoginName());
		sysUser.setCdNm(parms.getLoginName());
		sysUser.setChgDt(new Date());
		List<SysUser> list = sysUserMapper.qByLoginNm(sysUser.getLoginNm());
		if (null != list && list.size() > 0) {
			return ResultBuilderUtil.statusMessage(2, "用户名重复！");
		}
		sysUserMapper.insert(sysUser);
		return ResultBuilderUtil.RESULT_SUCCESS;

	}

	@Override
	public String mobileUpdateUser(MobileParms parms) {
		SysUser userInfo = new SysUser();
		String loginNm = "";
		SessionBean sessionBean = getUserBean();
		userInfo.setIdKey(sessionBean.getUserId());
		userInfo.setDeptId(parms.getCommunityId());
		userInfo.setTelephone(parms.getTelephone());
		userInfo.setAddr(parms.getAddr());
		if (parms.getUserCdNm() != null
				&& !parms.getUserCdNm().equals(sessionBean.getUserName())) {
			List<SysUser> list = sysUserMapper.queryBycdNm(parms.getUserCdNm());
			
			for (SysUser sysUser : list)
			{
				loginNm = sysUser.getLoginNm();
				break;
			}
			if (list.size() > 0 && null != list && !loginNm.equals(sessionBean.getLoginNm())) {
				return ResultBuilderUtil.statusMessage(2, "用户名重复");
			}
			userInfo.setCdNm(parms.getUserCdNm());
		}
		sysUserMapper.update(userInfo);
		/*
		 * 更新session内容
		 */
		if (parms.getCommunityId() != null) {
			getUserBean().setDeptId(parms.getCommunityId());
		}
		if (parms.getAddr() != null) {
			getUserBean().setAddr(parms.getAddr());
		}
		if (parms.getTelephone() != null) {
			getUserBean().setTelephone(parms.getTelephone());
		}
		return ResultBuilderUtil.statusMessage(1, "操作成功");
	}

	@Override
	public void mobileUpdateUserCd(SysUser sysUser) {
		sysUserMapper.update(sysUser);
	}

	@Override
	public String mobileGetPassword(MobileParms parms) {
		List<SysUser> list = sysUserMapper.qByLoginNm(parms.getLoginName());
		int appType = parms.getAppType() + 6;
		Long roleId = (long) appType;
		if (list.size() != 0) {
			if (list.size() == 1) {
				for (SysUser userInfo : list) {
					if (userInfo.getRoleId() == roleId) {
						userInfo.setIsModify(1);
						userInfo.setUserPwd(StringUtils.encrypt(parms
								.getNewPassWord()));
						sysUserMapper.modifyPwd(userInfo);
						return ResultBuilderUtil.RESULT_SUCCESS;
					} else {
						return ResultBuilderUtil.statusMessage(3, "角色错误");
					}
				}
			}
		}
		return ResultBuilderUtil.statusMessage(2, "更新失败");
	}

	@Override
	public String mobileBindTel(MobileParms parms) {
		String str = " ";
		try {
			MobSmsVerifyKit mob = new MobSmsVerifyKit();
			str = mob.smsVerify(parms.getPhone(), parms.getZone(),
					parms.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBuilderUtil.statusMessage(4, "程序异常");
		}
		SessionBean sessionBean = getUserBean();
		if (parms.getType() == 3 && str.indexOf("200") != -1) {
			SysUser sys = new SysUser();
			sys.setBindTel(parms.getPhone());
			sys.setIdKey(sessionBean.getUserId());
			sysUserMapper.update(sys);
			return ResultBuilderUtil.statusMessage(1, "绑定成功");
		}
		if (str.indexOf("520") != -1) {
			return ResultBuilderUtil.statusMessage(2, "绑定失败");
		}
		return ResultBuilderUtil.statusMessage(3, "验证码无效");
	}

	@Override
	public SysUser queryByLoginName2Community(String loginNm) {
		return sysUserMapper.queryByLoginName2Community(loginNm);
	}

	@Override
	public String cmdLoginByPhoneNumber(HttpServletRequest request, SysUser user) {
		SysUser userInfo = sysUserMapper.queryByBindTel(user.getBindTel());
		if (null != userInfo) {
			if (userInfo.getRoleId() != user.getRoleId())
				return ResultBuilderUtil.statusMessage(5, "登录版本不对");
			if (StringUtils.encrypt(user.getUserPwd()).equals(
					userInfo.getUserPwd())) {
				HttpSession httpSession = request.getSession();

				httpSession.setAttribute("sessionBean",
						getSessionBean(userInfo));
				SysCommunityVO sysCommunityVO = sysCommunityService
						.queryCommunityVOById(userInfo.getDeptId());
				StringBuilder sb = new StringBuilder();
				sb.append("{\"status\":\"1\",\"message\":\"操作成功\",\"data\":{")
						.append("\"province\":\"")
						.append(sysCommunityVO.getProvince())
						.append("\",\"city\":\"")
						.append(sysCommunityVO.getCity())
						.append("\",\"district\":\"")
						.append(sysCommunityVO.getDistrict())
						.append("\",\"community\":\"")
						.append(sysCommunityVO.getCommunityName())
						.append("\",\"communityId\":\"")
						.append(userInfo.getDeptId())
						.append("\",\"buliding\":\"")
						.append(StringUtils.null2Blank(userInfo.getBuilding()))
						.append("\",\"unit\":\"")
						.append(StringUtils.null2Blank(userInfo.getUnit()))
						.append("\",\"floor\":\"")
						.append(StringUtils.null2Blank(userInfo.getFloor()))
						.append("\",\"room\":\"")
						.append(StringUtils.null2Blank(userInfo.getRoom()))
						.append("\",\"addr\":\"")
						.append(StringUtils.null2Blank(userInfo.getAddr()))
						.append("\",\"name\":\"")
						.append(StringUtils.null2Blank(userInfo.getCdNm()))
						.append("\",\"bindTel\":\"")
						.append(userInfo.getBindTel())
						.append("\",\"telephone\":\"")
						.append(StringUtils.null2Blank(userInfo.getTelephone()))
						.append("\",\"JSESSIONID\": \"")
						.append(StringUtils.null2Blank(httpSession.getId()))
						.append("\"").append("}");
				sb.append("}");
				return sb.toString();
			} else {
				return ResultBuilderUtil.statusMessage(2, "密码错误");
			}
		}
		return ResultBuilderUtil.statusMessage(2, "用户不存在");
	}

	/**
	 * 给sessionBean赋值
	 * 
	 * @Title: getSessionBean
	 * @author ts 2013-12-4
	 * @Description:
	 * @param userInfo
	 * @return
	 * @return SessionBean
	 * @throws
	 */
	private SessionBean getSessionBean(SysUser userInfo) {
		SessionBean sessionBean = new SessionBean();
		sessionBean.setUserId(userInfo.getIdKey());
		sessionBean.setUserCd(userInfo.getUserCd());
		sessionBean.setDeptName(userInfo.getDeptName());
		sessionBean.setLoginNm(userInfo.getLoginNm());
		sessionBean.setUserName(userInfo.getCdNm());
		sessionBean.setIsModify(userInfo.getIsModify());
		sessionBean.setAddr(userInfo.getAddr());
		sessionBean.setTelephone(userInfo.getTelephone());
		sessionBean.setBindTel(userInfo.getBindTel());
		sessionBean.setPopedomCommunityIds(userInfo.getPopedomCommunityIds());

		if (userInfo.getRoleId() == Constants.SYS_MENU_TREE_ROOT_ID) { // 如果是系统管理员则可以看到所有的树结构
			sessionBean.setDeptId(Constants.SYS_DEPT_TREE_ROOT_ID);// userInfo.getDeptId()
			// SysDept dept =
			// sysDeptService.edit(Constants.SYS_DEPT_TREE_ROOT_ID);//
			// userInfo.getDeptId()
			// sessionBean.setDeptName(dept.getCdNm());
		} else if (userInfo.getRoleId() == Constants.PROPERTY_ROLE_ID) { // 物业管理人员
			sessionBean.setDeptId(userInfo.getDeptId());
			// SysDept dept = sysDeptService.edit(userInfo.getDeptId());//
			// userInfo.getDeptId()
			// sessionBean.setDeptName(dept.getCdNm());
		} else if (userInfo.getRoleId() == Constants.SALES_ROLE_ID
				|| userInfo.getRoleId() == Constants.USER_ROLE_ID) {
			sessionBean.setDeptId(userInfo.getDeptId());
		}
		sessionBean.setRoleId(userInfo.getRoleId());
		SysRole role = sysRoleService.queryRoleByIdKey(userInfo.getRoleId());
		sessionBean.setRoleName(role.getRoleName());
		sessionBean.setRoleType(role.getRoleType());

		return sessionBean;
	}

	
}
