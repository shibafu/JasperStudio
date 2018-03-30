package tsugaruinfo.entity;

import lombok.Data;

@Data
public class Order {

	private String item_name;
	private int num;
	private int price;
	private int amount;
	private String note;
	
	public Order() {
		super();
	}
	
	public Order(String item_name, int num, int price, int amount, String note) {
		super();
		this.item_name = item_name;
		this.num = num;
		this.price = price;
		this.amount = amount;
		this.note = note;
	}
}
