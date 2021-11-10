<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.finantial.user.ApiAddDebit"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="methionine.auth.User"%>
<%@page import="methionine.billing.UsageCost"%>
<%@page import="arginine.finantial.user.ApiAddCredit"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackUserFinantial"%>
<% 
    WebBackUserFinantial back = (WebBackUserFinantial)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
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
var ledger = <%=back.jLedgerEntries()%>;
var entrynumber = 1;
let openAddCredit = () => {
    document.getElementById('divaddcredit').style.height = '100%';
    document.getElementById('divaddcredit').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'divaddcreditbox');
    turnon.start();
}
let closeAddCredit = () => {
    document.getElementById('divaddcredit').style.height = '0px';
    document.getElementById('divaddcredit').style.width = '0px';
    document.getElementById('formdivaddcredit').reset();
}
let openAddDebit = () => {
    document.getElementById('divadddebit').style.height = '100%';
    document.getElementById('divadddebit').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'divadddebitbox');
    turnon.start();
}
let closeAddDebit = () => {
    document.getElementById('divadddebit').style.height = '0px';
    document.getElementById('divadddebit').style.width = '0px';
    document.getElementById('formdivadddebit').reset();
}
let addCredit = () => {
    var form = document.getElementById('formdivaddcredit');
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
        closeAddCredit();
        addEntry(objresp.entry, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'entry' + entrynumber);
        turnon.start();
        entrynumber++;
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiAddCredit.USERID%>', <%=user.userID()%>);
    req.addParam('<%=ApiAddCredit.CONVERTCURRENCY%>', formdata.get('<%=ApiAddCredit.CONVERTCURRENCY%>'));
    req.addParam('<%=ApiAddCredit.CONVERTAMOUNT%>', formdata.get('<%=ApiAddCredit.CONVERTAMOUNT%>'));
    req.addParam('<%=ApiAddCredit.DESCRIPTION%>', formdata.get('<%=ApiAddCredit.DESCRIPTION%>'));
    req.addParam('<%=ApiAddCredit.URAMOUNT%>', formdata.get('<%=ApiAddCredit.URAMOUNT%>'));
    req.setURL('<%=back.addCreditURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert (err.getMessage);
    }
}
let addDebit = () => {
    var form = document.getElementById('formdivadddebit');
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
        closeAddDebit();
        addEntry(objresp.entry, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'entry' + entrynumber);
        turnon.start();
        entrynumber++;
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiAddDebit.USERID%>', <%=user.userID()%>);
    req.addParam('<%=ApiAddDebit.CONVERTCURRENCY%>', formdata.get('<%=ApiAddDebit.CONVERTCURRENCY%>'));
    req.addParam('<%=ApiAddDebit.CONVERTAMOUNT%>', formdata.get('<%=ApiAddDebit.CONVERTAMOUNT%>'));
    req.addParam('<%=ApiAddDebit.DESCRIPTION%>', formdata.get('<%=ApiAddDebit.DESCRIPTION%>'));
    req.addParam('<%=ApiAddDebit.URAMOUNT%>', formdata.get('<%=ApiAddDebit.URAMOUNT%>'));
    req.setURL('<%=back.addDebitURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert (err.getMessage);
    }
}
let fillLedger = () => {
    if (ledger.count === 0) return;
    var div = document.getElementById('ledger');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < ledger.count; n++) {
        addEntry(ledger.items[n], false);
        entrynumber++;
    }
}
let addEntry = (entry, isnew) => {
    var top;
    var img;
    var line;
    var column;
    var subline;
    var cell;
    var bartop;
    var barprop;
    var link;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.setAttribute('id', 'entry' + entrynumber);
    top.setAttribute('style', 'padding: 8px 0px; border-bottom: solid 1px #ddd');
    if (isnew) top.style.opacity = 0;
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    /*Column Date*/{
        column = document.createElement("div");
        column.style.width = "110px";
        column.style.fontSize = "15px";
        column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(entry.date);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Column Conversion*/{
        column = document.createElement("div");
        column.style.width = "180px";
        column.style.fontSize = "12px";
        column.style.color = "#666";
        column.style.display = "flex";
        column.style.flexDirection = "row";
        cell = document.createElement('div');
        cell.style.width = "60px";
        cell.style.textAlign = "left";
        cell.innerHTML = decodeURIComponent(entry.conversioncurrency);
        column.appendChild(cell);
        cell = document.createElement('div');
        cell.style.width = "60px";
        cell.style.textAlign = "right";
        cell.innerHTML = decodeURIComponent(entry.conversionamount);
        column.appendChild(cell);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Column Description*/{
        column = document.createElement("div");
        column.style.width = "600px"
        column.style.fontSize = "14px";
        column.style.color = "666#";
        column.innerHTML = decodeURIComponent(entry.description);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Colum Amount*/{
        column = document.createElement("div");
        column.style.flex = 1;
        column.style.fontSize = "14px";
        column.style.fontWeight = 600;
        column.style.color = "#555";
        column.style.textAlign = "right";
        column.innerHTML = decodeURIComponent(entry.amount);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    top.appendChild(line);
    if (isnew)
        document.getElementById('ledger').insertBefore(top, document.getElementById('ledger').childNodes[0]);
    else document.getElementById('ledger').appendChild(top);
    /*---------------------------------------------------*/
}
</script>
<title>User Account</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div id="divaddcredit" class="popupformmodal">
    <div id="divaddcreditbox" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">Give Credit</div>
        <form id="formdivaddcredit">
            <label for="<%=ApiAddCredit.CONVERTCURRENCY%>">Conversion Currency</label>
            <input type="text" name="<%=ApiAddCredit.CONVERTCURRENCY%>" placeholder="Currency of convertion" />
            <label for="<%=ApiAddCredit.CONVERTAMOUNT%>">Conversion Amount</label>
            <input type="text" name="<%=ApiAddCredit.CONVERTAMOUNT%>" placeholder="Conversion amount" />
            <label for="<%=ApiAddCredit.DESCRIPTION%>">Description</label>
            <input type="text" name="<%=ApiAddCredit.DESCRIPTION%>" placeholder="Description" />
            <label for="<%=ApiAddCredit.URAMOUNT%>">Amount</label>
            <input type="text" name="<%=ApiAddCredit.URAMOUNT%>" placeholder="Quantity of <%=UsageCost.CURRENCYNAME%>" />
            <div style="height: 35px"></div>
        </form>
        <button class="greenwidththin" onclick="addCredit(); return false;">Create</button>
        <button class="graywidththin" onclick="closeAddCredit(); return false;">Cancel</button>
    </div>
</div>
<div id="divadddebit" class="popupformmodal">
    <div id="divadddebitbox" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">Remove Credit</div>
        <form id="formdivadddebit">
            <label for="<%=ApiAddDebit.CONVERTCURRENCY%>">Conversion Currency</label>
            <input type="text" name="<%=ApiAddDebit.CONVERTCURRENCY%>" placeholder="Currency of convertion" />
            <label for="<%=ApiAddDebit.CONVERTAMOUNT%>">Conversion Amount</label>
            <input type="text" name="<%=ApiAddDebit.CONVERTAMOUNT%>" placeholder="Conversion amount" />
            <label for="<%=ApiAddDebit.DESCRIPTION%>">Description</label>
            <input type="text" name="<%=ApiAddDebit.DESCRIPTION%>" placeholder="Description" />
            <label for="<%=ApiAddDebit.URAMOUNT%>">Amount</label>
            <input type="text" name="<%=ApiAddDebit.URAMOUNT%>" placeholder="Quantity of <%=UsageCost.CURRENCYNAME%>" />
            <div style="height: 35px"></div>
        </form>
        <button class="greenwidththin" onclick="addDebit(); return false;">Create</button>
        <button class="graywidththin" onclick="closeAddDebit(); return false;">Cancel</button>
    </div>
</div>
<div class="content">
<div style="margin-top: 40px; display: flex; flex-direction: row">
    <div style="width: 170px">
        <div>
            <a href="#" style="color: #05f" onclick="openAddCredit(); return false;">Add Credit</a>
        </div>
        <div style="margin-top: 15px">
            <a href="#" style="color: #05f" onclick="openAddDebit(); return false;">Add Debit</a>
        </div>


        <div style="margin-top: 15px">
            <a href="<%=back.usagePeriodsURL()%>" style="color: #05f">Usage Periods</a>
        </div>



    </div>
    <div style="width: 1px; background-color: #dddddd; margin-left: 30px"></div>
    <div style="flex: 1; margin-left: 30px">
        <div style="padding: 8px 0px; border-bottom: solid 1px #ddd; display: flex; flex-direction: row">
            <div style="width: 110px">Date</div>
            <div style="width: 180px">Conversion</div>
            <div style="width: 600px">Description</div>
            <div style="flex: 1; text-align: right">Amount</div>
        </div>
        <div id="ledger"></div>
    </div>    
</div>    
</div>
</body>
<script>
    fillLedger();
</script>
</html>
