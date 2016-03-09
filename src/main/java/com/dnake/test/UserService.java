package com.dnake.test;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserService {
	/**
	 * <pre>
	 * select * from User1
	 * </pre>
	 * <pre>
	 * select * from User </pr
	 *  </pre>
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		String path = "E:/suyh/eclipse/workspace/SmartCommunityWeb/src/main/java/com/dnake/test/UserService.java";
		String content = readFileContent(path);
		//System.out.println(content);
		String regex = "<pre>[^<]*[^p]*[^r]*[^e]*[^>]*</pre>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			System.out.println(matcher.group(1));
		}
	}
	public static String readFileContent(String path) throws IOException {
		FileReader  reader = new FileReader(path);
		char[] bs = new char[1024];
		StringBuffer buffer = new StringBuffer();
		while (reader.read(bs)!=-1) {
			buffer.append(bs);
		}
		reader.close();
		return buffer.toString();
		
	}
}
