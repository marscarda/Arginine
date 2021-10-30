package arginine.user;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebBackAlpha;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.AppException;
import methionine.auth.AuthErrorCodes;
import methionine.auth.Session;
import methionine.auth.User;
//***************************************************************************
@WebServlet(name = "WebFrontUserDet", urlPatterns = {WebFrontUserDet.URLPATTERN}, loadOnStartup=1)
public class WebFrontUserDet extends WebFrontAlpha{
    //=======================================================================
    public static final String PAGE = "/userdetails";
    public static final String URLPATTERN = PAGE + "/*";
    //=======================================================================
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================
        WebBackUserDet back = new WebBackUserDet();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        //===================================================================
        //If there is not valid session.
        Session session = flowbeta.getSession();
        if (!session.isValid()) {
            this.toAuthPage(flowbeta);
            return;
        }
        //===================================================================
        boolean allowed = false;
        if (session.isAdmin()) allowed = true;
        //-------------------------------------------------------------------
        if (!allowed) {
            flowbeta.setStatusResponse(403);
            this.fleeRequest(flowbeta);
            return;
        }
        //===================================================================
        long userid = 0;
        try { userid = Long.parseLong(this.getURLsParamPart(request)); } catch (Exception e) {}
        //===================================================================
        try {
            User user = flowbeta.getAurigaObject().getAuthLambda().getUser(userid, true);
            back.setUser(user);
        }
        catch (AppException e) {
            if (e.getErrorCode() != AuthErrorCodes.USERNOTFOUND) {
                back.setErrorMessageCode(WebBackAlpha.ERRINTERNAL);
                //----------------------------------------------
                System.out.println("Failed to get user (Unexpected error code) (Web)");
                System.out.println(e.getMessage());
                //----------------------------------------------
            }
            back.setErrorMessageCode(WebBackUserDet.USERNOTFOUND);
        }
        catch (Exception e) {
            back.setErrorMessageCode(WebBackAlpha.ERRINTERNAL);
            //----------------------------------------------
            System.out.println("Failed to get user (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //===================================================================
        back.setLoginToken(session.getLoginToken());
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/users/userdetail.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //=======================================================================
}
//***************************************************************************
