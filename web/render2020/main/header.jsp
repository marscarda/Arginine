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
.menuouter {
    background-color: #eeffee;
    border-bottom: solid 1px #555555;
    padding: 5px 30px;
}
.menuinner {
    display: flex;
    flex-direction: row;
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
                
    <div class="menuouter">
        <div class="menuinner">
            <div style="flex: 3; text-align: left; font-size: 15px">
                <a href="<%=hback.getHomeURL()%>" style="color: #030; text-decoration: none">Home</a>
            </div>
            <div style="flex: 3; text-align: left; font-size: 15px">
                <a href="<%=hback.usersURL()%>" style="color: #030; text-decoration: none">Users</a>
            </div>


            <div style="flex: 3; text-align: left; font-size: 15px">
                <a href="<%=hback.financialURL()%>" style="color: #030; text-decoration: none">Financial</a>
            </div>
            


            <div style="flex: 2; text-align: right; font-size: 12px; display: flex; flex-direction: column-reverse">
                <div>
                </div>
            </div>
        </div>
    </div>                
</div>
<script>
var promptokcall = null;
function showPrompt (message, promptcall) {
    promptokcall = promptcall;
    var msg = document.getElementById('promptmessage');
    var div = document.getElementById('promptactiontop');
    msg.innerHTML = message;
    div.style.height = "100%";
    div.style.width = "100%";
    var promptshow = new DivPopUpPromptShow();
    promptshow.setElement(document, 'confirmbox');
    promptshow.start();
}
function promptOK () {
    if (promptokcall === null) alert ("Dont forget to define promptokcall");
    promptokcall();
    var div = document.getElementById('promptactiontop');
    div.style.height = 0;
    div.style.width = 0;
}
function promptCancel () {
    var div = document.getElementById('promptactiontop');
    div.style.height = 0;
    div.style.width = 0;
}
function showNotice (message, color) {
    var msg = document.getElementById('messagebox');
    msg.style.color = color;
    msg.innerHTML = decodeURI(message);
    var popupmessage = new DivPopUpMessage();
    popupmessage.setElement(document, 'messagebox');
    popupmessage.start();
}
</script>
<div id="promptactiontop" class="popupformmodal">
    <div id="confirmbox" class="confirmbox">
        <div id="promptmessage">Message</div>
        <div style="margin-top: 20px; display: flex; flex-direction: row">
            <div style="flex: 1; text-align: right; margin-right: 10px">
                <button class="okbutton" onclick="promptOK(); return false;">Ok</button>
            </div>
            <div style="flex: 1; text-align: left; margin-left: 10px">
                <button class="cancelbutton" onclick="promptCancel(); return false">Cancel</button>
            </div>
        </div>
    </div>
</div>
<div id="messagebox" class="messagebox">
    <div id="noticemessage">Message</div>
</div>
