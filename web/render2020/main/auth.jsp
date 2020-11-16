<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.main.WebFrontAuth"%>
<%@page import="arginine.main.WebBackAuth"%>
<%@page import="arginine.WebFrontStatic"%>
<% WebBackAuth back = (WebBackAuth)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<style>
    div.pageparent {
        border: solid 1px #0055ff;
        border-radius: 2px;
        width: 70%;
        max-width: 800px;
        margin-left: auto;
        margin-right: auto;
        display: flex;
        flex-direction: row;
        margin-top: 30px;
    }
    div.loginbox {
        flex: 10;
        color: #444444;
        background-color: #ffffff;
        padding: 10px 50px 30px 50px;
        display: block;
    }
    div.signinbox {
        flex: 10;
        color: #ffffff;
        background-color: #0055ff;
        padding: 10px 50px 30px 50px;
        display: block;
    }
</style>
<title>Ingresar / Registrarse</title>
<body>
<%@include file="header.jsp" %>
<div class="content">
    <% if (back.getErrorMessageCode() != 0) { %>
    <div style="margin-bottom: 100px; color: #ff0055; font-size: 15px; text-align: center; display: block">
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRINTERNAL) { %>Internal server error<% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRINVALIDCREDENTIALS) { %>
        <p>Invalid credentials</p>
        <% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRUSERALREADYEXISTS) { %>El nombre de usuario ya se encuentra registrado<% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRUSERNOTACCEPTED) { %>
        <p>User name must 6 to 20 chars long</p>
        <p>It must start with a letter</p>
        <p>May contain letters, numbers, '-', '_' And '.'</p>
        <% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRINVALIDEMAIL ) { %>
        <p>A valid email address is required</p>
        <% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERREMAILALREADYUSED ) { %>
        <p>The email address is already in use</p>
        <% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRPASSNOTMATCH) { %>
        <p>Passwords don't match</p>
        <% } %>
        <% if (back.getErrorMessageCode() == WebBackAuth.ERRPASSNOTACCEPTED) { %>
        <p>The password must be 8 to 15 chars long</p>
        <p>Must contain letters and numbers</p>
        <% } %>
    </div>
    <% } %>
    <div class="pageparent">
        <div class="loginbox">
            <p style="font-size: 18px; font-weight: 500">Login to your account</p>
            <div style="margin-top: 30px; font-size: 14px; font-weight: 650">
            <div style="height: 15px"></div>
            <form action="<%=back.getAuthURL()%>" method="POST">
                User or email
                <input type="text" 
                    name ="<%=WebFrontAuth.USER%>"
                    <% if (back.authError()) { %>class="error"<% } %>
                    value="<%=back.getLoginUser()%>"
                />
                <div style="height: 10px"></div>
                Password
                <input type="password" 
                    name ="<%=WebFrontAuth.PASSWORD%>"
                    <% if (back.authError()) { %>class="error"<% } %>
                />
                <div style="height: 10px"></div>
                <button class="bluewidth">Login</button>
            </form>
            <div style="margin-top: 10px; font-size: 14px; font-weight: 650">
                Forgot my password
            </div>
            </div>
        </div>
        <div class="signinbox">
            <p style="font-size: 20px; font-weight: 500">Create account</p>
            <form action="<%=back.getAuthURL()%>" method="POST">
                User Name
                <input type="text" 
                    name ="<%=WebFrontAuth.USER%>"
                    <% if (back.signUpUserErrorError() ) { %>class="error"<% } %>
                    value="<%=back.getSignUpUser()%>"
                />
                <div style="height: 10px"></div>
                Email
                <input type="text" 
                    name ="<%=WebFrontAuth.EMAIL%>"
                    <% if (back.emailErrorError() ) { %>class="error"<% } %>
                    value="<%=back.getEmail()%>"
                />
                <div style="height: 10px"></div>
                Password
                <input type="password" 
                    name ="<%=WebFrontAuth.PASSWORD%>"
                    <% if (back.signUpPasswordError() ) { %>class="error"<% } %>
                />
                <div style="height: 10px"></div>
                <input type="password" 
                    name ="<%=WebFrontAuth.PASSWORDRETYPE%>"
                    <% if (back.signUpPasswordError() ) { %>class="error"<% } %>
                />
                <div style="height: 10px"></div>
                <input type="hidden" name="<%=WebFrontAuth.SIGNUP%>" value="1">
                <button class="whitewidth">Sign Up</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
