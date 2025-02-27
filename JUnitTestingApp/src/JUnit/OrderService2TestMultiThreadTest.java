package JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class OrderService2TestMultiThreadTest {
    private OrderService2 orderService2;
 
    @BeforeEach
    public void setUp() {
        orderService2 = new OrderService2();
    }
 
    @Test
	public void testPlaceOrderMultiThread() throws InterruptedException {
		
		int threadCount = 10;
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		CountDownLatch latch = new CountDownLatch(threadCount);
		
		for (int i=0 ; i<threadCount; i++) {  // i=0  i<10 , i=0 to 9
			executor.execute(() -> {
				try {
					orderService2.placeOrder(1);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				} // non crtitical task
			});
			latch.countDown(); //
		}
		latch.await(); // wait for all threads to finish
		executor.shutdown();
		
		// initial stock as 10
		// concurrently 10 threads are running -> placeorder -> 1 quan each -> final stock -> 0
		
		assertEquals(0, orderService2.getStock());		
	}
}