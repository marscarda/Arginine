<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
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




function addCredit () {
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
        addLedgerEntry (objresp.entry, true);
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'ledgerentry' + entryid);
        turnon.start();
        entryid++;
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







<div class="content">
    
    
    
    
    <a href="#" style="color: #05f" onclick="openAddCredit(); return false;">Add Credit</a>
    
    
    
    
    
    
    
</div>
</body>
</html>
