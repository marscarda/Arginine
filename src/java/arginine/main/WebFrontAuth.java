package arginine.main;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.AppException;
import methionine.auth.Session;
import methionine.auth.SignUpUser;
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
        if (signup != 0) this.signUp(flowbeta);
        else logIn(flowbeta);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //***********************************************************************
    private void signUp (FlowBeta flowbeta) {
        //===================================================================
        String user = flowbeta.getRequest().getParameter(USER);
        String email = flowbeta.getRequest().getParameter(EMAIL);
        String password = flowbeta.getRequest().getParameter(PASSWORD);
        String passwordretype = flowbeta.getRequest().getParameter(PASSWORDRETYPE);
        //===================================================================
        //We check both password match.
        if (!passwordretype.equals(password)) {
            WebBackAuth back = new WebBackAuth();
            back.setRootURL(flowbeta.getRootURL());
            back.setErrorMessageCode(WebBackAuth.ERRPASSNOTMATCH);
            back.setSignUpUser(user);
            back.setEmail(email);
            back.setSignUpPasswordError();
            flowbeta.getRequest().setAttribute(PAGEATTRKEY, back);
            this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/auth.jsp", flowbeta.getRequest(), flowbeta.getResponse());
            return;
        }
        //===================================================================
        long userid = 0;
        //===================================================================
        try {
            SignUpUser sgnupuser = new SignUpUser();
            sgnupuser.setName(user);
            sgnupuser.setPassWord(password);
            sgnupuser.setEmail(email);
            sgnupuser.setApplication("Webapp Alanine");
            userid = flowbeta.getAurigaObject().getAuthLambda().signUpUser(sgnupuser);
        }
        catch (AppException e) {
            WebBackAuth back = new WebBackAuth();
            back.setRootURL(flowbeta.getRootURL());
            back.setSignUpUser(user);
            back.setEmail(email);
            //-----------------------------------------------
            switch (e.getErrorCode()) {
                //-------------------------------------------
                case AppException.USERNAMENOTACCEPTED:
                    back.setErrorMessageCode(WebBackAuth.ERRUSERNOTACCEPTED);
                    back.setSignUpUserError();
                    break;
                //-------------------------------------------
                case AppException.PASSWORDNOTACCEPTED:
                    back.setErrorMessageCode(WebBackAuth.ERRPASSNOTACCEPTED);
                    back.setSignUpPasswordError();
                    break;
                //-------------------------------------------
                case AppException.EMAILNOTACCEPTED:
                    back.setErrorMessageCode(WebBackAuth.ERRINVALIDEMAIL);
                    back.setEmailError();
                    break;
                //-------------------------------------------
                case AppException.USEREXISTS:
                    back.setErrorMessageCode(WebBackAuth.ERRUSERALREADYEXISTS);
                    back.setSignUpUserError();
                    break;
                //-------------------------------------------
                case AppException.EMAILEXISTS:
                    back.setErrorMessageCode(WebBackAuth.ERREMAILALREADYUSED);
                    back.setEmailError();
                    break;
                //-------------------------------------------
            }
            flowbeta.getRequest().setAttribute(PAGEATTRKEY, back);
            //-----------------------------------------------
            //this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/auth.jsp", flowbeta.getRequest(), flowbeta.getResponse());
            //-----------------------------------------------
            return;
        }
        catch (Exception e) {
            WebBackAuth back = new WebBackAuth();
            back.setRootURL(flowbeta.getRootURL());
            back.setSignUpUser(user);
            back.setEmail(email);
            back.setErrorMessageCode(WebBackAuth.ERRINTERNAL);
            flowbeta.getRequest().setAttribute(PAGEATTRKEY, back);
            this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/auth.jsp", flowbeta.getRequest(), flowbeta.getResponse());
            //----------------------------------------------
            System.out.println("Failed to create user (Web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
            return;
        }
        //===================================================================
        //We try to create the session for the new user.
        try {
            Session session = flowbeta.getAurigaObject().getAuthLambda().createSession(flowbeta.getIpAddress(), userid);
            flowbeta.getCookiesFlow().setLoggedIn(true);
            flowbeta.getCookiesFlow().setLoginToken(session.getLoginToken());
            flowbeta.getCookiesFlow().commitCookies();
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
        }
        catch (Exception e) {
            WebBackAuth back = new WebBackAuth();
            back.setRootURL(flowbeta.getRootURL());
            back.setErrorMessageCode(WebBackAuth.ERRSESSION);
            flowbeta.getRequest().setAttribute(PAGEATTRKEY, back);
            this.beforeSend(flowbeta);
            this.dispatchNormal("/render2020/main/auth.jsp", flowbeta.getRequest(), flowbeta.getResponse());
            //----------------------------------------------
            System.out.println("Failed to create session after signup (web)");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //===================================================================
    }
    //***********************************************************************
    private void logIn (FlowBeta flowbeta) {
        String user = flowbeta.getRequest().getParameter(USER);
        String password = flowbeta.getRequest().getParameter(PASSWORD);
        //===================================================================
        try {
            Session session = flowbeta.getAurigaObject().getAuthLambda().createSession(flowbeta.getIpAddress(), user, password);
            flowbeta.getCookiesFlow().setLoggedIn(true);
            flowbeta.getCookiesFlow().setLoginToken(session.getLoginToken());
            flowbeta.getCookiesFlow().commitCookies();
            flowbeta.setStatusResponse(302);
            flowbeta.setHeader("location", flowbeta.getCookiesFlow().goBackTo());
            return;
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
            return;
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
            return;
        }
        //===================================================================
    }
    //***********************************************************************
}
//***************************************************************************
