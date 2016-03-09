package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizAdImage;
import com.dnake.domain.business.BizAdImageVO;
public interface BizAdImageMapper {
void insert(BizAdImage bizAdImage);

void delete(long idKey);

void update(BizAdImage bizAdImage);

BizAdImage queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizAdImageVO> queryPage(Page<BizAdImageVO> page);

void insertMulti(List<BizAdImage> list);
/**
 * 查询广告的类型
 * @param page
 * @return
 *@author cqg 
 *2015年5月26日
 */
List<BizAdImageVO> queryAdImageType(Page<BizAdImageVO> page);
/**
 * 查询当前广告里最大的排序号
 * @param dictKey
 * @return
 *@author cqg 
 *2015年5月27日
 */
Long queryMaxOrderByType(String dictKey,String communityId);
}
