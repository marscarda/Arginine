<%@page import="serine.blogging.publication.PostRecord"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.post.ApiCreateTextPart"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
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
</div>
</body>
</html>
