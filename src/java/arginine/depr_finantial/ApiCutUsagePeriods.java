package arginine.depr_finantial;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import static arginine.depr_finantial.ApiFetchUsagePeriods.OPEN;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiCutUsagePeriods", urlPatterns = {ApiCutUsagePeriods.URL}, loadOnStartup=1)
public class ApiCutUsagePeriods extends ApiAlpha {
    public static final String URL = "/account/cutusageperiods";
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
        int count = 0;
        try { count = Integer.parseInt(req.getParameter(COUNT)); } catch(Exception e) {}
        try {
            flowalpha.getAurigaObject().getBillingLambda().cutUsagePeriods(count);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Periods Cut Done"));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //-------------------------------------------------------
        }        
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to cut usage periods Api nmnwrfhg");
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
