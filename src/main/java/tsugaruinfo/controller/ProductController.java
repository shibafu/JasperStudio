package tsugaruinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import tsugaruinfo.report.*;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		ProductReport pr = new ProductReport();
		modelMap.put("listProducts", pr.findALL());
		return "index";
	}
}
