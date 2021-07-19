package arginine.user;
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
@WebServlet(name = "ApiSetUserPermissions", urlPatterns = {ApiSetUserPermissions.URL}, loadOnStartup=1)
public class ApiSetUserPermissions extends ApiAlpha{
    //***********************************************************************
    public static final String URL = "/users/setpermissions";
    public static final String PERRMISSIONS = "permissions";
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
            this.sendErrorResponse(resp, "Unuthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        long userid = 0;
        try { userid = Long.parseLong(req.getParameter(USERID)); } catch (Exception e) {}
        String permissions = req.getParameter(PERRMISSIONS);
        //==========================================================
        try {
            flowalpha.getAurigaObject().getAuthLambda().setUserPermissions(userid, permissions);
            JsonObject jsonresp = new JsonObject();
            JsonPair pairResp;
            pairResp = new JsonPair(RESULT, RESULTOK);
            jsonresp.addPair(pairResp);
            pairResp = new JsonPair(RESULTDESCRIPTION, "Permissions updated");
            jsonresp.addPair(pairResp);
            this.sendResponse(resp, jsonresp);            
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to set user permissions");
            System.out.println(e.getMessage());            
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
   //************************************************************************
}
//***************************************************************************
