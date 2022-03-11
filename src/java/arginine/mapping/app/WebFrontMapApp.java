package arginine.mapping.app;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "WebFrontMapApp", urlPatterns = {WebFrontMapApp.PAGE}, loadOnStartup=1)
public class WebFrontMapApp extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/maps/gftrgfsfd6rgfgdf";
    //=======================================================================
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //-------------------------------------------------------------------
        //This prevents redirecting to here.
        flowbeta.getCookiesFlow().blockGoBackTo();
        //===================================================================
        Session session = flowbeta.getSession();
        WebBackMapApp back = new WebBackMapApp();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        back.setLoginToken(session.getLoginToken());
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        response.setHeader("Cache-Control", "max-age=30, max-stale=30");
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2022/mapping/mapjscriptapp.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************