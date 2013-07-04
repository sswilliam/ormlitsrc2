package net.sswilliam.java.ormlite;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import test.net.sswilliam.java.ormlite.DBInstanceSelectEnvConfig;
import test.net.sswilliam.java.ormlite.materials.guid.Task;

import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;
import net.sswilliam.java.utils.FileUtils;

public class DBInstance {

	private String path;
	private boolean isConnected = false;
	private Connection connection;
	private Statement statement;
	private Object sLock = new Object();
	
	static{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new ORMLiteException("no driver found");
		}
	}
	public DBInstance(String path){
		this.path = path.trim();
		validatePath();
	}
	public void connect() throws ORMLiteException{
		synchronized (sLock) {
			if(!isConnected){
				
				try {
					connection = DriverManager.getConnection("jdbc:sqlite:"+path);
					statement = connection.createStatement();
					isConnected = true;
				} catch (Exception e) {
					throw new ORMLiteException(e.getMessage());
				}
			}
		}
		
	}
	public void close(){
		synchronized (sLock) {
			if(isConnected){
				try {
					connection.close();
					isConnected = false;
				} catch (Exception e) {
					throw new ORMLiteException(e.getMessage());
				}
			}
		}
	}
	
	public ResultSet executeQuery(String sql) throws SQLException{
		synchronized (sLock) {
			if(!isConnected){
				throw new ORMLiteException("The connection is not established yet");
			}

			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet;
			
		}
	}
	public void executeNoQuery(String sql) throws SQLException{
		synchronized (sLock) {
			if(!isConnected){
				throw new ORMLiteException("The connection is not established yet");
			}
			statement.executeUpdate(sql);
			
		}
	}
	public void insertObject(Object object) throws SQLException{
		String sql = generateInsertFromObject(object);
		System.out.println(sql);
		this.executeNoQuery(sql);
		
	}
	public void updateObjectWithId(Object object) throws SQLException{
		String sql = generateUpdateFromObjectMustWithId(object);
		System.out.println(sql);
		this.executeNoQuery(sql);
	}
	public void updateObjectWithoutId(Object object, Object condition) throws SQLException{
		String sql = generateUpdateFromObject(object,condition);
		System.out.println(sql);
		this.executeNoQuery(sql);
	}
	public void deleteObject(Object object) throws SQLException{
		String sql = generateDeleteFromObject(object);
		System.out.println(sql);
		this.executeNoQuery(sql);
		
	}
	public ArrayList<Object> selectObject(Object object) throws SQLException{
		String sql = generateSelectFromObject(object);
		System.out.println(sql);
		ResultSet resultSet = executeQuery(sql);
		ArrayList<Object> retObject = new ArrayList<Object>();
		Object[] idNameField = DBUtils.getIdNameField(object);
		ArrayList<Object[]> columnsNameField = DBUtils.getColumnNameField(object);
		while(resultSet.next()){
			Object resultObject = convertRowToObject(resultSet, object.getClass(), idNameField, columnsNameField);
			retObject.add(resultObject);
		}
		return retObject;
	}
	public void backupDB(String target) throws Exception{
		FileUtils.copyFile(new File(this.path), new File(target));
	}
	private Object convertRowToObject(ResultSet resultSet,Class clazz, Object[] idNameField, ArrayList<Object[]> columnsNameField){
		try {

			Object instance = clazz.newInstance();
			Object idValue = DBUtils.getValueFromResultSet(resultSet, (String)idNameField[0], (Field)idNameField[1]);
			((Field)idNameField[1]).set(instance, idValue);
			for(int i = 0;i<columnsNameField.size();i++){
				Object columnsValue = DBUtils.getValueFromResultSet(resultSet, (String)columnsNameField.get(i)[0], (Field)columnsNameField.get(i)[1]);
				((Field)columnsNameField.get(i)[1]).set(instance, columnsValue);
			}
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ORMLiteException("Error happened when convert data from db to object "+e.getMessage());
		}
	}
	private String generateInsertFromObject(Object object){
		String tableName = DBUtils.getTableName(object);
		String[] idNameType = DBUtils.getIdNameType(object);
		ArrayList<String[]> columnvaluePair = DBUtils.getNoIDNameValue(object);
		IDGenerateStrategy strategy = DBUtils.getIdGenerateStrategy(object);
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" (");
		if(strategy == IDGenerateStrategy.GUID){
			sb.append(idNameType[0]);
			sb.append(',');
		}
		for(int i = 0;i<columnvaluePair.size();i++){
			if(!columnvaluePair.get(i)[0].equals(idNameType[0])){
				sb.append(columnvaluePair.get(i)[0]);
				sb.append(',');
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") VALUES (");
		if(strategy == IDGenerateStrategy.GUID){
			sb.append('\'');
			sb.append(UUID.randomUUID());
			sb.append("',");
		}
		for(int i = 0;i<columnvaluePair.size();i++){
			if(!columnvaluePair.get(i)[0].equals(idNameType[0])){
				sb.append(columnvaluePair.get(i)[1]);
				sb.append(',');
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(");");
		return sb.toString();
	}
	public String generateDeleteFromObject(Object object){
		String tableName = DBUtils.getTableName(object);
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM ");
		sb.append(tableName);
		sb.append(generateConditionStatement(object));
		sb.append(";");
		return sb.toString();
	}
	private String generateUpdateFromObjectMustWithId(Object object){

		String[] idNameValue = DBUtils.getIdNameValue(object);
		if(idNameValue[1] == null){
			throw new ORMLiteException("No id value detected");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(generateUpdateColumnValueSql(object));
		sb.append(" WHERE ");
		sb.append(idNameValue[0]);
		sb.append("=");
		sb.append(idNameValue[1]);
		sb.append(';');
		return sb.toString();
		
		
	}
	private String generateUpdateFromObject(Object object, Object condition){
		StringBuffer sb = new StringBuffer();
		sb.append(generateUpdateColumnValueSql(object));
		sb.append(generateConditionStatement(condition));
		sb.append(';');
		return sb.toString();
	}
	
	public String generateSelectFromObject(Object object){
		String tableName = DBUtils.getTableName(object.getClass());
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ");
		sb.append(tableName);
		sb.append(generateConditionStatement(object));
		sb.append(";");
		
		return sb.toString();
	}
	private String generateUpdateColumnValueSql(Object object){
		String tableName = DBUtils.getTableName(object);
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(tableName);
		sb.append(" SET ");
		ArrayList<String[]> colummNameValue = DBUtils.getColumnNameValue(object);
		int size = colummNameValue.size();
		if(size == 0){
			return "";
		}
		if(size > 1){
			for(int i = 0;i<size-1;i++){
				sb.append(colummNameValue.get(i)[0]);
				sb.append("=");
				sb.append(colummNameValue.get(i)[1]);
				sb.append(", ");
			}
		}
		sb.append(colummNameValue.get(size-1)[0]);
		sb.append("=");
		sb.append(colummNameValue.get(size-1)[1]);
		return sb.toString();
	}
	private String generateConditionStatement(Object object){
		StringBuffer sb = new StringBuffer();
		String[] idNameValue = DBUtils.getIdNameValue(object);
		if(idNameValue[1] != null){
			sb.append(" WHERE ");
			sb.append(idNameValue[0]);
			sb.append("=");
			sb.append(idNameValue[1]);
		}else{
			ArrayList<String[]> conditionsColumns = DBUtils.getNoneNullColumnNameValue(object);
			int size = conditionsColumns.size();
			if(size == 0){
				return "";
			}
			sb.append(" WHERE ");
			if(size > 1){
				for(int i = 0;i<conditionsColumns.size()-1;i++){
					sb.append(conditionsColumns.get(i)[0]);
					sb.append("=");
					sb.append(conditionsColumns.get(i)[1]);
					sb.append(" AND ");
				}
			}
			sb.append(conditionsColumns.get(size-1)[0]);
			sb.append("=");
			sb.append(conditionsColumns.get(size-1)[1]);
			
		}
		return sb.toString();
	}
	private void validatePath() throws ORMLiteException{
		if(this.path == null || this.path.length() == 0){
			throw new ORMLiteException("The path should not be null");
		}
		if(!this.path.endsWith(".db")){
			throw new ORMLiteException("The path should be use .db as its suffix");
		}
		File file = new File(this.path);
		if(!file.exists()){
			throw new ORMLiteException("the file you specified does not exists");
		}
		
	}
	
	public static void main(String[] args) {
//		DBInstance instance = new DBInstance(DBInstanceSelectEnvConfig.autoIncreaseDBPath);
//		Task task1 = new Task();
//		System.out.println(instance.generateSelectFromObject(task1));
//		Task task2 = new Task();
//		task2.id = 3;
//		System.out.println(instance.generateSelectFromObject(task2));
//		Task task3 = new Task();
//		task3.taskCreateDate = new Date();
//		task3.taskName = "test task 3";
//		System.out.println(instance.generateSelectFromObject(task3));
		
		DBInstance instance = new DBInstance(DBInstanceSelectEnvConfig.autoIncreaseDBPath);
		Task task1 = new Task();
		task1.categoryId = 2;
		System.out.println(instance.generateDeleteFromObject(task1));
		Task task2 = new Task();
		task2.id = 3;
		System.out.println(instance.generateDeleteFromObject(task2));
		Task task3 = new Task();
		task3.taskCreateDate = new Date();
		task3.taskName = "test task 3";
		System.out.println(instance.generateDeleteFromObject(task3));
	}
	
}
