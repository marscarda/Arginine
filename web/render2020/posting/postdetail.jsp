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
    top.setAttribute('style', 'padding: 15px 0px; border-bottom: solid 1px #dddddd');
    if (isnew) top.style.opacity = 0;
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    column.innerHTML = part.text;
    line.appendChild(column);
    /*---------------------------------------------------*/
    top.appendChild(line);
    document.getElementById('partlist').appendChild(top);
    /*
    if (isnew)
        document.getElementById('partlist').insertBefore(top, document.getElementById('partlist').childNodes[0]);
    else document.getElementById('partlist').appendChild(top);
    */
    /*---------------------------------------------------*/
}
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
<div class="content">
<h1>Post page!</h1>
        <div style="width: 300px">
            <form id="formcreatetext">
                <textarea name="<%=ApiCreateTextPart.TEXT%>" value="" placeholder="Type the paragraph text here"></textarea>
            <div style="height: 10px"></div>
            <button class="greenwidththin" onclick="createTextPart(); return false;">Add paragraph</button>
            </form>
        </div>

    <div style="font-size: 13px; font-weight: bold; color: #444444; margin-bottom: 10px; border-bottom: solid 1px #cccccc; padding: 0 0 8px 0px;">Posts</div>
    <div id="partlist" style="width: 100%"></div>
</div>
</body>
<script>initParts();</script>
</html>
