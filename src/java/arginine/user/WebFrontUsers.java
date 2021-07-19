package arginine.user;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebBackAlpha;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.auth.Session;
import methionine.auth.User;
//***************************************************************************
@WebServlet(name = "WebFrontUsers", urlPatterns = {WebFrontUsers.PAGE}, loadOnStartup=1)
public class WebFrontUsers extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/users";
    public static final String STARTAT = "startat";
    //=======================================================================
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
        boolean allowed = false;
        if (session.isAdmin()) allowed = true;
        //-------------------------------------------------------------------
        if (!allowed) {
            flowbeta.setStatusResponse(403);
            this.fleeRequest(flowbeta);
            return;
        }
        //===================================================================
        String startat = request.getParameter(STARTAT);
        if (startat == null) startat = "";
        //===================================================================
        WebBackUsers back = new WebBackUsers();
        back.setRootURL(flowbeta.getRootURL());
        back.setLoggedInUser(flowbeta.getLogedUser());
        back.setLoginToken(session.getLoginToken());
        //===================================================================
        try { 
            User[] users = flowbeta.getAurigaObject().getAuthLambda().getUsers(startat, false); 
            back.setUsers(users);
        }
        catch (Exception e) {
            back.setErrorMessageCode(WebBackAlpha.ERRINTERNAL);
            //----------------------------------------------
            System.out.println("Failed to get users (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //===================================================================
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/user/users.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
}
//***************************************************************************
