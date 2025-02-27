package JUnit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	OrderServicesTest.class,
	OrderService2Test.class,
	//OrderService2MultiThreadTest.class,
	
})
public class OrderServiceTestsuite {
	//empty, serving as a test suite container

}
