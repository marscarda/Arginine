<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.universe.ApiCreateTemplate"%>
<%@page import="arginine.ApiAlpha"%>
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

<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSTAGANIMATE%>"></script><!-- comment -->
<script>
var universes = <%=back.jUniverses()%>;
let fillUniverses = () => {
    var div = document.getElementById('universelist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < universes.count; n++) 
        addUniverse(universes.items[n], false);
    var clear = document.createElement("div");
    clear.style.clear = "both";
    div.appendChild(clear);    
}
let addUniverse = (universe, isnew)  => {
    /*---------------------------------------------------*/
    var top;
    var line;
    var column;
    var link;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.id = "universe" + universe.universeid;
    top.style.border = "solid 4px #ce8";
    if (isnew) top.style.opacity = 0;
    top.style.borderRadius = "7px";
    top.style.width = "450px";
    top.style.float = "left";
    top.style.marginRight = "30px";
    top.style.marginTop = "30px";
    top.style.padding = "25px 20px";
    /*---------------------------------------------------*/
    /* Universe Name */ {
        line = document.createElement("div");
        line.style.display = "flex";
        line.style.flexDirection = "row";
        line.style.fontSize = "15px";
        line.style.fontWeight = "600";
        line.innerHTML = decodeURI(decodeURIComponent(universe.universe_name));
        top.appendChild(line);
    }
    /*---------------------------------------------------*/
    /* Description title */ {
        line = document.createElement("div");
        line.style.fontSize = "12px";
        line.style.fontWeight = "600";
        line.style.color = "#666";
        line.style.marginTop = "10px";
        line.innerHTML = "Description";
        top.appendChild(line);
    }
    /*---------------------------------------------------*/
    /* Description Body */ {
        line = document.createElement("div");
        line.style.fontSize = "12px";
        line.style.fontWeight = "600";
        line.style.color = "#666";
        line.style.marginTop = "4px";
        line.style.height = "40px";
        line.style.overflowY = decodeURI(decodeURIComponent(universe.universe_description));
        line.innerHTML = "";
        top.appendChild(line);
    }
    /*---------------------------------------------------*/
    /* Options */ {
        line = document.createElement("div");
        line.style.fontSize = "15px";
        line.style.color = "#666";
        line.style.marginTop = "4px";
        column = document.createElement("div");
        link = document.createElement("a");
        link.href = '<%--=back.universePageURL()--%>/' + universe.universeid;
        link.style.color = "#05f";
        link.innerHTML = "Open universe";
        column.appendChild(link);
        line.appendChild(column);
        top.appendChild(line);
    }
    /*---------------------------------------------------*/
    if (isnew) document.getElementById('universelist').insertBefore(top, document.getElementById('universelist').childNodes[0]);
    else document.getElementById('universelist').appendChild(top);
    /*---------------------------------------------------*/    
}
let openAddTemplate = () => {
    document.getElementById('divaddtemplate').style.height = 'auto';
    document.getElementById('divaddtemplate').style.marginTop = 0;
    setCurtain();
    var fadein = new ElementFadeIn();
    fadein.setElement('divaddtemplate');
    fadein.start();
}
let closeAddTemplate = () => {
    document.getElementById('divaddtemplate').style.height = 0;
    document.getElementById('divaddtemplate').style.marginTop = '-1000px';
    document.getElementById('divaddtemplate').style.opacity = 0;
    hideCurtain();
    document.getElementById('formaddtemplate').reset();
}
let addTemplate = () => {
    var form = document.getElementById('formaddtemplate');
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
        showToast ("Universe Template started to copy.", "#290");
        closeAddTemplate();
    };
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiCreateTemplate.UNIVERSEID%>', formdata.get('<%=ApiCreateTemplate.UNIVERSEID%>'));
    req.addParam('<%=ApiCreateTemplate.UNIVERSENAME%>', formdata.get('<%=ApiCreateTemplate.UNIVERSENAME%>'));    
    req.addParam('<%=ApiCreateTemplate.DESCRIPTION%>', formdata.get('<%=ApiCreateTemplate.DESCRIPTION%>'));
    req.setURL('<%=back.apiCreateTemplate()%>');
    try { req.executepost(); }
    catch(err) { alert (err.getMessage); }
}
</script>
<title>Universe templates</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div id="divaddtemplate" class="formlite" style="width: 460px; height: auto; margin-left: -230px; margin-top: -1000px; opacity: 0">
    <div class="formtitle">
        Add Template
    </div>
    <form id="formaddtemplate">
        <div class="fieldname">Universe ID</div>
        <input type="text" placeholder="The ID of the Universe." name="<%=ApiCreateTemplate.UNIVERSEID%>" />
        <div class="fieldname">Name</div>
        <input type="text" placeholder="if Blank remains as it is." name="<%=ApiCreateTemplate.UNIVERSENAME%>" />
        <div class="fieldname">Description</div>
        <textarea placeholder="A description. if Blank remains as it is" name="<%=ApiCreateTemplate.DESCRIPTION%>"></textarea>
    </form>
    <div style="margin-top: 30px">
        <button class="ok" onclick="addTemplate(); return false">Add As Template</button>
        <button class="cancel" onclick="closeAddTemplate(); return false">Cancel</button>
    </div>    
</div>
<div class="content">
    <div class="listheadertop"> 
        <div class="listheadertit">Universe Templates</div>
        <div class="listheaderadd"><a href="#" onclick="openAddTemplate(); return false;">Add Template</a></div>
    </div>
    <div id="universelist" style="width: 100%; font-weight: normal">
    </div> 
</div>
</body>
<script>fillUniverses();</script>
</html>
