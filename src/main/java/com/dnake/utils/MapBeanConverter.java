package com.dnake.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapBeanConverter
 * @Description:Map与Bean（对应domain对象）互相转换类
 * @author xql
 * @date 2013-11-29 下午15:04:22
 * 
 */
public class MapBeanConverter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 将一个 JavaBean 对象（对应domain对象）转化为一个 Map<String, Object>
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象，如果出现异常则返回null
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> convertBean(Object bean) {
		try {
			Class type = bean.getClass();
			Map returnMap = new HashMap();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);

			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
			return returnMap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将一个 Map<String, Object> 对象转化为一个 JavaBean （对应domain对象）
	 * 
	 * @param @param javabean（对应domain对象）
	 * @param @param data即 Map<String, Object>
	 * @return Object 返回 JavaBean （对应domain对象），如果出现异常则返回null
	 * @throws
	 */
	public static Object toJavaBean(Object javabean, Map data) {

		Method[] methods = javabean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					method.invoke(javabean, new Object[] { data.get(field) });
				}
			} catch (Exception e) {
				return null;
			}
		}
		return javabean;

	}

}
