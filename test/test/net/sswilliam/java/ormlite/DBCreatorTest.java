package test.net.sswilliam.java.ormlite;


import net.sswilliam.java.ormlite.DBCreator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.internal.runners.statements.Fail;

import test.net.sswilliam.java.ormlite.materials.TestConstants;
import test.net.sswilliam.java.ormlite.materials.guid.Category;
import test.net.sswilliam.java.ormlite.materials.guid.GCategory;
import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.GThread;
import test.net.sswilliam.java.ormlite.materials.guid.Task;
import test.net.sswilliam.java.ormlite.materials.guid.Thread;

public class DBCreatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		DBCreator creator = new DBCreator(
				TestConstants.autoIncreaseDBPath, 
				new Class[]{
						Task.class,
						Thread.class,
						Category.class
				}
		);
		try {

			creator.create();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			
			// TODO: handle exception
		}
	}

	@Test
	public void testWithGuid(){
		DBCreator creator = new DBCreator(
				TestConstants.guidDBPath, 
				new Class[]{
						GTask.class,
						GThread.class,
						GCategory.class
				}
		);
		try {

			creator.create();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			// TODO: handle exception
		}
	}

}
