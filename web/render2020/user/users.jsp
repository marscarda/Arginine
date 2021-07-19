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
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%--=WebFrontStatic.CSSLISTANDBOX--%>">
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
        //users = objresp.folders;
        //fillFolders();
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiGetUserList.STARTAT%>', formdata.get('<%=ApiGetUserList.STARTAT%>'));
    req.setURL('<%=back.userGetUserListURL()%>');
    try { 
        req.executepost(); 
    }
    catch(err) {
        alert (err.getMessage);
    }
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
              <input type="text" placeholder="Show 10 user starting at or after" name="<%=ApiGetUserList.STARTAT%>">
              <button onclick="fetchUserList(); return false">Search</button>
            </div>
            </form>        
        </div><div style="clear: both; height: 50px"></div>
        <div id="userslist"></div>
    </div>
</div>    
<div style="height: 100px"></div>    

    
    
    


    <div style="width: 100%; display: flex; flex-direction: row">
        <div style="flex: 2">
            <div class="listheaderouter" style="margin-top: 20px">
            <div class="listheaderinner" style="margin-top: 20px">
                <div style="flex: 3; text-align: left">User ID</div>
                <div style="flex: 3; text-align: left">Login Name</div>
                <div style="flex: 3; text-align: left">Email</div >
                <div style="flex: 1">&nbsp;</div>
            </div>
            </div>
            <% for (User user : users) { %>
            <div class="listrowouter">
            <div class="listrowinner">
                <div style="flex: 3; text-align: left; font-size: 13px"><%=user.userID()%></div>
                <div style="flex: 3; text-align: left; font-size: 14px"><%=user.loginName()%></div>
                <div style="flex: 3; text-align: left; font-size: 14px"><%=user.eMail()%></div>
                <div style="flex: 1; font-size: 22px; font-weight: bold; text-align: right">
                <a href="<%=back.userDetailURL()%>/<%=user.userID()%>" 
                   style="color: #0055ff; text-decoration: none">></a>
                </div>
            </div>
            </div>
            <% } %>
        </div>
        <div style="flex: 3">
        </div>            
    </div>
</div>
</body>
</html>
