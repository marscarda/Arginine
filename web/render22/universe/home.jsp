<%@page import="arginine.universe.ApiCreateTemplate"%>
<%@page import="arginine.ApiAlpha"%>
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

<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSTAGANIMATE%>"></script><!-- comment -->
<script>
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
    <div id="layerlist" style="margin-top: 30px"></div>    
</div>
</body>
</html>
