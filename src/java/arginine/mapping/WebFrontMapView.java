package arginine.mapping;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.sample.ExcSamplePanel;
import methionine.auth.Session;
import tryptophan.sample.ResponseCall;
import tryptophan.sample.Sample;
//***************************************************************************
@WebServlet(name = "WebFrontMapView", urlPatterns = {WebFrontMapView.URLPATTERN}, loadOnStartup=1)
public class WebFrontMapView extends WebFrontAlpha {
    public static final String PAGE = "/mapping/viewlayer";
    public static final String URLPATTERN = PAGE + "/*";
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
        long layerid = 0;
        try { layerid = Long.parseLong(this.getURLsParamPart(request)); } catch (Exception e) {}
        WebBackMapView back = new WebBackMapView();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try {
            
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failure in web responses calls page. janefa");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render22/mapping/maplayerview.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //************************************************************************
}
//***************************************************************************
