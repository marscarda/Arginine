package arginine.mapping;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JMaps;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.mapping.ExcMapLayerAdmin;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import threonine.mapping.MapLayer;
//***************************************************************************
@WebServlet(name = "ApiSetLayerPublic", urlPatterns = {ApiSetLayerPublic.URL}, loadOnStartup=1)
public class ApiSetLayerPublic extends ApiAlpha {
    public static final String URL = "/mapping/setlayerpublic";
    public static final String LAYERID = "layerid";
    public static final String LAYERNAME = "layername";
    public static final String DESCRIPTION = "description";
    public static final String JLAYER = "layer";
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
        String layername = req.getParameter(LAYERNAME);
        String description = req.getParameter(DESCRIPTION);
        try { layerid = Long.parseLong(req.getParameter(LAYERID)); } catch(Exception e) {}
        try {
            //--------------------------------------------------------------- 
            ExcMapLayerAdmin exc = new ExcMapLayerAdmin();
            exc.setAuriga(flowalpha.getAurigaObject());
            MapLayer layer = exc.setLayerPublic(layerid, layername, description, session);
            //--------------------------------------------------------------- 
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Map Layer added to project"));
            jsonresp.addPair(new JsonPair(JLAYER, JMaps.getLayer(layer)));
            //---------------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //---------------------------------------------------------------
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Api Set Layer failure (majrefter)");
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
