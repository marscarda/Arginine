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
<script src="<%=back.mapAppURL()%>"></script>
<script>
var layer = <%=back.jMapLayer()%>;
</script>
<title>JSP Page</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <div id="mapview" style="height: 450px"></div>
</div>
</body>
<script>
    MapApp.parentdivid = "mapview";
    MapApp.create();
    MapApp.layerid = layer.layerid;
    MapView.getMapDrawing();
</script>

</html>
