package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sswilliam.java.ormlite.DBInstance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.Task;

public class DBInstanceSelectTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelect() throws Exception {
		DBInstance instance = new DBInstance(DBInstanceSelectEnvConfig.autoIncreaseDBPath);
		instance.connect();
		Task task1 = new Task();
		ArrayList<Object> task1Result= instance.selectObject(task1);
		assertEquals(400, task1Result.size());
		
		Task task2 = new Task();
		task2.id = 3;
		ArrayList<Object> task2Result = instance.selectObject(task2);
		assertEquals(1, task2Result.size());
		Task ATask2 = (Task)task2Result.get(0);
		assertTrue(ATask2.taskName == null);
		assertTrue(ATask2.taskDescription == null);
		assertEquals(0, ATask2.taskState);
//		
//
		Task task3 = new Task();
		task3.id = -7;
		ArrayList<Object> task3Result= instance.selectObject(task3);
		assertEquals(400, task3Result.size());
//		
		Task task4 = new Task();
		task4.id = 10;
		task4.taskName = "test";
		ArrayList<Object> task4Result = instance.selectObject(task4);
		assertEquals(1, task2Result.size());
		Task ATask4 = (Task)task4Result.get(0);
		assertTrue(ATask4.taskName == null);
		assertEquals("task 2 desc", ATask4.taskDescription);
		assertEquals(2, ATask4.taskState);
//		
		Task task5 = new Task();
		task5.taskName = "task0";
		ArrayList<Object> task5Result = instance.selectObject(task5);
		assertEquals(1, task5Result.size());
		Task ATask5 = (Task)task5Result.get(0);
		assertEquals("task0", ATask5.taskName);
		assertEquals(1, ATask5.id);
		assertEquals("task 0 desc", ATask5.taskDescription);
		
//		
		Task task6  = new Task();
		task6.taskDescription = "task 1 desc";
		ArrayList<Object> task6Result = instance.selectObject(task6);
		assertEquals(2, task6Result.size());
		
		Task ATask60 = (Task)task6Result.get(0);
		assertEquals(5, ATask60.id);
		assertEquals("task1", ATask60.taskName);
		Task ATask61 = (Task)task6Result.get(1);
		assertEquals(6, ATask61.id);
		assertTrue(ATask61.taskName == null);
		
//		
//
		Task task7  = new Task();
		task7.taskName = "task10";
		task7.taskDescription = "task 10 desc";
		task7.taskState = 1;
		ArrayList<Object> task7Result = instance.selectObject(task7);
		assertEquals(1, task7Result.size());
		Task ATask7 = (Task)task7Result.get(0);
		assertEquals(41, ATask7.id);
		assertEquals("task10", ATask7.taskName);
		assertEquals("task 10 desc", ATask7.taskDescription);
		assertEquals(1, ATask7.taskState);
//		
//
		Task task8  = new Task();
		task8.taskName = "task10";
		task8.taskDescription = "task 10 desc";
		task8.taskState = 2;
		ArrayList<Object> task8Result = instance.selectObject(task8);
		assertEquals(0, task8Result.size());
		
		instance.close();
	}

	@Test
	public void testSelectGUID() throws Exception {
		DBInstance instance = new DBInstance(DBInstanceSelectEnvConfig.guidDBPath);
		instance.connect();
		
		GTask task1 = new GTask();
		ArrayList<Object> task1Result= instance.selectObject(task1);
		assertEquals(400, task1Result.size());
		
		GTask task2 = new GTask();
		task2.id = "3d14dc93-0c13-4ddd-aace-0cfa6842c426";
		ArrayList<Object> task2Result = instance.selectObject(task2);
		assertEquals(1, task2Result.size());
		GTask ATask2 = (GTask)task2Result.get(0);
		assertTrue(ATask2.taskName == null);
		assertTrue(ATask2.taskDescription == null);
		assertEquals(0, ATask2.taskState);
////		
////
		GTask task3 = new GTask();
		task3.id = null;
		ArrayList<Object> task3Result= instance.selectObject(task3);
		assertEquals(400, task3Result.size());
////		
		GTask task4 = new GTask();
		task4.id = "47e65acc-5a87-4ee1-8f0c-7e34fa5a427b";
		task4.taskName = "test";
		ArrayList<Object> task4Result = instance.selectObject(task4);
		assertEquals(1, task2Result.size());
		GTask ATask4 = (GTask)task4Result.get(0);
		assertTrue(ATask4.taskName == null);
		assertEquals("task 2 desc", ATask4.taskDescription);
		assertEquals(2, ATask4.taskState);
////		
		GTask task5 = new GTask();
		task5.taskName = "task0";
		ArrayList<Object> task5Result = instance.selectObject(task5);
		assertEquals(1, task5Result.size());
		GTask ATask5 = (GTask)task5Result.get(0);
		assertEquals("task0", ATask5.taskName);
		assertEquals("0f116722-0be5-4c1d-8caa-1df7f3e6c4e7", ATask5.id);
		assertEquals("task 0 desc", ATask5.taskDescription);
//		
////		
		GTask task6  = new GTask();
		task6.taskDescription = "task 1 desc";
		ArrayList<Object> task6Result = instance.selectObject(task6);
		assertEquals(2, task6Result.size());
//		
		GTask ATask60 = (GTask)task6Result.get(0);
		assertEquals("ed8a59a1-5bc5-47ec-86bd-6af6a2312599", ATask60.id);
		assertEquals("task1", ATask60.taskName);
		GTask ATask61 = (GTask)task6Result.get(1);
		assertEquals("dd391212-4a16-4f04-be03-4840aefa82f4", ATask61.id);
		assertTrue(ATask61.taskName == null);
//		
////		
////
		GTask task7  = new GTask();
		task7.taskName = "task10";
		task7.taskDescription = "task 10 desc";
		task7.taskState = 1;
		ArrayList<Object> task7Result = instance.selectObject(task7);
		assertEquals(1, task7Result.size());
		GTask ATask7 = (GTask)task7Result.get(0);
		assertEquals("354e079c-b182-4f72-a750-f90be3e9d7d4", ATask7.id);
		assertEquals("task10", ATask7.taskName);
		assertEquals("task 10 desc", ATask7.taskDescription);
		assertEquals(1, ATask7.taskState);
////		
////
		GTask task8  = new GTask();
		task8.taskName = "task10";
		task8.taskDescription = "task 10 desc";
		task8.taskState = 2;
		ArrayList<Object> task8Result = instance.selectObject(task8);
		assertEquals(0, task8Result.size());
		
		instance.close();
	}
}
