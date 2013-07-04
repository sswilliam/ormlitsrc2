package net.sswilliam.java.utils;

import java.io.UnsupportedEncodingException;

public class StringByteUtils {

	public static byte[] str2byte(String str) throws UnsupportedEncodingException{
		return str.getBytes("UTF-8");
	}
	public static String byte2str(byte[] bytes) throws UnsupportedEncodingException{
		return new String(bytes,"UTF-8");
	}
	
}
