package com.dnake.mapper.common;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
/**
 * 接口类，负责对接
 * @ClassName CommMapper
 * @author zgj
 * 2014年10月25日 上午9:33:25
 */
public interface CommMapper
{
	/*@SelectProvider(type = CommMapperProv.class, method = "getSelectSqlOne")  // @Options(useCache = true, flushCache = false, timeout = 10000)  
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	public Map findByMapKey(@Param("mapkey") String mapkey,@Param("params") String[] params);*/
	
	 @SelectProvider(type = CommMapperProv.class, method = "getId_key") 
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public Map findPk(@Param("user") String user);
	 
	 @SelectProvider(type = CommMapperProv.class, method = "getPageSelect")
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public List<Map> findBySqlPage(@Param("sql") String sql,@Param("currPage") int currPage, @Param("pageLength") int pageLength);
	
	 @SelectProvider(type = CommMapperProv.class, method = "getCountSql")
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public int getCountSql(@Param("count") String sql);
	 
/*	 @SelectProvider(type = CommMapperProv.class, method = "getCountMap")
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public int getCountMap(@Param("mapkey") String mapkey,@Param("params") String[] params);*/
	 
	 @InsertProvider(type = CommMapperProv.class, method = "insertSql")
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public int insert(@Param("sql") String sql);
	 
	/* @SelectProvider(type = CommMapperProv.class, method = "getSelectMapkey")  //按传入的Mapper key 查询 
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public List<Map> findByMapKeys(@Param("mapkey") String mapkey,@Param("params") String[] params);*/
	 
	 @SelectProvider(type = CommMapperProv.class, method = "getSelectSql")  // 按传人的sql查询 
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public List<Map> findBySqls(@Param("sql") String sql);
	 
	 @SelectProvider(type = CommMapperProv.class, method = "getSelectSql")  // 按传人的sql查询 返回单条记录在map里
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public Map findBySql(@Param("sql") String sql);
	
	 @UpdateProvider(type = CommMapperProv.class, method = "updateSql")
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public int updateSql(@Param("sql") String sql);
	 
	 @DeleteProvider(type = CommMapperProv.class, method = "deleteSql")
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public int deleteSql(@Param("sql") String sql);
	 
	 @SelectProvider(type = CommMapperProv.class, method = "jsonarray_sql")  // 按传人的sql查询 
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public JSONArray  findJSONArrayBySqls(@Param("jsonarray_sql") String sql);
	 
	 
	 @SelectProvider(type = CommMapperProv.class, method = "json_sql")  // 按传人的sql查询 
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public JSONObject findJSONObjectBySqls(@Param("json_sql") String sql);
	 
	 @SelectProvider(type = CommMapperProv.class, method = "meta_sql")  // 获得表结构 
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public List<List> findTableMeta(@Param("meta") String sql);
	 
	 @SelectProvider(type = CommMapperProv.class, method = "pkey")  // 获得表主键
	 @Options(useCache = false, flushCache = true, timeout = 10000)  
	 public List<String> findTablePkey(@Param("pkey") String sql);

	@SelectProvider(type = CommMapperProv.class, method = "fkey")  // 获得表外键
	@Options(useCache = false, flushCache = true, timeout = 10000)  
	public List<String> findTableFkey(@Param("fkey") String sql);
}
