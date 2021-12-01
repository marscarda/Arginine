package arginine.finantial.user;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import histidine.finance.ExcLedgerBackOfice;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import methionine.auth.User;
import methionine.finance.ComunityTransfer;
//***************************************************************************
@WebServlet(name = "WebFrontTransfer", urlPatterns = {WebFrontTransfer.URLPATTERN}, loadOnStartup=1)
public class WebFrontTransfer extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/finantial/user/transfer";
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
        WebBackTransfer back = new WebBackTransfer();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try {
            ExcLedgerBackOfice exec = new ExcLedgerBackOfice();
            exec.setAuriga(flowbeta.getAurigaObject());
            ComunityTransfer[] transfers = exec.getTransferByUser(userid, session.getUserId());
            User user = flowbeta.getAurigaObject().getAuthLambda().getUser(userid, true);
            back.setUser(user);
            back.setTransfers(transfers);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failure in transfers page for user (Web) banijar");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }            
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/user/finantial/transfer.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************
