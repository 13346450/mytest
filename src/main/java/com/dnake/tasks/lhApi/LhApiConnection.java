package com.dnake.tasks.lhApi;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dnake.utils.MD5Util;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class LhApiConnection {
	Connection connection;
	Map<String, String> dataMap;
	public LhApiConnection(String url) {
		connection = Jsoup.connect(url);
		connection.method(Method.GET);
		connection.data(MD5Util.getLHEncrypt());
		connection.ignoreContentType(true);
		connection.timeout(10000);
		dataMap = new HashMap<String, String>();
	}
	public LhApiConnection data(String key,Object value){
		if(value!=null){
			connection.data(key, value.toString());
			dataMap.put(key, value.toString());
		}
		return this;
	}
	public LhApiConnection data(Object ...ps){
		for(int i = 0;i<ps.length/2;i+=1){
			if(ps[i*2+1]!=null){
				connection.data(ps[i*2].toString(), ps[i*2+1].toString());
				dataMap.put(ps[i*2].toString(), ps[i*2+1].toString());
			}
		}
		return this;
	}
	public String response(){
		try {
			Response response = connection.execute();
			return new String(Base64.decode(response.body()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public <T> LhListResult<T> getListResult(Class<T> classType) {
		String result = response();
		System.out.println(connection.request().url());
		@SuppressWarnings("unchecked")
		LhListResult<T> listResult = JSON.parseObject(result,LhListResult.class);
		listResult.setData(JSONArray.parseArray(listResult.getData().toString(),classType));
		return listResult;
	}
	public void doPost() {
		connection.method(Method.POST);
	}
	public LhApiConnection data(Object object){
		JSONObject json = (JSONObject) JSONObject.toJSON(object);
		for(String key:json.keySet()){
			data(key, json.getString(key));
		}
		return this;
	}
	public <T> void  doPage(Class<T> classType,LhListItemHandler<T> handler){
		int pageIndex = 0;
		do {
			LhApiConnection connection = new LhApiConnection(this.connection.request().url().toString());
			connection.connection.data(dataMap);
			connection.data("pageIndex",pageIndex);
			LhListResult<T> result = connection.getListResult(classType);
			for (T item : result.getData()) {
				handler.execute(result, item);
			}
			if(result.getData().size()!=10){
				return;
			}
			pageIndex++;

		} while (true);
	}
}
