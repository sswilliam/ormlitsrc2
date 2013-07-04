package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.util.Date;

import net.sswilliam.java.ormlite.DBInstance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.net.sswilliam.java.ormlite.materials.TestConstants;
import test.net.sswilliam.java.ormlite.materials.guid.Category;
import test.net.sswilliam.java.ormlite.materials.guid.GCategory;
import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.GThread;
import test.net.sswilliam.java.ormlite.materials.guid.Task;
import test.net.sswilliam.java.ormlite.materials.guid.Thread;

public class DBInstanceInsertTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAutoIncrease() throws Exception {
		DBInstance instance = new DBInstance(TestConstants.autoIncreaseDBPath);
		instance.connect();
		
		Category category = new Category();
		category.categoryDescriptionString = "testDesc1";
		category.categoryId = 0;
		category.categoryName = "testName1";
	
		instance.insertObject(category);
		instance.insertObject(category);
		
		Task task = new Task();
		task.categoryId = 0;
		task.id = 9;
		task.taskCreateDate = new Date();
		task.taskDescription = "task Desc 1";
		task.taskName = "task name 1";
		task.taskState = 0;
		instance.insertObject(task);
		
		Thread thread = new Thread();
		thread.beginDate = new Date();
		thread.endDate = new Date(new Date().getTime() + 6000);
		thread.taskId = 0;
		thread.threadNote = "thread note 1";
		instance.insertObject(thread);
		
		instance.close();
	}
	
	@Test
	public void testGUID() throws Exception {
		DBInstance instance = new DBInstance(TestConstants.guidDBPath);
		instance.connect();
		
		GCategory category = new GCategory();
		category.categoryDescriptionString = "testDesc1";
		category.categoryId = "0";
		category.categoryName = "testName1";
	
		instance.insertObject(category);
		instance.insertObject(category);
		
		GTask task = new GTask();
		task.categoryId = "unknown";
		task.id = "un know";
		task.taskCreateDate = new Date();
		task.taskDescription = "task Desc 1";
		task.taskName = "task name 1";
		task.taskState = 0;
		instance.insertObject(task);
		
		GThread thread = new GThread();
		thread.beginDate = new Date();
		thread.endDate = new Date(new Date().getTime() + 6000);
		thread.taskId = null;
		thread.threadNote = "thread note 1";
		instance.insertObject(thread);
		
		instance.close();
	}

}
