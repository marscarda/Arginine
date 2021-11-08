package arginine.depr_finantial;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JBilling;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.billing.LedgerEntry;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiFetchLedger", urlPatterns = {ApiFetchLedger.URL}, loadOnStartup=1)
public class ApiFetchLedger extends ApiAlpha {
    public static final String URL = "/account/fetchledger";
    public static final String JLEDGER = "ledger";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //==========================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //==========================================================
        Session session = flowalpha.getSession();
        boolean allowed = false;
        if (session.isAdmin()) allowed = true;
        if (!allowed) {
            this.sendErrorResponse(resp, "Unauthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        long userid = 0;
        try { userid = Long.parseLong(req.getParameter(USERID)); } catch (Exception e) {}
        try {
            /*
            LedgerEntry[] entries = flowalpha.getAurigaObject().getBillingLambda().getLedgerForUserID(userid);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "User list"));
            jsonresp.addPair(new JsonPair(JLEDGER, JBilling.getLedgerEntryList(entries)));
            this.sendResponse(resp, jsonresp);
            */
            //----------------------------------------
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to fetch ledger mrksgqwax");
            System.out.println(e.getMessage());            
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
    }
    //***********************************************************************
}
//***************************************************************************
