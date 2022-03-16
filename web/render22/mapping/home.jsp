<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.mapping.ApiSetLayerPublic"%>
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
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSPOPUP%>">
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
let addLayer = (layer, isnew) => {
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
    if (isnew) top.style.opacity = 0;
    /* Layer name */ {
        line = document.createElement("div");
        line.style.fontSize = "15px";
        line.style.color = "#333";
        line.style.fontSize = "16px";
        line.style.fontWeight = 600;
        line.innerHTML = decodeURI(decodeURIComponent(layer.layername));
        top.appendChild(line);
    }
    /* Options */ {
        line = document.createElement("div");
        line.style.fontSize = "15px";
        line.style.color = "#333";
        line.style.fontSize = "16px";
        line.style.display = "flex";
        line.style.marginTop = "10px";
        line.style.flexDirection = "row";
        /* View */ {
            column = document.createElement("div");
            link = document.createElement('a');
            link.href = '<%=back.pageMapView()%>/' + layer.layerid;
            link.innerHTML = "View Map";
            column.appendChild(link);
            line.appendChild(column);
        }
        /* Copy ID */ {
            column = document.createElement("div");
            column.style.marginLeft = "20px";
            link = document.createElement('a');
            link.href = "#";
            link.innerHTML = "Copy Id";
            link.onclick = () => {
                navigator.clipboard.writeText(layer.layerid);
                showToast ("Layer ID coppied To clipboard (" + layer.layerid + ")", "#290");
                return false;
            }
            column.appendChild(link);
            line.appendChild(column);
        }
        top.appendChild(line);
    }
    if (isnew) 
        document.getElementById('layerlist').insertBefore(top, document.getElementById('layerlist').childNodes[0]);
    else document.getElementById('layerlist').appendChild(top);
}
let openSetPublic = () => {
    document.getElementById('divsetpublic').style.height = 'auto';
    document.getElementById('divsetpublic').style.marginTop = 0;
    setCurtain();
    var fadein = new ElementFadeIn();
    fadein.setElement('divsetpublic');
    fadein.start();
}
let closeSetPublic = () => {
    document.getElementById('divsetpublic').style.height = 0;
    document.getElementById('divsetpublic').style.marginTop = '-1000px';
    document.getElementById('divsetpublic').style.opacity = 0;
    hideCurtain();
    document.getElementById('formsetpublic').reset();
}
let setLayerPublic = () => {
    var form = document.getElementById('formsetpublic');
    var formdata = new FormData (form);
    var req = new HttpRequest();
    var callback = (status, objresp) => {
        if (status === 0) {
            showToast ('Could not connect to server', '#ff3333');
            return;
        }
        if (status !== 200) {
            showToast ('Error server. Probably in maintenance', '#ff3333');
            return;
        }
        if (objresp.result !== 'OK') {
            showToast (objresp.description, '#ff3333');
            return;
        }
        /* Success */
        addLayer(objresp.layer, true);
        showToast ("Layer is now public", "#290");
        closeSetPublic();
        var turnon = new ElementFadeIn();
        turnon.setElement('layer' + objresp.layer.layerid);
        turnon.start();
    };
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiSetLayerPublic.LAYERID%>', formdata.get('<%=ApiSetLayerPublic.LAYERID%>'));
    req.addParam('<%=ApiSetLayerPublic.LAYERNAME%>', formdata.get('<%=ApiSetLayerPublic.LAYERNAME%>'));    
    req.addParam('<%=ApiSetLayerPublic.DESCRIPTION%>', formdata.get('<%=ApiSetLayerPublic.DESCRIPTION%>'));
    req.setURL('<%=back.apiSetPublic()%>');
    try { req.executepost(); }
    catch(err) { alert (err.getMessage); }
}
</script>
<title>Mapping</title>
</head>
<body>


<div id="divsetpublic" class="formlite" style="width: 460px; height: auto; margin-left: -230px; margin-top: -1000px; opacity: 0">
    <div class="formtitle">
        Set Layer Public
    </div>
    <form id="formsetpublic">
        <div class="fieldname">Layer ID</div>
        <input type="text" placeholder="The ID of the layer." name="<%=ApiSetLayerPublic.LAYERID%>" />
        <div class="fieldname">Name</div>
        <input type="text" placeholder="if Blank remains as it is." name="<%=ApiSetLayerPublic.LAYERNAME%>" />
        <div class="fieldname">Description</div>
        <textarea placeholder="A description." name="<%=ApiSetLayerPublic.DESCRIPTION%>"></textarea>
    </form>
    <div style="margin-top: 30px">
        <button class="ok" onclick="setLayerPublic(); return false">Set Public</button>
        <button class="cancel" onclick="closeSetPublic(); return false">Cancel</button>
    </div>    
</div>



<%@include file="../main/header.jsp" %>
<div class="content">
    <div class="listheadertop"> 
        <div class="listheadertit">Map Layers</div>
        <div class="listheaderadd"><a href="#" onclick="openSetPublic(); return false;">Add Layer</a></div>
    </div>
    <div id="layerlist" style="margin-top: 30px"></div>
</div>
</body>
<script>
    fillLayers();
</script>
</html>
