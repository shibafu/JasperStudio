package tsugaruinfo.entity;

import lombok.Data;

@Data
public class Product {
	
	private String id;
	private String name;
	private int quantity;
	private double price;

	public Product(String id, String name, int quantity, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
}
