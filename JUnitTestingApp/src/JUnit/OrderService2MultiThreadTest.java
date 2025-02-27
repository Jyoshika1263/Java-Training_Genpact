package JUnit;
 
import static org.junit.Assert.assertEquals;
 
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
 

	public class OrderService2MultiThreadTest{
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
			for(int i = 1; i<threadCount; i++) {
				executor.execute(() -> {
					try {
						orderService2.placeOrder(1);
					}catch (Exception Ignored) {}
				});
					latch.countDown();	
			}
			latch.await();
			executor.shutdown();
			//initial stock as 10
			//concurrently 10 threads are running -> placeorder -> quan each -> final stock -> 0
			assertEquals(0, orderService2.getStock());
		}
	}
 