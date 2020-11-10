package arginine.main;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import methionine.auth.User;
import methionine.project.Project;
import serine.webmedia.WebMediaList;
//***************************************************************************
@WebServlet(name = "WebFrontHome", urlPatterns = {WebFrontHome.PAGE}, loadOnStartup=1)
public class WebFrontHome extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/home";
    //=======================================================================
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================
        WebMediaList medlist = flowbeta.getAurigaObject().mediaList();
        //===================================================================
        Session session = flowbeta.getSession();
        User lggedinuser = flowbeta.getLogedUser();
        Project project = flowbeta.getCurrentProject();
        WebBackHome back = new WebBackHome();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(lggedinuser, project);
        back.setLoginToken(session.getLoginToken());
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/main/home.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************

