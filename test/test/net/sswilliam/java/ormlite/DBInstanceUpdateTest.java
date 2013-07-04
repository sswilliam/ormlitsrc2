package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import net.sswilliam.java.ormlite.DBInstance;
import net.sswilliam.java.ormlite.DBUtils;
import net.sswilliam.java.ormlite.ORMLiteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.Task;

public class DBInstanceUpdateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test() throws Exception {
//
//		DBInstance instance = new DBInstance(DBInstanceUpdateEnvConfig.autoIncreaseDBPath);
//		instance.connect();
//		
//
//		Task task1 = new Task();
//		instance.deleteObject(task1);
//		
//		String sql1 = "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (1, 'Task0', 'Task1 Desc', 1, '2013-05-21 14:57:01.042' ,1);\n";
//		sql1 += "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (2, 'Task0', 'Task0 Desc', 1, '2013-05-21 14:58:01.042' ,2);\n";
//		sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (3, 'Task1', 'Task0 Desc', 2, '2013-05-21 14:59:01.042' ,5);\n";
//		sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (4, 'Task1', 'Task0 Desc', 1, '2013-05-21 14:00:01.042' ,7);\n";
//		sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (5, 'Task1', 'Task0 Desc', 2, '2013-05-21 14:01:01.042' ,8);";
//		instance.executeNoQuery(sql1);
//		
//		
//		try {
//
//			instance.updateObjectWithId(task1);
//			fail("exception should be cateched");
//		} catch (Exception e) {
//			assertTrue(e instanceof ORMLiteException);
//		}
//		
//		Task task2 = new Task();
//		task2.id = 1;
//		instance.updateObjectWithId(task2);
//		
//		ArrayList<Object> result = instance.selectObject(task2);
//		assertEquals(1, result.size());
//		Task Atask2 = (Task)result.get(0);
//		assertEquals(1, Atask2.id);
//		assertTrue(Atask2.taskName == null);
//		assertTrue(Atask2.taskDescription == null);
//		assertTrue(Atask2.taskCreateDate == null);
//		assertTrue(Atask2.taskState == 0);
//		assertTrue(Atask2.categoryId == 0);
//		
//		Task task3 = new Task();
//		task3.id = 3;
//		task3.categoryId = 100;
//		Date date = new Date();
//		task3.taskCreateDate = date;
//		task3.taskName = "task3 modifiled";
//		task3.taskDescription = "task3 desc modified";
//		instance.updateObjectWithId(task3);
//
//		result = instance.selectObject(task3);
//		assertEquals(1, result.size());
//		Task ATask3 = (Task)result.get(0);
//		assertEquals(3, ATask3.id);
//		assertEquals(100, ATask3.categoryId);
//		assertEquals(date, ATask3.taskCreateDate);
//		assertEquals("task3 modifiled", ATask3.taskName);
//		assertEquals("task3 desc modified", ATask3.taskDescription);
//		
//		
//		Task condition = new Task();
//		condition.taskName = "Task1";
//		
//		Task task4 = new Task();
//		task4.taskName = "task1 mdofiied";
//		task4.taskDescription = "task1 desc modified";
//		task4.taskState = 3;
//		task4.taskCreateDate = new Date();
//		task4.categoryId = 5;
//		instance.updateObjectWithoutId(task4, condition);
//		
//		result = instance.selectObject(task4);
//		assertEquals(2, result.size());
//		
////		
//		
//		instance.deleteObject(task1);
//		instance.executeNoQuery(sql1);
////		
//		Task condition2 = new Task();
//		condition2.taskName = "Task0";
//		condition2.taskCreateDate = DBUtils.convertSqlToDate("2013-05-21 14:58:01.042");
////		
//		Task task5 = new Task();
//		task5.taskName = "task 5 updated";
//		task5.taskDescription ="task 5 desc updated";
//		instance.updateObjectWithoutId(task5, condition2);
//		
//		result = instance.selectObject(task5);
//		assertEquals(1, result.size());
//		
//		instance.updateObjectWithoutId(task5, task1);
//		result = instance.selectObject(task5);
//		assertEquals(5, result.size());
//		
//		instance.close();
//		
//		
//	}
//
//	

	@Test
	public void testGUID() throws Exception {

		DBInstance instance = new DBInstance(DBInstanceUpdateEnvConfig.guidDBPath);
		instance.connect();
		

		Task task1 = new Task();
		instance.deleteObject(task1);
		

		String sql2 = "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('0f116722-0be5-4c1d-8caa-1df7f3e6c4e7', 'Task0', 'Task1 Desc', 1, '2013-05-21 14:57:01.042' ,1);\n";
		sql2 += "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('96742f9b-6678-4ca3-9279-8eaf90f92e3f', 'Task0', 'Task0 Desc', 1, '2013-05-21 14:58:01.042' ,2);\n";
		sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('3d14dc93-0c13-4ddd-aace-0cfa6842c426', 'Task1', 'Task0 Desc', 2, '2013-05-21 14:59:01.042' ,5);\n";
		sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('b6828194-5a19-410a-bcb9-7650a1a87c78', 'Task1', 'Task0 Desc', 1, '2013-05-21 14:00:01.042' ,7);\n";
		sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('ed8a59a1-5bc5-47ec-86bd-6af6a2312599', 'Task1', 'Task0 Desc', 2, '2013-05-21 14:01:01.042' ,8);";
		instance.executeNoQuery(sql2);
		
		
		try {

			instance.updateObjectWithId(task1);
			fail("exception should be cateched");
		} catch (Exception e) {
			assertTrue(e instanceof ORMLiteException);
		}
		
		GTask task2 = new GTask();
		task2.id = "0f116722-0be5-4c1d-8caa-1df7f3e6c4e7";
		instance.updateObjectWithId(task2);
//		
		ArrayList<Object> result = instance.selectObject(task2);
		assertEquals(1, result.size());
		GTask Atask2 = (GTask)result.get(0);
		assertEquals("0f116722-0be5-4c1d-8caa-1df7f3e6c4e7", Atask2.id);
		assertTrue(Atask2.taskName == null);
		assertTrue(Atask2.taskDescription == null);
		assertTrue(Atask2.taskCreateDate == null);
		assertTrue(Atask2.taskState == 0);
		assertTrue(Atask2.categoryId == null);
//		
		GTask task3 = new GTask();
		task3.id = "3d14dc93-0c13-4ddd-aace-0cfa6842c426";
		task3.categoryId = "asdfad";
		Date date = new Date();
		task3.taskCreateDate = date;
		task3.taskName = "task3 modifiled";
		task3.taskDescription = "task3 desc modified";
		instance.updateObjectWithId(task3);
//
		result = instance.selectObject(task3);
		assertEquals(1, result.size());
		GTask ATask3 = (GTask)result.get(0);
		assertEquals("3d14dc93-0c13-4ddd-aace-0cfa6842c426", ATask3.id);
		assertEquals("asdfad", ATask3.categoryId);
		assertEquals(date, ATask3.taskCreateDate);
		assertEquals("task3 modifiled", ATask3.taskName);
		assertEquals("task3 desc modified", ATask3.taskDescription);
//		
//		
		GTask condition = new GTask();
		condition.taskName = "Task1";
		
		GTask task4 = new GTask();
		task4.taskName = "task1 mdofiied";
		task4.taskDescription = "task1 desc modified";
		task4.taskState = 3;
		task4.taskCreateDate = new Date();
		task4.categoryId = "adsfad";
		instance.updateObjectWithoutId(task4, condition);
		
		result = instance.selectObject(task4);
		assertEquals(2, result.size());
//		
////		
//		
		instance.deleteObject(task1);
		instance.executeNoQuery(sql2);
////		
		GTask condition2 = new GTask();
		condition2.taskName = "Task0";
		condition2.taskCreateDate = DBUtils.convertSqlToDate("2013-05-21 14:58:01.042");
////		
		GTask task5 = new GTask();
		task5.taskName = "task 5 updated";
		task5.taskDescription ="task 5 desc updated";
		instance.updateObjectWithoutId(task5, condition2);
//		
		result = instance.selectObject(task5);
		assertEquals(1, result.size());
//		
		instance.updateObjectWithoutId(task5, task1);
		result = instance.selectObject(task5);
		assertEquals(5, result.size());
		
		instance.close();
	}

}
