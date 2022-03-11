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
@WebServlet(name = "ApiMapDisplaceLayer", urlPatterns = {ApiMapDisplaceLayer.URL}, loadOnStartup=1)
public class ApiMapDisplaceLayer extends ApiAlpha {
    public static final String URL = "/mapping/app/newcenterlayer";
    public static final String LAYERID = "layerid";
    public static final String CANVASWIDTH = "canvaswidth";
    public static final String CANVASHEIGHT = "canvasheight";
    public static final String MAPSCALE = "mapscale";
    public static final String CENTERLATITUDE = "clatitude";
    public static final String CENTERLONGITUDE = "clongitude";
    public static final String EVENTFROMX = "eventdownx";
    public static final String EVENTFROMY = "eventdowny";
    public static final String EVENTTOX = "eventupx";
    public static final String EVENTTOY = "eventupy";
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
        int eventxf = 0, eventyf = 0, eventxt = 0, eventyt = 0;
        //==========================================================
        try { layerid = Long.parseLong(req.getParameter(LAYERID)); } catch (Exception e) {}
        try { canvaswidth = Integer.parseInt(req.getParameter(CANVASWIDTH)); } catch (Exception e) {}
        try { canvasheight = Integer.parseInt(req.getParameter(CANVASHEIGHT)); } catch (Exception e) {}
        try { mapscale = Integer.parseInt(req.getParameter(MAPSCALE)); } catch (Exception e) {}
        try { clatitude = Float.parseFloat(req.getParameter(CENTERLATITUDE)); } catch (Exception e) {}
        try { clongitude = Float.parseFloat(req.getParameter(CENTERLONGITUDE)); } catch (Exception e) {}
        try { eventxf = Integer.parseInt(req.getParameter(EVENTFROMX)); } catch (Exception e) {}
        try { eventyf = Integer.parseInt(req.getParameter(EVENTFROMY)); } catch (Exception e) {}
        try { eventxt = Integer.parseInt(req.getParameter(EVENTTOX)); } catch (Exception e) {}
        try { eventyt = Integer.parseInt(req.getParameter(EVENTTOY)); } catch (Exception e) {}
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
            view.calculateDisplacement(eventxf, eventyf, eventxt, eventyt);
            view.initialize();
            view.makeDraw();
            //-----------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Map New Center Ok"));
            jsonresp.addPair(new JsonPair(JMAPDRAWING, JMaps.getDraw(view)));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
            //-----------------------------------------------------            
        }        
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to change map zoom map");
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
