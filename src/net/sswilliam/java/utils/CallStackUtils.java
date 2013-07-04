package net.sswilliam.java.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CallStackUtils {

	public static String getCallStack(Exception e){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream outstream = new PrintStream(out);
		e.printStackTrace(outstream);
		try {
			return out.toString("UTF-8");
		} catch (Exception e2) {
			return "";
		}
	}
}
