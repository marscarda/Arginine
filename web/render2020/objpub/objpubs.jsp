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
        showNotice ('Access Created', '#229900');
        closeCreateAccessForm();
        addAccess (objresp.accessrecord, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'accessrec' + objresp.accessrecord.recordid);
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


</script>


<title>JSP Page</title>
</head>
<body>
<%@include file="../main/header.jsp" %>



<div class="content">
<h2>Object Pubs!</h2>



<form id="formcreatepub">
<label for "<%=ApiCreateObjectPub.ACCESSNAME%>">Access name</label>
<input type="text" name="<%=ApiCreateObjectPub.ACCESSNAME%>" placeholder="Complete this" />
<label for "<%=ApiCreateObjectPub.TITLE%>">Title</label>
<input type="text" name="<%=ApiCreateObjectPub.TITLE%>" placeholder="Complete this" />
<textarea name="<%=ApiCreateObjectPub.TEXT%>" style="height: 150px" value="" placeholder="Type the paragraph text here"></textarea>
<button class="greenwidththin" onclick="createPublication(); return false;">Create</button>
<button class="graywidththin" onclick="closeCreateAccessForm(); return false;">Cancel</button>
</form>









</div>

</body>
</html>
