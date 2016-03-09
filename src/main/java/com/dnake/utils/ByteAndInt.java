package com.dnake.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ByteAndInt {
	/**
	 * 
	 * @param value 需要转换的数值
	 * @param dir 数值在数组中的方向
	 * 		0 表示数值的低位在数组下标的低位(小端表示).
	 * 		1表示数值的低位在数组下标的高位(大端表示).
	 * 转换算法：value -> byte: 先对value进行无符号>>右移位再与0xFF相与 如：byte b = (byte) ((value >>> offset) & 0xff);
	 */
	public static byte[] short2ByteArray(short value,int dir) {
		byte[] b = new byte[2];
		int offset = 0;
		for (int i = 0; i < b.length; i++) {
			if(dir==1){
				offset = (b.length - 1 - i) * 8;}//大端表示时的移位数
			else if(dir==0){
				offset = i * 8;//小端表示时的移位数
			}
			b[i] = (byte) ((value >> offset) & 0xff);
		}
		return b;
	}
	/**
	 * 
	 * @param b 需要转换的数值数组
	 * @param dir 数值在数组中的方向
	 * 		0 表示数值的低位在数组下标的低位(小端表示).
	 * 		1表示数值的低位在数组下标的高位(大端表示).
	 * 转换算法：byte -> value: 先把byte与0xFF相与,再把byte进行左移位,如：value = ((byte & 0xFF)<<offset);
	 */
	public static final int byteArray2Short(byte[] b,int dir) {
		int value = 0;
		if(dir==1){
			for(int i=0;i<b.length;i++){
				int offset = (b.length-1-i)*8;//需要移位的位数
				if(i==0){//采用大端表示时数组的(低位)只需要做移位不需要和0xff相与
					value += (b[i]<<offset);
				}
				else {//与0xff相与后再移位
					value+=((b[i] & 0xFF)<<offset);
				}
			}
		}else if(dir==0){
			for(int i=0;i<b.length;i++){
				int offset = i*8;//需要移位的位数
				if(i==b.length-1){//采用小端表示时数组的(高位)只需要移位不需要与0xff相与
					value += (b[i]<<offset);
				}else{//与0xff相与后再移位
					value += ((b[i] & 0xFF)<<offset);
				}
			}
		}
		return value;
	}
	/**
	 * 
	 * @param value 需要转换的数值
	 * @param dir 数值在数组中的方向
	 * <br/>
	 * 		0 表示数值的低位在数组下标的低位(小端表示).
	 * <br/>
	 * 		1表示数值的低位在数组下标的高位(大端表示).
	 * 转换算法：value -> byte: 先对value进行无符号>>右移位，再与0xFF相与， 如：byte b = (byte) ((value >>> offset) & 0xff);
	 */
	public static byte[] int2ByteArray(int value,int dir) {
		byte[] b = new byte[4];
		int offset = 0;
		for (int i = 0; i < b.length; i++) {
			if(dir==1){
				offset = (b.length - 1 - i) * 8;
			}else if(dir==0){
				offset = i * 8;
			}
			b[i] = (byte) ((value >> offset) & 0xFF);
		}
		return b;
	}
	/**
	 * 
	 * @param b 需要转换的数值
	 * @param dir 数值在数组中的方向
	 * 		0 表示数值的低位在数组下标的低位(小端表示).
	 * 		1表示数值的低位在数组下标的高位(大端表示).
	 * @return int 返回转换后的数据
	 * 转换算法：byte -> value: 先把byte与0xFF相与,再把byte进行左移位,如：value = ((byte & 0xFF)<<offset);
	 */
	public static final int byteArray2Int(byte[] b,int dir) {
		int value = 0;
		if(dir==1){
			for(int i=0;i<b.length;i++){
				int offset = (b.length-1-i)*8;//需要移位的位数
				if(i==0){//采用大端表示时数组的(低位)只需要做移位不需要和0xff相与
					value += (b[i]<<offset);
				}
				else {//与0xff相与后再移位
					value+=((b[i] & 0xFF)<<offset);
				}
			}
		}else if(dir==0){
			for(int i=0;i<b.length;i++){
				int offset = i*8;//需要移位的位数
				if(i==b.length-1){//采用小端表示时数组的(高位)只需要移位不需要与0xff相与
					value += (b[i]<<offset);
				}else{//与0xff相与后再移位
					value += ((b[i] & 0xFF)<<offset);
				}
			}
		}
		return value;
	}
	
	public static byte int2Byte(int value) {
		byte b = (byte) (value & 0xFF);
		return b;
	}
	
	public static final int byte2Int(byte b) {
		return (b & 0xFF);
	}
	
	public static int ipToInt(String ip){
	      int intIp = 0; 
	      try { 
	         byte[] ipBytes = InetAddress.getByName(ip).getAddress();	         
	         intIp = ipBytes[3] & 0xFF; 
	         intIp |= ((ipBytes[2] << 8) & 0xFF00); 
	         intIp |= ((ipBytes[1] << 16) & 0xFF0000); 
	         intIp |= ((ipBytes[0] << 24) & 0xFF000000); 
	      } catch (UnknownHostException e) { 
	         e.printStackTrace(); 
	      }
	      return intIp; 
	 }
	
	/**
	 * ip地址转成byteArray
	 * @title      ip2Bytes
	 * @author  ts    
	 * @date      2014年6月26日 
	 * @param ip
	 * @param dir
	 * <br/>
	 * 		0 表示数值的低位在数组下标的低位(小端表示).
	 * <br/>
	 * 		1表示数值的低位在数组下标的高位(大端表示).
	 * @return
	 */
	public static byte[] ip2Bytes(String ip, int dir){
		return  int2ByteArray(ipToInt(ip), dir);
	}
	
	public static void main(String[] args) {
		int a = byteArray2Int(new byte[]{0x3f,0x00},0);
		int b = byteArray2Short(new byte[]{0x07,0x00},0);
		byte[] bytes = int2ByteArray(1,0);
		byte[] bytes2 = short2ByteArray((short)1, 0);
		byte bx = int2Byte(1);
		int c = a+b;
		System.out.println("ip2Bytes="+ip2Bytes("192.168.98.98",0));
		System.out.println("ip2Bytes="+ip2Bytes("192.168.98.98",1));
	}
}
