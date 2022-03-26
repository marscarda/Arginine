<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic22"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.universe.WebBackUniverses"%>
<%
    WebBackUniverses back = (WebBackUniverses)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSPOPUP%>">
<title>Universe templates</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <div class="listheadertop"> 
        <div class="listheadertit">Universe Templates</div>
        <div class="listheaderadd"><a href="#" onclick="openNewTemplate(); return false;">Add Template</a></div>
    </div>
    <div id="layerlist" style="margin-top: 30px"></div>    
</div>
</body>
</html>
