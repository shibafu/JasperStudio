package tsugaruinfo.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tsugaruinfo.dao.OrderDataAccessObject;
import tsugaruinfo.entity.Order;
import tsugaruinfo.entity.Product;
import tsugaruinfo.model.ProductModel;
import tsugaruinfo.report.*;

import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ProductController {

	@Autowired
	ResourceLoader resource;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		ProductReport pr = new ProductReport();
		modelMap.put("listProducts", pr.findALL());
		return "htmlsidereport";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(ModelMap modelMap, HttpServletResponse response) {
		//Mapに入れなおす
		List<Map<String, ?>> listOrders = new ArrayList<Map<String, ?>>();
		OrderDataAccessObject a = new OrderDataAccessObject();
		
		for(Order o: a.findByALL()) {
			Map<String, Object> m = new HashMap<String, Object>();
			
			m.put("Orderid", o.getOrderId());
			m.put("Productname", o.getProductId());
			m.put("Productprice", o.getProductName());
			m.put("Price", o.getPrice());
			m.put("Quantity", o.getQuantity());
			m.put("Amount", o.getAmount());
			
			m.put("OrderIdTitle", o.getOrderId());
			m.put("ProductNameTitle", o.getProductId());
			m.put("ProductPriceTitle", o.getProductName());
			m.put("PriceTitle", o.getPrice());
			m.put("QuantityTitle", o.getQuantity());
			m.put("AmountTitle", o.getAmount());
			Date date = new Date();
			m.put("date", date.getTime());
			m.put("client", "田中様");
			
			listOrders.add(m);
		}
		
		byte[] output  = OrderReporting(listOrders, response);
		
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "Order.pdf");
        response.setContentLength(output.length);
		
        OutputSreamWrite(response, output);
		
		return "showMessage";
	}
	
	private byte[] OrderReporting(List<Map<String, ?>> data, HttpServletResponse response) {
		List<Map<String, ?>> dataSource = data;
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
		
		
		InputStream input;
		try {
			input = new FileInputStream(resource.getResource("classpath:report/OrderReport.jrxml").getFile());
			
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			
			JasperPrint jasperPrint;
			
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);

			return 	JasperExportManager.exportReportToPdf(jasperPrint);
	        
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (JRException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	/**
	 * ダウンロードファイル書き込み
	 * @param response
	 * @param fileContent
	 */
	public void OutputSreamWrite(HttpServletResponse response, byte[] output) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(output);
            os.flush();
        } catch (IOException e) {
            e.getStackTrace();
        }
	}
}
