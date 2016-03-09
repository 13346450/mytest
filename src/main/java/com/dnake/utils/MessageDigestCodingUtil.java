
package com.dnake.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *说明：进行数值摘要的加密和解密处理
 *@创建：作者:詹保山(zbs),softzbs@126.com  创建时间：Jun 14, 2009
 *@修改历史：
 *		[序号](zbs	Jun 14, 2009)<修改说明>
 */
public class MessageDigestCodingUtil {
	
    /**
     * 
     *摘要：加密操作
     *@说明：先进行64位的加密 再继续16进制的加密
     *@创建：作者:詹保山(zbs),softzbs@126.com	 创建时间：Jun 14, 2009
     *@param encode
     *@return 
     *@修改历史：
     *		[序号](zbs	Jun 14, 2009)<修改说明>
     */
	public static String encode(String encode)
	{
		encode=new String(Base64.encode(encode.getBytes()));
		encode=new String(Hex.encodeHex(encode.getBytes()));
		return encode;
	}
	
	/**
	 * 
	 *摘要：解密操作
	 *@说明：先进行16进制的解密，再进行64位的解密
	 *@创建：作者:詹保山(zbs),softzbs@126.com	 创建时间：Jun 14, 2009
	 *@param decode
	 *@return 
	 *@修改历史：
	 *		[序号](zbs	Jun 14, 2009)<修改说明>
	 */
	public static String decode(String decode)
	{
		try {
			decode=new String(Hex.decodeHex(decode.toCharArray()));
			decode=new String(Base64.decode(decode.toCharArray()));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return decode;
	}
	
	public static void main(String[] args)
	{
		//String str=MessageDigestCodingUtil.encode("jdbc:oracle:thin:@10.188.80.98:1521:dev");
		System.out.println("encrypt="+MessageDigestCodingUtil.encode("Xmhj@1234"));
		System.out.println("encrypt="+MessageDigestCodingUtil.decode("5a4735686132553d"));
		//System.out.println("encrypt="+MessageDigestCodingUtil.decode("4d673d3d595752746157343d"));
	}

}

