package com.dnake.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanUtil {
	/**
	 * 获取对象属性，返回一个字符串数组
	 * 
	 * @param o
	 *            对象
	 * @return String[] 字符串数组
	 */
	private static String[] getFiledName(Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName();
			}
			return fieldNames;
		} catch (SecurityException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return null;
	}

	/**
	 * 使用反射根据属性名称获取属性值
	 * 
	 * @param fieldName
	 *            属性名称
	 * @param o
	 *            操作对象
	 * @return Object 属性值
	 */

	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}

	/**
	 * 获取不为空的java属性转成字符串
	 * 
	 * @param object
	 * @return
	 * @author cqg 2015年5月6日
	 */
	public static String getJavaBeanNotNullProperty(Object object) {
		String[] filedArr = getFiledName(object);
		StringBuilder sb = new StringBuilder();
		String className=object.getClass().getName();
		if(className.indexOf(".")!=-1){
			className=className.substring(className.lastIndexOf(".")+1);
		}
		sb.append(className + " [");
		for (String string : filedArr) {
			Object o = getFieldValueByName(string, object);
			if (o != null) {
				sb.append(" " + string + "=" + o + ",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
