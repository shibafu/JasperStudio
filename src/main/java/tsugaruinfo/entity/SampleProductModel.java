package tsugaruinfo.entity;

import lombok.Data;

@Data
public class SampleProductModel {

	private String Product_name;
	private int Price;
	
	public SampleProductModel(String name, int Price){
		this.setProduct_name(name);
		this.setPrice(Price);
	}
}
