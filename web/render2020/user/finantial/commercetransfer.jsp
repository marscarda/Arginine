<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackCommerce"%>
<%
    WebBackCommerce back = (WebBackCommerce)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    //User user = back.getUser();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<script>
var transfers = <%=back.jTransfers()%>;
let fillTransfers = () => {
    if (transfers.count === 0) return;
    var div = document.getElementById('transferlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < transfers.count; n++) {
        addTransfer(transfers.items[n], false);
    }
}
let addTransfer = (transfer) => {
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
    if (transfer.direction > 0) top.style.color = "#353";
    else top.style.color = "#733";
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
    
    /*Amount*/{
        column = document.createElement("div");
        column.style.width = "100px";
        column.style.fontSize = "15px";
        column.style.textAlign = "right";
        column.innerHTML = transfer.amount;
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
    document.getElementById('transferlist').appendChild(top);
    /*---------------------------------------------------*/
}
</script>
<title>Commercial Transfers</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div class="content">
<h3>Commercial Transfers</h3>
<div style="padding: 8px 0px; border-bottom: solid 1px #0df; display: flex; flex-direction: row;
     font-size: 12px; color: #666666; font-weight: 600; margin-top: 30px">
    <div style="width: 110px">Date</div>
    <div style="width: 100px">Direction</div>
    <div style="width: 150px">User</div>
    <div style="width: 380px">Description</div>
    <div style="width: 100px; text-align: right">Amount</div>
    <div style="width: 40px"></div>
    <div style="flex: 1; text-align: right">Ledger Ref</div>
</div>
<div id="transferlist">
</div>
</body>
<script>
    fillTransfers();
</script>
</html>
