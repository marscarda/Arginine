<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="serine.blogging.publication.PostRecord"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.main.WebBackHome"%>
<%@page import="arginine.WebFrontAlpha"%>
<%
    WebBackHome back = (WebBackHome)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
    PostRecord[] posts = back.getPosts();
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
<%@include file="header.jsp" %>
<div class="content">
    <% for (PostRecord post : posts) { %>

    
    
    <div style="padding: 0px 0px 35px 0px;">
        
        <div style="font-size: 35px; font-weight: bold; color: #444444">
            <%=post.postTitle()%>
        </div>
        <div style="font-size: 16px; margin-top: 10px; color: #777777">
            <%=post.postSumary()%>
        </div>
        
        
    </div>

    <% } %>
    <div style="height: 1000px"></div>

</div>
</body>
</html>
