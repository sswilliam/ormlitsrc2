package net.sswilliam.java.utils.test;

import static org.junit.Assert.*;

import net.sswilliam.java.utils.CallStackUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CallStackUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			throw new Exception("mock exception");
		} catch (Exception e) {
			System.out.println(CallStackUtils.getCallStack(e));
			assertEquals("mock exception", e.getMessage());
			// TODO: handle exception
		}
	}

}
