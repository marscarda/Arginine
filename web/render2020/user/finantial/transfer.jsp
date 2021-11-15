<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackTransfer"%>
<%
    WebBackTransfer back = (WebBackTransfer)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    //User user = back.getUser();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSTAGANIMATE%>"></script>
<title>Transfers</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div class="content">
    <h1>Transfers!</h1>
</div>

</body>
</html>
