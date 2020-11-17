package arginine.post;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "WebFrontPost", urlPatterns = {WebFrontPost.URLPATTERN}, loadOnStartup=1)
public class WebFrontPost extends WebFrontAlpha {
    public static final String PAGE = "/posting/post";
    public static final String URLPATTERN = PAGE + "/*";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================

        //===================================================================
        //If there is not valid session.
        Session session = flowbeta.getSession();
        if (!session.isValid()) {
            this.toAuthPage(flowbeta);
            return;
        }
        WebBackPost back = new WebBackPost();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        back.setLoginToken(session.getLoginToken());
        //-------------------------------------------------------------------

        //-------------------------------------------------------------------
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/posting/postdetail.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }    
    //***********************************************************************
}
//***************************************************************************

