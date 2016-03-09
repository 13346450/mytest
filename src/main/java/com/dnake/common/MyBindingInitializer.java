package com.dnake.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.dnake.utils.SessionBean;

public class MyBindingInitializer implements WebBindingInitializer {
	/**
	 * 对前台提交来的日期格式进行处理
	 * 主要是对前台提交来的日期格式，如果即有yyyy-MM-dd，又有yyyy-MM-dd HH:mm:ss时的处理
	 * 主要有两种格式yyyy-MM-dd和yyyy-MM-dd HH:mm:ss
	 * 如果是第一种的需在domain中设置相应日期字段类型为Date
	 * 如果是第二种的需在domain中设置相应日期字段类型为Timestamp
	* @ClassName: initBinder 
	* @Description: 
	* @author xzm
	* @date 2013-12-11
	*
	 */
	public void initBinder(WebDataBinder binder, WebRequest request) {
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //可以设定任意的日期格式
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(java.sql.Timestamp.class,
				new CustomTimestampEditor(datetimeFormat, true));
		
			       
    }
}
