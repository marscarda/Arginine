<%@page import="arginine.ApiAlpha"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.finantial.WebBackUsage"%>
<%@page import="arginine.WebFrontStatic"%>
<%
    WebBackUsage back = (WebBackUsage)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSROOT%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSFORM%>">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.CSSPOPUP%>">
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSHTTP%>"></script>
<style>
    a.filterperiods {
        color: #05f;
        text-decoration: none;
        font-size: 15px
    }
</style>
<script src="<%=back.getRootURL()%><%=WebFrontStatic.PAGE%>/<%=WebFrontStatic.JSTAGANIMATE%>"></script>
<script>
var periods = <%=back.jperiods()%>;
let fillPeriods = (fade) => {
    var div = document.getElementById('periodslist');
    if (fade) div.style.opacity = 0;
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < periods.count; n++) {
        addUsagePeriod(periods.items[n]);
    }
    if (fade) {
        var turnon = new ElementFadeIn();
        turnon.setElement(document, 'periodslist');
        turnon.start();
    }
}
function addUsagePeriod (period) {
    var top;
    var line;
    var column;
    var link;
    top = document.createElement("div");
    top.setAttribute('id', 'ledgerentry' + period.idecode);
    top.setAttribute('style', 'padding: 8px 0px; border-bottom: solid 1px #dddddd');
    /*----------------------------------------*/
    line = document.createElement("div");
    line.setAttribute('style', 'display: flex; flex-direction: row');
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 120px; font-size: 11px; font-weight: normal; color: #222");
    column.innerHTML = decodeURIComponent(decodeURI(period.datestart));
    line.appendChild(column);   
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 180px; font-size: 13px; font-weight: normal; color: #222");
    column.innerHTML = decodeURI(period.event);
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 120px; font-size: 11px; font-weight: normal; color: #222");
    column.innerHTML = decodeURIComponent(decodeURI(period.dateend));
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 210px; font-size: 13px; font-weight: normal; color: #222");
    column.innerHTML = decodeURIComponent(decodeURI(period.projectname));
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 90px; font-size: 13px; font-weight: normal; color: #222; text-align: right");
    column.innerHTML = decodeURIComponent(decodeURI(period.costperday));
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 90px; font-size: 13px; font-weight: normal; color: #222; text-align: right");
    column.innerHTML = decodeURIComponent(decodeURI(period.finalminutes));
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "width: 90px; font-size: 13px; font-weight: normal; color: #222; text-align: right");
    column.innerHTML = decodeURIComponent(decodeURI(period.finalcost));
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    column = document.createElement("div");
    column.setAttribute("style", "flex: 1; font-size: 13px; font-weight: normal; color: #222; text-align: right");
    if (period.billed !== 0) column.innerHTML = "Yes"
    else column.innerHTML = "No"
    line.appendChild(column);
    top.appendChild(line);
    /*----------------------------------------*/
    document.getElementById('periodslist').appendChild(top);
    /*----------------------------------------*/
}
let loadUsagePeriods = () => {
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
        console.log(objresp);
        periods = objresp.periods;
        fillPeriods(true);
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
    req.setURL('<%=back.fetchUsagePeriodsURL()%>');
    try { 
        req.executepost(); 
    }
    catch(err) {
        alert (err.getMessage);
    }
}
</script>
<title>System Usage</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
<h2>Usage & Billing</h2>
<div style="display: flex; flex-direction: row; margin-top: 30px">
    <div style="width: 180px">
        <div style="padding: 10px 0px; border-top: solid 1px #ccc; border-bottom: solid 1px #ccc">
            <div style="font-size: 12px; font-weight: 600">View Periods</div>
            <div style="margin-top: 10px">
                <a href="#" class="filterperiods" onclick="loadUsagePeriods(); return false;" >All</a>
            </div>
            <div style="margin-top: 10px">
                <a href="" class="filterperiods">Open</a>
            </div>
            <div style="margin-top: 10px; color: #888">
                Closed
            </div>
            <div style="margin-left: 15px; margin-top: 5px">
                <a href="" class="filterperiods">All</a>
            </div>
            <div style="margin-left: 15px; margin-top: 5px">
                <a href="" class="filterperiods">Billed</a>
            </div>
            <div style="margin-left: 15px; margin-top: 5px">
                <a href="" class="filterperiods">Not billed</a>
            </div>
        </div>
        <div style="margin-top: 20px; font-size: 13px; color: #666">Close periods</div>
        <form id="formsearchusers" class="docount">
        <div>
          <input type="text" placeholder="N Periods to close" name="<%--=ApiGetUserList.STARTAT--%>">
          <button onclick="fetchUserList(); return false">Close</button>
        </div>
        </form>
        <div style="clear: both"></div>
        <div style="margin-top: 20px; font-size: 13px; color: #666">Bill periods</div>
        <form id="formsearchusers" class="docount">
        <div>
          <input type="text" placeholder="N periods to bill" name="<%--=ApiGetUserList.STARTAT--%>">
          <button onclick="fetchUserList(); return false">Bill</button>
        </div>
        </form>
        <div style="clear: both"></div>
    </div>
    <div style="width: 1px; background-color: #dddddd; margin-left: 30px"></div>
    <div style="flex: 1; margin-left: 30px">
        <div style="width: 100%; font-weight: normal; border-bottom: solid 1px #ccc">
            <div style="display: flex; flex-direction: row; padding: 5px 0px">
                <div style="width: 300px; font-size: 14px; font-weight: normal; color: #444">Start</div>
                <div style="width: 120px; font-size: 14px; font-weight: normal; color: #444">End</div>
                <div style="width: 210px; font-size: 14px; font-weight: normal; color: #444">Project</div>
                <div style="width: 90px; font-size: 14px; font-weight: normal; color: #444; text-align: right">Cost/day</div>
                <div style="width: 90px; font-size: 14px; font-weight: normal; color: #444; text-align: right">Minutes</div>
                <div style="width: 90px; font-size: 14px; font-weight: normal; color: #444; text-align: right">Final cost</div>
                <div style="flex: 1; font-size: 12px; font-weight: normal; color: #888; text-align: right">Billed</div>
            </div>
        </div>
        <div id="periodslist" style="width: 100%; margin-top: 10px; font-weight: normal"></div>
    </div>    
    
</div>    
</div>
</body>
<script>fillPeriods(false);</script>
</html>