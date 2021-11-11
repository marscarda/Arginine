<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.user.WebBackUsagePeriods"%>
<% 
    WebBackUsagePeriods back = (WebBackUsagePeriods)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
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
var periods = <%=back.jUsagePeriods()%>;
let fillPeriods = () => {
    if (periods.count === 0) return;
    var div = document.getElementById('periodlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < periods.count; n++) {
        addPeriod(periods.items[n], false);
    }
}
let addPeriod = (period) => {
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
    top.setAttribute('id', 'period' + period.idcode);
    top.setAttribute('style', 'padding: 8px 0px; border-bottom: solid 1px #ddd');
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*---------------------------------------------------*/
    /*Column Date Start*/{
        column = document.createElement("div");
        column.style.width = "130px";
        column.style.fontSize = "12px";
        column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(period.datestart);
        line.appendChild(column);
    }
    
    /*Column Event*/{
        column = document.createElement("div");
        column.style.width = "290px";
        column.style.fontSize = "12px";
        column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(period.event);
        line.appendChild(column);

    }    
    /*Column Date End*/{
        column = document.createElement("div");
        column.style.width = "130px";
        column.style.fontSize = "12px";
        column.style.color = "#666";
        column.style.textAlign = "left";
        column.innerHTML = decodeURIComponent(period.dateend);
        line.appendChild(column);
    }
    /*Column Project*/{
        column = document.createElement("div");
        column.style.width = "150px";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "left";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(period.projectname);
        line.appendChild(column);
    }
    /*Column Cost per day*/{
        column = document.createElement("div");
        column.style.width = "100px";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "right";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(period.costperday);
        line.appendChild(column);
    }
    /*Column Minutes*/{
        column = document.createElement("div");
        column.style.width = "100px";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "right";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(period.finalminutes);
        line.appendChild(column);
    }
    /*Column Final Cost*/{
        column = document.createElement("div");
        column.style.width = "100px";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "right";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(period.finalcost);
        line.appendChild(column);
    }
    /*Column Final Cost*/{
        column = document.createElement("div");
        column.style.flex = "1";
        column.style.fontSize = "14px";
        column.style.color = "#333";
        column.style.textAlign = "right";
        //column.style.backgroundColor = "#f00";
        column.innerHTML = decodeURIComponent(period.billingref);
        line.appendChild(column);
    }
    /*---------------------------------------------------*/
    top.appendChild(line);
    document.getElementById('periodlist').appendChild(top);
    /*---------------------------------------------------*/
}
</script>
<title>Usage Periods</title>
</head>
<body>
<%@include file="../../main/header.jsp" %>
<div class="content">
<h1>Periods page</h1>
<div style="padding: 8px 0px; border-bottom: solid 2px #09f; display: flex; 
     flex-direction: row; font-size: 14px; font-weight: 600; color: #555">
    <div style="width: 130px">Start</div>
    <div style="width: 290px">Event</div>
    <div style="width: 130px">End</div>
    <div style="width: 150px">Project</div>
    <div style="width: 100px; text-align: right">Cost Per Day</div>
    <div style="width: 100px; text-align: right">Minutes</div>
    <div style="width: 100px; text-align: right">Cost</div>
    <div style="flex: 1; text-align: right">Ledger Ref</div>
</div>
<div id="periodlist">
</div>
</div>
</body>
<script>
    fillPeriods();
</script>
</html>
