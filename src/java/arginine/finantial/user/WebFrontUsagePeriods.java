package arginine.finantial.user;
//***************************************************************************
import arginine.FlowBeta;
import arginine.finantial.WebFrontFinancialPanel;
import histidine.finance.ExcLedgerBackOfice;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import methionine.billing.UsagePeriod;
//***************************************************************************
@WebServlet(name = "WebFrontUsagePeriods", urlPatterns = {WebFrontUsagePeriods.URLPATTERN}, loadOnStartup=1)
public class WebFrontUsagePeriods extends WebFrontFinancialPanel {
    //=======================================================================
    public static final String PAGE = "/finantial/user/usage";
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
        WebBackUsagePeriods back = new WebBackUsagePeriods();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try{
            ExcLedgerBackOfice exec = new ExcLedgerBackOfice();
            exec.setAuriga(flowbeta.getAurigaObject());
            exec.setAuriga(flowbeta.getAurigaObject());
            UsagePeriod[] periods = exec.getPeriodsByUser(userid, session.getUserId());
            back.setUsagePeriods(periods);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get usage for user (Web) vfwqlmc");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/user/finantial/periods.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************
