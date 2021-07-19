<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="alanine.ApiAlpha"%>
<%@page import="alanine.users.ApiSetUserPermissions"%>
<%@page import="alanine.WebFrontStatic"%>
<%@page import="alanine.WebFrontAlpha"%>
<%@page import="methionine.auth.User"%>
<%@page import="alanine.users.WebBackUserDet"%>
<%
    WebBackUserDet back = (WebBackUserDet)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    User user = back.getUser();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<style>
    div.softbox {
        border: solid 1px #dddddd;
        border-radius: 3px;
        padding: 50px 35px;
        box-shadow: 3px 5px #ececec;
    }
    div.boxtitle {
        font-size: 20px;
        font-weight: bold;
        color: #666666;
        margin-bottom: 35px;
    }
    div.fieldname {
       font-size: 12px;
       font-weight: 300;
       color: #555555;
    }
    div.fieldvalue {
       font-size: 14px;
       font-weight: normal;
       color: #444444;
    }
</style>
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSTAGANIMATE%>"></script>
<script>
function setPermissions () {
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
        showNotice ('Permisions updated', '#229900');
        return;
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiAlpha.USERID%>',<%=user.userID()%>);
    req.addParam('<%=ApiSetUserPermissions.PERRMISSIONS%>', document.getElementById('<%=ApiSetUserPermissions.PERRMISSIONS%>').value);
    req.setURL('<%=back.apiSetPermissionsURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert ("Error class " + err.getMessage);
    }
}
</script>
<title>User: <%=user.loginName()%></title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <% if (back.getErrorMessageCode() != 0) { %>
    <div style="margin-bottom: 100px; color: #ff0055; font-size: 15px; text-align: center; display: block">
        <% if (back.getErrorMessageCode() == WebBackUserDet.ERRINTERNAL) { %>Internal server error<% } %>
        <% if (back.getErrorMessageCode() == WebBackUserDet.USERNOTFOUND) { %>Invalid credentials<% } %>
    </div>
    <% } %>
    <h2>User's details</h2>
    <div style="width: 100%; display: flex; flex-direction: row">
        <div style="flex: 3">
            <div class="softbox">
                <div class="boxtitle">Basic Data</div>
                <div class="fieldname">User id</div>
                <div class="fieldvalue"><%=user.userID()%></div>
                <div class="fieldname" style="margin-top: 20px">Login Name</div>
                <div class="fieldvalue"><%=user.loginName()%></div>
                <div class="fieldname" style="margin-top: 20px">Email</div>
                <div class="fieldvalue"><%=user.eMail()%><%=user.eMail()%></div>
                <div style="height: 20px"></div>
                <label for="<%=ApiSetUserPermissions.PERRMISSIONS%>">Permissions</label>
                <textarea id="<%=ApiSetUserPermissions.PERRMISSIONS%>" style="height: 90px"><%=user.getPermissions()%></textarea>
                <button class="whitewidth" onclick="setPermissions(); return false;">Update</button>
            </div>
        </div>
        <div style="flex: 5">
        </div>
    </div>
</div>    
</body>
</html>
