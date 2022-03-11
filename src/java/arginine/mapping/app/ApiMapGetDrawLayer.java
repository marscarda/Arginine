package arginine.mapping.app;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JMaps;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.mapping.MapDrawReader;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import threonine.midlayer.MapRecordDraw;
import threonine.midlayer.MapView;
//***************************************************************************
@WebServlet(name = "ApiMapGetDrawLayer", urlPatterns = {ApiMapGetDrawLayer.URL}, loadOnStartup=1)
public class ApiMapGetDrawLayer extends ApiAlpha {
    public static final String URL = "/mapping/app/getlayer";
    public static final String LAYERID = "layerid";
    public static final String CANVASWIDTH = "canvaswidth";
    public static final String CANVASHEIGHT = "canvasheight";
    public static final String MAPSCALE = "mapscale";
    public static final String CENTERLATITUDE = "clatitude";
    public static final String CENTERLONGITUDE = "clongitude";
    //public static final String EVENTX = "eventx";
    //public static final String EVENTY = "eventy";
    public static final String JMAPDRAWING = "mapdrawing";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //==========================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //==========================================================
        Session session = flowalpha.getSession();
        long layerid = 0;
        int canvaswidth = 0, canvasheight = 0; 
        float mapscale = 0;
        float clatitude = 0, clongitude = 0;
        try { layerid = Long.parseLong(req.getParameter(LAYERID)); } catch (Exception e) {}
        try { canvaswidth = Integer.parseInt(req.getParameter(CANVASWIDTH)); } catch (Exception e) {}
        try { canvasheight = Integer.parseInt(req.getParameter(CANVASHEIGHT)); } catch (Exception e) {}
        try { mapscale = Integer.parseInt(req.getParameter(MAPSCALE)); } catch (Exception e) {}
        try { clatitude = Float.parseFloat(req.getParameter(CENTERLATITUDE)); } catch (Exception e) {}
        try { clongitude = Float.parseFloat(req.getParameter(CENTERLONGITUDE)); } catch (Exception e) {}
        try {
            //-----------------------------------------------------
            MapDrawReader reader = new MapDrawReader();
            reader.setAuriga(flowalpha.getAurigaObject());
            MapRecordDraw[] records = reader.getDrawingLayer(layerid, session);
            //-----------------------------------------------------
            MapView view = new MapView();
            view.setRecords(records);
            view.setCanvasDimentions(canvaswidth, canvasheight);
            view.setScale(mapscale);
            view.setCenter(clatitude, clongitude);
            view.initialize();
            view.makeDraw();
            //-----------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Map drawings Ok"));
            jsonresp.addPair(new JsonPair(JMAPDRAWING, JMaps.getDraw(view)));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //-----------------------------------------------------
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to get map drawing");
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
