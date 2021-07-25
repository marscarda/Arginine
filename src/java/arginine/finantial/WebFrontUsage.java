package arginine.finantial;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.billing.BillingPeriod;
import methionine.billing.UsageQueryData;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "WebFrontUsage", urlPatterns = {WebFrontUsage.PAGE}, loadOnStartup=1)
public class WebFrontUsage extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/account/usagebilling";
    //=======================================================================
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
        WebBackUsage back = new WebBackUsage();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try{
            UsageQueryData querydata = new UsageQueryData();
            BillingPeriod[] periods = flowbeta.getAurigaObject().getBillingLambda().getBillingPeriods(querydata);
            back.setPeriods(periods);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Web page usage bghfgfh");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/finantial/usage.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************
