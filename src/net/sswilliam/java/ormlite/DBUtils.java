package net.sswilliam.java.ormlite;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;
import net.sswilliam.java.ormlite.annotation.Table;

public class DBUtils {

	//get the table name from the class metadata
	public static String getTableName(Class clazz){
		if(!clazz.isAnnotationPresent(Table.class)){
			throw new ORMLiteException("The table class should be annotated by Table annotation");
		}
		String tableName = ((Table)clazz.getAnnotation(Table.class)).name().trim();
		return tableName;
	}
	//get the table name from a object. 
	public static String getTableName(Object obj){
		return getTableName(obj.getClass());
	}
	
	//get the id generation strtegy
	public static IDGenerateStrategy getIdGenerateStrategy(Class clazz){
		Field idField = getIdField(clazz);
		return ((ID)idField.getAnnotation(ID.class)).strategy();
		
	}
	public static IDGenerateStrategy getIdGenerateStrategy(Object object){
		return getIdGenerateStrategy(object.getClass());
	}
	
	
	//get id column name and type mapping
	public static String[] getIdNameType(Class clazz){
		String idName = null;
		IDGenerateStrategy strategy = null;
		String idType = null;
		Field idField = getIdField(clazz);
		idName= ((ID)idField.getAnnotation(ID.class)).name().trim();
		idType = DBTypeMapping.getDBType(idField.getType());
		strategy = ((ID)idField.getAnnotation(ID.class)).strategy();
		if(idName == null){
			throw new ORMLiteException("No id column is found");
		}
		if(strategy == IDGenerateStrategy.AUTOINCREASE && !idType.equals("INTEGER")){
			throw new ORMLiteException("Auto Increase ID should with INTEGER type");
		}
		if(strategy == IDGenerateStrategy.GUID && !idType.equals("TEXT")){
			throw new ORMLiteException("GUID ID should with TEXT type");
		}
		if(strategy == IDGenerateStrategy.NONE && ((!idType.equals("Text"))  && (!idType.equals("INTEGER")))){
			throw new ORMLiteException("Raw Id column type only supports Text and Integer type");
		}
		return new String[]{idName,idType};
	}
	public static String[] getIdNameType(Object obj){
		return getIdNameType(obj.getClass());
	}
	
	public static Object[] getIdNameField(Class clazz){
		String idName = null;
		Field idField = getIdField(clazz);
		idName= ((ID)idField.getAnnotation(ID.class)).name().trim();
		
		return new Object[]{idName,idField};
	}
	public static Object[] getIdNameField(Object object){
		return getIdNameField(object.getClass());
	}
	
	
	public static Object getValueFromResultSet(ResultSet resultSet, String name, Field field) throws SQLException{
		if(field.getType().equals(int.class)){
			return new Integer(resultSet.getInt(name));
		}
		if(field.getType().equals(String.class)){
			return resultSet.getString(name);
		}
		if(field.getType().equals(double.class)){
			return new Double(resultSet.getDouble(name));
		}
		if(field.getType().equals(Date.class)){
			String dateString = resultSet.getString(name);
			return DBUtils.convertSqlToDate(dateString);
			
		}
		throw new ORMLiteException("Unsupported type:"+field.getType());
	}
	public static ArrayList<Object[]> getColunmNameField(Class clazz){
		ArrayList<Object[]> nameFields = new ArrayList<Object[]>();
		Field[] fields =  clazz.getDeclaredFields();
		for(int i = 0;i<fields.length;i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(Column.class)){
				String columnName = ((Column)field.getAnnotation(Column.class)).name().trim();
				Object[] columnNameField = new Object[]{
					columnName,field	
				};
				nameFields.add(columnNameField);
			}
			
		}
		return nameFields;
	}
	public static ArrayList<Object[]> getColumnNameField(Object object){
		return getColunmNameField(object.getClass());
	}
	
	
	public static ArrayList<String[]> getColumnNameType(Class clazz){
		ArrayList<String[]> nameTypes = new ArrayList<String[]>();
		Field[] fields =  clazz.getDeclaredFields();
		ArrayList<String> columnNames = new ArrayList<String>();
		for(int i = 0;i<fields.length;i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(Column.class)){
				String columnName = ((Column)field.getAnnotation(Column.class)).name().trim();
				validataColumnName(columnNames, columnName);
				columnNames.add(columnName);
				String type = DBTypeMapping.getDBType(field.getType());
				if(type == null){
					throw new ORMLiteException("unsupported type:"+ field.getType().getName());
				}
				String[] nameType = new String[]{
						columnName,type
				};
				nameTypes.add(nameType);
			}
			
		}
		return nameTypes;
	}
	public static ArrayList<String[]> getColumnNameType(Object object){
		return getColumnNameType(object.getClass());
	}
	
	
	
	//get the id column name and the value
	public static String[] getIdNameValue(Object object){
		Field idField = getIdField(object.getClass());
		String idName = ((ID)idField.getAnnotation(ID.class)).name().trim();
		IDGenerateStrategy strategy = ((ID)idField.getAnnotation(ID.class)).strategy();
		String idType = DBTypeMapping.getDBType(idField.getType());
		String idValue = null;
		if(idType.equals("INTEGER")){
			try {
				int intValue = idField.getInt(object);
				if(intValue > 0 ){
					idValue = ""+intValue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ORMLiteException("Get value from object failed: "+e.getMessage());
				// TODO: handle exception
			}
		}else if(idType.equals("TEXT")){
			try {
				Object stringValue = idField.get(object);
				if(stringValue != null){
					idValue = convertValueToSql(stringValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ORMLiteException("Get value from object failed: "+e.getMessage());
				// TODO: handle exception
			}
		}else {
			throw new ORMLiteException("Only INTEGER and TEXT type could be used as id");
		}
		
		return new String[]{idName,idValue};
		
		
	}
	
	public static Object getIdValue(Object object){
		Field idField = getIdField(object.getClass());
		try {
			return idField.get(object);
		} catch (Exception e) {
			throw new ORMLiteException("Get ID value Error:"+e.getMessage());
			// TODO: handle exception
		}
	}
	private static Field getIdField(Class clazz){
		Field targetField = null;
		Field[] fields =  clazz.getDeclaredFields();
		for(int i = 0;i<fields.length;i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(ID.class)){
				if(targetField != null){
					throw new ORMLiteException("More than one ID variable detected");
				}else{
					targetField = field;
				}
			}	
		}
		if(targetField == null){
			throw new ORMLiteException("No ID Variable detected");
		}
		targetField.setAccessible(true);
		return targetField;
	}
	
	//get all the columns from the object that can be used as the condition
	public static ArrayList<String[]> getColumnNameValue(Object object){
		ArrayList<String[]> nameValues = new ArrayList<String[]>();
		Class objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		
		for(int i = 0;i<fields.length;i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(Column.class)){
				String columnName = ((Column)field.getAnnotation(Column.class)).name().trim();
				try {
					Object value = field.get(object);
					nameValues.add(new String[]{
							columnName,
							convertValueToSql(value)
						});
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new ORMLiteException(e.getMessage());
				}
				
			}
		}
		return nameValues;
	}
	
	public static ArrayList<String[]> getNoneNullColumnNameValue(Object object){
		ArrayList<String[]> nameValues = new ArrayList<String[]>();
		Class objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		
		for(int i = 0;i<fields.length;i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(Column.class)){
				String columnName = ((Column)field.getAnnotation(Column.class)).name().trim();
				try {
					Object value = field.get(object);
					String valueSqlString = convertValueToSql(value);
					if(!valueSqlString.equals("NULL")){
						nameValues.add(new String[]{
								columnName,
								valueSqlString
							});
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ORMLiteException(e.getMessage());
				}
				
			}
		}
		return nameValues;
	}
	
	private static void validataColumnName(ArrayList<String> columnNames,String columnName){
		for(int i = 0;i<columnNames.size();i++){
			if(columnNames.get(i).equals(columnName)){
				throw new ORMLiteException("Duplicated column");
			}
		}
		if(columnName.length() == 0){
			throw new ORMLiteException("the column name is empty");
		}
		if(columnName.indexOf(' ') > -1){
			throw new ORMLiteException("The column name can't contain space");
		}
	}
	
	
	
	
	
	
	
	
	
	public static String convertValueToSql(Object object){
		if(object == null){
			return "NULL";
		}
		
		if(object instanceof Date){
			return "'"+convertDataToSql((Date)object)+"'";
		}
		if(object instanceof String){
			return "'"+object.toString()+"'";
		}
		if(object instanceof Integer){
			int intValue = ((Integer)object).intValue();
			if(intValue < 1){
				return "NULL";
			}
			
		}
		return object.toString();
	}
	public static ArrayList<String[]> getNoIDNameValue(Object object){
		ArrayList<String[]> nameValues = new ArrayList<String[]>();
		Class objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		String[] idNameType = getIdNameType(objClass);
		
		for(int i = 0;i<fields.length;i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(Column.class)){
				String columnName = ((Column)field.getAnnotation(Column.class)).name().trim();
				try {
					Object value = field.get(object);
					nameValues.add(new String[]{
							columnName,
							convertValueToSql(value)
						});
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new ORMLiteException(e.getMessage());
				}
				
			}
		}
		return nameValues;
	}
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static String convertDataToSql(Date date){
		return format.format(date);
	}
	
	public static Date convertSqlToDate(String sql){
		if(sql == null){
			return null;
		}
		try {
			return format.parse(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
