<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="serine.blogging.publication.PostRecord"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.post.ApiCreateTextPart"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.post.WebBackPost"%>
<%
    WebBackPost back = (WebBackPost)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
    PostRecord post = back.getPostRecord();
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
<script>
var parts = <%=back.jParts()%>;
let initParts = () => {
    var div = document.getElementById('partlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < parts.count; n++) 
        addPart(parts.items[n], false);
}
function addPart (part, isnew) {
    switch (part.type) {
        case 1:
            addTextPart(part, isnew);
            return;
        case 2:
            return;
    }
}
function addTextPart (part, isnew) {
    /*---------------------------------------------------*/
    var top;
    var line;
    var column;
    var link;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.setAttribute('id', 'part' + part.partid);
    top.setAttribute('style', 'padding: 17px 15px; border: solid 1px #e9e9e9; border-radius: 4px; margin-bottom: 20px');
    if (isnew) top.style.opacity = 0;
    /*---------------------------------------------------*/
    line = document.createElement("div");
    line.setAttribute('style', 'font-size: 12px; font-weight: bold; color: #888; margin-bottom: 7px');
    line.innerHTML = 'Text paragraph';
    top.appendChild(line);
    /*---------------------------------------------------*/
    line = document.createElement("div");
    line.setAttribute('style', 'font-size: 14px; font-weight: normal; color: #333');
    line.innerHTML = part.text;
    top.appendChild(line);    
    /*---------------------------------------------------*/
    document.getElementById('partlist').appendChild(top);
    /*---------------------------------------------------*/
}
/*------------------------------------------------------------------*/
function showAddText () {
    document.getElementById('divaddpviewcandidate').style.height = '100%';
    document.getElementById('divaddpviewcandidate').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'divaddpviewcandidate');
    turnon.start();
}
function hideAddText () {
    var form = document.getElementById('formcreatetext');
    document.getElementById('divaddpviewcandidate').style.height = '0px';
    document.getElementById('divaddpviewcandidate').style.width = '0px';
    document.getElementById('divaddpviewcandidate').style.opacity = 0;
    form.reset();
}
/*------------------------------------------------------------------*/
function createTextPart () {
    var form = document.getElementById('formcreatetext');
    var formdata = new FormData(form);
    var req = new HttpRequest();
    var callback = (status, objresp) => {
        if (status === 0) {
            showNotice ('Could not connect to server', '#ff3333');
            return;
        }
        if (status !== 200) {
            showNotice ('Error server. Probably in maintenance', '#ff3333');
            return;
        }
        if (objresp.result !== 'OK') {
            showNotice (objresp.description, '#ff3333');
            return;
        }
        /*---------------------------------------------------*/
        form.reset();
        showNotice ('Paragraph added', '#229900');
        addPart(objresp.part, true);
        hideAddText();
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'part' + objresp.part.partid);
        turnon.start();
        /*---------------------------------------------------*/
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiCreateTextPart.POSTID%>',<%=post.postID()%>);
    req.addParam('<%=ApiCreateTextPart.TEXT%>', formdata.get('<%=ApiCreateTextPart.TEXT%>'));
    req.setURL('<%=back.getCreateTextPartURL()%>');
    try { 
        req.executepost(); 
    }
    catch(err) {
        alert (err.getMessage);
    }    
}
</script>
<title>Post page</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div id="divaddpviewcandidate" class="popupformmodal">
    <div id="boxaddpviewcandidate" class="formbox" style="width: 600px">
        <form id="formcreatetext">
            <textarea name="<%=ApiCreateTextPart.TEXT%>" style="height: 150px" value="" placeholder="Type the paragraph text here"></textarea>
            <div style="height: 10px"></div>
            <button class="greenwidththin" onclick="createTextPart(); return false;">Add paragraph</button>
            <button class="graywidththin" onclick="hideAddText(); return false;">Cancel</button>
        </form>
    </div>
</div>
<div class="content">
<h1>Post page!</h1>
<div style="margin-top: 50px; display: flex; flex-direction: row">
    <div style="width: 350px">
        <div style="font-size: 17px; font-weight: 600; color: #666;">
            <%=post.postTitle()%>
        </div>
        <div style="font-size: 15px; color: #666; margin-top: 20px">
            <%=post.postSumary()%>
        </div>
        <div style="margin-top: 30px; height: 150px; border-top: solid 1px #cccccc">
            <div style="margin-top: 20px; margin-bottom: 30px; font-size: 16px; font-weight: bold; #333">
                Add Part
            </div>
            <div style="float: left; width: 90px">
                <div style="text-align: center">
                    <a href="#" style="color: #0055ff; text-decoration: none" onclick="showAddText(); return false;" >
                    <img src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.TEXTICON%>" style="width: 60px; height: 60px" alt="Public view">
                    </a>
                </div>
                <div style="font-size: 11px; text-align: center; margin-top: 4px">
                    <a href="#" style="color: #0055ff; text-decoration: none" onclick="showAddText(); return false;" >Text Paragraph</a>
                </div>
            </div>
            <div style="clear: both"></div>                    
        </div>
    </div>
    <div style="width: 1px; background-color: #dddddd; margin-left: 30px"></div>
    <div style="margin-left: 30px; flex: 1">
        <div style="font-size: 13px; font-weight: bold; color: #444444; margin-bottom: 20px; border-bottom: solid 1px #cccccc; padding: 0 0 8px 0px;">Post Parts</div>
        <div id="partlist" style="width: 100%"></div>
    </div>
</div>






    
    
</div>
</body>
<script>initParts();</script>
</html>
