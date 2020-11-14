<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.WebBackAlpha"%>
<% WebBackAlpha back = (WebBackAlpha)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<title>Log Out Fail</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="content">
<h2>Algo salio mal. Intenta en unos momentos</h2>
</div>
</body>
</html>
