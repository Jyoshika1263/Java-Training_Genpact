package JUnit;

public class OrderService2 {
	private int stock=10;
	private double discount=0.10; //10%
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		if(stock<0) {
			throw new IllegalArgumentException("stock cannot be negative.");
		}
		this.stock=stock;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		if(discount<0 || discount>1) {
			throw new IllegalArgumentException("discount shuld be between 0 and 1.");
		}
		this.discount = discount;
	}
	//calculate order price
	public double calPrice(double price,int quantity) {
		if(price<0) {
			throw new IllegalArgumentException("price cannot be negative.");
		}
		if(quantity<0) {
			throw new IllegalArgumentException("quantity cannot be negative.");
		}
		return price*quantity*(1-discount);
	}
	public boolean placeOrder(int quantity) {
		if(quantity<0) {
			throw new IllegalArgumentException("quantity cannot be negative.");
		}
		if(quantity>stock) {
			throw new IllegalArgumentException("insufficient stock");
		}
		stock -= quantity;
		return true;
	}

}
