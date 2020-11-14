<%@page import="methionine.auth.User"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.WebBackAlpha"%>
<%
    WebBackAlpha hback = (WebBackAlpha)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
    User lgdinuser = hback.getLoggedInUser();
%>
<style>
.headertop {
    height: auto;
    width: 100%;
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    z-index: 101;
}
.blueouter {
    background-color: #005500;
    padding: 5px 30px;
}
.blueinner {
    display: flex; 
    flex-direction: row;
    font-family: General;
    font-size: 15px;
    font-weight: normal;
    color: #ffffff;
}
</style>
<div class="headertop">
    <div class="blueouter">
        <div class="blueinner">
            <div style="flex: 2; text-align: left; font-size: 10px">
                Page ID here
            </div>
            <div style="flex: 2; text-align: left; font-size: 10px; display: flex; flex-direction: column-reverse">
                <div style="font-size: 15px; text-align: right">
                <% if (lgdinuser.isValid()) { %>
                <div>
                    <%=lgdinuser.loginName()%>
                    &nbsp;&nbsp;&nbsp;
                    My account
                    &nbsp;&nbsp;&nbsp;
                    <a href="<%=hback.logOutURL()%>" style="color: #ffffff; text-decoration: none">Logout</a>
                </div>
                <% } else { %>
                <div>
                    <a href="<%=hback.getAuthURL()%>" style="color: #ffffff; text-decoration: none">Login</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="<%=hback.getAuthURL()%>" style="color: #ffffff; text-decoration: none">Signup</a>
                </div>
                <% } %>

                </div>
            </div>
        </div>
    </div>
</div>
