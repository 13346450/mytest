
package com.dnake.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;

import com.dnake.utils.MessageDigestCodingUtil;


/**
 *说明：对.properties文件进行解析操作
 *@创建：作者:詹保山(zbs),softzbs@126.com  创建时间：Jun 14, 2009
 *@修改历史：
 *		[序号](zbs	Jun 14, 2009)<修改说明>
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static final Log log=LogFactory.getLog(PropertyConfigurer.class);

	/**
	 * 需要进行解密的属性名称 decodePropertyNames
	 * <list>
	 *   <value>zbs</value>
	 *   <value>zbs2</value>
	 * </list>
	 */
	private List<String> decodePropertyNames;
	
	private Properties springPropertiesDatas=null;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void convertProperties(Properties props) {
		Enumeration propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String propertyName = (String) propertyNames.nextElement();
			String propertyValue = props.getProperty(propertyName);
			String convertedValue=null;
			if(this.decodePropertyNames!=null&&decodePropertyNames.size()>0)
			{
			  if(this.decodePropertyNames.contains(propertyName))
				{
				  convertedValue=MessageDigestCodingUtil.decode(propertyValue);
				}else
				  {
					convertedValue = convertPropertyValue(propertyValue);
				  }
			}else
				 convertedValue = convertPropertyValue(propertyValue);
			if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
				props.setProperty(propertyName, convertedValue);
			}
		}
	}

	public List<String> getDecodePropertyNames() {
		return decodePropertyNames;
	}

	public void setDecodePropertyNames(List<String> decodePropertyNames) {
		this.decodePropertyNames = decodePropertyNames;
	}
	
	public String getPropertyValue(String key)
	{
		if(springPropertiesDatas==null)
		{
			try {
				this.springPropertiesDatas=super.mergeProperties();
			} catch (IOException e) {
				log.equals(e);
			}
		}
		String value=this.springPropertiesDatas.get(key).toString();
		if(this.decodePropertyNames.contains(key))
		{
			value=MessageDigestCodingUtil.decode(value);
		}
		return value;
		
	}

}