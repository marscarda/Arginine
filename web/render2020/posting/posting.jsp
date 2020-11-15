<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.post.WebBackPosting"%>
<%@page import="arginine.WebFrontStatic"%>
<%
    WebBackPosting back = (WebBackPosting)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<title>Post create and go</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <h1>Posting page!</h1>
</div>
</body>
</html>
