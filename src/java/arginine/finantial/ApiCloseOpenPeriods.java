package arginine.finantial;
//***************************************************************************
import arginine.ApiAlpha;
import static arginine.ApiAlpha.UNAUTHORIZED;
import arginine.FlowAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiCloseOpenPeriods", urlPatterns = {ApiCloseOpenPeriods.URL}, loadOnStartup=1)
public class ApiCloseOpenPeriods extends ApiAlpha {
    public static final String URL = "/finantial/closeperiods";
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
        
        

        //==========================================================
        JsonObject jsonresp = new JsonObject();
        jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
        jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Periods closing started"));
        //-------------------------------------------------------
        this.sendResponse(resp, jsonresp);
        //-------------------------------------------------------


    
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
   //************************************************************************
}
//***************************************************************************
