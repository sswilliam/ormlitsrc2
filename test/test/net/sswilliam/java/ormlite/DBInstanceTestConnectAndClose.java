package test.net.sswilliam.java.ormlite;

import static org.junit.Assert.*;

import java.io.File;


import net.sswilliam.java.ormlite.DBInstance;
import net.sswilliam.java.utils.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBInstanceTestConnectAndClose {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConnectRelativePathWithCreate() {
		DBInstance instance = new DBInstance("test_repo\\createdb.db");
		try {
			instance.connect();
			
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		} 
		try {
			instance.close();
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		}
		FileUtils.deleteFile(new File("test_repo\\createdb.db"));
	}
	@Test
	public void testConnectFullPathWithCreate(){
		DBInstance instance = new DBInstance("D:\\sswilliam_foundataion_lib\\java\\ormlite\\test_repo\\createdb2.db");
		try {
			instance.connect();
			
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		} 
		try {
			instance.close();
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		}

		FileUtils.deleteFile(new File("test_repo\\createdb2.db"));
	}
	
	@Test
	public void testConnectRelativePathJustExist() {
		DBInstance instance = new DBInstance("test_repo\\connectdb.db");
		try {
			instance.connect();
			
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		} 
		try {
			instance.close();
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		}
	}
	@Test
	public void testConnectFullPathWJustExist(){
		DBInstance instance = new DBInstance("D:\\sswilliam_foundataion_lib\\java\\ormlite\\test_repo\\connectdb2.db");
		try {
			instance.connect();
			
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		} 
		try {
			instance.close();
		} catch (Exception e) {
			fail("exception happenes when connect:"+e.getMessage());
			// TODO: handle exception
		}

	}

}
