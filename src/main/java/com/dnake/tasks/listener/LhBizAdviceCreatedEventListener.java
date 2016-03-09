package com.dnake.tasks.listener;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.dnake.domain.basic.BsIds;
import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.mapper.basic.BsIdsMapper;
import com.dnake.mapper.business.BizPropertiesRepairMapper;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.tasks.event.BizAdviceCreatedEvent;
import com.dnake.tasks.lhApi.LhApi;
import com.dnake.tasks.lhApi.bean.AppendFeedbackForm;
import com.dnake.tasks.lhApi.bean.CreateFeedbackForm;
@Component  
public class LhBizAdviceCreatedEventListener implements ApplicationListener<BizAdviceCreatedEvent>  {  
	@Autowired
	private SysCommunityMapper sysCommunityMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private BizPropertiesRepairMapper bizPropertiesRepairMapper;
	@Autowired
	private BsIdsMapper bsIdsMapper;
	@Override
	public void onApplicationEvent(BizAdviceCreatedEvent event) {
		BizUinfoCollect collect = event.getBizUinfoCollect();
		//追加
		if(collect.getParentId()>0){
			BsIds bsIds = bsIdsMapper.getByNameTypeAndTypeAndIId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_ADVICE, collect.getParentId());
			if(bsIds!=null){
				AppendFeedbackForm form = new AppendFeedbackForm(
						bsIds.getoId(), 
						collect.getTel(), 
						sysUserMapper.queryById(collect.getUserId()).getIdKey()+"",
						sysUserMapper.queryById(collect.getUserId()).getLoginNm());
				form.setContent(collect.getUserContent());
				LhApi.appendFeedback(form);
			}
			System.out.println("追加报修");
			return;
		}
		//新的
		String code = sysCommunityMapper.queryCommunityByIdKey(collect.getCommunityId()).getCommunityCode();
		CreateFeedbackForm from = new CreateFeedbackForm(
				 collect.getTitle(), 
				 collect.getTel(), 
				 code, 
				 "咨询建议", 
				 sysUserMapper.queryById(collect.getUserId()).getIdKey()+"", 
				 sysUserMapper.queryById(collect.getUserId()).getLoginNm(), 
				 collect.getType(), 
				 collect.getAddress(),
				 collect.getUserName());
		from.setContent(collect.getUserContent());
		String Id = LhApi.createFeedback(from);
		BsIds bsIds = new BsIds();
		bsIds.setiId(collect.getIdKey());
		bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
		bsIds.setType(BsIds.TYPE_OF_ADVICE);
		bsIds.setoId(Id);
		bsIdsMapper.insert(bsIds);
	}
}  