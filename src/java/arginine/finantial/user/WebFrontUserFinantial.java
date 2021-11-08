package arginine.finantial.user;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import histidine.billing.ExcLedger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import methionine.auth.User;
import methionine.billing.LedgerItem;
//***************************************************************************
@WebServlet(name = "WebFrontUserFinantial", urlPatterns = {WebFrontUserFinantial.URLPATTERN}, loadOnStartup=1)
public class WebFrontUserFinantial extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/finantial/user";
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
        long userid = 0;
        try { userid = Long.parseLong(this.getURLsParamPart(request)); } catch (Exception e) {}
        //===================================================================
        WebBackUserFinantial back = new WebBackUserFinantial();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try{
            ExcLedger exec = new ExcLedger();
            exec.setAuriga(flowbeta.getAurigaObject());
            User user = flowbeta.getAurigaObject().getAuthLambda().getUser(userid, true);
            LedgerItem[] ledger = exec.getLedgerByUser(userid, session.getUserId());
            back.setUser(user);
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
        this.dispatchNormal("/render2020/user/finantial/home.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************
