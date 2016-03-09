package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizUserGift;
import com.dnake.domain.business.BizUserGiftVO;
public interface BizUserGiftMapper {
void insert(BizUserGift bizUserGift);

void delete(long idKey);

void update(BizUserGift bizUserGift);

BizUserGift queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizUserGiftVO> queryPage(Page<BizUserGiftVO> page);

void insertMulti(List<BizUserGift> list);

}
