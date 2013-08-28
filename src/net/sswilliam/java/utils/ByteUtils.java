package net.sswilliam.java.utils;

import java.io.UnsupportedEncodingException;

public class ByteUtils {

	public static byte[] str2byte(String str) throws UnsupportedEncodingException{
		return str.getBytes("UTF-8");
	}
	public static String byte2str(byte[] bytes) throws UnsupportedEncodingException{
		return new String(bytes,"UTF-8");
	}
	public static byte[] int2byte(int integer){
		int byteNum = (40-Integer.numberOfLeadingZeros(integer<0?~integer:integer))/8;
		byte[] ret = new byte[4];
		for(int i = 0;i<byteNum;i++){
			ret[3-i] = (byte)(integer >>> (i*8));
		}
		return ret;
	}
	public static int byte2int(byte[] bytes){
		int value = 0;
		for(int i = 0;i<4;i++){
			int shift = (3-i)*8;
			value += (bytes[i] & 0x000000FF)<<shift;
		}
		return value;
	}
	
}
