package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.io.File;

import net.sswilliam.java.ormlite.DBInstance;
import net.sswilliam.java.utils.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBInstanceTestCreateInstance {

	private String parentPath = "D:\\sswilliam_foundataion_lib\\java\\ormlite\\test_create_repo";
	@Before
	public void setUp() throws Exception {
		File file = new File(parentPath);
		FileUtils.deleteFile(file);
	}

	@After
	public void tearDown() throws Exception {
		File file = new File(parentPath);
		FileUtils.deleteFile(file);
	}

	@Test
	public void testDBInstanceRelativePath() {
		try {
			DBInstance instance = new DBInstance("test_create_repo\\test\\test\\test_create.db");
			File file = new File("test_create_repo\\test\\test\\");
			assertTrue(file.exists());
		} catch (Exception e) {
			fail("failed to create the db directory");
			// TODO: handle exception
		}
	}

	@Test
	public void testDBIncorrectPath(){
		try {

			DBInstance instance = new DBInstance("test_create_repo\\test_create2");
			fail("this is incorrect name and should not be crteated");
		} catch (Exception e) {
			assertEquals("exception message ", e.getMessage(), "The path should be use .db as its suffix");
		}
	}
	@Test
	public void testDBInstanceFullPath(){
		try {

			DBInstance instance = new DBInstance("D:\\sswilliam_foundataion_lib\\java\\ormlite\\test_create_repo\\test2\\test_create2.db");
			File file = new File("D:\\sswilliam_foundataion_lib\\java\\ormlite\\test_create_repo\\test2\\");
			assertTrue(file.exists());
		} catch (Exception e) {
			fail("failed to create the db directory");
			// TODO: handle exception
		}
		
		
	}
	@Test
	public void testDBInstanceRelativePath2(){
		try {
			DBInstance instance = new DBInstance("test_create.db");
			File file = new File("test_create.db");
		} catch (Exception e) {
			e.printStackTrace();
			fail("failed to create the db directory");
		}
	}
}
