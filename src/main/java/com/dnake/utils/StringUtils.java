/**
 * 	文件名:   StringMessage.java
 *
 * 	Copyright (c) 2006 百联优力科技有限公司
 *
 * 	创建人:	吴克源
 *
 *	日 期:	2006-9-12
 * 
 *	修改人:	吴克源
 *	
 * 	日 期:	2006-9-13
 *	
 *	描 述:	字符串，消息处理工具类
 *
 *	版 本:	1.0
 */
package com.dnake.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
/**
 * 	类说明：字符串，消息处理工具类
 * 
 */

public class StringUtils {
	final static String[] chineseNumber = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	final static String[] chineseIntBit = {"元","拾","佰","仟"};
	final static String[] chineseLongBit = {"万","亿"};
	final static String[] chineseDecBit = {"角","分"};
	final static String   chineseZero = "整";
	
	/**
	 * 不允许实例化
	 *
	 */
	private StringUtils(){
		
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return 空返回 true,非空返回false
	 */
	public static boolean isNull(String str){
		if(str == null)
			return true;
		
		if(str.trim().length() == 0)
			return true;
		
		if(str.trim().equalsIgnoreCase("null")){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 转会空值为""
	 * @param str
	 * @return 空返回 "",非空返回本身
	 */
	public static String null2Blank(Object str){
		return ( str == null )?"":str.toString();
	}
	
	
	/**
	 * 转会空值为""
	 * @param str
	 * @return 空返回 "",非空返回本身
	 */
	public static String null2Zero(Object str){
		return ( str == null )?"0":str.toString();
	}
	
	/**
	 * 设置字符串为空
	 * @param strText
	 * @return
	 */
	public static String setNull(String strText){
		strText = "null";
		return strText;
	}
	
	/**
	 * 格式化字符串，数量字段专用，返回##0.0000.
	 * @param sQty
	 * @return
	 */
	public static String formatQty(String sQty){
		if(sQty == null || sQty.equals(""))
			sQty = "0";
		
		Double dQty = new Double(sQty);
		
		DecimalFormat df = new DecimalFormat("##0.000");
		sQty=df.format(dQty);
		return sQty;
	}
	
	
	/**
	 * 格式化字符串，数量字段专用，当f=null时默认返回###,##0.0000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param sQty
	 * @param f
	 * @return
	 */
	public static String formatQty(String sQty,String f){
		if(sQty == null || sQty.equals(""))
			sQty = "0";
		
		Double dQty = new Double(sQty);
		
		DecimalFormat df = new DecimalFormat("##0.0000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		sQty=df.format(dQty);
		return sQty;
	}
	
	/**
	 * 格式化字符串，数量字段专用，返回######.0000，并自动去除小数点后的0
	 * @param dQty
	 * @return
	 */
	public static String formatQty(double dQty){
		DecimalFormat df = new DecimalFormat("##0.0000");
		
		return df.format(dQty);
	}
//	两位小数
	public static String formatQty2(double dQty){
		DecimalFormat df = new DecimalFormat("##0.00");
		
		return df.format(dQty);
	}
	//三位小数
	public static String formatQty3(double dQty){
		DecimalFormat df = new DecimalFormat("##0.000");
		
		return df.format(dQty);
	}
	/**
	 * 格式化字符串，数量字段专用，当f=null时默认返回###,###.0000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param dQty
	 * @param f
	 * @return
	 */
	public static String formatQty(double dQty,String f){
		DecimalFormat df = new DecimalFormat("##0.0000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		String sQty=null;
		sQty=df.format(dQty);
		return sQty;
	}
	
	/**
	 * 格式化字符串，单价字段专用，返回######.000000，并自动去除小数点后的0
	 * @param sQty
	 * @return
	 */
	public static String formatPrice(String sPrice){
		if(sPrice == null || sPrice.equals(""))
			sPrice = "0";
		
		Double dPrice = new Double(sPrice);
		DecimalFormat df = new DecimalFormat("##0.000");
		return df.format(dPrice);
	}
	
	/**
	 * 格式化字符串，单价字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param sPrice
	 * @param f
	 * @return
	 */
	public static String formatPrice(String sPrice,String f){
		if(sPrice == null || sPrice.equals(""))
			sPrice = "0";
		Double dPrice = new Double(sPrice);
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		sPrice=df.format(dPrice);
		return  sPrice;
	}
	
	/**
	 * 格式化字符串，单价字段专用，返回######.000000，并自动去除小数点后的0
	 * @param dPrice
	 * @return
	 */
	public static String formatPrice(double dPrice){
		DecimalFormat df = new DecimalFormat("##0.000000");
		return df.format(dPrice);
	}
	//两位小数
	public static String formatPrice2(double dPrice){
		DecimalFormat df = new DecimalFormat("##0.000");
		return df.format(dPrice);
	}
	
	/**
	 * 格式化字符串，单价字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param dPrice
	 * @param f
	 * @return
	 */
	public static String formatPrice(double dPrice,String f){
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		String sPrice=df.format(dPrice);
		return sPrice;
	}
	
	/**
	 * 格式化字符串，金额字段专用，返回######.000000，并自动去除小数点后的0
	 * @param sQty
	 * @return
	 */
	public static String formatMny(String sMny){
		if(sMny == null || sMny.equals(""))
			sMny = "0";
		
		Double dMny = new Double(sMny);
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(dMny);
	}
	
	/**
	 * 格式化字符串，金额字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param sMny
	 * @param f
	 * @return
	 */
	public static String formatMny(String sMny,String f){
		if(sMny == null || sMny.equals(""))
			sMny = "0";
		
		Double dMny = new Double(sMny);
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		sMny=df.format(dMny);
		return sMny;
	}
	
	/**
	 * 格式化字符串，金额字段专用，返回######.000000，并自动去除小数点后的0
	 * @param dMny
	 * @return
	 */
	public static String formatMny(double dMny){
		DecimalFormat df = new DecimalFormat("##0.000000");
		return df.format(dMny);
	}
	//两位小数
	public static String formatMny2(double dMny){
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(dMny);
	}
	
	/**
	 * 格式化字符串，金额字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param dMny
	 * @param f
	 * @return
	 */
	public static String formatMny(double dMny,String f){
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		String sMny=null;
		sMny=df.format(dMny);
		return sMny;
	}
	

	/**
	 *摘要：数字格式化字符串
	 *@说明：如果为0，显示为空
	 *@创建：作者:lj		创建时间：2007-12-13
	 *@param dMny	格式化数字
	 *@param f		格式字符串
	 *@param isShowZero		true-显示0值，false-不显示0值
	 *@return 
	 *@修改历史：
	 *		[序号](lj	2007-12-13)<修改说明>
	 */
	public static String formatMum(double dMny,String f,boolean isShowZero){
		String sMny=null;
		
		if(isShowZero==false && dMny==0){
			sMny="";
		}else{
			DecimalFormat df ;
			if(f == null){
				df = new DecimalFormat("##0.000000");
			}else{
				df = new DecimalFormat(f);
			}
				
			sMny=df.format(dMny);
		}

		return sMny;
	}
	
	/**
	 * 格式化税率
	 * @param sRate
	 * @param nType 当nType==1时，sRate输入的值应该为17%;当nType!=1时，sRate输入的值应该为0.17;
	 * @return nType 当nType==1时，返回0.17;当nType!=1时，返回17%
	 */
	public static String formatRate(String sRate,int nType){
		
		if(nType == 1){
			Double dRate = Double.valueOf(sRate.substring(0,sRate.length() - 1));
			
			return new Double(dRate / 100).toString();
		}
		
		double dRate = new Double(sRate).doubleValue()*100;
		
		return formatMny(dRate,"##0.00")+"%";
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param sNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(String sNum){
		String sNumber = formatMny(sNum,"#####0.00");
		
		//查询小数点位置
		int nDecPos = 0;
		for(int i=0; i<sNumber.length(); i++){
			if(sNumber.charAt(i) == '.'){
				nDecPos = i;
				break;
			}
		}
		
		//整数位
		StringBuffer sbNumber = new StringBuffer();
		
		//小数位
		StringBuffer sbDecimal = new StringBuffer();
		
		//最终转换结果
		StringBuffer sbChinese = new StringBuffer();
		
		if(nDecPos > 0){
			sbNumber.append(sNumber.substring(0,nDecPos)).reverse();
			sbDecimal.append(sNumber.substring(nDecPos+1));
		}
		else{
			sbNumber.append(sNumber).reverse();
		}
		
		int nLen = sbNumber.length();
		for(int i=0; i<nLen; i++){
			String strNum = String.valueOf(sbNumber.charAt(i));
			int nNum = Integer.parseInt(strNum);
			int nPos = i % 4;
			int nZero = i / 4;
			
			if(nZero > 0){
				if(nPos > 0){
					if(nNum > 0)
						sbChinese.append(chineseIntBit[nPos]);
					
					sbChinese.append(chineseNumber[nNum]);
				}
				else{
					if(nNum > 0)
						sbChinese.append(chineseLongBit[nZero-1]);
					sbChinese.append(chineseNumber[nNum]);
				}
			}
			else{
				if(nNum > 0)
					sbChinese.append(chineseIntBit[nPos]);
				
				if(i <= (nLen - 1) && nNum != 0)
					sbChinese.append(chineseNumber[nNum]);
			}
		}
		sbChinese.reverse();
		
		if(nDecPos > 0){
			for(int i=0;i<sbDecimal.length(); i++){
				String strNum = String.valueOf(sbDecimal.charAt(i));
				int nNum = Integer.parseInt(strNum);
				int nPos = i % 4;

				sbChinese.append(chineseNumber[nNum]);
				if(nNum > 0)
					sbChinese.append(chineseDecBit[nPos]);
			}
		}
		else{
			sbChinese.append(chineseZero);
		}
		
		return sbChinese;
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param dNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(double dNum){
		String sNum = String.valueOf(dNum);
		
		return convertNumberToChinese(sNum);
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param nNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(long nNum){
		String sNum = String.valueOf(nNum);
		
		return convertNumberToChinese(sNum);
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param nNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(int nNum){
		String sNum = String.valueOf(nNum);
		
		return convertNumberToChinese(sNum);
	}
	
	/**
	 * 处理从页面获得的字符参数乱码问题<br>
	 * ISO8859-1 转 UTF-8
	 * @title      convertCharacter
	 * @author  ts    
	 * @date      2014年3月14日 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public  static String convertCharacter(String value,String charsetName)
	{
		String ret = "";
		if (value != null) {
			try {
				ret = new String(value.getBytes("ISO8859-1"),charsetName);
			} catch (Exception e) {
				System.err.println("exception:" + e.getMessage());
				System.err.println("The String is:" + value);
			}
		}
		return ret;
	}
	public static String formatQty1(double dQty){
		DecimalFormat df = new DecimalFormat("##0.00");
		
		return df.format(dQty);
	}
	public static long getArrayMaxValue(long[] lCompareArray){
		if(lCompareArray.length<=0){
			return 0;
		}else{
			long lRtn=0;
			for(int i=0;i<lCompareArray.length;i++){
				if(lCompareArray[i]>lRtn){
					lRtn=lCompareArray[i];
				}
			}
			return lRtn;
		}	
	}
	
	/**
	 *摘要：
	 *@说明：
	 *@创建：作者:whj		创建时间：2007-12-19
	 *@param sParam
	 *@return 
	 *@修改历史：
	 *		[序号](whj	2007-12-19)<修改说明>
	 */
	public static String formatResult(String sParam){
		if(sParam == null || sParam.equals("0")){
			return "";
		}else{
			String sResult = "";
			DecimalFormat df = new DecimalFormat("###,###.00");
			sResult = df.format(Double.valueOf(sParam));
			return sResult;
		}
	}
	/**
	 *摘要：获得指定字符串的长度
	 *@说明：
	 *@创建：作者:YQH 	创建时间：2008-07-19
	 *@param sStr   指定字符串
	 *@return 
	 *@修改历史：
	 *		[序号](whj	2007-12-19)<修改说明>
	 */
	public static int valieFiledLength(String sStr){
		String regex="^\\w+$";
    	String regex1 = "&@!#$%^)(-+=_\\'?><,~`!/ {}";
    	int lengthg=0;
    	int j = 0;
    	String srr = "";
    	
    	for(int i =0;i<sStr.length();i++){
    			if(i<sStr.length()) j=i+1;
    			else j=i;
    			srr=sStr.substring(i,j);
       			if(!srr.matches(regex)&&regex1.indexOf(srr)==-1){
    				lengthg=lengthg+2;
    			}else{
    				lengthg= lengthg+1;
    			}
    		
    	
    	
    	}
    	return lengthg;
    }
	public static String getFiledValue(String sStr,int length){ 
		String regex="^\\w+$";
    	String regex1 = "&@!#$%^)(-+=_\\'?><,~`!/ {}";
    	int lengthg=0;
    	int j = 0;
    	String srr = "";
    	String  sRtn = "";
    	for(int i =0;i<sStr.length();i++){
    		if(lengthg>=length) break;
    			if(i<sStr.length()) j=i+1;
    			else j=i;
    			srr=sStr.substring(i,j);
    			if(!srr.matches(regex)&&regex1.indexOf(srr)==-1){
    				lengthg=lengthg+2;
    				
    			}else{
    				lengthg= lengthg+1;
    				
    			}
    			sRtn=sRtn+srr;
    	
    	
    	}
    	return sRtn;
    }
	   public static void main(String[] argc){
		   System.out.println( encrypt("admin123"));
	    	//System.out.println(i);
	    	//System.out.println(getFiledValue("hello12</\\/aa'我的你A的拉拉",21));
	    }

	/**
	 *摘要：密码加密
	 *@说明：
	 *@创建：作者:Administrator		创建时间：Apr 9, 2009
	 *@param password
	 *@return 
	 *@修改历史：
	 *		[序号](Administrator	Apr 9, 2009)<修改说明>
	 */
	public static String encrypt(String password) {
	    String result = null;
	    String password1 = "";
	    if (password != null) {
	      try {
	        MessageDigest ca = MessageDigest.getInstance("SHA");
	        result = "";
	        char pass[] = password.toCharArray();
	        for(int i=0; i<pass.length; i++){
	        	password1 = (String)password1+pass[i]+"&^./&";
	        }
	        byte mess[] = password1.getBytes();
	        ca.reset();
	        byte[] hash = ca.digest(mess);
	        result = byte2hex(hash);
	      }
	      catch (Exception err) {
	        err.printStackTrace();
	      }
	    }
	    return result;
	  }

	private static String byte2hex(byte[] b) {
	    String hs = "";
	    String stmp = "";
	
	    for (int n = 0; n < b.length; n++) {
	      stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
	      if (stmp.length() == 1) {
	        hs = hs + "0" + stmp;
	      }
	      else {
	        hs = hs + stmp;
	      }
	    }
	    return hs.toUpperCase();
	  }
	
	/**
	 * 将用逗号隔开的字符串，转换成List<String>
	* @Title: getList 
	* @author tw  2013-11-22
	* @Description: 
	* @param @param value
	* @param @return    
	* @return List<String>    
	* @throws
	 */
	public static List<String> getList(String value) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNull(value))
			return list;
		String[] vl = value.split(",");
		for (String str : vl) {
			if (!StringUtils.isNull(str))
				list.add(str);
		}
		return list;
	}
	/**
	 * 替换指定的字符串
	* @Title: removeNoUseString 
	* @author xzm  2013-12-12
	* @Description: 
	* @param url
	* @param oldChar
	* @param newChar
	* @return    
	* @return String    
	* @throws
	 */
	public static String removeNoUseString(String url,String oldChar,String newChar)
	{
		if(url.contains(oldChar))
		{
			url=url.replace(oldChar, newChar);
			return removeNoUseString(url,oldChar,newChar);
		}
		return url;
	}
	/*public static Object getTrueField(Object field)
    {
		if(null == field){
			return null;
		}
		String trueField;
		String strField = field.toString();
		if (!strField.contains("_")) {
			int c = strField.length();
			trueField = String.valueOf(strField.charAt(0)).toUpperCase();
			for (int i = 1; i < c; i++) {
				Character cc = strField.charAt(i);
				if (Character.isUpperCase(cc)) {
					trueField = trueField + "_" + strField.charAt(i);
				} else {
					trueField += strField.charAt(i);
				}
			}
			return trueField;
		}
		return field;
	}*/
	/**
	 * 传进一个dataTime格式，返回Data_Time
	* @Title: getTrueField 
	* @author xzm  2013-12-17
	* @Description: 
	* @param field
	* @param order
	* @return    
	* @return String    
	* @throws
	 */
	public static String getTrueField(Object field,Object order)
	{
		if(null == field || null == order){
			return null;
		}
		String trueField;
		String strField = field.toString();
		String strOrder = order.toString();
		String[] strOrderArray = null;
		if (strOrder.contains(",")) {
			strOrderArray = strOrder.split(",");
		}
		strField = strField + ",";
		int c = strField.length();
		int j = -1;
		trueField = String.valueOf(strField.charAt(0)).toUpperCase();
		for (int i = 1; i < c; i++) {
			Character cc = strField.charAt(i);
			if (String.valueOf(cc).equals(",")) {
				if (strOrderArray != null && strOrderArray.length > 0) {
					j = j + 1;
					trueField += " " + strOrderArray[j];
				} else {
					trueField += " " + strOrder;
				}
			}
			if (Character.isUpperCase(cc)) {
				trueField = trueField + "_" + strField.charAt(i);
			} else {
				trueField += strField.charAt(i);
			}
		}
		return trueField.substring(0, trueField.length() - 1);
	}
	
	/**
	 * 去掉double小数点后面全部的零（小数点后面有非0数字，则不处理）
	* @Title: removePointZero 
	* @author xql  2014-1-9
	* @Description: 
	* @param @param para
	* @param @return    
	* @return String    
	* @throws
	 */
	public static String removePointZero(double para) {
		String s= Double.toString(para);
		if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;
	}
	/**
	 * 对页面输入的字符串进行转义，将换行空格转换成<br/> &nbsp; 
	 * @param content
	 * @return
	 */
	public static String replaceNewline(String content)
	{
		if(content.indexOf("\n") != -1)
		{
			content = content.replaceAll("<br/>", "\n");
		}
		else if(content.indexOf(" ") != -1)
		{
			content = content.replaceAll("&nbsp;&nbsp;", " ");
		}
		return content;
	}	
}
