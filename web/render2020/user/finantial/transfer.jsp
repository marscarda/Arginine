<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.finantial.user.ApiTransferFrom"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="methionine.billing.UsageCost"%>
<%@page import="arginine.finantial.user.ApiTransferTo"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackTransfer"%>
<%
    WebBackTransfer back = (WebBackTransfer)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
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
var transfers = <%=back.jTransfers()%>;
let openTransferTo = () => {
    document.getElementById('divtransferto').style.height = '100%';
    document.getElementById('divtransferto').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'divtransfertobox');
    turnon.start();
}
let closeTransferTo = () => {
    document.getElementById('divtransferto').style.height = '0px';
    document.getElementById('divtransferto').style.width = '0px';
    document.getElementById('formtransferto').reset();
}
let openTransferFrom = () => {
    document.getElementById('divtransferfrom').style.height = '100%';
    document.getElementById('divtransferfrom').style.width = '100%';
    var turnon = new ElementFadeIn();
    turnon.setElement(document, 'divtransferfrombox');
    turnon.start();
}
let closeTransferFrom = () => {
    document.getElementById('divtransferfrom').style.height = '0px';
    document.getElementById('divtransferfrom').style.width = '0px';
    document.getElementById('formtransferfrom').reset();
}
let addTransferTo = () => {
    var form = document.getElementById('formtransferto');
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
        closeTransferTo();
        showNotice("Added", "#295");
        addTransfer(objresp.transfer, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'transfer' + objresp.transfer.idcode);
        turnon.start();
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiTransferTo.USERID%>', <%=user.userID()%>);
    req.addParam('<%=ApiTransferTo.DESCRIPTION%>', formdata.get('<%=ApiTransferTo.DESCRIPTION%>'));
    req.addParam('<%=ApiTransferTo.AMOUNT%>', formdata.get('<%=ApiTransferTo.AMOUNT%>'));
    req.addParam('<%=ApiTransferTo.CONVERTCURRENCY%>', formdata.get('<%=ApiTransferTo.CONVERTCURRENCY%>'));
    req.addParam('<%=ApiTransferTo.CONVERTAMOUNT%>', formdata.get('<%=ApiTransferTo.CONVERTAMOUNT%>'));
    req.setURL('<%=back.transferToURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert (err.getMessage);
    }
}
let addTransferFrom = () => {
    var form = document.getElementById('formtransferfrom');
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
        closeTransferFrom();
        showNotice("Added", "#295");
        addTransfer(objresp.transfer, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'transfer' + objresp.transfer.idcode);
        turnon.start();
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.addParam('<%=ApiTransferFrom.USERID%>', <%=user.userID()%>);
    req.addParam('<%=ApiTransferFrom.DESCRIPTION%>', formdata.get('<%=ApiTransferFrom.DESCRIPTION%>'));
    req.addParam('<%=ApiTransferFrom.AMOUNT%>', formdata.get('<%=ApiTransferFrom.AMOUNT%>'));
    req.addParam('<%=ApiTransferFrom.CONVERTCURRENCY%>', formdata.get('<%=ApiTransferFrom.CONVERTCURRENCY%>'));
    req.addParam('<%=ApiTransferFrom.CONVERTAMOUNT%>', formdata.get('<%=ApiTransferFrom.CONVERTAMOUNT%>'));
    req.setURL('<%=back.transferFromURL()%>');
    try { req.executepost(); }
    catch(err) {
        alert (err.getMessage);
    }
}
let fillTransfers = () => {
    if (transfers.count === 0) return;
    var div = document.getElementById('transferlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < transfers.count; n++) {
        addTransfer(transfers.items[n], false);
    }
}
let addTransfer = (transfer, isnew) => {
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
    top.setAttribute('id', 'transfer' + transfer.idcode);
    top.style.padding = "8px 0px";
    top.style.borderBottom = "solid 1px #ddd";
    if (transfer.direction > 0) top.style.color = "#080";
    else top.style.color = "#f00";
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    /*Date*/{
        column = document.createElement("div");
        column.style.width = "110px";
        column.style.fontSize = "14px";
        //column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(transfer.date);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Direction*/{
        column = document.createElement("div");
        column.style.width = "100px";
        column.style.fontSize = "12px";
        column.style.textAlign = "left";
        if (transfer.direction > 0) column.innerHTML = "IN";
        else column.innerHTML = "OUT";
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Username*/{ 
        column = document.createElement("div");
        column.style.width = "150px";
        column.style.fontSize = "12px";
        //column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = transfer.username;
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Description*/{
        column = document.createElement("div");
        column.style.width = "380px";
        column.style.fontSize = "14px";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(transfer.description);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Amount*/{
        column = document.createElement("div");
        column.style.width = "100px";
        column.style.fontSize = "15px";
        column.style.textAlign = "right";
        column.innerHTML = transfer.amount;
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Sep*/{
        column = document.createElement("div");
        column.style.width = "40px";
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Convrsion currency*/{
        column = document.createElement("div");
        column.style.width = "70px";
        column.style.fontSize = "13px";
        //column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(decodeURI(transfer.conversioncurrency));
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Convrsion amount*/{
        column = document.createElement("div");
        column.style.width = "70px";
        column.style.fontSize = "13px";
        //column.style.color = "#666";
        column.style.textAlign = "right";
        column.innerHTML = transfer.conversionamount;
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Ledger Ref*/{
        column = document.createElement("div");
        column.style.flex = "1";
        column.style.fontSize = "13px";
        column.style.textAlign = "right";
        column.innerHTML = transfer.billingref;
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    top.appendChild(line);
    if (isnew)
        document.getElementById('transferlist').insertBefore(top, document.getElementById('transferlist').childNodes[0]);
    else document.getElementById('transferlist').appendChild(top);
    /*---------------------------------------------------*/
}
</script>
<title>Transfers</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div id="divtransferto" class="popupformmodal">
    <div id="divtransfertobox" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">Transfer To</div>
        <form id="formtransferto">
            <label for="<%=ApiTransferTo.DESCRIPTION%>">Description</label>
            <input type="text" name="<%=ApiTransferTo.DESCRIPTION%>" placeholder="Description" />
            <label for="<%=ApiTransferTo.AMOUNT%>">Amount</label>
            <input type="text" name="<%=ApiTransferTo.AMOUNT%>" placeholder="Quantity of <%=UsageCost.CURRENCYNAME%>" />
            <label for="<%=ApiTransferTo.CONVERTCURRENCY%>">Conversion Currency</label>
            <input type="text" name="<%=ApiTransferTo.CONVERTCURRENCY%>" placeholder="Currency of convertion" />
            <label for="<%=ApiTransferTo.CONVERTAMOUNT%>">Conversion Amount</label>
            <input type="text" name="<%=ApiTransferTo.CONVERTAMOUNT%>" placeholder="Conversion amount" />
            <div style="height: 35px"></div>
        </form>
        <button class="greenwidththin" onclick="addTransferTo(); return false;">Create</button>
        <button class="graywidththin" onclick="closeTransferTo(); return false;">Cancel</button>
    </div>
</div>
<div id="divtransferfrom" class="popupformmodal">
    <div id="divtransferfrombox" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">Transfer From</div>
        <form id="formtransferfrom">
            <label for="<%=ApiTransferTo.DESCRIPTION%>">Description</label>
            <input type="text" name="<%=ApiTransferTo.DESCRIPTION%>" placeholder="Description" />
            <label for="<%=ApiTransferTo.AMOUNT%>">Amount</label>
            <input type="text" name="<%=ApiTransferTo.AMOUNT%>" placeholder="Quantity of <%=UsageCost.CURRENCYNAME%>" />
            <label for="<%=ApiTransferTo.CONVERTCURRENCY%>">Conversion Currency</label>
            <input type="text" name="<%=ApiTransferTo.CONVERTCURRENCY%>" placeholder="Currency of convertion" />
            <label for="<%=ApiTransferTo.CONVERTAMOUNT%>">Conversion Amount</label>
            <input type="text" name="<%=ApiTransferTo.CONVERTAMOUNT%>" placeholder="Conversion amount" />
            <div style="height: 35px"></div>
        </form>
        <button class="greenwidththin" onclick="addTransferFrom(); return false;">Create</button>
        <button class="graywidththin" onclick="closeTransferFrom(); return false;">Cancel</button>
    </div>
</div>
<div class="content">
<h3>Transfers</h3>
<div style="display: flex; flex-direction: row; font-size: 15px; font-weight: 600">
    <div style="width: 150px">
        <a href="#" style="color: #05f" onclick="openTransferTo(); return false">Transfer To</a>
    </div>
    <div style="width: 150px">
        <a href="#" style="color: #05f" onclick="openTransferFrom(); return false">Transfer From</a>
    </div>
</div>


<div style="padding: 8px 0px; border-bottom: solid 1px #0df; display: flex; flex-direction: row;
     font-size: 12px; color: #666666; font-weight: 600; margin-top: 30px">
    <div style="width: 110px">Date</div>
    <div style="width: 100px">Direction</div>
    <div style="width: 150px">User</div>
    <div style="width: 380px">Description</div>
    <div style="width: 100px; text-align: right">Amount</div>
    <div style="width: 40px"></div>
    <div style="width: 140px">Conversion</div>
    <div style="flex: 1; text-align: right">Ledger Ref</div>
</div>


<div id="transferlist">
</div>
</div>
</body>
<script>
    fillTransfers();
</script>
</html>
