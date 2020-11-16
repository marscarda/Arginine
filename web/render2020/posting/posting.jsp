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
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSTAGANIMATE%>"></script>
<script>
var posts = <%=back.jPosts()%>
let initPosts = () => {
    var div = document.getElementById('postlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < posts.count; n++) 
        addPost(posts.items[n], false);
}
function addPost (post, isnew) {
    var top;
    var line;
    var column;
    var link;
    /*---------------------------------------------------*/    
    top = document.createElement("div");
    top.setAttribute('id', 'post' + post.postid);
    top.setAttribute('style', 'padding: 15px 0px; border-bottom: solid 1px #dddddd');
    if (isnew) top.style.opacity = 0;
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    column.innerHTML = post.title;
    line.appendChild(column);
    /*---------------------------------------------------*/
    top.appendChild(line);
    if (isnew)
        document.getElementById('postlist').insertBefore(top, document.getElementById('postlist').childNodes[0]);
    else document.getElementById('postlist').appendChild(top);
    /*---------------------------------------------------*/
    
}
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
        /*---------------------------------------------------*/
        form.reset();
        addPost(objresp.post, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'post' + objresp.post.postid);
        turnon.start();
        /*---------------------------------------------------*/
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




    <div id="postlist" style="width: 100%"></div>




</div>
</body>
<script>initPosts();</script>
</html>
