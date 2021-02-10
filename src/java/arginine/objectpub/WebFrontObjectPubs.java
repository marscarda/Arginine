package arginine.objectpub;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import serine.pubs.object.ObjectPub;
//***************************************************************************
@WebServlet(name = "WebFrontObjectPubs", urlPatterns = {WebFrontObjectPubs.PAGE}, loadOnStartup=1)
public class WebFrontObjectPubs extends WebFrontAlpha {
    public static final String PAGE = "/pubs/home";
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
        WebBackObjectPubs back = new WebBackObjectPubs();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        back.setLoginToken(session.getLoginToken());
        //-------------------------------------------------------------------
        try {
            ObjectPub[] pubs = flowbeta.getAurigaObject().getObjectPubsLambda().getObjectPubs();
            back.setObjectPubs(pubs);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get object pubs (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //-------------------------------------------------------------------
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/objpub/objpubs.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }    
    //***********************************************************************
}
//***************************************************************************

