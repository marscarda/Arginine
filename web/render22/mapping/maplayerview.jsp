<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.mapping.WebBackMapView"%>
<%@page import="arginine.WebFrontStatic22"%>
<%@page import="arginine.WebFrontAlpha"%>
<%
    WebBackMapView back = (WebBackMapView)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSROOT%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSTAGANIMATE%>"></script>
<title>JSP Page</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <h1>Hello World!</h1>
</div>
</body>
</html>
