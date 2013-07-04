package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sswilliam.java.ormlite.DBInstance;
import net.sswilliam.java.ormlite.DBUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.Task;

public class DBInstanceDeleteTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {

		DBInstance instance = new DBInstance(DBInstanceDeleteEnvConfig.autoIncreaseDBPath);
		instance.connect();
		
		Task taskAll = new Task();
		instance.deleteObject(taskAll);
		
		ArrayList<Object> result = instance.selectObject(taskAll);
		assertEquals(0, result.size());
		
		String sql1 = "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (1, 'Task0', 'Task1 Desc', 1, '2013-05-21 14:57:01.042' ,1);\n";
		sql1 += "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (2, 'Task0', 'Task0 Desc', 1, '2013-05-21 14:58:01.042' ,2);\n";
		sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (3, 'Task1', 'Task0 Desc', 2, '2013-05-21 14:59:01.042' ,5);\n";
		sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (4, 'Task1', 'Task0 Desc', 1, '2013-05-21 14:00:01.042' ,7);\n";
		sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (5, 'Task1', 'Task0 Desc', 2, '2013-05-21 14:01:01.042' ,8);";
		
		instance.executeNoQuery(sql1);
		result = instance.selectObject(taskAll);
		assertEquals(5, result.size());
		
		
		Task task1 = new Task();
		task1.id = 1;
		instance.deleteObject(task1);

		result = instance.selectObject(taskAll);
		assertEquals(4, result.size());
		
		result = instance.selectObject(task1);
		assertEquals(0, result.size());
		
		Task task2 = new Task();
		task2.taskState = 1;
		instance.deleteObject(task2);
		

		result = instance.selectObject(taskAll);
		assertEquals(2, result.size());
		

		result = instance.selectObject(task2);
		assertEquals(0, result.size());
//		
		Task task3 = new Task();
		task3.taskName = "Task1";
		task3.taskState = 2;
		task3.categoryId = 2;
		instance.deleteObject(task3);
		

		result = instance.selectObject(taskAll);
		assertEquals(2, result.size());

		result = instance.selectObject(task2);
		assertEquals(0, result.size());
		

		Task task4 = new Task();
		task4.taskName = "Task1";
		task4.taskState = 2;
		task4.categoryId = 8;
		instance.deleteObject(task4);
		

		result = instance.selectObject(taskAll);
		assertEquals(1, result.size());

		result = instance.selectObject(task4);
		assertEquals(0, result.size());
		
		instance.deleteObject(taskAll);
		
		result = instance.selectObject(taskAll);
		assertEquals(0, result.size());
		instance.executeNoQuery(sql1);
		result = instance.selectObject(taskAll);
		assertEquals(5, result.size());
		
		Task task5 = new Task();
		task5.taskCreateDate = DBUtils.convertSqlToDate("2013-05-21 14:59:01.042");
		instance.deleteObject(task5);

		result = instance.selectObject(taskAll);
		assertEquals(4, result.size());
		result = instance.selectObject(task5);
		assertEquals(0, result.size());
		
		Task task6 = new Task();
		task6.id = 5;
		task6.taskName = "Task0";
		instance.deleteObject(task6);

		result = instance.selectObject(taskAll);
		assertEquals(3, result.size());
		
//		
//
		instance.close();
	}
	
	
	@Test
	public void testGUID() throws SQLException {

		DBInstance instance = new DBInstance(DBInstanceDeleteEnvConfig.guidDBPath);
		instance.connect();
		
		Task taskAll = new Task();
		instance.deleteObject(taskAll);
		
		ArrayList<Object> result = instance.selectObject(taskAll);
		assertEquals(0, result.size());
		
		String sql2 = "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('0f116722-0be5-4c1d-8caa-1df7f3e6c4e7', 'Task0', 'Task1 Desc', 1, '2013-05-21 14:57:01.042' ,1);\n";
		sql2 += "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('96742f9b-6678-4ca3-9279-8eaf90f92e3f', 'Task0', 'Task0 Desc', 1, '2013-05-21 14:58:01.042' ,2);\n";
		sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('3d14dc93-0c13-4ddd-aace-0cfa6842c426', 'Task1', 'Task0 Desc', 2, '2013-05-21 14:59:01.042' ,5);\n";
		sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('b6828194-5a19-410a-bcb9-7650a1a87c78', 'Task1', 'Task0 Desc', 1, '2013-05-21 14:00:01.042' ,7);\n";
		sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('ed8a59a1-5bc5-47ec-86bd-6af6a2312599', 'Task1', 'Task0 Desc', 2, '2013-05-21 14:01:01.042' ,8);";
		
		instance.executeNoQuery(sql2);
		result = instance.selectObject(taskAll);
		assertEquals(5, result.size());
		
		
		GTask task1 = new GTask();
		task1.id = "0f116722-0be5-4c1d-8caa-1df7f3e6c4e7";
		instance.deleteObject(task1);
//
		result = instance.selectObject(taskAll);
		assertEquals(4, result.size());
//		
		result = instance.selectObject(task1);
		assertEquals(0, result.size());
//		
		GTask task2 = new GTask();
		task2.taskState = 1;
		instance.deleteObject(task2);
//		
//
		result = instance.selectObject(taskAll);
		assertEquals(2, result.size());
		

		result = instance.selectObject(task2);
		assertEquals(0, result.size());
////		
		GTask task3 = new GTask();
		task3.taskName = "Task1";
		task3.taskState = 2;
		task3.categoryId = "15";
		instance.deleteObject(task3);
//		
//
		result = instance.selectObject(taskAll);
		assertEquals(2, result.size());

		result = instance.selectObject(task3);
		assertEquals(0, result.size());
		
//
		GTask task4 = new GTask();
		task4.taskName = "Task1";
		task4.taskState = 2;
		task4.categoryId = "8";
		instance.deleteObject(task4);
//		
//
		result = instance.selectObject(taskAll);
		assertEquals(1, result.size());

		result = instance.selectObject(task4);
		assertEquals(0, result.size());
//		
		instance.deleteObject(taskAll);
		
		result = instance.selectObject(taskAll);
		assertEquals(0, result.size());
		instance.executeNoQuery(sql2);
		result = instance.selectObject(taskAll);
		assertEquals(5, result.size());
		
		GTask task5 = new GTask();
		task5.taskCreateDate = DBUtils.convertSqlToDate("2013-05-21 14:59:01.042");
		instance.deleteObject(task5);

		result = instance.selectObject(taskAll);
		assertEquals(4, result.size());
		result = instance.selectObject(task5);
		assertEquals(0, result.size());
//		
		GTask task6 = new GTask();
		task6.id = "ed8a59a1-5bc5-47ec-86bd-6af6a2312599";
		task6.taskName = "Task0";
		instance.deleteObject(task6);

		result = instance.selectObject(taskAll);
		assertEquals(3, result.size());
		
		instance.close();
		
//		
//
		
	}

}
