package arginine.finantial;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import histidine.finance.ExcLedgerBackOfice;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiBillUsage", urlPatterns = {ApiBillUsage.URL}, loadOnStartup=1)
public class ApiBillUsage extends ApiAlpha {
    public static final String URL = "/finantial/billperiods";
   //************************************************************************
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
            this.sendErrorResponse(resp, "Unuthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        try {
            ExcLedgerBackOfice exec = new ExcLedgerBackOfice();
            exec.setAuriga(flowalpha.getAurigaObject());
            exec.billPeriods(session.getUserId());
            //-------------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Periods billing started"));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }        
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Failed to add credit");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
   //************************************************************************
}
//***************************************************************************
