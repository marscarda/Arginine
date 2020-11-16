package arginine.post;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import methionine.pub.publication.PostRecord;
//***************************************************************************
@WebServlet(name = "WebFronPosting", urlPatterns = {WebFrontPosting.PAGE}, loadOnStartup=1)
public class WebFrontPosting extends WebFrontAlpha {
    public static final String PAGE = "/post/home";
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
        WebBackPosting back = new WebBackPosting();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        back.setLoginToken(session.getLoginToken());
        //-------------------------------------------------------------------
        try {
            PostRecord[] posts = flowbeta.getAurigaObject().getPubsLambda().getPostRecords();
            back.setPosts(posts);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get post list (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //-------------------------------------------------------------------
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/posting/posting.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }    
    //***********************************************************************
}
//***************************************************************************
