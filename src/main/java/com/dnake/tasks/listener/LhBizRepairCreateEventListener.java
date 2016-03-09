package com.dnake.tasks.listener;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.dnake.domain.basic.BsIds;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.mapper.basic.BsIdsMapper;
import com.dnake.mapper.business.BizPropertiesRepairMapper;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.tasks.event.BizRepairCreateEvent;
import com.dnake.tasks.lhApi.LhApi;
import com.dnake.tasks.lhApi.bean.AppendFeedbackForm;
import com.dnake.tasks.lhApi.bean.CreateFeedbackForm;
@Component  
public class LhBizRepairCreateEventListener implements ApplicationListener<BizRepairCreateEvent>  {  
	@Autowired
	private SysCommunityMapper sysCommunityMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private BizPropertiesRepairMapper bizPropertiesRepairMapper;
	@Autowired
	private BsIdsMapper bsIdsMapper;
	public void onApplicationEvent(BizRepairCreateEvent arg0) {
		BizPropertiesRepair repair = arg0.getBizPropertiesRepair();
		//追加
		if(repair.getParentId()>0){
			BsIds bsIds = bsIdsMapper.getByNameTypeAndTypeAndIId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_BX, repair.getParentId());
			if(bsIds!=null){
				AppendFeedbackForm form = new AppendFeedbackForm(
						bsIds.getoId(), 
						repair.getContactsTel(), 
						sysUserMapper.queryById(repair.getUserId()).getIdKey()+"",
						sysUserMapper.queryById(repair.getUserId()).getLoginNm());
				form.setContent(repair.getContent());
				LhApi.appendFeedback(form);
			}
			System.out.println("追加报修");
			return;
		}
		//新的
		String code = sysCommunityMapper.queryCommunityByIdKey(repair.getCommunityId()).getCommunityCode();
		CreateFeedbackForm from = new CreateFeedbackForm(
				 repair.getTitle(), 
				 repair.getContactsTel(), 
				 code, 
				 "故障申告", 
				 sysUserMapper.queryById(repair.getUserId()).getIdKey()+"", 
				 sysUserMapper.queryById(repair.getUserId()).getLoginNm(), 
				 repair.getType()==1?"个人保修":"公共保修", 
				 repair.getContactsAddr(),
				 repair.getContacts());
		from.setContent(repair.getContent());
		String Id = LhApi.createFeedback(from);
		BsIds bsIds = new BsIds();
		bsIds.setiId(repair.getIdKey());
		bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
		bsIds.setType(BsIds.TYPE_OF_BX);
		bsIds.setoId(Id);
		bsIdsMapper.insert(bsIds);
	}
}  