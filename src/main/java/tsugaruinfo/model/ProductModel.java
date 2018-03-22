package tsugaruinfo.model;

import java.util.*;
import tsugaruinfo.entity.*;

public class ProductModel {

	public List<Product> findALL(){
		List<Product> listProducts = new ArrayList<Product>();
		
		listProducts.add(new Product("p1", "name1", 5, 1000.00));
		listProducts.add(new Product("p2", "name2", 6, 2000.00));
		listProducts.add(new Product("p3", "name3", 7, 3000.00));
		listProducts.add(new Product("p4", "name4", 8, 4000.00));
		listProducts.add(new Product("p5", "name5", 9, 5000.00));
		
		return listProducts;
	}
}
