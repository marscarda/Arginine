package arginine.finantial;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JBilling;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methinine.billing.LedgerEntry;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiAddLedgerEntry", urlPatterns = {ApiAddLedgerEntry.URL}, loadOnStartup=1)
public class ApiAddLedgerEntry extends ApiAlpha {
    public static final String URL = "/users/addledgerentry";
    public static final String USERID = "userid";
    public static final String DESCRIPTION = "description";
    public static final String SIZE = "size";
    public static final String JENTRY = "entry";
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
        int size = 0;
        String description = req.getParameter(DESCRIPTION);
        try { userid = Long.parseLong(req.getParameter(USERID)); } catch (Exception e) {}
        try { size = Integer.parseInt(req.getParameter(SIZE)); } catch (Exception e) {}
        try {
            LedgerEntry entry = new LedgerEntry();
            entry.setUserID(userid);
            entry.setDescription(description);
            entry.setSize(size);
            flowalpha.getAurigaObject().getBillingLambda().addEntryToLedger(entry);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Ledger Entry Added"));
            //-------------------------------------------------------
            jsonresp.addPair(new JsonPair(JENTRY, JBilling.getLedgerEntry(entry)));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
        }
//        catch (AppException e) {
//            this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode());
//        }        
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to create fundpost");
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
