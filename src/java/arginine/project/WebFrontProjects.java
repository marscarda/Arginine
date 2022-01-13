package arginine.project;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.project.ExcProjectBack;
import methionine.auth.Session;
import methionine.project.Project;
//***************************************************************************
@WebServlet(name = "WebFrontProjects", urlPatterns = {WebFrontProjects.PAGE}, loadOnStartup=1)
public class WebFrontProjects extends WebFrontAlpha {
    public static final String PAGE = "/project/list";
    public static final String USERID = "userid";
    public static final String OFFSET = "offset";
    //***********************************************************************
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================
        if (this.toSSLIfLoggedIn(flowbeta)) return;
        if (this.toNonSSLIfNotLoggedIn(flowbeta)) return;
        //===================================================================
        //If there is not valid session.
        Session session = flowbeta.getSession();
        if (!session.isValid()) {
            this.toAuthPage(flowbeta);
            return;
        }
        //===================================================================
        boolean allowed = false;
        if (session.isAdmin()) allowed = true;
        //-------------------------------------------------------------------
        if (!allowed) {
            flowbeta.setStatusResponse(403);
            this.fleeRequest(flowbeta);
            return;
        }
        //===================================================================
        long userid = 0;
        int offset = 0;
        try { userid = Long.parseLong(request.getParameter(USERID)); } catch (Exception e) {}
        try { offset = Integer.parseInt(request.getParameter(OFFSET)); } catch (Exception e) {}
        //===================================================================
        WebBackProjects back = new WebBackProjects();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try{
            ExcProjectBack exc = new ExcProjectBack();
            exc.setAuriga(flowbeta.getAurigaObject());
            Project[] projects = exc.getProjectList(userid, offset);
            back.setProjects(projects);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get acount for user (Web) cantuser");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/project/projects.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //***********************************************************************
}
//***************************************************************************
