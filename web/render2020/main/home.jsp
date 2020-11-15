<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.main.WebBackHome"%>
<%@page import="arginine.WebFrontAlpha"%>
<% WebBackHome back = (WebBackHome)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<title>JSP Page</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="content">
    <h1>Hello World!</h1>
    <div style="height: 1000px"></div>
</div>
</body>
</html>
