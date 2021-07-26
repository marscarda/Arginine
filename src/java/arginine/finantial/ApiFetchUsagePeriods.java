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
import methionine.billing.UsagePeriod;
import methionine.billing.UsageQueryData;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiFetchUsagePeriods", urlPatterns = {ApiFetchUsagePeriods.URL}, loadOnStartup=1)
public class ApiFetchUsagePeriods extends ApiAlpha {
    public static final String URL = "/account/fetchusageperiods";
    public static final String OPEN = "open";
    public static final String CLOSED = "closed";
    public static final String BILLED = "billed";
    public static final String NOTBILLED = "notbilled";
    public static final String JPERIODS = "periods";
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
        UsageQueryData querydata = new UsageQueryData();
        try { querydata.setOpen(Integer.parseInt(req.getParameter(OPEN))); } catch(Exception e) {}
        try { querydata.setClosed(Integer.parseInt(req.getParameter(CLOSED))); } catch(Exception e) {}
        try { querydata.setBilled(Integer.parseInt(req.getParameter(BILLED))); } catch(Exception e) {}
        try { querydata.setNoBilled(Integer.parseInt(req.getParameter(NOTBILLED))); } catch(Exception e) {}
        try {
            //----------------------------------------
            UsagePeriod[] periods = flowalpha.getAurigaObject().getBillingLambda().getBillingPeriods(querydata);
            //----------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "User list"));
            jsonresp.addPair(new JsonPair(JPERIODS, JBilling.usagePeriods(periods)));
            this.sendResponse(resp, jsonresp);
            //----------------------------------------
        }    
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to fetch usage periods Api nmjkjhg");
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
