<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.objectpub.ApiCreateObjectPub"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.objectpub.WebBackObjectPubs"%>
<%
    WebBackObjectPubs back = (WebBackObjectPubs)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
    //PostRecord post = back.getPostRecord();
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
var objectpubs = <%=back.jObjectPubs()%>;
function createPublication () {
    var form = document.getElementById('formcreatepub');
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
        closeCreateObjectPubForm();
        addPub(objresp.objectpub, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'objpub' + objresp.objectpub.objpubid);
        turnon.start();
    };
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiCreateObjectPub.ACCESSNAME%>', formdata.get('<%=ApiCreateObjectPub.ACCESSNAME%>'));
    req.addParam('<%=ApiCreateObjectPub.TITLE%>', formdata.get('<%=ApiCreateObjectPub.TITLE%>'));
    req.addParam('<%=ApiCreateObjectPub.TEXT%>', formdata.get('<%=ApiCreateObjectPub.TEXT%>'));
    req.setURL('<%=back.getCreateObjectPubURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert (err.getMessage);
    }
}
<%@include file="./objpubspage.js" %>
</script>


<title>JSP Page</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div id="divcreateobjpub" class="popupformmodal">
    <div id="boxnewobjpub" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">New object publication</div>
        <form id="formcreatepub">
        <label for "<%=ApiCreateObjectPub.ACCESSNAME%>">Access name</label>
        <input type="text" name="<%=ApiCreateObjectPub.ACCESSNAME%>" placeholder="Complete this" />
        <label for "<%=ApiCreateObjectPub.TITLE%>">Title</label>
        <input type="text" name="<%=ApiCreateObjectPub.TITLE%>" placeholder="Complete this" />
        <textarea name="<%=ApiCreateObjectPub.TEXT%>" style="height: 150px" value="" placeholder="Type the paragraph text here"></textarea>
        <button class="greenwidththin" onclick="createPublication(); return false;">Create</button>
        <button class="graywidththin" onclick="closeCreateObjectPubForm(); return false;">Cancel</button>
        </form>
    </div>
</div>
<div class="content">
<h2>Object Publications</h2>

<div style="font-size: 16px; color: #444444; margin-top: 12px; font-weight: bold; border-top: solid 1px #dddddd; padding: 15px 0px">
    <div style="float: left">Publications</div>    
    <div style="float: right">
        <a href="#" style="color: inherit; text-decoration: none" onclick="openCreateObjectPubForm(); return false">
            <img src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.ADDCOMMAND%>" 
                style="width: 20px; height: 20px" alt="Create Publication" title="Create New Publication" />
        </a>
    </div>
    <div id="objpublist" style="width: 100%; margin-top: 30px; font-weight: normal"></div>
</div>

</div>
</body>
<script>initPubs();</script>
</html>
