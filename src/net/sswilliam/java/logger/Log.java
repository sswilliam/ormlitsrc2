package net.sswilliam.java.logger;

import net.sswilliam.java.utils.CallStackUtils;

import org.apache.logging.log4j.LogManager;

public class Log {

	private Log() {

	}

	private static boolean logEnable = true;

	public static void Info(String message, Class clazz) {
		if (logEnable) {
			LogManager.getLogger(getLoggerName(clazz)).info(message);
		}
	}
	
	public static void Debug(String message, Class clazz){
		if(logEnable){
			LogManager.getLogger(getLoggerName(clazz)).debug(message);
		}
	}
	public static void Debug(String message, Object instance){
		Log.Debug(message, instance == null ? null : instance.getClass());
	}

	public static void Info(String message, Object instance) {
		Log.Info(message, instance == null ? null : instance.getClass());
	}

	public static void Warn(String message, Class clazz) {
		if (logEnable) {
			LogManager.getLogger(getLoggerName(clazz)).warn(message);
		}
	}

	public static void Warn(String message, Object instance) {
		Log.Warn(message, instance == null ? null : instance.getClass());
	}

	public static void Error(String message, Class clazz) {
		if (logEnable) {
			LogManager.getLogger(getLoggerName(clazz)).error(message);
		}
	}
	public static void Error(String message, Object instance) {
		Log.Error(message, instance == null ? null : instance.getClass());
	}

	public static void Excption(Exception e) {
		Log.Error(CallStackUtils.getCallStack(e), e.getClass());
	}
	
	private static String getLoggerName(Class clazz){
		if(clazz == null){
			return "net.sswilliam.NULL";
		}
		return clazz.getName();
	}
}
