package com.dnake.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnake.mapper.common.CommMapper;
import com.dnake.service.common.CommSqlService;

@Service
public class CommSqlServiceImpl implements CommSqlService
{
	@Autowired
	CommMapper comm = null;
	
	@Override
	public int insertSql(String sql)
	{
		return comm.insert(sql);
	}

	@Override
	public int updateSql(String sql)
	{
		return comm.updateSql(sql);
	}

	@Override
	public List<Map> selectSql(String sql)
	{
		return comm.findBySqls(sql);
	}

	@Override
	public int deleteSql(String sql)
	{
		return comm.deleteSql(sql);
	}

}
