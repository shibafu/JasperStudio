package tsugaruinfo.report;

import tsugaruinfo.entity.*;
import java.util.*;
import tsugaruinfo.model.*;

public class ProductReport {

	public List<Map<String, ?>> findALL(){
		List<Map<String, ?>> listProducts = new ArrayList<Map<String, ?>>();
		ProductModel productModel = new ProductModel();
		
		for(Product p: productModel.findALL()) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", p.getId());
			m.put("name", p.getName());
			m.put("price", p.getPrice());
			m.put("quantity", p.getQuantity());
			listProducts.add(m);
		}
		
		return listProducts;
	}
}
