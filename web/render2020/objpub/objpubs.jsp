<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.objectpub.WebBackObjectPubs"%>
<%
    WebBackObjectPubs back = (WebBackObjectPubs)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
    //PostRecord post = back.getPostRecord();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<title>JSP Page</title>
</head>
<body>
<%@include file="../main/header.jsp" %>


<div class="content">
<h2>Object Pubs!</h2>
</div>

</body>
</html>
