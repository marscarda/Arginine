package arginine.finantial;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.billing.LedgerEntry;
import methionine.billing.Payment;
import methionine.auth.Session;
import methionine.auth.User;
//***************************************************************************
@WebServlet(name = "WebFrontUserAccount", urlPatterns = {WebFrontUserAccount.URLPATTERN}, loadOnStartup=1)
public class WebFrontUserAccount extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/account/useraccount";
    public static final String URLPATTERN = PAGE + "/*";
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
        long userid = 0;
        try { userid = Long.parseLong(this.getURLsParamPart(request)); } catch (Exception e) {}
        //===================================================================
        WebBackUserAccount back = new WebBackUserAccount();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try{
            User user = flowbeta.getAurigaObject().getAuthLambda().getUser(userid, true);
            //int totalbalance = flowbeta.getAurigaObject().getBillingLambda().getTotalBalanceForUserID(userid);
            Payment[] payments = flowbeta.getAurigaObject().getBillingLambda().getPayments(userid);
            LedgerEntry[] ledger = flowbeta.getAurigaObject().getBillingLambda().getLedgerForUserID(userid);
            back.setUser(user);
            //back.setTotalBalance(totalbalance);
            back.setPayments(payments);
            back.setLedger(ledger);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get acount for user (Web) vfwqlmc");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/finantial/useraccount.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************
