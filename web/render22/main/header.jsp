<%@page import="arginine.WebFrontAlpha"%>
<%@page import="methionine.auth.User"%>
<%@page import="arginine.WebBackAlpha"%>
<%
    WebBackAlpha hback = (WebBackAlpha)request.getAttribute(WebFrontAlpha.PAGEATTRKEY);
    User lgdinuser = hback.getLoggedInUser();
%>
<!DOCTYPE html>
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
.headertitle {
    padding: 3px 0px;
    font-size: 18px;
    font-weight: bold;
    color: #cfc;
}
.headerouter {
    background-color: #030;
    padding: 5px 30px;
}
.headermenubar {
    display: flex;
    flex-direction: row;
}
div.headermenuitem {
    font-size: 16px;
    margin-right: 20px;
}
a.headermenuitem {
    color: #fff;
}
</style>
<div class="headertop">
    <div class="headerouter">
        <div class="headertitle">
            Radar Admin Panel
        </div>
        <div class="headermenubar">
            <div class="headermenuitem">
                <a href="<%=hback.usersURL()%>" class="headermenuitem">Users</a>
            </div>
            <div class="headermenuitem">
                <a href="<%=hback.projectlURL()%>" class="headermenuitem">Projects</a>
            </div>
            <div class="headermenuitem">
                <a href="<%=hback.financialURL()%>" class="headermenuitem">Financial</a>
            </div>
            <div class="headermenuitem">
                <a href="<%=hback.pageMapping()%>" class="headermenuitem">Mapping</a>
            </div>                    
        </div>
    </div>
</div>
