<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="arginine.WebFrontStatic"%>
<%@page import="arginine.WebFrontAlpha"%>
<%@page import="arginine.project.WebBackProjects"%>
<% WebBackProjects back = (WebBackProjects)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
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
var projects = <%=back.jProjects()%>;



let fillProjects = () => {
    if (projects.count === 0) return;
    var n;
    var div = document.getElementById('projectlist');
    while (div.hasChildNodes())
        div.removeChild(div.lastChild);
    for (n = 0; n < projects.count; n++) {
        addProject(projects.items[n], false);
    }    
}




let addProject = (project) => {
    /*---------------------------------------------------*/
    var top;
    var line;
    var column;
    var space;
    /*---------------------------------------------------*/
    top = document.createElement("div");
    top.id = "projectbox" + project.sellpubid;
    top.style.border = "solid 4px #cdc";
    //else top.style.border = "solid 4px #8c5";
    //if (isnew) top.style.opacity = 0;
    top.style.borderRadius = "7px";
    top.style.width = "320px";
    top.style.float = "left";
    top.style.marginRight = "30px";
    top.style.marginTop = "30px";
    top.style.padding = "25px 20px";
    /*---------------------------------------------------*/
    /* Project Name */ {
        line = document.createElement("div");
        line.style.display = "flex";
        line.style.flexDirection = "row";
        line.style.fontSize = "15px";
        line.innerHTML = decodeURI(decodeURIComponent(project.name));
        top.appendChild(line);
    }
    
    /*---------------------------------------------------*/
    /* Project Name */ {
        line = document.createElement("div");
        line.style.display = "flex";
        line.style.flexDirection = "row";
        line.style.fontSize = "15px";
        line.innerHTML = "User name: " + project.ownername;
        top.appendChild(line);
    }
    /*---------------------------------------------------*/
    /* Project Name */ {
        line = document.createElement("div");
        line.style.display = "flex";
        line.style.flexDirection = "row";
        line.style.fontSize = "12px";
        line.style.color = "#666";
        line.style.marginTop = "6px";
        
        line.innerHTML = "Project ID: " + project.projectid;
        top.appendChild(line);
    }    
    /*---------------------------------------------------*/
    document.getElementById('projectlist').appendChild(top);    
    /*---------------------------------------------------*/
}
</script>
<title>Projects</title>
</head>
<body>
<%@include file="../main/header.jsp" %>
<div class="content">
    <div style="width: 100%; margin-top: 5px; padding: 20px 0px; border-top: solid 3px #baa">
        <div id="projectlist" style="width: 100%; font-weight: normal; margin-top: 10px">
        </div>        
    </div>
</div>
</body>
<script>
    fillProjects();
</script>
</html>
