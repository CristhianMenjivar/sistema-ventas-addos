<%-- 
    Document   : Reporte2
    Created on : 09-25-2019, 06:38:07 PM
    Author     : HP
--%>

<%@page import="devs.com.sistema.ventas.coneccion.BaseDatosMYSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="devs.com.sistema.ventas.modelos.Cliente"%>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page import="org.apache.jasper.JasperException" %>

<%
Connection conexion;

BaseDatosMYSQL base = new BaseDatosMYSQL();
conexion = base.getConeccion();

File reportFile = new File(application.getRealPath("Reportes/ReporteBoleta.jasper"));

Map<String, Object> parameters = new HashMap();
String valor = request.getParameter("idBoleta");
parameters.put("idboleta",new String(valor));

byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion);
response.setContentType("application/pdf");
response.setContentLength(bytes.length); 
ServletOutputStream outputstream = response.getOutputStream();
outputstream.write(bytes, 0, bytes.length);
outputstream.flush();
outputstream.close(); %>
