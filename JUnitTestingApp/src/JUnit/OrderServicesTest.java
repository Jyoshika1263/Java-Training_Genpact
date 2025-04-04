package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderServicesTest {
	OrderServices os=new OrderServices();
	@Test
	void test() {
		OrderServices os=new OrderServices();
		assertEquals(450, os.calPrice(50, 10));
		//fail("Not yet implemented");
	}
 
	@Test
	void testException()
	{
		OrderServices os=new OrderServices();
		IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->{
			os.placeOrder(12);
		});
	}
	@Test
	void SufficientStock()
	{
		OrderServices os=new OrderServices();
		assertTrue(os.placeOrder(5));
	}
	@Test
	void InSufficientStock()
	{
		OrderServices os=new OrderServices();
		assertTrue(os.placeOrder(2));
	}
	@ParameterizedTest
	@CsvSource({
			"50, 10, 450",
			"10, 10, 90"
//			"7, 5, 12",
//			"8, 9, 17"

	})
	void testAddParamcsv(int a, int b, int expected)
	{
		OrderServices os=new OrderServices();
		assertEquals(expected,os.calPrice(a,b));
	}
	@Test
	void testTimeout()
	{
		OrderServices os=new OrderServices();
		assertTimeout(java.time.Duration.ofMillis(1000),()->{
			Thread.sleep(500);
			os.calPrice(50, 10);
		});
	}
	@Test
	void test0() {
		OrderServices os=new OrderServices();
		assertEquals(0, os.calPrice(50, 0));
		//fail("Not yet implemented");
	}
	
	@Test
	void testPlaceOrderMultithread() throws InterruptedException{
		Thread t1 = new Thread(() -> os.placeOrder(2));
		Thread t2 = new Thread(() -> os.placeOrder(1));
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		assertEquals(7,os.getStock());
	}
	@BeforeAll
    public static void beforeAllTests() {
        System.out.println("Testing Started");
    }
 
    @AfterAll
    public static void afterAllTests() {
        System.out.println("All Tests Completed");
    }
    @AfterEach
    public void afterEachTest() {
        System.out.println("Test Completed");
    }

    @Test
    @Disabled("Test disabled for demonstration purposes")
    public void testDisabled() {
        System.out.println("This test is disabled and will not run");
    }
}

//		@Test
//		void test() {
//			OrderServices os=new OrderServices();
//			assertEquals(450, os.calPrice(50, 10));
//			//fail("Not yet implemented");
//		}
//
//		@Test
//		void testException()
//		{
//			OrderServices os=new OrderServices();
//			IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->{
//				os.placeOrder(12);
//			});
//		}
//		// Test case 1: Sufficient stock
//	    @Test
//	    void testPlaceOrderSufficientStock() {
//	        OrderServices os = new OrderServices();
//	        assertTrue(os.placeOrder(5));
//	        assertEquals(5, os.getStock());
//	    }
//
//	    // Test case 2: Insufficient stock
//	    @Test
//	    void testPlaceOrderInsufficientStock() {
//	        OrderServices os = new OrderServices();
//	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//	            os.placeOrder(15);
//	        });
//	        assertEquals("insufficient stock", exception.getMessage());
//	    }
//
//	    // Test case 3: Boundary case
//	    @Test
//	    void testPlaceOrderBoundaryCase() {
//	        OrderServices os = new OrderServices();
//	        // Add boundary case tests here
//	    }
//
//	    // Test case 4: Calculate total with 0 quantity
//	    @Test
//	    void testCalculateTotalWithZeroQuantity() {
//	        OrderServices os = new OrderServices();
//	        assertEquals(0, os.calPrice(100, 0));
//	    }
//
//	    // Test case 5: Parameterized test case
//	    @ParameterizedTest
//	    @CsvSource({
//	        "100, 2, 180",
//	        "50, 4, 180",
//	        "75, 3, 202.5",
//	        "30, 5, 135"
//	    })
//	    void testCalPriceParameterized(double price, int quantity, double expected) {
//	        OrderServices os = new OrderServices();
//	        assertEquals(expected, os.calPrice(price, quantity));
//	    }
//
//	    // Test case 6: Timeout
//	    @Test
//	    void testOrderProcessingTimeout() {
//	        OrderServices os = new OrderServices();
//	        assertTimeout(Duration.ofMillis(1000), () -> {
//	            os.orderProcessingTest(500);
//	        });
//	        assertTimeout(Duration.ofMillis(1000), () -> {
//	            os.orderProcessingTest(1500);
//	        });
//	    }
//		
//}		
		
//test case 1:	//sufficient stock
		//testPlaceOrder()->placeorder(5) ->succeed->assertTRue
		//stock should reduce to 5, assertEquals(5, getStock())
		
//tesst case 2:	     //insufficientstock
		//placeorder(15)->assertThrows ->IllegalArgumentExpections
		//assertEquals("Insufficient stock",var.getmsg());
		
//tesst case 3:		//boundary case
		//exception cases
		
//tesst case 4:		//caltotal->with 0 quantity
		//quantity =0,total will always be 0
		//assertEquals(0,)
		
//tesst case 5:		//parameterise test case
		//@csvSource->4 sets->(price,quant,expected value)
		//multiple cases
		
//test case 6:		//Timeout->time->1000
		//orderprocessingtest->
		//sleep->500,1500
		
//MultiThread test
//placeorder->parallel->2 orders
//1 st order-> 2
//2nd order->1
//final stock->7




//	@Test
//    public void testInsufficientStocks() {
//        OrderServices orderServices = new OrderServices();
//        assertThrows(IllegalArgumentException.class, () -> {
//            orderServices.placeOrder(15);
//        });
//    }
//
//    @Test
//    public void testCalculateTotal() {
//        OrderServices orderServices = new OrderServices();
//        double price = 100.0;
//        int quantity = 2;
//        double expectedTotal = price * quantity * (1 - orderServices.getDiscount());
//        assertEquals(expectedTotal, orderServices.calPrice(price, quantity));
//    }
