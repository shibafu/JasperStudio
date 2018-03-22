<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page contentType="application/pdf" %>
<%@ page trimDirectiveWhtietespaes="true" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	try{
		List<Map<String, ?>> dataSource = (List<Map<String, ?>>)request
				.getAttribute("listProducts");
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
		String jrxmFile = session.getServletContext().getRealPath("/report/ProductReport.jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		
		JaperReport jasperReport = JasperCompileManager.compileReport(input);
		
		JasperPrint jasperPring = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	} catch (Exception e){
		e.printStackTrace();
	}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>