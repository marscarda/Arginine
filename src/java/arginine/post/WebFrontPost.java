package arginine.post;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.AppException;
import methionine.auth.Session;
import serine.blogging.publication.PostPart;
import serine.blogging.publication.PostRecord;
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
        //===================================================================
        long postid = 0;
        try { postid = Long.parseLong(this.getURLsParamPart(request)); } catch (Exception e) {}
        //===================================================================
        WebBackPost back = new WebBackPost();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        back.setLoginToken(session.getLoginToken());
        //-------------------------------------------------------------------
        try {
            PostRecord post = flowbeta.getAurigaObject().getPubsLambda().getPostRecord(postid, true);
            PostPart[] parts = flowbeta.getAurigaObject().getPubsLambda().getPostParts(postid);
            back.setPostRecord(post);
        }
        catch (AppException e) {
            if (e.getErrorCode() != AppException.OBJECTNOTFOUND) {
                back.setErrorMessageCode(WebBackPost.ERRINTERNAL);
                System.out.println("Failed to get Post.");
                System.out.println("Unexpected error ");
                System.out.println(e.getMessage());
            }
            //else back.setErrorMessageCode(WebBackPost.);
        }
        catch (Exception e) {
            //----------------------------------------------
            System.out.println("Failed to get post (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
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

