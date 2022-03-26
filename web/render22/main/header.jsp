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
    color: #ffb;
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
<script>
let setCurtain = () => {
    var expand = new DivExpandHeight();
    expand.setDimensions (0, 1500);
    expand.setElement(document, 'curtain');
    expand.start();
}
let hideCurtain = () => { document.getElementById('curtain').style.height = "0px"; }
let showToast = (message, color) => {
    var msg = document.getElementById('divtoast');
    msg.style.color = color;
    msg.innerHTML = decodeURI(message);
    var toastmsg = new ToastMessage();
    toastmsg.setElement('divtoast');
    toastmsg.start();
}
let confirmOk = () => {};
let closeConfirm = () => {
    var div = document.getElementById('confirmbox');
    div.style.height = 0;
    div.style.opacity = 0;
    hideCurtain();
}
let askContirmation = (message, confirmok) => {
    confirmOk = confirmok;
    var msg = document.getElementById('confirmmsg');
    msg.innerHTML = message;
    setCurtain();
    document.getElementById('confirmbox').style.height = 'auto';
    var fadein = new ElementFadeIn();
    fadein.setElement('confirmbox');
    fadein.start();    
}
</script>
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
            <div class="headermenuitem">
                <a href="<%=hback.pageUniverse()%>" class="headermenuitem">Universes</a>
            </div>            
        </div>
    </div>
    <div id="curtain" style="height: 0px; overflow: hidden; background-color: #335000; opacity: 0.15"></div>
</div>
<div id="divtoast" class="toastmessage">
    <div id="toastmessage">Message</div>
</div>
<div id="confirmbox" class="confirmbox" style="height: 0px; opacity: 0">
    <div id="confirmmsg" style="font-size: 16px; font-weight: 600; color: #666"></div>
    <div style="margin-top: 30px">
        <button class="ok" onclick="confirmOk(); return false">Ok</button>
        <button class="cancel" onclick="closeConfirm(); return false">Cancel</button>
    </div>
</div>
