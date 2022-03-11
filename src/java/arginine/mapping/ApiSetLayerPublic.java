package arginine.mapping;
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
@WebServlet(name = "ApiSetLayerPublic", urlPatterns = {ApiSetLayerPublic.URL}, loadOnStartup=1)
public class ApiSetLayerPublic extends ApiAlpha {
    public static final String URL = "/mapping/setlayerpublic";
    public static final String LAYERID = "layerid";
    public static final String DESCRIPTION = "description";
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
        long layerid = 0;
        String description = req.getParameter(DESCRIPTION);
        try { layerid = Long.parseLong(req.getParameter(LAYERID)); } catch(Exception e) {}
        try {



            //---------------------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Map Layer added to project"));
            //---------------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //---------------------------------------------------------------
        }
//        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
        }
        //===================================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //===================================================================
    }    
    //***********************************************************************
}
//***************************************************************************
