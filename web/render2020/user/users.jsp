<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.user.ApiGetUserList"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.user.WebBackUsers"%>
<%@page import="arginine.user.WebFrontUsers"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="methionine.auth.User"%>
<%@page import="arginine.WebFrontAlpha"%>
<%
    WebBackUsers back = (WebBackUsers)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    User[] users = back.getUsers();
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
var users = {};
function fetchUserList () {
    var form = document.getElementById('formsearchusers');
    var formdata = new FormData (form);
    var req = new HttpRequest();
    var callback = (status, objresp) => {
        if (status === 0) {
            showNotice('Could not connect to server', '#ff3333');
            return;
        }
        if (status !== 200) {
            showNotice('Error server. Probably in maintenance', '#ff3333');
            return;
        }
        if (objresp.result !== 'OK') {
            showNotice(objresp.description, '#ff3333');
            return;
        }
        console.log(objresp);
        users = objresp.users;
        fillUsers();
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiGetUserList.STARTAT%>', formdata.get('<%=ApiGetUserList.STARTAT%>'));
    req.setURL('<%=back.userListURL()%>');
    try { 
        req.executepost(); 
    }
    catch(err) {
        alert (err.getMessage);
    }
}
function fillUsers () {
    var div = document.getElementById('userslist');
    div.style.opacity = 0;
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < users.count; n++)
        addUser(users.items[n]);
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'userslist');
    turnon.start();
}
function addUser (user) {
    /*---------------------------------------------------*/
    var top;
    var line;
    var column;
    var link;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.setAttribute('id', 'user' + user.userid);
    top.setAttribute('style', 'padding: 15px 0px; border-bottom: solid 1px #dddddd');
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    column.innerHTML = user.username;
    line.appendChild(column);
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    column.innerHTML = user.userid;
    line.appendChild(column);
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    column.innerHTML = user.isadmin;
    line.appendChild(column);
    /*---------------------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    link = document.createElement("a");
    link.setAttribute("href", "<%=back.userAccountURL()%>/" + user.userid);
    link.setAttribute("style", "color: #0055ff; text-decoration: none");
    link.innerHTML = "Account";
    column.appendChild(link);
    line.appendChild(column);
    /*
    column = document.createElement("div");
    column.setAttribute("style", "flex: 3; font-size: 15px; color: #666");
    column.innerHTML = "Account";
    line.appendChild(column);
    */
    /*---------------------------------------------------*/
    top.appendChild(line);
    /*---------------------------------------------------*/
    document.getElementById('userslist').appendChild(top);
    /*---------------------------------------------------*/
}
</script>
<title>Users Administration</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
<h2>Users</h2>
<div style="display: flex; flex-direction: row; margin-top: 30px">
    <div style="width: 300px">
        General info panel.
    </div>    
    <div style="width: 1px; background-color: #dddddd; margin-left: 30px"></div>
    <div style="flex: 1; margin-left: 30px">
        <div style="width: 450px; float: left">
            <form id="formsearchusers" class="search">
            <div>
              <input type="text" placeholder="Show a number of users starting at or after" name="<%=ApiGetUserList.STARTAT%>">
              <button onclick="fetchUserList(); return false">Show</button>
            </div>
            </form>        
        </div><div style="clear: both; height: 50px"></div>
        <div id="userslist"></div>
    </div>
</div>    
<div style="height: 100px"></div>    
</div>
</body>
</html>
