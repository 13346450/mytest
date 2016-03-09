package com.dnake.mapper.common;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 提供基础的查询语句类
 * @ClassName CommMapperProv
 * @author zgj
 * 2014年10月25日 上午9:22:30
 */
public class CommMapperProv
{
	private static Logger logger = LoggerFactory
			.getLogger(CommMapperProv.class);
	public static Map<String, String> sqlMap = new HashMap<String, String>();
	

/*	public String getSelectSqlOne(Map<String, Object> parameters) {
		String sKey = (String) parameters.get("mapkey");
		String[] sParam = (String[]) parameters.get("params");
		String sSql = new MapperSqlLoader().getSQLByCache(sKey);
		
		for(String sPrm:sParam){
			sSql = sSql.replaceFirst("\\?", sPrm);
		}
		
		return sSql;

	}*/
	public String getId_key(Map<String, Object> parameters){
		String sKey = (String) parameters.get("user");
		String sSql="select  f_getid('"+sKey+"') id_key from dual";
		logger.info("sSql:"+sSql);
		return sSql;
	}

	public String getPageSelect(Map<String, Object> parameters){
		String sSql = (String) parameters.get("sql");
		int pageLength =  Integer.parseInt(String.valueOf(parameters.get("pageLength")));
		int currPage = Integer.parseInt(String.valueOf(parameters.get("currPage")));
		String sSqql = " select * from ( select b.*, rownum row_num from ("+sSql+") b where" 
				 + " rownum <="+pageLength*currPage+" ) where row_num >"+pageLength*(currPage-1);
		return sSqql;
	}
/*	public String getCountMap(Map<String, Object> parameters){
		String sKey = (String) parameters.get("mapkey");
		String sSql = new MapperSqlLoader().getSQLByCache(sKey);
		if(null!=parameters.get("params")){
			String[] sParam = (String[]) parameters.get("params");
	
			for(String sPrm:sParam){
				sSql = sSql.replaceFirst("\\?", sPrm);
			}
		}
		return sSql;
	}*/
	public String getCountSql(Map<String, Object> parameters){
		String sSql = (String) parameters.get("count");
	
		return sSql;
	}
	public String insertSql(Map<String, Object> parameters){
		String sSql = (String) parameters.get("sql");
		return sSql;
	}
	public String updateSql(Map<String, Object> parameters){
		String sSql = (String) parameters.get("sql");
		return sSql;
	}
	public String getSelectSql(Map<String, Object> parameters) {
		String sSql = (String) parameters.get("sql");
		return sSql;
	}
	public String deleteSql(Map<String, Object> parameters){
		String sSql = (String) parameters.get("sql");
		return sSql;
	}
	public String jsonarray_sql(Map<String, Object> parameters) {
		String sSql = (String) parameters.get("jsonarray_sql");
		
		return sSql;

	}
	
	public String json_sql(Map<String, Object> parameters) {
		String sSql = (String) parameters.get("json_sql");
	
		return sSql;

	}
	public String meta_sql(Map<String, Object> parameters) {
		String sSql = (String) parameters.get("meta");
		
		return sSql;

	}
	public String pkey(Map<String, Object> parameters) {
		String sSql = (String) parameters.get("pkey");
		BEGIN();
		SELECT("col.column_name ");
		FROM(" user_constraints con,user_cons_columns col");
		WHERE ("con.constraint_name = col.constraint_name and con.constraint_type='P' "
				+ " and col.table_name = '"+sSql.toUpperCase()+"'");
		return SQL();
		//return sSql;

	}
	public String fkey(Map<String, Object> parameters) {
		String sSql = (String) parameters.get("fkey");
		BEGIN();
		SELECT("t2.column_name ");
		FROM("  user_constraints t1,user_cons_columns t2");
		WHERE ("t1.constraint_name=t2.constraint_name and t1.table_name='"+sSql.toUpperCase()+"' AND r_constraint_name IS NOT NULL");
		return SQL();
		
	}
/*	public String getSelectMapkey(Map<String, Object> parameters) {
		String key = (String) parameters.get("mapkey");
		
		String[] sParam = (String[]) parameters.get("params");
		String sSql = new MapperSqlLoader().getSQLByCache(key);
		
		
		for(String sPrm:sParam){
			sSql = sSql.replaceFirst("\\?", sPrm);
		}
		return sSql;

	}*/
	public String ce(Map<String, Object> parameters){
		BEGIN();
		SELECT("AA,BB");
		FROM("TABLE");
		WHERE ("USR=");
		return SQL();
	}
}
