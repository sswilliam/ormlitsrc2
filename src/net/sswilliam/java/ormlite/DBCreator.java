package net.sswilliam.java.ormlite;

import java.io.Console;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;
import net.sswilliam.java.ormlite.annotation.Table;

public class DBCreator {

	private String dbPath;
	private Class[] tables;
	private Set<String> tableNames = new HashSet<String>();
	static{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new ORMLiteException("no driver found");
		}
	}
	
	public DBCreator(String dbPath,Class[] tables){
		this.dbPath = dbPath;
		this.tables = tables;
		validatePath();
		validateTables();
		
	}
	public void create(){
		//delete the db file if existing
		tableNames.clear();
		File file = new File(this.dbPath);
		if(file.exists()){
			try {
				boolean deleted = file.delete();
				if(!deleted){
					throw new ORMLiteException("can't delete the existing file");
				}
			} catch (Exception e) {
				throw new ORMLiteException("can't delete the existing file");
				// TODO: handle exception
			}
		}
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:"+this.dbPath);
			for(int i = 0;i<tables.length;i++){
				addTable(connection, tables[i]);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ORMLiteException(e.getMessage());
		}
	}
	private void validatePath(){
		if(this.dbPath == null || this.dbPath.length() == 0){
			throw new ORMLiteException("The path should not be null");
		}
		if(!this.dbPath.endsWith(".db")){
			throw new ORMLiteException("The path should be use .db as its suffix");
		}
		File file = new File(this.dbPath);
		if(!file.exists()){
			File parent = file.getAbsoluteFile().getParentFile();
			if(!parent.exists()){
				boolean make = parent.mkdirs();
				if(!make){
					throw new ORMLiteException("failed to create the directory for the db file.");
				}
			}
		}
		this.dbPath = file.getAbsolutePath();
		
	}
	private void validateTables(){
		if(tables == null || tables.length == 0){
			throw new ORMLiteException("There is no table class passed in");
		}
		
	}
	private void addTable(Connection connection, Class clazz){
		
		String tableName = DBUtils.getTableName(clazz);
		validateTableName(tableName);
		tableNames.add(tableName);
		
		String[] idNameType = DBUtils.getIdNameType(clazz);
		IDGenerateStrategy strategy = DBUtils.getIdGenerateStrategy(clazz);
		
		ArrayList<String[]> colunmNameType = DBUtils.getColumnNameType(clazz);
		if(colunmNameType.size() == 0){
			throw new ORMLiteException("Only primary key is defined");
		}
		StringBuffer addTableSql = new StringBuffer();
		addTableSql.append("CREATE TABLE IF NOT EXISTS ");
		addTableSql.append(tableName);
		addTableSql.append(" (");
		
		addTableSql.append(idNameType[0]);
		addTableSql.append(" ");
		addTableSql.append(idNameType[1]);
		addTableSql.append(" PRIMARY KEY");
		if(strategy ==  IDGenerateStrategy.AUTOINCREASE){

			addTableSql.append(" AUTOINCREMENT");
		}
		
		for(int i = 0;i< colunmNameType.size();i++){
			if(!colunmNameType.get(i)[0].equals(idNameType[0])){
				addTableSql.append(", ");
				addTableSql.append(colunmNameType.get(i)[0]);
				addTableSql.append(' ');
				addTableSql.append(colunmNameType.get(i)[1]);
			}
		}
		addTableSql.append(");");
		System.out.println(addTableSql.toString());
		try {

			Statement statement = connection.createStatement();
			statement.executeUpdate(addTableSql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ORMLiteException("Failed when create the table");
			// TODO: handle exception
		}
		
		
		
	}
	
	private void validateTableName(String tableName){
		if(this.tableNames.contains(tableName)){
			throw new ORMLiteException("Duplicated Table Name:"+tableName);
		}
		if(tableName.length() == 0){
			throw new ORMLiteException("The table name is empty");
		}
		if(tableName.indexOf(' ')>-1){
			throw new ORMLiteException("The table name can't contain space");
		}
	}
	
}
