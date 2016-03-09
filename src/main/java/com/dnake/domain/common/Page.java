package com.dnake.domain.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> {
	
	private int pageNo = 1;     //当前页码，默认是第一页
    private int pageSize = 30;  //每页显示的记录数，默认是30  
    private int total;    //总记录数
    private int totalPage;      //总页数
    private List<T> rows;    //当前页的数据
    private Map<String, Object> params = new HashMap<String, Object>();//查询参数，放到Map中
    
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		 //在设置总条数的时候计算出总页数
        this.setTotalPage((0==total%pageSize) ? total/pageSize : (total/pageSize + 1));  
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public String toString() {  
       StringBuilder builder = new StringBuilder();  
       builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")  
              .append(pageSize).append(", results=").append(rows).append(  
                     ", totalPage=").append(totalPage).append(  
                     ", totalRecord=").append(total).append("]");  
       return builder.toString();  
	}

}
