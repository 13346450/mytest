package com.dnake.mapper.basic;

import com.dnake.domain.basic.BsIds;

/**
 */
public interface BsIdsMapper {
	public void insert(BsIds bsIds);
	public BsIds getById(Long idKey);
	public BsIds getByNameTypeAndTypeAndIId(int nameType,int type,Long iId);
	public BsIds getByNameTypeAndTypeAndOId(int nameType,int type,String oId);
}
