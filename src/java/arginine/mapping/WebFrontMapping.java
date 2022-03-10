package arginine.mapping;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import arginine.finantial.WebBackFinancialPanel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import threonine.mapping.MapLayer;
//***************************************************************************
@WebServlet(name = "WebFrontMapping", urlPatterns = {WebFrontMapping.PAGE}, loadOnStartup=1)
public class WebFrontMapping extends WebFrontAlpha {
    public static final String PAGE = "/mapping/home";
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
        WebBackMapping back = new WebBackMapping();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try {
            MapLayer[] layers = flowbeta.getAurigaObject().getMapsLambda().publicLayers();
            back.setLayers(layers);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failure in mapping page (Web) cananite");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/mapping/home.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
   //************************************************************************    
}
//***************************************************************************
