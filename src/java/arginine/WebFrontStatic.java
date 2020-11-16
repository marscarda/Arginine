package arginine;
//***************************************************************************
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//***************************************************************************
@WebServlet (name="WebEntryStatic", urlPatterns= { WebFrontStatic.URLPATTERN} )
public class WebFrontStatic extends WebFrontAlpha {
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
    public static final String CSSROOT = "bghrtrgdftyffhgtytdfg";
    public static final String CSSFORM = "vfrddh675ttyutyutyut";
    //public static final String CSSLISTANDBOX = "auggagccgaucagcgauggag";
    public static final String CSSPOPUP = "auggagccgaucagccgaugcaag";
    //=======================================================================
    //JS
    public static final String JSHTTP = "ndfrtgdger";
    public static final String JSTAGANIMATE = "augucauggcuugguacuagugacgu";
    /*
    public static final String JSMAP = "augucauggcucagucacfgu";
    */
    //***********************************************************************
    //BITMAP
    /*
    public static final String ZOOMINICON = "mssewsdtetsda";
    public static final String ZOOMOUTICON = "bstdrgdgdfgd";
    public static final String HANDICON = "rwerdfsdfsdfs";
    //-----------------------------------------------------------------------
    public static final String PUBVIEW = "nfdgrtertertert";
    public static final String MULTIPLECHEXCLUSIVE = "ltrfgrtvzdertrt";
    public static final String SAMPLING = "nbdsterferterter";
    public static final String SAMPLES = "gergrtrterter";
    //-----------------------------------------------------------------------
    public static final String SAMPLETUBE = "sedfgddjghjgjjjdfg";
    */
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
        if (resourcename.equals(TEXTGRAL20)) dispatchfile = "/static2020/arm.ttf";
        //-------------------------------------------------------------------
        //CSSs
        if (resourcename.equals(CSSROOT)) dispatchfile = "/static2020/root.css";
        if (resourcename.equals(CSSFORM)) dispatchfile = "/static2020/form.css";
        //if (resourcename.equals(CSSLISTANDBOX)) dispatchfile = "/static2020/listandbox.css";
        if (resourcename.equals(CSSPOPUP)) dispatchfile = "/static2020/popup.css";
        //-------------------------------------------------------------------
        //JS
        if (resourcename.equals(JSHTTP)) dispatchfile = "/static2020/httprequest.js";
        if (resourcename.equals(JSTAGANIMATE)) dispatchfile = "/static2020/taganimate.js";
        //if (resourcename.equals(JSMAP)) dispatchfile = "/static2020/mapdata.js";
        //-------------------------------------------------------------------
        //BITMAP
        //if (resourcename.equals(ZOOMINICON)) dispatchfile = "/static2020/zoomin.png";
        //if (resourcename.equals(ZOOMOUTICON)) dispatchfile = "/static2020/zoomout.png";
        //if (resourcename.equals(HANDICON)) dispatchfile = "/static2020/hand.png";
        //if (resourcename.equals(PUBVIEW)) dispatchfile = "/static2020/publicview.png";
        //if (resourcename.equals(MULTIPLECHEXCLUSIVE)) dispatchfile = "/static2020/multiplechexcl.png";
        //if (resourcename.equals(SAMPLING)) dispatchfile = "/static2020/sampling.png";
        //if (resourcename.equals(SAMPLES)) dispatchfile = "/static2020/samples.png";
        //if (resourcename.equals(SAMPLETUBE)) dispatchfile = "/static2020/sampletube.png";
        //-------------------------------------------------------------------
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
    //=======================================================================
}
//***************************************************************************
