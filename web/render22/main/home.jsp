<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic22"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.main.WebBackHome"%>
<%
    WebBackHome back = (WebBackHome)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSROOT%>">



<title>Radar Admin</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="content">
<div style="height: 1000px"></div>
</div>
</body>
</html>
</body>
</html>
