package test.net.sswilliam.java.ormlite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	DBInstanceTestCreateInstance.class, 
	DBInstanceTestConnectAndClose.class
		 })
public class AllTests {

}
