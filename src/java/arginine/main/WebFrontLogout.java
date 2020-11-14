package arginine.main;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "WebFrontLogout", urlPatterns = {WebFrontLogout.PAGE})
public class WebFrontLogout extends WebFrontAlpha {
    //***********************************************************************
    public static final String PAGE = "/logout";
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
        if (this.forceToSSL(flowbeta)) return;
        //===================================================================
        Session session = flowbeta.getSession();
        if (!session.isValid()) {
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
            fleeRequest(flowbeta);
            return;
        }
        //===================================================================
        try {
            flowbeta.getAurigaObject().getAuthLambda().destroySession(session.getUserId(), session.getSessionId(), null);
            flowbeta.getCookiesFlow().setLoggedIn(false);
            flowbeta.getCookiesFlow().setLoginToken(null);
            flowbeta.getCookiesFlow().commitCookies();
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
        }
        catch (Exception e) {
            this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/logout.jsp", request, response);
            System.out.println("Failed to destroy session");
            System.out.println(e.getMessage());
        }
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }    
}
//***************************************************************************

