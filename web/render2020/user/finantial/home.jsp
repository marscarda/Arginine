<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackUserFinantial"%>
<%
    WebBackUserFinantial back = (WebBackUserFinantial)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
<h1>Hello World!</h1>
</body>
</html>
