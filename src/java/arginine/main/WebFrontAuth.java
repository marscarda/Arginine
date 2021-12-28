package arginine.main;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import histidine.auth.ExcAuth;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.AppException;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "WebFrontLogin", urlPatterns = {WebFrontAuth.PAGE})
public class WebFrontAuth extends WebFrontAlpha {
    //***********************************************************************
    public static final String PAGE = "/login";
    public static final String SIGNUP = "signup";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PASSWORDRETYPE = "passwordretype";
    public static final String EMAIL = "email";
    //=======================================================================
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //-------------------------------------------------------------------
        //This prevents redirecting to here.
        flowbeta.getCookiesFlow().blockGoBackTo();
        //===================================================================
        if (this.forceToSSL(flowbeta)) return;
        //===================================================================
        //Nothing to do here if there is a valid session
        Session session = flowbeta.getSession();
        if (session.isValid()) {
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
            fleeRequest(flowbeta);
            return;
        }
        //===================================================================
        WebBackAuth back = new WebBackAuth();
        back.setRootURL(flowbeta.getRootURL());
        request.setAttribute(PAGEATTRKEY, back);
        //===================================================================
        this.beforeSend(flowbeta);
        this.dispatchNormal("/render2020/main/auth.jsp", request, response);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }    
    //***********************************************************************
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //-------------------------------------------------------------------
        //This prevents redirecting to here.
        flowbeta.getCookiesFlow().blockGoBackTo();
        //===================================================================
        //Nothing to do here if there is a valid session
        Session session = flowbeta.getSession();
        if (session.isValid()) {
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
            fleeRequest(flowbeta);
            return;
        }
        //===================================================================
        int signup = 0;
        try { signup = Integer.parseInt(request.getParameter(SIGNUP)); } catch (Exception e) {}
        //===================================================================
        logIn(flowbeta);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //***********************************************************************
    private void logIn (FlowBeta flowbeta) {
        String user = flowbeta.getRequest().getParameter(USER);
        String password = flowbeta.getRequest().getParameter(PASSWORD);
        //===================================================================
        try {
            ExcAuth exc = new ExcAuth();
            exc.setAuriga(flowbeta.getAurigaObject());
            String ipaddr = flowbeta.getIpAddress();
            String app = "Web Admin Panel";
            String client = flowbeta.getCookiesFlow().clientId();
            Session session = exc.signIn(ipaddr, app, client, user, password);
            flowbeta.getCookiesFlow().setLoggedIn(true);
            flowbeta.getCookiesFlow().setLoginToken(session.getLoginToken());
            flowbeta.getCookiesFlow().commitCookies();
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
        }
        catch (AppException e) {
            //----------------------------------------------
            WebBackAuth back = new WebBackAuth();
            back.setRootURL(flowbeta.getRootURL());
            back.setLoginUser(user);
            back.setErrorMessageCode(WebBackAuth.ERRINVALIDCREDENTIALS);
            back.setAuthError();
            //----------------------------------------------
            flowbeta.getRequest().setAttribute(PAGEATTRKEY, back);
            this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/auth.jsp", flowbeta.getRequest(), flowbeta.getResponse());
            //----------------------------------------------
        }
        catch (Exception e) {
            WebBackAuth back = new WebBackAuth();
            back.setRootURL(flowbeta.getRootURL());
            back.setLoginUser(user);
            back.setErrorMessageCode(WebBackAuth.ERRINTERNAL);
            flowbeta.getRequest().setAttribute(PAGEATTRKEY, back);
            this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/auth.jsp", flowbeta.getRequest(), flowbeta.getResponse());
            //----------------------------------------------
            System.out.println("Failed auth user (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //===================================================================
    }
    //***********************************************************************
}
//***************************************************************************
