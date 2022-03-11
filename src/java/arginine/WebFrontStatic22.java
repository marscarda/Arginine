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
    //***********************************************************************
    //JS
    public static final String JSHTTP = "janertywe";
    public static final String JSTAGANIMATE = "majlarian";
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
        //===================================================================
        if (resourcename.equals(JSHTTP)) dispatchfile = "/static22/httprequest.js";
        if (resourcename.equals(JSTAGANIMATE)) dispatchfile = "/static22/taganimate.js";
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
