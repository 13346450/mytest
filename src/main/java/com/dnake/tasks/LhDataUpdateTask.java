package com.dnake.tasks;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.basic.BsIds;
import com.dnake.domain.business.BizProperInfo;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysUser;
import com.dnake.mapper.basic.BsIdsMapper;
import com.dnake.mapper.business.BizProperInfoMapper;
import com.dnake.mapper.business.BizPropertiesRepairMapper;
import com.dnake.mapper.business.BizUinfoCollectMapper;
import com.dnake.mapper.system.SysCommunityInfoMapper;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.service.business.BizPropertiesRepairService;
import com.dnake.tasks.lhApi.LhApi;
import com.dnake.tasks.lhApi.LhListItemHandler;
import com.dnake.tasks.lhApi.LhListResult;
import com.dnake.tasks.lhApi.bean.GetCommunityList;
import com.dnake.tasks.lhApi.bean.GetFeedbackDetails;
import com.dnake.tasks.lhApi.bean.GetFeedbackDetails2;
import com.dnake.tasks.lhApi.bean.GetNotice;
import com.dnake.tasks.lhApi.bean.GetNoticeList;
import com.dnake.tasks.lhApi.bean.GetUserAdvices;
import com.dnake.tasks.lhApi.bean.GetUserFeedback;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

@Component
public class LhDataUpdateTask {
	public static void main(String[] args) {
		// 18859255842
		// 18859255842
		// System.out.println(LhApi.bindingCommunity("18859255842", "35020601990001"));
		// boolean s = LhApi.binding("18859255842","35029997001378","18859255842","18859255842");
		// System.out.println(s);
		// System.out.println(JSON.toJSON(LhApi.GetUserCodes("18859255842")));
		// LhApi.getCommunityList("18859255842",new LhListItemHandler<GetCommunityList>() {
		// @Override
		// public void execute(LhListResult<GetCommunityList> root, GetCommunityList item) {
		// System.out.println("小区数据:" + JSON.toJSONString(item));
		// }
		// });
		// LhApi.getUserFeedback("www2222", new LhListItemHandler<GetUserFeedback>() {
		// @Override
		// public void execute(LhListResult<GetUserFeedback> root, GetUserFeedback item) {
		// System.out.println(item);
		// }
		// });
		// 8244cdf2-8202-4b4d-a8c1-c6be75fa29bc
		// 73ed9722-5f50-4213-bc4b-fe9646b73023
		// LhApi.GetFeedbackDetails("22861cd8-32c6-472c-b19f-df827862ffcb",new LhListItemHandler<GetFeedbackDetails>() {
		// @Override
		// public void execute(LhListResult<GetFeedbackDetails> root, GetFeedbackDetails item) {
		// System.out.println(JSON.toJSON(item));
		// }
		// });
		// LhApi.getUserAdvices("id",new LhListItemHandler<GetUserAdvices>() {
		//
		// @Override
		// public void execute(LhListResult<GetUserAdvices> root, GetUserAdvices item) {
		//
		// }
		// });
	}

	@Autowired
	private SysCommunityInfoMapper sysCommunityInfoMapper;
	@Autowired
	private SysCommunityMapper sysCommunityMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private BizProperInfoMapper bizProperInfoMapper;
	@Autowired
	private BsIdsMapper bsIdsMapper;
	@Autowired
	private BizPropertiesRepairService bizPropertiesRepairService;
	@Autowired
	private BizPropertiesRepairMapper bizPropertiesRepairMapper;
	@Autowired
	private BizUinfoCollectMapper bizUinfoCollectMapper;
	// 同步用户投诉建议的信息
	@Scheduled(cron="${scheduled.corn.updateAdvicesInfo}")
	public void updateAdvicesInfo() {
		List<SysUser> sysUsers = sysUserMapper.listByRoleId(7);
		for (final SysUser sysUser : sysUsers) {
			LhApi.getUserAdvices(sysUser.getIdKey()+"", new LhListItemHandler<GetUserAdvices>() {
				@Override
				public void execute(LhListResult<GetUserAdvices> root, GetUserAdvices item) {
					BsIds bsIds = bsIdsMapper.getByNameTypeAndTypeAndOId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_BX, item.getID());
					if(bsIds!=null){
						BizUinfoCollect bizUinfoCollect = new BizUinfoCollect();
						bizUinfoCollect.setUserContent(item.getContent());
						bizUinfoCollect.setCommunityId(sysUser.getDeptId());
						bizUinfoCollect.setUserId(sysUser.getIdKey());
						bizUinfoCollect.setPublishDt(item.getWriteTime());
						bizUinfoCollect.setTitle(item.getTitle());
						bizUinfoCollect.setTel(item.getTel());
						bizUinfoCollect.setLinkMan(item.getLinkman());
						bizUinfoCollect.setAddress(item.getAddress());
						bizUinfoCollect.setContentType(1);
						bizUinfoCollect.setReplyStatus(1);
						bizUinfoCollectMapper.insert(bizUinfoCollect);
						
						bsIds = new BsIds();
						bsIds.setiId(bizUinfoCollect.getIdKey());
						bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
						bsIds.setType(BsIds.TYPE_OF_ADVICE);
						bsIds.setoId(item.getID());
						bsIdsMapper.insert(bsIds);
					}
				}
			});
			// List<GetUserCodes> codes = LhApi.GetUserCodes(sysUser.getLoginNm());
			// System.out.println(JSON.toJSONString(codes));
			// if(codes.size()>0){
			// continue;
			// }
			// LhApi.getUserFeedback(sysUser.getLoginNm(), new LhListItemHandler<GetUserFeedback>() {
			// @Override
			// public void execute(LhListResult<GetUserFeedback> root, GetUserFeedback item) {
			// BizPropertiesRepair repair = new BizPropertiesRepair();
			//
			// System.out.println(item);
			// }
			// });
		}
	}

	// 同步用户故障报修列表
	@Scheduled(cron="${scheduled.corn.updateRepairInfo}")
	public void updateRepairInfo() {
		System.out.println("同步用户故障报修<start>");
		List<SysUser> sysUsers = sysUserMapper.listByRoleId(7);
		for (final SysUser sysUser : sysUsers) {
			LhApi.getUserFeedback(sysUser.getIdKey()+"", new LhListItemHandler<GetUserFeedback>() {
				@Override
				public void execute(LhListResult<GetUserFeedback> root, GetUserFeedback item) {
					GetFeedbackDetailshHandler detailshHandler = new GetFeedbackDetailshHandler();
					detailshHandler.feedback = item;
					detailshHandler.user = sysUser;
					LhApi.getFeedbackDetails(item.getID(), detailshHandler);
				}
			});
		}
		System.out.println("同步用户故障报修<end>");
	}
	private class GetFeedbackDetailshHandler implements LhListItemHandler<GetFeedbackDetails> {
		SysUser user;
		GetUserFeedback feedback;
		@Override
		public void execute(LhListResult<GetFeedbackDetails> root, GetFeedbackDetails item) {
			BsIds bsIds = bsIdsMapper.getByNameTypeAndTypeAndOId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_BX, feedback.getID());
			BizPropertiesRepair biz = null;
			if(bsIds!=null){
				biz = bizPropertiesRepairMapper.getById(bsIds.getiId());
				biz.setRepairStatus("待处理".equals(item.getStatus())?1:2);
				bizPropertiesRepairMapper.update(biz);
				//bizPropertiesRepairMapper.update(biz);
			}else{
				biz = new BizPropertiesRepair();
				biz.setTitle(item.getTitle());
				biz.setType(item.getFlag().equals("个人保险")?1:2);
				biz.setContent(item.getDetails().get(0).getContent());
				biz.setContacts(item.getLinkman());
				biz.setContactsAddr(item.getAddress());
				biz.setContactsTel(item.getTel());
				biz.setPublishDt(feedback.getWriteTime());
				biz.setUserId(user.getIdKey());
				biz.setCommunityId(user.getDeptId());
				biz.setRepairStatus("待处理".equals(item.getStatus())?1:2);
				bizPropertiesRepairMapper.insert(biz);
				bsIds = new BsIds();
				bsIds.setiId(biz.getIdKey());
				bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
				bsIds.setType(BsIds.TYPE_OF_BX);
				bsIds.setoId(item.getDetails().get(0).getFID().toLowerCase());
				bsIdsMapper.insert(bsIds);
				System.out.println("同步报修主题信息:"+JSON.toJSONString(item));
			}
			for(int i = 1;i<item.getDetails().size();i++){
				GetFeedbackDetails2 details2 = item.getDetails().get(i);
				bsIds = bsIdsMapper.getByNameTypeAndTypeAndOId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_BX, details2.getID());
				if(bsIds==null){
					biz = new BizPropertiesRepair();
					biz.setTitle(details2.getContent());
					biz.setType(item.getFlag().equals("个人保险")?1:2);
					biz.setContent(details2.getContent());
					biz.setContacts(item.getLinkman());
					biz.setContactsAddr(item.getAddress());
					biz.setContactsTel(details2.getTel());
					biz.setPublishDt(details2.getWriteTime());
					biz.setUserId(user.getIdKey());
					biz.setCommunityId(user.getDeptId());
					biz.setRepairStatus("待处理".equals(item.getStatus())?1:2);
					bizPropertiesRepairMapper.insert(biz);
					bsIds = new BsIds();
					bsIds.setiId(biz.getIdKey());
					bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
					bsIds.setType(BsIds.TYPE_OF_BX);
					bsIds.setoId(details2.getID().toLowerCase());
					bsIdsMapper.insert(bsIds);
					System.out.println("同步追加信息:"+JSON.toJSONString(details2));
				}
			}
		}
	};
	// 通知公告
	@Scheduled(cron="${scheduled.corn.updateRepairInfo}")
	public void updateNotify() throws IOException, Base64DecodingException {
		List<SysCommunity> codes = sysCommunityMapper.queryCommunityIdAndCodeByCodes();
		for (final SysCommunity code : codes) {
			// 获得列表
			LhApi.getNoticeList(code.getCommunityCode(), new LhListItemHandler<GetNoticeList>() {
				@Override
				public void execute(LhListResult<GetNoticeList> root, GetNoticeList item) {
					BsIds bsIds = bsIdsMapper.getByNameTypeAndTypeAndOId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_NOTIFY, item.getID());
					if (bsIds != null) {// 已更新，跳过
						return;
					}
					// 获得明细
					GetNotice getNotice = LhApi.getNotice(item.getID());
					BizProperInfo bizProperInfo = new BizProperInfo();
					bizProperInfo.setCommunityCode(item.getCommunityCode());
					bizProperInfo.setInfoType(54);
					bizProperInfo.setCommunityId(code.getIdKey());
					bizProperInfo.setLhID(item.getID());
					bizProperInfo.setLastUpdated(item.getLastUpdated());
					bizProperInfo.setPublishDt(item.getIssuesTime());
					bizProperInfo.setExpirationTime(item.getExpirationTime());
					bizProperInfo.setOindex(Integer.parseInt(item.getOIndex()));
					bizProperInfo.setFlag(item.getFlag());
					bizProperInfo.setCreateDt(new Date());
					bizProperInfo.setInfoTitle(item.getTitle());
					bizProperInfo.setInfoContent(getNotice.getContent());
					bizProperInfo.setInfoStatus(-1);
					System.out.println(bizProperInfo.getInfoContent().length());
					bizProperInfoMapper.insert(bizProperInfo);
					System.out.println("从LH导入通知公告" + JSON.toJSONString(item));
					bsIds = new BsIds();
					bsIds.setiId(bizProperInfo.getIdKey());
					bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
					bsIds.setoId(item.getID());
					bsIds.setType(BsIds.TYPE_OF_NOTIFY);
					bsIdsMapper.insert(bsIds);
				}
			});
		}
	}

	// 同步小区
	@Scheduled(cron="${scheduled.corn.getCommunityList}")
	public void getCommunityList() {
		LhApi.getCommunityList(null, new LhListItemHandler<GetCommunityList>() {
			@Override
			public void execute(LhListResult<GetCommunityList> root, GetCommunityList item) {
				BsIds bsIds = bsIdsMapper.getByNameTypeAndTypeAndOId(BsIds.NAME_TYPE_OF_LIHANG, BsIds.TYPE_OF_COMMUNITY, item.getCommunityCode());
				if (bsIds != null) {
					return;
				}
				List<SysCommunity> communities = sysCommunityMapper.queryCommunityByName(item.getCommunityName());
				if (communities == null || communities.size() == 0) {
					return;
				}
				bsIds = new BsIds();
				bsIds.setiId(communities.get(0).getIdKey());
				bsIds.setNameType(BsIds.NAME_TYPE_OF_LIHANG);
				bsIds.setoId(item.getCommunityCode());
				bsIds.setType(BsIds.TYPE_OF_COMMUNITY);
				bsIdsMapper.insert(bsIds);
				System.out.println("从LH导入小区数据:" + JSON.toJSONString(item));
			}
		});
	}
}
