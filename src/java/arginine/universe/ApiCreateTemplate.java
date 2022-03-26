package arginine.universe;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiCreateTemplate", urlPatterns = {ApiCreateTemplate.URL}, loadOnStartup=1)
public class ApiCreateTemplate extends ApiAlpha {
    public static final String URL = "/universe/createtemplate";
    public static final String UNIVERSEID = "universeid";
    public static final String UNIVERSENAME = "name";
    public static final String DESCRIPTION = "description";
    //public static final String JUNIVERSE = "template";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //===================================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //===================================================================
        Session session = flowalpha.getSession();
        boolean allowed = false;
        if (session.isValid()) allowed = true;
        if (!allowed) {
            this.sendErrorResponse(resp, "Unauthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //===================================================================
        long universeid = 0;
        String name = req.getParameter(UNIVERSENAME);
        String description = req.getParameter(DESCRIPTION);
        try { universeid = Long.parseLong(req.getParameter(UNIVERSEID)); } catch(Exception e) {}
        try {
            //---------------------------------------------------------------
            System.out.println(universeid);
            System.out.println(name);
            System.out.println(description);
            //---------------------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Universe copy started"));
            //---------------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //---------------------------------------------------------------
        }
//        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Api add Universe template failure (jertonles)");
            System.out.println(e.getMessage());
        }    
        //===================================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //===================================================================
    }    
    //***********************************************************************    
}
//***************************************************************************
