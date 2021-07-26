package arginine.finantial;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiDoBilling", urlPatterns = {ApiDoBilling.URL}, loadOnStartup=1)
public class ApiDoBilling extends ApiAlpha {
    public static final String URL = "/account/dobilling";
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
            flowalpha.getAurigaObject().getBillingLambda().doBilling(count);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Billing done"));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //-------------------------------------------------------
        }        
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to bill usage periods Api nmkiofhg");
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
