package tsugaruinfo.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
import tsugaruinfo.dao.SampleProductDao;
import tsugaruinfo.entity.Order;
import tsugaruinfo.entity.Product;
import tsugaruinfo.entity.SampleProductModel;
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
		
		OrderDataAccessObject a = new OrderDataAccessObject();
		//データ作成
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		//ヘッダーデータ作成
		params.put("sum_amount", (double)sumAmount(a.findByALL()));
		params.put("com_name", "鈴木証券株式会社");
		params.put("now", Calendar.getInstance().getTime().toString());
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DATE, 31);
		
		params.put("to_date", cal.getTime().toString());
		
		//データを検索し帳票を出力
		byte[] output  = OrderReporting(params, a.findByALL());
		
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "Order.pdf");
        response.setContentLength(output.length);
		//データをダウンロード
        OutputSreamWrite(response, output);
		
		return null;
	}
	
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String sample( HttpServletResponse response) {
		
		/**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼　データ作成部　▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼**/
		//ヘッダーデータ作成
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("Client_name", "山本証券");
		params.put("Date_today", "平成30年5月1日");
		
		//フィールドデータ作成
		SampleProductDao dao = new SampleProductDao();
		List<SampleProductModel> fields = dao.findByAll();
		
		/**▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲　データ作成部　▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲**/
		/**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼　帳票出力部　▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼**/
		//データを検索し帳票を出力
		byte[] output  = OrderReporting2(params, fields);
		/**▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲　データ作成部　▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲**/
		
		/**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼　データ作成データダウンロード部 ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼**/
		response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "sample.pdf");
        response.setContentLength(output.length);
		
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(output);
            os.flush();
            
            os.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
		/**▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲　データ作成部　▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲**/
        
        
		return null;
	}
	
	/**
	 * ジャスパーレポートコンパイル。バイナリファイルを返却する。
	 * @param data
	 * @param response
	 * @return
	 */
	private byte[] OrderReporting(HashMap<String, Object> param, List<Order> data) {
		InputStream input;
		try {
			//帳票ファイルを取得
			input = new FileInputStream(resource.getResource("classpath:report/OrderReport.jrxml").getFile());
			//リストをフィールドのデータソースに
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			//帳票をコンパイル
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			
			JasperPrint jasperPrint;
			//パラメーターとフィールドデータを注入
			jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
			//帳票をByte形式で出力
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
	 * ジャスパーレポートコンパイル。バイナリファイルを返却する。
	 * @param data
	 * @param response
	 * @return
	 */
	private byte[] OrderReporting2(HashMap<String, Object> param, List<SampleProductModel> data) {
		InputStream input;
		try {
			//帳票ファイルを取得
			input = new FileInputStream(resource.getResource("classpath:report/Blank_A4.jrxml").getFile());
			//リストをフィールドのデータソースに
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
			//帳票をコンパイル
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			
			JasperPrint jasperPrint;
			//パラメーターとフィールドデータを注入
			jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
			//帳票をByte形式で出力
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
            
            os.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
	}
	
	/**
	 * 金額の合計を返す。
	 * @param response
	 * @param fileContent
	 */
	public int sumAmount(List<Order> list) {
        int sum_amount = 0;
        for(Order o : list) {
        	sum_amount = sum_amount + o.getAmount();
        }
        
        return sum_amount;
	}
}
