/**
 * 
 */
package com.dnake.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/** 
 * @ClassName: JsonDateYearMonthSerializer 
 * @Description: 
 * @author wuhuimin
 * @date 2013-12-10 上午9:52:34  
 */
public class JsonDateYearMonthSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}

}
