/**
 * 	文件名:   DoubleUtil.java
 *
 * 	Copyright (c) 2006 百联优力科技有限公司
 *
 * 	创建人:	吴克源
 *
 *	日 期:	2006-10-9
 * 
 *	修改人:
 *	
 * 	日 期:
 *	
 *	描 述:	浮点类型精确运算，截取，四舍五入工具(DoubleUtil)
 *
 *	版 本:	1.0
 */
package com.dnake.utils;

import java.math.BigDecimal;

/**
 *说明：浮点类型精确运算，截取，四舍五入工具类
 *@创建：作者:Administrator		创建时间：Apr 14, 2009
 *@修改历史：
 *		[序号](Administrator	Apr 14, 2009)<修改说明>
 */

public class DoubleUtil {

	private static final int DIV_SCALE = 10;
	
	/**
	 * 不允许实例化
	 *
	 */
	private DoubleUtil(){
		
	}
	
	/**
	 * 提供精确的加法运算
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(String v1,String v2){
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(String v1,String v2){
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		if(b1.doubleValue()==0.0||b2.doubleValue()==0.0)
			return 0.0;
		else
		    return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(String v1,String v2){
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时，精确到
	 * 小数点以后10位，以后的数字四舍五入
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商v1 / v2
	 */
	public static double div(double v1,double v2){
		return div(v1,v2,DIV_SCALE);
	}
	
	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时，精确到
	 * 小数点以后10位，以后的数字四舍五入
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(String v1,String v2){
		return div(v1,v2,DIV_SCALE);
	}
	
	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时，精确到
	 * 小数点以后几位，以后的数字四舍五入
	 * @param v1  被除数
	 * @param v2 除数
	 * @param scale 表示需要精确到小数点几位
	 * @return 两个参数的商
	 */
	public static double div(double v1,double v2,int scale){
		if(scale < 0){
			throw new IllegalArgumentException ("scale 的值必须为正整数或零");
		}
		
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时，精确到
	 * 小数点以后几位，以后的数字四舍五入
	 * @param v1  被除数
	 * @param v2 除数
	 * @param scale 表示需要精确到小数点几位
	 * @return 两个参数的商
	 */
	public static double div(String v1,String v2,int scale){
		if(scale < 0){
			throw new IllegalArgumentException ("scale 的值必须为正整数或零");
		}
		
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 提供(相对)精确的四舍五入
	 * @param v 输入数字
	 * @param scale 表示需要精确到小数点几位
	 * @return 四舍五入后的数字
	 */
	public static double round(double v,int scale){
		if(scale < 0){
			throw new IllegalArgumentException ("scale 的值必须为正整数或零");
		}
		
		BigDecimal b1 = new BigDecimal(Double.toString(v));
		BigDecimal b2 = new BigDecimal("1");
		
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 提供(相对)精确的四舍五入
	 * @param v 输入数字
	 * @param scale 表示需要精确到小数点几位
	 * @return 四舍五入后的数字
	 */
	public static double round(String v,int scale){
		if(scale < 0){
			throw new IllegalArgumentException ("scale 的值必须为正整数或零");
		}
		
		BigDecimal b1 = new BigDecimal(v);
		BigDecimal b2 = new BigDecimal("1");
		
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * double类型格式化，当f=null时返回(###,###.00)的格式
	 * @param d 要格式化的数据
	 * @param f 格式字符串
	 * @return 
	 */
	public static String format(double d,String f){
		if(f == null || f.equals(""))
			f = "%(,.2f";

		return String.format(f,d);
	}
}
