<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.finantial.ApiAddLedgerEntry"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.WebBackUserAccount"%>
<%
    WebBackUserAccount back = (WebBackUserAccount)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    User user = back.getUser();
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
var entryid = 1;
var entries = <%=back.jLedgerEntries()%>;
function openAddLedgerEntry () {
    document.getElementById('divaddledgerentry').style.height = '100%';
    document.getElementById('divaddledgerentry').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'addledgerbox');
    turnon.start();
}
function closeAddLedgerEntry() {
    document.getElementById('divaddledgerentry').style.height = '0px';
    document.getElementById('divaddledgerentry').style.width = '0px';
    document.getElementById('formaddledgerentry').reset();
}
function createLedgerEntry () {
    var form = document.getElementById('formaddledgerentry');
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
        closeAddLedgerEntry();
        addLedgerEntry (objresp.entry, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'ledgerentry' + entryid);
        turnon.start();
        entryid++;
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiAddLedgerEntry.USERID%>', <%=user.userID()%>);
    req.addParam('<%=ApiAddLedgerEntry.DESCRIPTION%>', formdata.get('<%=ApiAddLedgerEntry.DESCRIPTION%>'));
    req.addParam('<%=ApiAddLedgerEntry.SIZE%>', formdata.get('<%=ApiAddLedgerEntry.SIZE%>'));
    req.setURL('<%=back.addLedgerEntryURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert (err.getMessage);
    }
}
let initLedger = () => {
    var div = document.getElementById('ledgerlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < entries.count; n++) {
        addLedgerEntry(entries.items[n], false);
        entryid++;
    }
}
function addLedgerEntry (entry, isnew) {
    var top;
    var line;
    var column;
    var link;
    top = document.createElement("div");
    top.setAttribute('id', 'ledgerentry' + entryid);
    top.setAttribute('style', 'padding: 5px 0px');
    if (isnew) top.style.opacity = 0;
    /*----------------------------------------*/
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 2; font-size: 13px; font-weight: normal; color: #222");
    column.innerHTML = entry.date;
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 5; font-size: 13px; font-weight: normal; color: #222");
    column.innerHTML = decodeURIComponent(decodeURI(entry.description));
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 5; font-size: 13px; text-align: right; font-weight: normal; color: #222");
    column.innerHTML = entry.size;
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    if (isnew)
        document.getElementById('ledgerlist').insertBefore(top, document.getElementById('ledgerlist').childNodes[0]);
    else document.getElementById('ledgerlist').appendChild(top);
    /*----------------------------------------*/
}
</script>
<title>User Account</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div id="divaddledgerentry" class="popupformmodal">
    <div id="addledgerbox" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">Add Ledger Entry</div>
        <form id="formaddledgerentry">
            <label for="<%=ApiAddLedgerEntry.DESCRIPTION%>">Description</label>
            <input type="text" name="<%=ApiAddLedgerEntry.DESCRIPTION%>" placeholder="fdhktufhfhfh" />
            <label for="<%=ApiAddLedgerEntry.SIZE%>">Size</label>
            <input type="text" name="<%=ApiAddLedgerEntry.SIZE%>" placeholder="fdhktufhfhfh" />
            <div style="height: 35px"></div>
        </form>
        <button class="greenwidththin" onclick="createLedgerEntry(); return false;">Create</button>
        <button class="graywidththin" onclick="closeAddLedgerEntry(); return false;">Cancel</button>
    </div>
</div>
<div class="content">
<h2>User Account</h2>
<div style="display: flex; flex-direction: row; margin-top: 30px">
    
    <div style="width: 230px">
        Panel A
    </div>    
    <div style="width: 1px; background-color: #dddddd; margin-left: 30px"></div>
    <div style="width: 250px; margin-left: 30px">
        Panel B
    </div>    
    <div style="width: 1px; background-color: #dddddd; margin-left: 30px"></div>
    <div style="flex: 1; margin-left: 30px">
        <div style="margin-top: 15px">
            <div style="font-size: 16px; color: #444444; margin-top: 12px; font-weight: bold; float: left">
                Ledger
            </div>
            <div style="font-size: 16px; color: #444444; margin-top: 12px; font-weight: bold; float: right">
                <a href="#" style="color: inherit; text-decoration: none" onclick="openAddLedgerEntry(); return false">
                    <img src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.ADDCOMMAND%>" 
                            style="width: 20px; height: 20px" alt="newfolder" title="New Folder" />
                </a>
            </div>
            <div style="font-size: 16px; color: #444444; margin-top: 12px; border-top: solid 1px #dddddd; padding: 15px 0px">
                <div id="ledgerlist" style="width: 100%; margin-top: 30px; font-weight: normal"></div>
            </div>
        </div>
    </div>


</div>

</div>

</body>
<script>/*initFundPosts();*/initLedger();</script>
</html>
