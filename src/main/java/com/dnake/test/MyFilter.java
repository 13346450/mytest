package com.dnake.test;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

public class MyFilter extends SimplePropertyPreFilter  implements ValueFilter{

	public MyFilter(Class<?> clazz, String... properties) {
		super(clazz, properties);
	}

	@Override
	public Object process(Object object, String name, Object value) {
		if(value==null){
			return "hehe";
		}
		return value;
	}
}