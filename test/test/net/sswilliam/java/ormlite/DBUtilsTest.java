package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import net.sswilliam.java.ormlite.DBUtils;
import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.net.sswilliam.java.ormlite.materials.TaskGUID;
import test.net.sswilliam.java.ormlite.materials.guid.Category;
import test.net.sswilliam.java.ormlite.materials.guid.GCategory;
import test.net.sswilliam.java.ormlite.materials.guid.GThread;
import test.net.sswilliam.java.ormlite.materials.guid.Task;
import test.net.sswilliam.java.ormlite.materials.guid.Thread;

public class DBUtilsTest {

	
	@Test
	public void testGetTableName() {
		String BTableName1 = "Task";
		String BTableName2 = "Category";
		String BTableName3 = "Thread";
		
		String ATableName1 = DBUtils.getTableName(Task.class);
		String ATableName2 = DBUtils.getTableName(GCategory.class);
		String ATableName3 = DBUtils.getTableName(new Thread());
		
		assertEquals("Task table name", BTableName1, ATableName1);
		assertEquals("GCategory table name", BTableName2, ATableName2);
		assertEquals("Thraed table name", BTableName3, ATableName3);
		
	}
	
	@Test 
	public void testGetIdGenerationStrategy(){
		IDGenerateStrategy BStrategy1 = IDGenerateStrategy.AUTOINCREASE;
		IDGenerateStrategy BStrategy2 = IDGenerateStrategy.GUID;
		
		IDGenerateStrategy AStrategy1 = DBUtils.getIdGenerateStrategy(Task.class);
		IDGenerateStrategy AStrategy2 = DBUtils.getIdGenerateStrategy(GThread.class);
		assertEquals(BStrategy1, AStrategy1);
		assertEquals(BStrategy2, AStrategy2);
		
		IDGenerateStrategy AStrategy3 = DBUtils.getIdGenerateStrategy(new Category());
		IDGenerateStrategy AStrategy4 = DBUtils.getIdGenerateStrategy(new GCategory());
		assertEquals(BStrategy1, AStrategy3);
		assertEquals(BStrategy2, AStrategy4);
		
	}
	
	@Test 
	public void testGetIdNameType(){
		String BIdName1 = "task_id";
		String BIdType1 = "INTEGER";
		String BIdName2 = "category_id";
		String BIdType2 = "TEXT";
		
		String[] idNameType1 = DBUtils.getIdNameType(Task.class);
		assertEquals(BIdName1, idNameType1[0]);
		assertEquals(BIdType1, idNameType1[1]);
		
		String[] idNameType2 = DBUtils.getIdNameType(new GCategory());
		assertEquals(BIdName2, idNameType2[0]);
		assertEquals(BIdType2, idNameType2[1]);
	}
	
	@Test
	public void testGetColumnNameType(){
		ArrayList<String[]> BColumnNameType1 = new ArrayList<String[]>();
		BColumnNameType1.add(new String[]{"task_name","TEXT"});
		BColumnNameType1.add(new String[]{"task_description","TEXT"});
		BColumnNameType1.add(new String[]{"task_state","INTEGER"});
		BColumnNameType1.add(new String[]{"task_create_date","TIMESTAMP"});
		BColumnNameType1.add(new String[]{"category_id","INTEGER"});
		
		ArrayList<String[]> AcolunmNameType1 = DBUtils.getColumnNameType(Task.class);
		assertEquals(BColumnNameType1.size(), AcolunmNameType1.size());
		for(int i =0;i<BColumnNameType1.size();i++){
			assertArrayEquals(BColumnNameType1.get(i), AcolunmNameType1.get(i));
		}
		
		ArrayList<String[]> BColumnNameType2 = new ArrayList<String[]>();
		BColumnNameType2.add(new String[]{"category_name","TEXT"});
		BColumnNameType2.add(new String[]{"category_description","TEXT"});
		
		ArrayList<String[]> AcolumnNameType2 = DBUtils.getColumnNameType(new GCategory());
		assertEquals(BColumnNameType2.size(), AcolumnNameType2.size());
		for(int i = 0;i<BColumnNameType2.size();i++){
			assertArrayEquals(BColumnNameType2.get(i), AcolumnNameType2.get(i));
		}
		
	}
	@Test
	public void testGetIdNameValue(){
		String BTaskId1 = "1";
		Task task = new Task();
		String[] taskNameValue1 = DBUtils.getIdNameValue(task);
		assertTrue(taskNameValue1[1] == null);
		
		task.id = -1;
		String[] taskNameValue2 = DBUtils.getIdNameValue(task);
		assertTrue(taskNameValue2[1] == null);
		
		task.id = 1;
		String[] taskNameValue3 = DBUtils.getIdNameValue(task);
		assertEquals(BTaskId1, taskNameValue3[1]);
		
		String BCategory1 = UUID.randomUUID().toString();
		GCategory category = new GCategory();
		
		String[] ACategory1 = DBUtils.getIdNameValue(category);
		assertTrue(ACategory1[1] == null);
		
		category.categoryId = BCategory1;
		String[] ACategory2 = DBUtils.getIdNameValue(category);
		assertEquals("'"+BCategory1+"'", ACategory2[1]);
	}

	@Test
	public void testGetIdValue(){
		int BTaskId1 = 0;
		Task task = new Task();
		Object ATaskId1 = DBUtils.getIdValue(task);
		assertTrue(ATaskId1 instanceof Integer);
		assertEquals(BTaskId1, ((Integer)ATaskId1).intValue());
		
		String BCategory1 = UUID.randomUUID().toString();
		GCategory category = new GCategory();
		category.categoryId = BCategory1;
		Object ACategory1 = DBUtils.getIdValue(category);
		assertTrue(ACategory1 instanceof String);
		assertEquals(BCategory1, ACategory1);
		
		
	}
	
	@Test
	public void testGetColumnNameValue(){
		Task task = new Task();
		ArrayList<String[]> AColunmNameValue = DBUtils.getColumnNameValue(task);
		assertEquals(5, AColunmNameValue.size());
		for(int i = 0;i<5;i++){
			assertEquals("NULL", AColunmNameValue.get(i)[1]);
		}
		
		int BCategoryId = 3;
		Date BDate = new Date();
		String BTaskDescriptionString = "I am a description";
		
		task.categoryId = BCategoryId;
		task.taskDescription = BTaskDescriptionString;
		task.taskCreateDate = BDate;
		

		ArrayList<String[]> AColunmNameValue2 = DBUtils.getColumnNameValue(task);
		
		ArrayList<String[]> BColumnNameValue = new ArrayList<String[]>();
		BColumnNameValue.add(new String[]{"task_name","NULL"});
		BColumnNameValue.add(new String[]{"task_description","'"+BTaskDescriptionString+"'"});
		BColumnNameValue.add(new String[]{"task_state","NULL"});
		BColumnNameValue.add(new String[]{"task_create_date","'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(BDate)+"'"});
		BColumnNameValue.add(new String[]{"category_id","3"});
		
		assertEquals(BColumnNameValue.size(), AColunmNameValue2.size());
		for(int i =0;i<BColumnNameValue.size();i++){
			assertArrayEquals(BColumnNameValue.get(i), AColunmNameValue2.get(i));
		}
				
	}
	
	@Test
	public void testGetNoneNullColumnNameValue(){
		Task task = new Task();
		ArrayList<String[]> AColunmNameValue = DBUtils.getNoneNullColumnNameValue(task);
		assertEquals(0, AColunmNameValue.size());
		
		
		int BCategoryId = 3;
		Date BDate = new Date();
		String BTaskDescriptionString = "I am a description";
		
		task.categoryId = BCategoryId;
		task.taskDescription = BTaskDescriptionString;
		task.taskCreateDate = BDate;
		

		ArrayList<String[]> AColunmNameValue2 = DBUtils.getNoneNullColumnNameValue(task);
		
		ArrayList<String[]> BColumnNameValue = new ArrayList<String[]>();
		BColumnNameValue.add(new String[]{"task_description","'"+BTaskDescriptionString+"'"});
		BColumnNameValue.add(new String[]{"task_create_date","'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(BDate)+"'"});
		BColumnNameValue.add(new String[]{"category_id","3"});
		
		assertEquals(BColumnNameValue.size(), AColunmNameValue2.size());
		for(int i =0;i<BColumnNameValue.size();i++){
			assertArrayEquals(BColumnNameValue.get(i), AColunmNameValue2.get(i));
		}
	}
}
