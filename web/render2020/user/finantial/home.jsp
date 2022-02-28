<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.Format"%>
<%@page import="methionine.finance.BalanceInfo"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="methionine.auth.User"%>
<%@page import="methionine.finance.FinanceRules"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackUserFinantial"%>
<%
    WebBackUserFinantial back = (WebBackUserFinantial)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    User user = back.getUser();
    BalanceInfo balance = back.getBalance();
    Format format = new DecimalFormat("#.######");
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
<div class="content">
<div style="margin-top: 40px; display: flex; flex-direction: row">
    <div style="width: 170px">
        <div style="font-size: 13px; color: #777">
            Total balance
        </div>
        <div style="font-size: 25px; color: #333; font-weight: 600">
            <%=format.format(balance.getTotalBalance())%>
        </div>
        <div style="margin-top: 15px">
            <a href="<%=back.transferURL()%>/<%=back.getUser().userID()%>" style="color: #05f">Transfers</a>
        </div>        
        <div style="margin-top: 15px">
            <a href="<%=back.usagePeriodsURL()%>/<%=back.getUser().userID()%>" style="color: #05f">Usage Periods</a>
        </div>
        <div style="margin-top: 15px">
            <a href="<%=back.systemChargeURL()%>/<%=back.getUser().userID()%>" style="color: #05f">System Charges</a>
        </div>
        <div style="margin-top: 15px">
            <a href="<%=back.commerciaTransferURL()%>/<%=back.getUser().userID()%>" style="color: #05f">Commercial Transfer</a>
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
