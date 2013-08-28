package net.sswilliam.java.utils.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import net.sswilliam.java.utils.ByteUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringByteUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStr2byte() throws UnsupportedEncodingException {
		String a = "qwertyuioplkjhgfdsazxcvbnm,./'[]=-0987654321`~!@#$%^&*()_+";
		byte[] abytes = ByteUtils.str2byte(a);
		byte[] abytesExp = new byte[]{113, 119, 101, 114, 116, 121, 117, 105, 111, 112, 108, 107, 106, 104, 103, 102, 100, 115, 97, 122, 120, 99, 118, 98, 110, 109, 44, 46, 47, 39, 91, 93, 61, 45, 48, 57, 56, 55, 54, 53, 52, 51, 50, 49, 96, 126, 33, 64, 35, 36, 37, 94, 38, 42, 40, 41, 95, 43};
		
		assertArrayEquals(abytesExp, abytes);
	}

	@Test
	public void testByte2str() throws UnsupportedEncodingException {
		byte[] abytes = new byte[]{113, 119, 101, 114, 116, 121, 117, 105, 111, 112, 108, 107, 106, 104, 103, 102, 100, 115, 97, 122, 120, 99, 118, 98, 110, 109, 44, 46, 47, 39, 91, 93, 61, 45, 48, 57, 56, 55, 54, 53, 52, 51, 50, 49, 96, 126, 33, 64, 35, 36, 37, 94, 38, 42, 40, 41, 95, 43};
		String a = ByteUtils.byte2str(abytes);
		assertEquals("qwertyuioplkjhgfdsazxcvbnm,./'[]=-0987654321`~!@#$%^&*()_+", a);
	}
	
	@Test
	public void testIntAndByte() throws Exception{
		int test = 5;
		byte[] bytes = ByteUtils.int2byte(test);
		int result = ByteUtils.byte2int(bytes);
		assertEquals(test, result);
		
		
		

		test = 100;
		bytes = ByteUtils.int2byte(test);
		result = ByteUtils.byte2int(bytes);
		System.out.println(result);
		assertEquals(test, result);
		
		

		test = 1024;
		bytes = ByteUtils.int2byte(test);
		result = ByteUtils.byte2int(bytes);
		System.out.println(result);
		assertEquals(test, result);
		

		test = 1023;
		bytes = ByteUtils.int2byte(test);
		result = ByteUtils.byte2int(bytes);
		System.out.println(result);
		assertEquals(test, result);

		test = 1025;
		bytes = ByteUtils.int2byte(test);
		result = ByteUtils.byte2int(bytes);
		System.out.println(result);
		assertEquals(test, result);

		test = Integer.MAX_VALUE;
		bytes = ByteUtils.int2byte(test);
		result = ByteUtils.byte2int(bytes);
		System.out.println(result);
		assertEquals(test, result);
		
	}

}
