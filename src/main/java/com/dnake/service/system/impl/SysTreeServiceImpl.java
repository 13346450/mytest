package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.common.EasyUiTreeModel;
import com.dnake.mapper.system.SysTreeMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysTreeService;
import com.dnake.utils.SessionBean;

@Service
public class SysTreeServiceImpl extends BaseServiceImpl implements SysTreeService {
	@Resource
	SysTreeMapper sysTreeMapper;
	
	public List<EasyUiTreeModel> selectDeptTree(){
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		//SysCommunication sysCommunication = sessionBean.getSysCommunitation();
		List<EasyUiTreeModel> list = new ArrayList<EasyUiTreeModel>();
		/*if (sysCommunication != null && sysCommunication.getYesnoIdCallchild().equalsIgnoreCase("yes")) {
			list = sysTreeMapper.selectDeptTree(sessionBean.getDeptId());
		}else{
			EasyUiTreeModel et = new EasyUiTreeModel();
			et.setId(sessionBean.getDeptId().toString());
			et.setState("open");
			et.setText(sessionBean.getDeptName());
			list.add(et);
		}*/
		return list;
	}
	public List<EasyUiTreeModel> getDeptTreeNotRoot(){
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		//SysCommunication sysCommunication = sessionBean.getSysCommunitation();
		List<EasyUiTreeModel> list = new ArrayList<EasyUiTreeModel>();
		/*if (sysCommunication != null && sysCommunication.getYesnoIdCallchild().equalsIgnoreCase("yes")) {
			if(sessionBean.getDeptId()==1)
			{
				list = sysTreeMapper.queryChildrenByParentId(sessionBean.getDeptId());
			}else{
				list = sysTreeMapper.selectDeptTree(sessionBean.getDeptId());
			}
		}else{
			if(sessionBean.getDeptId()==1)
			{
				list = sysTreeMapper.queryChildrenByParentId(sessionBean.getDeptId());
			}else{
				EasyUiTreeModel et = new EasyUiTreeModel();
				et.setId(sessionBean.getDeptId().toString());
				et.setState("open");
				et.setText(sessionBean.getDeptName());
				list.add(et);
			}
		}*/
		return list;
	}
}
