package arginine;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//***************************************************************************
@WebServlet (name="WebFrontStatic22", urlPatterns= { WebFrontStatic22.URLPATTERN} )
public class WebFrontStatic22 extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/static";
    public static final String URLPATTERN = PAGE + "/*";
    //***********************************************************************
    static final int MAXAGE = 3600;
    //***********************************************************************
    //FONTS
    public static final String TEXTGRAL20 = "general";
    //***********************************************************************
    //CSSs
    public static final String CSSROOT = "mekelarewa";
    public static final String CSSFORM = "wertenfert";
    public static final String CSSPOPUP = "hartonketf";
    //***********************************************************************
    //JS
    public static final String JSHTTP = "janertywe";
    public static final String JSTAGANIMATE = "majlarian";
    //***********************************************************************
    //MAM BITMAP 
    public static final String ZOOMINICON = "mssewsdtetsda";
    public static final String ZOOMOUTICON = "bstdrgdgdfgd";
    public static final String HANDICON = "rwerdfsdfsdfs";
    public static final String MAPRECINFOICON = "mqftyfgdrt";
    public static final String MAPENTERICON = "hetdfvdtes";
    public static final String MAPSTATSICON = "bfgrtgfhghg";
    //***********************************************************************
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta libintfc = this.createFlowBeta(request, response);
        this.initialJob(libintfc);
        //===================================================================
        String resourcename = this.getURLsParamPart(request);
        if (resourcename == null) {
            response.setStatus(403);
            this.finallJob(libintfc);
            return;
        }
        //===================================================================
        String dispatchfile = null;
        //===================================================================
        //Fonts
        if (resourcename.equals(TEXTGRAL20)) dispatchfile = "/static22/arm.ttf";
        //===================================================================
        //CSS
        if (resourcename.equals(CSSROOT)) dispatchfile = "/static22/root.css";
        if (resourcename.equals(CSSFORM)) dispatchfile = "/static22/form.css";
        if (resourcename.equals(CSSPOPUP)) dispatchfile = "/static22/popup.css";
        //===================================================================
        //JS
        if (resourcename.equals(JSHTTP)) dispatchfile = "/static22/httprequest.js";
        if (resourcename.equals(JSTAGANIMATE)) dispatchfile = "/static22/animate.js";
        //===================================================================
        //MAP BITMAP
        if (resourcename.equals(MAPRECINFOICON)) dispatchfile = "/static22/mapbuttons/maprecinfo.png";
        if (resourcename.equals(MAPENTERICON)) dispatchfile = "/static22/mapbuttons/mapentericon.png";
        if (resourcename.equals(MAPSTATSICON)) dispatchfile = "/static22/mapbuttons/stats.png";
        if (resourcename.equals(ZOOMINICON)) dispatchfile = "/static22/mapbuttons/zoomin.png";
        if (resourcename.equals(ZOOMOUTICON)) dispatchfile = "/static22/mapbuttons/zoomout.png";
        if (resourcename.equals(HANDICON)) dispatchfile = "/static22/mapbuttons/hand.png";
        //===================================================================
        if (dispatchfile == null) {
            libintfc.setStatusResponse(404);
            this.finallJob(libintfc);
            return;
        }
        //===================================================================
        StringBuilder maxage = new StringBuilder("max-age=");
        maxage.append(MAXAGE);
        maxage.append(", max-stale=");
        maxage.append(MAXAGE);
        response.setHeader("Cache-Control", maxage.toString());
        dispatchNormal(dispatchfile, request, response);
        //===================================================================
        this.finallJob(libintfc);
        this.destroyFlowBeta(libintfc);
        //===================================================================
    }    
    //***********************************************************************
}
//***************************************************************************
