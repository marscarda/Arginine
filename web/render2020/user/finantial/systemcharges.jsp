<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackSystemCharge"%>
<%
    WebBackSystemCharge back = (WebBackSystemCharge)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
    //User user = back.getUser();
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
var charges = <%=back.jSystemCharges()%>;
let fillCharges = () => {
    if (charges.count === 0) return;
    var div = document.getElementById('chargelist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < charges.count; n++) {
        addCharge(charges.items[n], false);
    }
}
let addCharge = (charge) => {
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
    top.setAttribute('id', 'charge' + charge.idcode);
    top.style.padding = "8px 0px ";
    top.style.borderBottom = "solid 1px #ddd";
    line = document.createElement("div");
    line.style.display = "flex";
    line.style.flexDirection = "row";
    /*---------------------------------------------------*/
    /*Column Date*/{
        column = document.createElement("div");
        column.style.width = "130px";
        column.style.fontSize = "12px";
        column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(charge.date);
        line.appendChild(column);
    }    
    /*---------------------------------------------------*/
    /*Column Description*/{
        column = document.createElement("div");
        column.style.width = "400px";
        column.style.fontSize = "12px";
        column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(charge.description);
        line.appendChild(column);
    }    
    /*---------------------------------------------------*/
    /*Column Project*/{
        column = document.createElement("div");
        column.style.width = "200px";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "left";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(charge.projectname);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    /*Column Amoun*/{
        column = document.createElement("div");
        column.style.width = "120px";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "right";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(charge.cost);
        line.appendChild(column);
    }    
    /*---------------------------------------------------*/
    /*Column Billing ref*/{
        column = document.createElement("div");
        column.style.flex = "1";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "right";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(charge.billingref);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    top.appendChild(line);
    document.getElementById('chargelist').appendChild(top);
    /*---------------------------------------------------*/
}
</script>
<title>JSP Page</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div class="content">
<h1>System Charges</h1>
<div style="padding: 8px 0px; border-bottom: solid 2px #09f; display: flex;
     flex-direction: row; font-size: 14px; font-weight: 600; color: #555">
    <div style="width: 130px">Date</div>
    <div style="width: 400px">Description</div>
    <div style="width: 200px">Project</div>
    <div style="width: 120px; text-align: right">Amount</div>
    <div style="flex: 1; text-align: right">Ledger Ref</div>
</div>
<div id="chargelist">
</div>
</div>
</body>
<script>
    fillCharges();
</script>
</html>
