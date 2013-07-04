package net.sswilliam.java.ormlite;

import java.util.ArrayList;

public class DBInstanceMock {

	
	private String generateSelectFromObject(Object object){
		String tableName = DBUtils.getTableName(object.getClass());
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ");
		sb.append(tableName);
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
		sb.append(";");
		return sb.toString();
	}
	
	private String generateInsertFromObject(Object object){
		return null;
	}
	
	private String generateUpdateFromObjectMustWithId(Object object){
		return null;
	}
	
	private String generateUpdateFromObject(Object object, Object condition){
		return null;
	}

	private String generateDeleteFromObject(Object object){
		return null;
	}
	
	
	
}
