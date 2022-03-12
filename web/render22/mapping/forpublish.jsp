<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.mapping.ApiSetLayerPublic"%>
<%@page import="arginine.WebFrontStatic22"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.mapping.WebBackForPublishLayer"%>
<%
    WebBackForPublishLayer back = (WebBackForPublishLayer)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSPOPUP%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSTAGANIMATE%>"></script>
<script>
var layers = <%=back.jLayers()%>;
var currentlayer = 0;
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
        link.href = '<%=back.pageMapView()%>/' + layer.layerid;
        link.target = "_blank";
        link.innerHTML = decodeURI(decodeURIComponent(layer.layername));
        line.appendChild(link);
        top.appendChild(line);
    }
    /* Options */ {
        line = document.createElement("div");
        line.style.fontSize = "15px";
        line.style.color = "#333";
        line.style.fontSize = "16px";
        line.style.display = 'flex';
        line.style.flexDirection = 'row';
        line.style.marginTop = "15px";
        /* Accept */ {
            column = document.createElement('div');
            column.style.marginRight = '25px';
            link = document.createElement("a");
            link.href = '#';
            link.onclick = () => {
                openAccept(layer.layerid, layer.layername, layer.description);
                return false;
            }
            link.innerHTML = "Accept";
            column.appendChild(link);
            line.appendChild(column);
        }
        /* Reject */ {
            column = document.createElement('div');
            column.style.marginRight = '25px';
            link = document.createElement("a");
            link.href = '#';
            link.onclick = () => {
                alert("Reject");
                return false;
            }
            link.innerHTML = "Reject";
            column.appendChild(link);
            line.appendChild(column);
        }
        top.appendChild(line);
    }
    document.getElementById('layerlist').appendChild(top);
}
let openAccept = (layerid, name, description) => {
    currentlayer = layerid;
    document.getElementById('<%=ApiSetLayerPublic.LAYERNAME%>').value = name;
    document.getElementById('<%=ApiSetLayerPublic.DESCRIPTION%>').value = description;
    document.getElementById('divsetpublic').style.height = 'auto';
    setCurtain();
    var fadein = new ElementFadeIn();
    fadein.setElement('divsetpublic');
    fadein.start();
}
let closeAccept = () => {
    document.getElementById('divsetpublic').style.height = '0px';
    hideCurtain();
    document.getElementById('divsetpublic').style.opacity = 0;
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
        showToast ("Layer is now public", "#290");
        closeAccept();
    };
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiSetLayerPublic.LAYERID%>', currentlayer);
    req.addParam('<%=ApiSetLayerPublic.LAYERNAME%>', formdata.get('<%=ApiSetLayerPublic.LAYERNAME%>'));    
    req.addParam('<%=ApiSetLayerPublic.DESCRIPTION%>', formdata.get('<%=ApiSetLayerPublic.DESCRIPTION%>'));
    req.setURL('<%=back.apiSetLayerPublic()%>');
    try { req.executepost(); }
    catch(err) { alert (err.getMessage); }    
}
</script>
<title>For Publish Candidate Layers</title>
</head>
<body>
<div id="divsetpublic" class="formlite" style="width: 460px; height: 0px; margin-left: -230px; opacity: 0">
    <div class="formtitle">
        Set Map Layer public
    </div>
    <form id="formsetpublic">
        <div class="fieldname">Layer Name</div>
        <textarea id="<%=ApiSetLayerPublic.LAYERNAME%>" name="<%=ApiSetLayerPublic.LAYERNAME%>" style="" placeholder="The name of the layer"></textarea>
        <div class="fieldname">Layer Description</div>
        <textarea id="<%=ApiSetLayerPublic.DESCRIPTION%>" name="<%=ApiSetLayerPublic.DESCRIPTION%>" style="" placeholder="A description for the public"></textarea>
    </form>
    <div style="margin-top: 30px">
        <button class="ok" onclick="setLayerPublic(); return false">Create</button>
        <button class="cancel" onclick="closeAccept(); return false">Cancel</button>
    </div>
</div>
<%@include file="../main/header.jsp" %>
<div class="content">
    <div style="width: 100%; margin-top: 5px; padding: 5px 0px;">
        <a href="<%=back.pageMapping()%>">Map Layers</a>
    </div>
    <div style="width: 100%; margin-top: 5px; border-bottom: solid 3px #baa; font-size: 25px; color: #444"> 
        Publish Candidates Map Layers
    </div>
    <div id="layerlist" style="margin-top: 30px"></div>
</div>
</body>
<script>
    fillLayers();
</script>
</html>
