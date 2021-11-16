<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
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
</script>
<title>Transfers</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div id="divaddcredit" class="popupformmodal">
    <div id="divaddcreditbox" class="formbox" style="width: 500px">
        <div style="color: #666666; font-size: 17px; margin-bottom: 20px">Give Credit</div>
        <form id="formdivaddcredit">
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
        <button class="greenwidththin" onclick="addCredit(); return false;">Create</button>
        <button class="graywidththin" onclick="closeAddCredit(); return false;">Cancel</button>
    </div>
</div>
<div class="content">
    <h1>Transfers!</h1>
    <a href="#" onclick="openAddCredit(); return false">Transfer To</a>
</div>
</body>
</html>