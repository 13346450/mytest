package com.dnake.common.error;

public class TempStringUtils {
	public static String temp(String temp,Object...ps){
		for(int i = 0;i<ps.length;i++){
			Object p = ps[i];
			temp = temp.replace("{x}", p+"");
		}
		return temp;
	}
}
