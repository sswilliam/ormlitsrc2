package net.sswilliam.java.ormlite;

import java.util.Date;
import java.util.HashMap;

public class DBTypeMapping {

	private static HashMap<Class, String> variableTableMapping;
	private static HashMap<String, Class> tableVariableMapping;
	static{
		variableTableMapping = new HashMap<Class, String>();
		variableTableMapping.put(int.class, "INTEGER");
		variableTableMapping.put(double.class, "REAL");
		variableTableMapping.put(String.class, "TEXT");
		variableTableMapping.put(Date.class, "TIMESTAMP");
		
		tableVariableMapping = new HashMap<String, Class>();
		tableVariableMapping.put("INTEGER", int.class);
		tableVariableMapping.put("REAL", double.class);
		tableVariableMapping.put("TEXT", String.class);
		tableVariableMapping.put("TIMESTAMP", Date.class);
	}
	
	public static Class getJavaType(String type){
		return tableVariableMapping.get(type);
	}
	public static String getDBType(Class clazz){
		return variableTableMapping.get(clazz);
	}
}
