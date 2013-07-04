package test.net.sswilliam.java.utils;

import static org.junit.Assert.*;

import java.io.File;

import net.sswilliam.java.utils.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception{
		File targetPlace = new File("fileutil\\target");
		FileUtils.deleteFile(targetPlace);
		assertFalse(targetPlace.exists());
		targetPlace.mkdirs();
		assertTrue(targetPlace.exists());
		
		File destFile = new File("fileutil\\target\\file.txt");
		FileUtils.copyFile(new File("fileutil\\source\\file.txt"), destFile);
		assertTrue(destFile.exists());
		
		File destFolder = new File("fileutil\\target\\");
		FileUtils.copyDirectory(new File("fileutil\\source\\folder"), destFolder);
		
		File actualDestFilder = new File("fileutil\\target\\folder");
		assertTrue(actualDestFilder.exists());
	}

}
