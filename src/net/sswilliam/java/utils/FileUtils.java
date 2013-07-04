package net.sswilliam.java.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

	public static void deleteFile(File root){
		
		if(root.exists()){
			if(root.isDirectory()){
				File[] files = root.listFiles();
				for(int i = 0;i<files.length;i++){
					deleteFile(files[i]);
				}
				root.delete();
			}else{
				root.delete();
			}
		}
	}
	public static boolean existing(File file){
		return file.exists();
	}
	public static boolean existing(String path){
		return new File(path).exists();
	}
	
	public static void copyFile(File from, File to) throws IllegalArgumentException, IOException{
		if(!from.exists()){
			throw new IllegalArgumentException("The source file does not exist");
		}
		if(!from.isFile()){

			throw new IllegalArgumentException("The parameter should be File not directory");
		}
		if(to.exists()){
			throw new IllegalArgumentException("The destination file already exists");
		}
		File parent = to.getParentFile();
		parent.mkdirs();
		to.createNewFile();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(from));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(to));
		byte[] buffer = new byte[1024];
		int len;
		while((len = bis.read(buffer)) != -1){
			bos.write(buffer, 0, len);
		}
		bos.flush();
		bos.close();
		bis.close();
	}
	public static void copyDirectory(File from, File to) throws IllegalArgumentException,IOException{
		if(!from.exists()){
			throw new IllegalArgumentException("The source directory does not exist");
		}
		if(!from.isDirectory()){
			throw new IllegalArgumentException("The parameter should be directory");
		}
		to = new File(to.getAbsolutePath()+File.separator+from.getName());
		if(!to.exists()){
			to.mkdirs();
		}
		doCopyDirectory(from, to);
	}
	private static void doCopyDirectory(File from, File to) throws IllegalArgumentException,IOException{
		if(!to.exists()){
			to.mkdirs();
		}
		File[] children = from.listFiles();
		for(int i = 0;i<children.length;i++){
			File targetFile = children[i];
			if(targetFile.isDirectory()){
				doCopyDirectory(targetFile, new File(to.getAbsolutePath()+File.separator+targetFile.getName()));
			}else{
				copyFile(targetFile, new File(to.getAbsolutePath()+File.separator+targetFile.getName()));
			}
		}
	}
}
