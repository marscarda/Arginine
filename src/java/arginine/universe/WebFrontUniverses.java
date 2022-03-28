package arginine.universe;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import arginine.mapping.WebBackMapping;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import threonine.universe.Universe;
//***************************************************************************
@WebServlet(name = "WebFrontUniverses", urlPatterns = {WebFrontUniverses.PAGE}, loadOnStartup=1)
public class WebFrontUniverses extends WebFrontAlpha {
    public static final String PAGE = "/universe/templates";
   //************************************************************************    
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
        WebBackUniverses back = new WebBackUniverses();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try {
            //---------------------------------------------------------------
            Universe[] templates = flowbeta.getAurigaObject().getUniverseAtlas().getTemplates();
            back.setUniverses(templates);
            //---------------------------------------------------------------
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failure in universe templates page (hertyant)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render22/universe/home.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
   //************************************************************************    
}
//***************************************************************************
