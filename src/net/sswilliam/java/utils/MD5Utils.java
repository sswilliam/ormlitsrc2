package net.sswilliam.java.utils;

import java.security.MessageDigest;

public class MD5Utils {

	public static String encrypt(String source){
		try {
			byte[] sourceByte = source.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(sourceByte);
			byte[] resultByte = md5.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i<resultByte.length;i++){
				int content = resultByte[i];
				if(content<0)
					content+= 256;
				if(content < 16)
					sb.append('0');
				sb.append(Integer.toHexString(content));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("generate md5 str error");
		}
	}
}
