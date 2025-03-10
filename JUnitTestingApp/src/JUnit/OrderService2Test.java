package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderService2Test {
    private OrderService2 orderService2;
    @BeforeEach // initialize orderservice2 before each test case
    public void setUp() {
    	orderService2 = new OrderService2();
    }
	@Test
	public void TestCalPrice_ValidInputs() {
		double price=100.00;
		int quantity=2;
		double expectedPrice=price*quantity*(1-orderService2.getDiscount());
		assertEquals(expectedPrice, orderService2.calPrice(price, quantity)); //checking calPrice()
	}
	// test case -> 0 quantity
	//Test case -> passing quantity = 0
		@Test
	    void TestCalZeroQuantity() {
	        double total = orderService2.calPrice(100.00, 0);
	        assertEquals(0, total, "Total should be 0 when quantity is 0");
	    }
		//Test case -> successfully placing order
		//Sufficient Stocks
		@Test
		void TestSuccessfullyPlaced() {
			boolean result = orderService2.placeOrder(5);
			assertTrue(result, "Order should be placed successfully");
			assertEquals(5, orderService2.getStock(), "Stock should be reduced by the ordered quantity");
		}

		//Boundary test case edge cases
		//edge case -> quantity exactly same as stock
		 @Test
		    void TestPlaceOrderExactStock() {
		        boolean result = orderService2.placeOrder(10);
		        assertTrue(result, "Order should be placed successfully");
		        assertEquals(0, orderService2.getStock(), "Stock should be 0 after ordering the exact stock amount");
		    }
	 
	 
		//edge case -> order just below stock limit i.e. 10
		  @Test
		    void TestPlaceOrderBelowStockLimit() {
		        boolean result = orderService2.placeOrder(9);
		        assertTrue(result, "Order should be placed successfully");
		        assertEquals(1, orderService2.getStock(), "Stock should be reduced to 1 after ordering 9 out of 10");
		    }
		 // -------------------------------
		  //EXCEPTIONAL Cases
		  //place order beyond stock
		  //set stock -> -ve stock
		  //set discount -> (0-1),1.5->
		  //negative price
		  //negative quantity
		  //--------------------------------
		// Test case place order beyond stock
		    @Test
		    void TestPlaceOrderBeyondStock() {
		    	int stockOrder = 15;
		        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.placeOrder(stockOrder);
		        });
		        assertEquals("insufficient stock", e.getMessage());
		    }
	 
		    // Test set stock to negative value
		    @Test
		    void TestSetNegativeStock() {
		    	int stockOrder = -5;
		        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.setStock(stockOrder);
		        });
		        assertEquals("stock cannot be negative.", e.getMessage());
		    }
		    // Test set discount to an invalid value 
		    @Test
		    void TestSetInvalidDiscount() {
		    	double discount = 1.5;
		        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.setDiscount(discount);
		        });
		        assertEquals("discount shuld be between 0 and 1.", e.getMessage());
		    }
	 
		    
		  //Test where price is negative
		    @Test
			void TestNegativePrice() {
				int quantity = 4;
				double price = -100.00;
		        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.calPrice(price, quantity);
		        });
		        assertEquals("price cannot be negative.", e.getMessage());
		    }

			 //Test where negative is negative
		    @Test
			void TestNegativeQuantity() {
				int quantity = -4;
				double price = 100.00;
		        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.calPrice(price, quantity);
		        });
		        assertEquals("quantity cannot be negative.", e.getMessage());
		    }		  

//---------------------------------
//parameterized test -> @csvSource, @ValueSource, @MethodSource

//csv source - cal price -> 4 sets - valid
//price,q,expected


//test to check valid order placements with diff quantites
// (1,4,7,10)

//test to check for boundary conditions for placing orders
//values -> 11,13,15

//test to check invalid discounts
//value -> 0.5,1.5,2.0
//test to check invalid stock values
//values -> -5,-10,-15

//test for negtaive price and negative quantity
//csv source - cal price -> 4 sets
//price,q
//"100.0,-5"
//"100.0,5"
//"-50.0,-2"
//---------------------------------

		@ParameterizedTest
	    @CsvSource ({
	        "100, 7, 630",
	        "200, 3, 540",
	        "300, 4, 1080",
	        "800, 9, 6480"
	    })
	    void testCalculatePrice(double price, int quantity, double expected) {
	        assertEquals(expected, orderService2.calPrice(price, quantity));
	    }

		@ParameterizedTest
		@ValueSource(ints = { 1, 4, 7, 10 })
		void testValidOrderPlacement(int quantity) {
		    assertTrue(orderService2.placeOrder(quantity));
		}
		//Test to check for boundary conditions for placing order
		//values -> 11, 13, 15
		@ParameterizedTest
		@ValueSource(ints = { 11, 13, 15 })
		void testOrderBeyondStock(int quantity) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.placeOrder(quantity);
		    });
		}
		
		@ParameterizedTest
		@ValueSource(doubles = { -0.5, 1.5, 2.0 })
		void testInvalidDiscountValues(double discount) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.setDiscount(discount);
		    });
		    assertEquals("discount shuld be between 0 and 1.", e.getMessage());
		}
		
		@ParameterizedTest
		@ValueSource(ints = { -5, -10, -15 })
		void testInvalidStockValues(int stock) {
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
				orderService2.setStock(stock);
			});
		}

		
		@ParameterizedTest
		@CsvSource({
		    "100.0, -5",
		    "-100.0, 5",
		    "-50.0, -2"
		})
		void testNegativePriceAndQuantity(double price, int quantity) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.calPrice(price, quantity);
		    });
		    if (price < 0) {
		        assertEquals("price cannot be negative.", e.getMessage());
		    } else if (quantity < 0) {
		        assertEquals("quantity cannot be negative.", e.getMessage());
		    }
		}

 //methodsource -> getting values from method
 //static methods -> stream of values

//test for valid stock values
		static Stream<Arguments> validInputs() {
			return Stream.of(
					org.junit.jupiter.params.provider.Arguments.of(100.00, 2, 180.0),
					org.junit.jupiter.params.provider.Arguments.of(100.00, 3, 270.0),
					org.junit.jupiter.params.provider.Arguments.of(100.00, 4, 360.0)
					);
		}
		@ParameterizedTest
		@MethodSource("validInputs")
		public void testValidInputs(double price, int quantity, double expected) {
	        assertEquals(expected, orderService2.calPrice(price, quantity));
		}
		@ParameterizedTest
		@MethodSource("boundaryOrderValues")
		void testBoundaryOrderPlace(int number) {
		    assertThrows(IllegalArgumentException.class, () -> {
		    	orderService2.placeOrder(number);
		    });
		}
		@ParameterizedTest
		@MethodSource("invalidDiscountValues")
		void testInvalidDiscounts(double number) {
		    assertThrows(IllegalArgumentException.class, () -> {
		    	orderService2.setDiscount(number);
		    });
		}
		static Stream<Integer> boundaryOrderValues() {
		    return Stream.of(11, 13, 15);
		}
		static Stream<Double> invalidDiscountValues() {
		    return Stream.of(-0.5, 1.5, 2.0);
		}
		@ParameterizedTest
		@MethodSource("negativePriceAndQuantity")
		void testNegPriceNegQuan(double price, int quantity) {
		    assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.calPrice(price, quantity);
		    });
		}
		static Stream<Arguments> negativePriceAndQuantity() {
		    return Stream.of(
		        Arguments.of(100.0, -5),
		        Arguments.of(-50.0, -2)
		    );
		}
	 
}