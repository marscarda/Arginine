<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic22"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.mapping.WebBackMapping"%>
<%
    WebBackMapping back = (WebBackMapping)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSROOT%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSTAGANIMATE%>"></script><!-- comment -->
<script>
var layers = <%=back.jLayers()%>;
let fillLayers = () => {
    var div = document.getElementById('layerlist');
    for (n = 0; n < layers.count; n++) 
        addLayer(layers.items[n], false);
    var clear = document.createElement('div');
    clear.style.clear = "both";
    div.appendChild(clear);
}
let addLayer = (layer) => {
    var top;
    var line;
    var column;
    var link;
    top = document.createElement("div");
    top.id = 'layer' + layer.layerid;
    top.style.padding = '12px 20px';
    top.style.border = 'solid 2px #6af';
    top.style.borderRadius = '5px';
    top.style.marginBottom = "12px";
    top.style.width = "480px";
    top.style.marginRight = "30px";
    top.style.float = "left";    
    
    /* Layer name */ {
        line = document.createElement("div");
        line.style.fontSize = "15px";
        line.style.color = "#333";
        line.style.fontSize = "16px";
        line.style.fontWeight = 600;
        link = document.createElement('a');
        link.href = '<%--=back.pageRecords()--%>/' + layer.layerid;
        link.innerHTML = decodeURI(decodeURIComponent(layer.layername));
        line.appendChild(link);
        top.appendChild(line);
    }    
    document.getElementById('layerlist').appendChild(top);
}
</script>
<title>Mapping</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <div class="submenu">
        <a href="<%=back.pageForPublish()%>">For Publish Candidates</a>
    </div>
    <div class="pagetit"> 
        Map Layers
    </div>
    <div id="layerlist"></div>
</div>
</body>
<script>
    fillLayers();
</script>
</html>
