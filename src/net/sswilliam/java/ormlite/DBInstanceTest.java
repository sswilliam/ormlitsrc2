package net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.net.sswilliam.java.ormlite.DBInstanceSelectEnvConfig;
import test.net.sswilliam.java.ormlite.materials.guid.Task;

public class DBInstanceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelect() throws Exception {
		//ommit
		Thread.currentThread().sleep((long)(Math.floor(Math.random()*5)+10));
	}
	
	@Test
	public void testUpdate() throws Exception{
		//ommit
		Thread.currentThread().sleep((long)(Math.floor(Math.random()*5)+10));
	}
	
	@Test
	public void testDelete() throws Exception{
		String expectTask1Sql = "DELETE FROM Task;";
		String expectTask2Sql = "DELETE FROM Task WHERE task_id=3;";
		
		DBInstance instance = new DBInstance(DBInstanceSelectEnvConfig.autoIncreaseDBPath);
		Task task1 = new Task();
		String actualTask1Sql = instance.generateDeleteFromObject(task1);
		assertEquals(expectTask1Sql, actualTask1Sql);
		
		Task task2 = new Task();
		task2.id = 3;
		String actualTask2Sql = instance.generateDeleteFromObject(task2);
		assertEquals(expectTask2Sql, actualTask2Sql);
	}
	@Test
	public void testInsert() throws Exception{
		//ommit
		Thread.currentThread().sleep((long)(Math.floor(Math.random()*5)+10));
	}

	@Test
	public void testDeleteWithInteger(){
		String expectedSQL = "DELETE FROM Task WHERE category_id=2;";
		DBInstance instance = new DBInstance(DBInstanceSelectEnvConfig.autoIncreaseDBPath);
		Task task = new Task();
		task.categoryId = 2;
		String actualSQL = instance.generateDeleteFromObject(task);
		assertEquals(expectedSQL, actualSQL);
	}
}
