package arginine.user;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JUsers;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
import methionine.auth.User;
//***************************************************************************
@WebServlet(name = "ApiGetUserList", urlPatterns = {ApiGetUserList.URL}, loadOnStartup=1)
public class ApiGetUserList extends ApiAlpha {
    public static final String URL = "/users/getlist";
    public static final String STARTAT = "startat";
    public static final String JUSERS = "users";
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
        String startat = req.getParameter(STARTAT);
        try { 
            User[] users = flowalpha.getAurigaObject().getAuthLambda().getUsers(startat, false);
            //----------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "User list"));
            jsonresp.addPair(new JsonPair(JUSERS, JUsers.getUserList(users)));
            this.sendResponse(resp, jsonresp);
            //----------------------------------------
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get users");
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