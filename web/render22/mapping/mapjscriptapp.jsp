<%@page contentType="application/javascript" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.mapping.app.ApiPointInRecordLayer"%>
<%@page import="arginine.mapping.app.ApiMapDisplaceLayer"%>
<%@page import="arginine.mapping.app.ApiMapChangeZoomLayer"%>
<%@page import="arginine.mapping.app.ApiMapGetDrawLayer"%>
<%@page import="arginine.ApiAlpha"%>
<%@page import="arginine.WebFrontStatic22"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.mapping.app.WebBackMapApp"%>
<% WebBackMapApp back = (WebBackMapApp)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
//<script>
let ZOOMIN = 1;
let ZOOMOUT = 2;
let PAN = 3;
let INFO = 4;
let GOIN = 5;
let STATS = 6;
class MapApp {
    static parentdivid = null;
    static reccount = 0;
    static layerid = 0;
    static universeid = 0;
    static subsetid = 0;
    static trialid = 0;
    static nodecode = 0;
    static records = [];
    static resetView = () => {
        MapView.scale = 0;
        MapView.clatitude = 0;
        MapView.clongitude = 0;
        MapApp.reccount = 0;
        MapApp.records = [];
    }
    static create = () => {
        var topparent = document.getElementById(MapApp.parentdivid);
        var mapparent = document.createElement("div");
        mapparent.setAttribute("id", "mapparent");
        mapparent.style.width = "100%";
        MapTools.setToolbar();
        topparent.appendChild(mapparent);
        MapView.setMapView();
    }
    static clearMapObjects = () => { 
        MapApp.reccount = 0;
        MapApp.records = [];
    }
}
class MapTools {
    static mouseaction = 0;
    static infoenabled = false;
    static goinenabled = false;
    static statsenabled = false;
    static selectInfoTool () { MapTools.setMapTool(INFO); }
    static selectGoInTool () { MapTools.setMapTool(GOIN); }
    static setMapTool = (action) => {
        var cnv = document.getElementById('cnvdrspc');
        switch (action) {
            case ZOOMIN:
                document.getElementById('mtinfo').className = 'maptool';
                document.getElementById('mtzoomin').className = 'maptoolselected';
                document.getElementById('mtzoomout').className = 'maptool';
                document.getElementById('mtpan').className = 'maptool';
                document.getElementById('mtentersublevel').className = 'maptool';
                document.getElementById('mtstats').className = 'maptool';
                MapTools.mouseaction = ZOOMIN;
                cnv.style.cursor = "url(<%--=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CURSORZOOMIN--%>), auto";
                break;
            case ZOOMOUT:
                document.getElementById('mtinfo').className = 'maptool';
                document.getElementById('mtzoomin').className = 'maptool';
                document.getElementById('mtzoomout').className = 'maptoolselected';
                document.getElementById('mtpan').className = 'maptool';
                document.getElementById('mtentersublevel').className = 'maptool';
                document.getElementById('mtstats').className = 'maptool';
                MapTools.mouseaction = ZOOMOUT;
                cnv.style.cursor = "url(<%--=back.getRootURL()%><%=WebFrontStatic20.PAGE%>/<%=WebFrontStatic20.CURSORZOOMOUT--%>), auto";
                break;
            case PAN:
                document.getElementById('mtinfo').className = 'maptool';
                document.getElementById('mtzoomin').className = 'maptool';
                document.getElementById('mtzoomout').className = 'maptool';
                document.getElementById('mtpan').className = 'maptoolselected';
                document.getElementById('mtentersublevel').className = 'maptool';
                document.getElementById('mtstats').className = 'maptool';
                MapTools.mouseaction = PAN;
                cnv.style.cursor = "url(<%=back.getRootURL()%><%--=WebFrontStatic20.PAGE%>/<%=WebFrontStatic20.CURSORPAN--%>), auto";
                break;
            case INFO:
                document.getElementById('mtinfo').className = 'maptoolselected';
                document.getElementById('mtzoomin').className = 'maptool';
                document.getElementById('mtzoomout').className = 'maptool';
                document.getElementById('mtpan').className = 'maptool';
                document.getElementById('mtentersublevel').className = 'maptool';
                document.getElementById('mtstats').className = 'maptool';
                MapTools.mouseaction = INFO;
                cnv.style.cursor = "crosshair";
                break;
            case GOIN:
                document.getElementById('mtinfo').className = 'maptool';
                document.getElementById('mtzoomin').className = 'maptool';
                document.getElementById('mtzoomout').className = 'maptool';
                document.getElementById('mtpan').className = 'maptool';
                document.getElementById('mtentersublevel').className = 'maptoolselected';
                document.getElementById('mtstats').className = 'maptool';
                MapTools.mouseaction = GOIN;
                cnv.style.cursor = "crosshair";
                break;
            case STATS:
                document.getElementById('mtinfo').className = 'maptool';
                document.getElementById('mtzoomin').className = 'maptool';
                document.getElementById('mtzoomout').className = 'maptool';
                document.getElementById('mtpan').className = 'maptool';
                document.getElementById('mtentersublevel').className = 'maptool';
                document.getElementById('mtstats').className = 'maptoolselected';
                MapTools.mouseaction = STATS;
                cnv.style.cursor = "crosshair";
                break;
            default:
                document.getElementById('mtinfo').className = 'maptool';
                document.getElementById('mtstats').className = 'maptool';
                document.getElementById('mtentersublevel').className = 'maptoolselected';
                document.getElementById('mtzoomin').className = 'maptool';
                document.getElementById('mtzoomout').className = 'maptool';
                document.getElementById('mtpan').className = 'maptool';
                MapTools.mouseaction = 0;
        }
    }
    static cancelMapTool = () => { MapTools.setMapTool(0); }
    static createStyles = () => {
        var style;
        style = document.createElement('style');
        style.type = 'text/css';
        style.innerHTML = '.maptool { padding: 3px; border: solid 1px #fff; border-radius: 3px; margin-right: 2px; float: left; }';
        document.getElementsByTagName('head')[0].appendChild(style);
        style = document.createElement('style');
        style.type = 'text/css';
        style.innerHTML = '.maptoolselected { padding: 3px; border: solid 1px #05f; border-radius: 3px; margin-right: 2px; float: left; }';
        document.getElementsByTagName('head')[0].appendChild(style);
    }
    static setToolbar = () => {
        /*--------------------------------------------------------*/
        MapTools.createStyles();
        /*--------------------------------------------------------*/
        var tool;
        var link;
        var img;
        var toolbar = document.createElement('div');
        toolbar.style.marginBottom = "20px";
        toolbar.style.borderBottom = "solid 1px #e5e5e5";
        toolbar.style.padding = "0px 0px 6px 0px"
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.setAttribute("id", "mtinfo");
        tool.setAttribute("class", "maptool");
        link = document.createElement("a");
        if (MapTools.infoenabled) {
            link.href = "#";
            link.onclick = () => {
                MapTools.setMapTool(INFO);
                return false;
            }
        }
        else tool.style.opacity = 0.3;
        img = document.createElement("img");
        img.setAttribute("src", "<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.MAPRECINFOICON%>")
        img.style.width = "22px";
        img.style.height = "22px";
        link.appendChild(img);
        tool.appendChild(link);
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.setAttribute("id", "mtentersublevel");
        tool.setAttribute("class", "maptool");
        link = document.createElement("a");
        if (MapTools.goinenabled) {
            link.href = "#";
            link.onclick = () => {
                MapTools.setMapTool(GOIN);
                return false;
            }
        }
        else tool.style.opacity = 0.3;
        img = document.createElement("img");
        img.setAttribute("src", "<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.MAPENTERICON%>")
        img.style.width = "22px";
        img.style.height = "22px";
        link.appendChild(img);
        tool.appendChild(link);
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.setAttribute("id", "mtstats");
        tool.setAttribute("class", "maptool");
        link = document.createElement("a");
        if (MapTools.statsenabled) {
            link.href = "#";
            link.onclick = () => {
                MapTools.setMapTool(STATS);
                return false;
            }
        }
        else tool.style.opacity = 0.3;
        img = document.createElement("img");
        img.setAttribute("src", "<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.MAPSTATSICON%>")
        img.style.width = "22px";
        img.style.height = "22px";
        link.appendChild(img);
        tool.appendChild(link);
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.setAttribute("id", "mtzoomin");
        tool.setAttribute("class", "maptool");
        link = document.createElement("a");
        link.setAttribute("href", "#");
        link.onclick = () => {
            MapTools.setMapTool(1);
            return false;
        }
        img = document.createElement("img");
        img.setAttribute("src", "<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.ZOOMINICON%>")
        img.style.width = "22px";
        img.style.height = "22px";
        link.appendChild(img);
        tool.appendChild(link);
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.setAttribute("id", "mtzoomout");
        tool.setAttribute("class", "maptool");
        link = document.createElement("a");
        link.setAttribute("href", "#");
        link.onclick = () => {
            MapTools.setMapTool(2);
            return false;
        }
        img = document.createElement("img");
        img.setAttribute("src", "<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.ZOOMOUTICON%>")
        img.style.width = "22px";
        img.style.height = "22px";
        link.appendChild(img);
        tool.appendChild(link);
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.setAttribute("id", "mtpan");
        tool.setAttribute("class", "maptool");
        link = document.createElement("a");
        link.setAttribute("href", "#");
        link.onclick = () => {
            MapTools.setMapTool(3);
            return false;
        }
        img = document.createElement("img");
        img.setAttribute("src", "<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.HANDICON%>")
        img.style.width = "22px";
        img.style.height = "22px";
        link.appendChild(img);
        tool.appendChild(link);
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        tool = document.createElement("div");
        tool.style.clear = "both";
        toolbar.appendChild(tool);
        /*--------------------------------------------------------*/
        var topparent = document.getElementById(MapApp.parentdivid);
        topparent.appendChild(toolbar);
        /*--------------------------------------------------------*/
    }
}
class MapView {
    static canvaswidth = 0;
    static canvasheight = 360;
    static scale = 0;
    static ctrlatitude = 0;
    static ctrlongitude = 0;
    static drawfadein = true;
    static usractionfirstx;
    static usractionfirsty;
    static usractionprevx;
    static usractionprevy;
    static usractioncurrentx;
    static usractioncurrenty;
    static displacing = false;
    static aux = 0;
    static clearMapView = () => {
        var div = document.getElementById('mapparent');
        while (div.hasChildNodes())
            div.removeChild(div.lastChild);
    }
    static setMapView = () => {
        var div = document.getElementById('mapparent');
        var cnv = document.createElement("canvas");
        MapView.canvaswidth = div.clientWidth;
        cnv.setAttribute("id", "cnvdrspc");
        cnv.setAttribute("width", MapView.canvaswidth);
        cnv.setAttribute("height", MapView.canvasheight);
        cnv.onclick = () => {
            MapView.mapClick();
            return false;
        }
        cnv.onmousedown = () => {
            MapView.pressStatusChange(1);
            return false;
        }
        cnv.onmouseup = () => {
            MapView.pressStatusChange(0);
            return false;
        }
        cnv.onmousemove = () => {
            MapView.mouseMove();
            return false;
        }
        switch(MapTools.mouseaction) {
            case ZOOMIN: 
                cnv.style.cursor = "url(<%=back.getRootURL()%><%--=WebFrontStatic20.PAGE%>/<%=WebFrontStatic20.CURSORZOOMIN--%>), auto";
                break;
            case ZOOMOUT:
                cnv.style.cursor = "url(<%=back.getRootURL()%><%--=WebFrontStatic20.PAGE%>/<%=WebFrontStatic20.CURSORZOOMOUT--%>), auto";
                break;
            case PAN:
                cnv.style.cursor = "url(<%=back.getRootURL()%><%--=WebFrontStatic20.PAGE%>/<%=WebFrontStatic20.CURSORPAN--%>), auto";
                break;
        }
        div.appendChild(cnv);
    }
    static renderMap = () => {
        if (MapApp.records === null) return;
        if (MapApp.reccount === 0) return;
        var r, o, p, record, object, point;
        var canvas = document.getElementById('cnvdrspc');
        if (MapView.drawfadein) canvas.style.opacity = 0;
        var ctx = canvas.getContext("2d");
        if (MapView.displacing) {
            ctx.strokeStyle = "#bbb";
            ctx.lineWidth = 2.5;
        } 
        else {
            ctx.fillStyle = "#f9f9f9";
            ctx.strokeStyle = "#000055";
            ctx.lineWidth = 0.3;
        }
        ctx.clearRect(0, 0, MapView.canvaswidth, MapView.canvasheight);        
        for (r = 0; r < MapApp.reccount; r++) {
            record = MapApp.records[r];
            ctx.beginPath();
            for (o = 0; o < record.objcount; o++) {
                object = record.objects[o];
                for (p = 0; p < object.ptcount; p++) {
                    point = object.points[p];
                    if (p === 0) ctx.moveTo(point.x, point.y);
                    else ctx.lineTo(point.x, point.y);
                }
            }
            if (!MapView.displacing) ctx.fill("evenodd");
            ctx.stroke();
        }
        if (MapView.drawfadein) {
            var turnon = new ElementFadeIn();
            turnon.setElement('cnvdrspc');
            turnon.start();
            MapView.drawfadein = false;
        }
    }
    static mapClick = () => {
        if (MapApp.reccount === 0) {
            showToast('No map to interact with', '#0099ff');
            return;
        }
        switch (MapTools.mouseaction) {
            case 0: {
                showToast('No map action selected. Please se one from above the map', '#0099ff');
                return;
            }
            case INFO: 
            case GOIN:
            case STATS:
                MapView.recordInPoint();
                break;
            case ZOOMIN: MapView.changeZoom(1.5); break;
            case ZOOMOUT: MapView.changeZoom(0.7); break;
        }
    }
    static getMapDrawing = () => {
        var req = new HttpRequest();
        var callback = (status, objresp) => {
            if (status === 0) {
                showToast('Fetching Map Drawing: Could not connect to server', '#ff3333');
                return;
            }
            if (status !== 200) {
                showToast('Fetching Map Drawing: Error server. Probably in maintenance', '#ff3333');
                return;
            }
            if (objresp.result !== 'OK') {
                showToast("Fetching Map Drawing: " + objresp.description, '#ff3333');
                return;
            }
            var draw = objresp.mapdrawing;
            MapView.ctrlatitude = draw.ctrlatitude;
            MapView.ctrlongitude = draw.ctrlongitude;
            MapView.scale = draw.mapscale;
            MapApp.reccount = draw.reccount;
            MapApp.records = draw.records;
            MapView.renderMap();
        }
        req.setCallBack(callback);
        req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
        req.addParam('<%=ApiMapGetDrawLayer.LAYERID%>', MapApp.layerid);
        req.addParam('<%=ApiMapGetDrawLayer.CANVASWIDTH%>',MapView.canvaswidth);
        req.addParam('<%=ApiMapGetDrawLayer.CANVASHEIGHT%>',MapView.canvasheight);
        req.addParam('<%=ApiMapGetDrawLayer.MAPSCALE%>', MapView.scale);
        req.addParam('<%=ApiMapGetDrawLayer.CENTERLATITUDE%>', MapView.ctrlatitude);
        req.addParam('<%=ApiMapGetDrawLayer.CENTERLONGITUDE%>', MapView.ctrlongitude);
        req.setURL('<%=back.apiGetDrawingURL()%>');
        try { req.executepost(); }
        catch(err) { alert (err.getMessage); }
    }
    static changeZoom = (factor) => {
        var x = event.offsetX;
        var y = event.offsetY;
        var req = new HttpRequest();
        var callback = (status, objresp) => {
            if (status === 0) {
                showToast('Could not connect to server', '#ff3333');
                return;
            }
            if (status !== 200) {
                showToast('Error server. Probably in maintenance', '#ff3333');
                return;
            }
            if (objresp.result !== 'OK') {
                showToast(objresp.description, '#ff3333');
                return;
            }
            var draw = objresp.mapdrawing;
            MapView.ctrlatitude = draw.ctrlatitude;
            MapView.ctrlongitude = draw.ctrlongitude;
            MapView.scale = draw.mapscale;
            MapApp.reccount = draw.reccount;
            MapApp.records = draw.records;
            MapView.renderMap();
        }
        req.setCallBack(callback);
        req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
        req.addParam('<%=ApiMapChangeZoomLayer.LAYERID%>', MapApp.layerid);
        req.addParam('<%=ApiMapChangeZoomLayer.CANVASWIDTH%>',MapView.canvaswidth);
        req.addParam('<%=ApiMapChangeZoomLayer.CANVASHEIGHT%>',MapView.canvasheight);
        req.addParam('<%=ApiMapChangeZoomLayer.MAPSCALE%>',MapView.scale);
        req.addParam('<%=ApiMapChangeZoomLayer.CENTERLATITUDE%>',MapView.ctrlatitude);
        req.addParam('<%=ApiMapChangeZoomLayer.CENTERLONGITUDE%>',MapView.ctrlongitude);
        req.addParam('<%=ApiMapChangeZoomLayer.EVENTX%>',x);
        req.addParam('<%=ApiMapChangeZoomLayer.EVENTY%>',y);
        req.addParam('<%=ApiMapChangeZoomLayer.SCALEFACTOR%>',factor);
        req.setURL('<%=back.apiChangeZoom()%>');
        try { req.executepost(); }
        catch(err) { alert (err.getMessage); }    
    }
    static displaceMap = () => {
        var req = new HttpRequest();
        var callback = (status, objresp) => {
            if (status === 0) {
                showToast('Could not connect to server', '#ff3333');
                return;
            }
            if (status !== 200) {
                showToast('Error server. Probably in maintenance', '#ff3333');
                return;
            }
            if (objresp.result !== 'OK') {
                showToast(objresp.description, '#ff3333');
                return;
            }
            var draw = objresp.mapdrawing;
            MapView.ctrlatitude = draw.ctrlatitude;
            MapView.ctrlongitude = draw.ctrlongitude;
            MapView.scale = draw.mapscale;
            MapApp.reccount = draw.reccount;
            MapApp.records = draw.records;
            MapView.renderMap();
        }
        req.setCallBack(callback);
        req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
        req.addParam('<%=ApiMapDisplaceLayer.LAYERID%>', MapApp.layerid);
        req.addParam('<%=ApiMapDisplaceLayer.CANVASWIDTH%>',MapView.canvaswidth);
        req.addParam('<%=ApiMapDisplaceLayer.CANVASHEIGHT%>',MapView.canvasheight);
        req.addParam('<%=ApiMapDisplaceLayer.MAPSCALE%>',MapView.scale);
        req.addParam('<%=ApiMapDisplaceLayer.CENTERLATITUDE%>',MapView.ctrlatitude);
        req.addParam('<%=ApiMapDisplaceLayer.CENTERLONGITUDE%>',MapView.ctrlongitude);
        req.addParam('<%=ApiMapDisplaceLayer.EVENTFROMX%>',MapView.usractionfirstx);
        req.addParam('<%=ApiMapDisplaceLayer.EVENTFROMY%>',MapView.usractionfirsty);
        req.addParam('<%=ApiMapDisplaceLayer.EVENTTOX%>',MapView.usractioncurrentx);
        req.addParam('<%=ApiMapDisplaceLayer.EVENTTOY%>',MapView.usractioncurrenty);
        req.setURL('<%=back.apiDisplaceMapURL()%>');
        try { req.executepost(); }
        catch(err) { alert (err.getMessage); }
    }
    static recordInPoint = () => {
        var x = event.offsetX;
        var y = event.offsetY;
        var req = new HttpRequest();
        var callback = (status, objresp) => {
            if (status === 0) {
                showToast('Could not connect to server', '#ff3333');
                return;
            }
            if (status !== 200) {
                showToast('Error server. Probably in maintenance', '#ff3333');
                return;
            }
            if (objresp.result !== 'OK') {
                showToast(objresp.description, '#ff3333');
                return;
            }
            MapView.clickedRecordsReady(objresp.idlist, MapTools.mouseaction);
        }
        req.setCallBack(callback);
        req.addParam('<%=ApiAlpha.CREDENTIALTOKEN%>','<%=back.loginToken()%>');
        req.addParam('<%=ApiPointInRecordLayer.LAYERID%>',MapApp.layerid);
        req.addParam('<%=ApiPointInRecordLayer.CANVASWIDTH%>',MapView.canvaswidth);
        req.addParam('<%=ApiPointInRecordLayer.CANVASHEIGHT%>',MapView.canvasheight);
        req.addParam('<%=ApiPointInRecordLayer.MAPSCALE%>',MapView.scale);
        req.addParam('<%=ApiPointInRecordLayer.CENTERLATITUDE%>',MapView.ctrlatitude);
        req.addParam('<%=ApiPointInRecordLayer.CENTERLONGITUDE%>',MapView.ctrlongitude);
        req.addParam('<%=ApiPointInRecordLayer.EVENTX%>',x);
        req.addParam('<%=ApiPointInRecordLayer.EVENTY%>',y);
        req.setURL('<%=back.apiFindRecordInPoint()%>');
        try { req.executepost(); }
        catch(err) { alert (err.getMessage); }    
    }
    static pressStatusChange = (status) => {
        if (status === 1) {
            MapView.usractionfirstx = event.offsetX;
            MapView.usractionfirsty = event.offsetY;
            MapView.usractionprevx = MapView.usractionfirstx;
            MapView.usractionprevy = MapView.usractionfirsty;
            switch (MapTools.mouseaction) {
                case 3:
                    MapView.displacing = true;
                    return;
            }
        }
        else {
            switch (MapTools.mouseaction) {
                case 3:
                    MapView.displacing = false;
                    MapView.displaceMap();
                    return;
            }
        }
    }
    static mouseMove () {
        if (MapTools.mouseaction === 0) return;
        if (MapTools.mouseaction === 1) return;
        if (MapTools.mouseaction === 2) return;
        MapView.usractioncurrentx = event.offsetX;
        MapView.usractioncurrenty = event.offsetY;
        if (MapView.displacing) {
            if (MapView.aux < 5) { MapView.aux++; return; }
            MapView.aux = 0;
            var difx = MapView.usractioncurrentx - MapView.usractionprevx;
            var dify = MapView.usractioncurrenty - MapView.usractionprevy;
            MapView.usractionprevx = MapView.usractioncurrentx;
            MapView.usractionprevy = MapView.usractioncurrenty;
            var r, o, p, record, object, point, difx, dify;
            for (r = 0; r < MapApp.reccount; r++) {
                record = MapApp.records[r];
                for (o = 0; o < record.objcount; o++) {
                    object = record.objects[o];
                    for (p = 0; p < object.ptcount; p++) {
                        point = object.points[p];
                        point.x = point.x + difx;
                        point.y = point.y + dify;
                    }
                }
            }
            MapView.renderMap();
        }
    }
    static changeViewEvent = () => {};
    static clickedRecordsReady = (idlist, maptool) => {};
}
//</script>