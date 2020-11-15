<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.post.ApiCreatePost"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.post.WebBackPosting"%>
<%@page import="arginine.WebFrontStatic"%>
<%
    WebBackPosting back = (WebBackPosting)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSTAGANIMATE%>"></script>
<script>
function createPost () {
    var form = document.getElementById('formcreatepost');
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
        form.reset();
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiCreatePost.TITLE%>', formdata.get('<%=ApiCreatePost.TITLE%>'));
    req.setURL('<%=back.getCreatePostURL()%>');
    try { 
        req.executepost(); 
    }
    catch(err) {
        alert (err.getMessage);
    }    
}
</script>
    

<title>Post create and go</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <h1>Posting page!</h1>







    <div style="width: 300px">
        <form id="formcreatepost">
            <input type="text" name="<%=ApiCreatePost.TITLE%>" value="" placeholder="Title for your post" />
        <div style="height: 10px"></div>
        <button class="greenwidththin" onclick="createPost(); return false;">Create Post</button>
        </form>
    </div>









</div>
</body>
</html>
