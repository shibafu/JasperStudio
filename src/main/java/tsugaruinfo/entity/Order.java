package tsugaruinfo.entity;

import lombok.Data;

@Data
public class Order {

	private String orderId;
	private int productId;
	private String productName;
	private int quantity;
	private long price;
	private long amount;
	
	public Order() {
		super();
	}
	
	public Order(String orderId, int productId, String productName, int quantity, long price, long amount) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
}
