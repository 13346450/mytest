package com.dnake.service.common;

import java.util.List;
import java.util.Map;

/**
 * SQL注入方式的查询语句
 * @ClassName CommSqlService
 * @author zgj
 * 2014年10月25日 上午10:15:19
 */
public interface CommSqlService
{
	/**
	 * 根据传入的插入语句进行插入
	 * @param sql
	 * @return
	 */
	public int insertSql(String sql);
	/**
	 * 根据传入的更新语句更新
	 * @param sql
	 * @return
	 */
	public int updateSql(String sql);
	/**
	 * 根据传入的查询语句进行查询
	 * @param sql
	 * @return
	 */
	public List<Map> selectSql(String sql);
	/**
	 * 删除语句
	 * @param sql
	 * @return
	 */
	public int deleteSql(String sql);
}
