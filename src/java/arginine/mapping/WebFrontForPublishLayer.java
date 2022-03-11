package arginine.mapping;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.mapping.ExcMapLayer;
import methionine.auth.Session;
import threonine.mapping.MapLayer;
//***************************************************************************
@WebServlet(name = "WebFrontForPublishLayer", urlPatterns = {WebFrontForPublishLayer.PAGE}, loadOnStartup=1)
public class WebFrontForPublishLayer extends WebFrontAlpha {
    public static final String PAGE = "/mapping/forpublish";
   //************************************************************************    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================
        if (this.toSSLIfLoggedIn(flowbeta)) return;
        if (this.toNonSSLIfNotLoggedIn(flowbeta)) return;
        //===================================================================
        //If there is not valid session.
        Session session = flowbeta.getSession();
        if (!session.isValid()) {
            this.toAuthPage(flowbeta);
            return;
        }
        //===================================================================
        WebBackForPublishLayer back = new WebBackForPublishLayer();
        back.setRootURL(flowbeta.getRootURL());
        back.setDisplayCustom(flowbeta.getLogedUser(), flowbeta.getCurrentProject());
        back.setLoginToken(session.getLoginToken());
        try {
            ExcMapLayer exc = new ExcMapLayer();
            exc.setAuriga(flowbeta.getAurigaObject());
            MapLayer[] layers = exc.getForPublishLayers(session);
            back.setLayers(layers);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failure in mapping page (Web) cameite");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        request.setAttribute(PAGEATTRKEY, back);        
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render22/mapping/forpublish.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //************************************************************************        
}
//***************************************************************************
