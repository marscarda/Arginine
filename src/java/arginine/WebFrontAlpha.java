package arginine;
//***************************************************************************
//import alanine.main.WebFrontAuth;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//***************************************************************************
public class WebFrontAlpha extends HttpServlet {
    //***********************************************************************
    public static final String HEADERATTRKEY = "jesdgrthfgh";
    public static final String FOOTERATTRKEY = "qftrtgsrter";
    public static final String PAGEATTRKEY = "bretdgrtds";
    //***********************************************************************
    /**
     * Combines the methods beforeSend() finallJob() destroyFlowBeta() in that order
     * @param flowbeta 
     */
    protected void fleeRequest (FlowBeta flowbeta) {
        beforeSend(flowbeta);
        finallJob(flowbeta);
        destroyFlowBeta(flowbeta);
    }
    //***********************************************************************
    protected FlowBeta createFlowBeta (HttpServletRequest rq, HttpServletResponse rp) {
        FlowBeta flowbeta = new FlowBeta();
        flowbeta.setRequest(rq);
        flowbeta.setResponse(rp);
        flowbeta.initialize();
        //------------------------
        //More tasks here.
        //------------------------
        return flowbeta;
    }
    //=======================================================================
    protected void destroyFlowBeta (FlowBeta flowbeta) {
        if (flowbeta == null) return;
        flowbeta.cleanUp();
    }
    //***********************************************************************
    /**
     * initial task before starting processing the request.
     * @param flowbeta 
     */
    protected void initialJob (FlowBeta flowbeta) {
        //===================================================================
        //Set the request parameters encoding.
        try { flowbeta.getRequest().setCharacterEncoding("utf-8"); }
        catch (UnsupportedEncodingException e) { 
            System.out.println("When setting to utf8"); 
            System.out.println(e.getMessage()); 
        }
        //===================================================================
        CookiesFlow cookies = flowbeta.getCookiesFlow();
        if (cookies.hasLoginToken()) {
            String logintoken = cookies.loginToken();
            if (!flowbeta.trySetLogin(logintoken)) {
                cookies.setLoginToken(null);
                cookies.setLoggedIn(false);
            } 
            else cookies.setLoggedIn(true);
        }
        //===================================================================
    }
    //=======================================================================
    /**
     * Tasks to be carried out just before to sent the first response byte.
     * @param flowbeta 
     */
    protected void beforeSend (FlowBeta flowbeta) {
        flowbeta.getCookiesFlow().commitCookies();
    }
    //=======================================================================
    protected void finallJob (FlowBeta flowbeta) {
        //--------------------------------------------------
        if (flowbeta == null) return;
        //libInterface.LogHTTPHit();
        //libInterface.logRequest();
        //--------------------------------------------------
    }
    //***********************************************************************
    /**
     * Returns the "parameter" part of the URL.
     * For instances: Should the URL be "http://domain.com/document/name" this method returns "name".
     * @param request
     * @return 
     */
    protected String getURLsParamPart (HttpServletRequest request) {
        //======================================================
        String url = request.getRequestURI();
        int len = url.length();
        int ind = len - 1;
        int cp;
        //======================================================
        for (int n = ind; n > 0; n--) {
            cp = url.codePointAt(n);
            if (cp == 0x2f) {
                ind = n;
                ind++;
                return url.substring(ind);
            }
        }
        //======================================================
        return null;
        //======================================================
    }
    //***********************************************************************
    /**
     * Forces to an SSL conection
     * As long as the app has SSL property active.
     * @param flowbeta
     * @return 
     */
    protected boolean forceToSSL (FlowBeta flowbeta) {
        //=========================================================
        //If not using SSL nothing to do
        if (!LifeTimeValues.usingssl) return false;
        //=========================================================
        //Is already secure.
        if (flowbeta.getRequest().isSecure()) return false;
        //=========================================================
        HttpServletRequest rq = flowbeta.getRequest();
        StringBuilder redirto = new StringBuilder("HTTPS://");
        redirto.append(flowbeta.getHost());
        redirto.append(rq.getRequestURI());
        //----------------------------------
        String qs = rq.getQueryString();
        if (qs != null) {
            redirto.append("?");
            redirto.append(qs);
        }
        //---------------------------------------------------------
        HttpServletResponse response = flowbeta.getResponse();
        flowbeta.setStatusResponse(302);
        response.setHeader("Location", redirto.toString());
        //=========================================================
        this.fleeRequest(flowbeta);
        //=========================================================
        return true;
        //=========================================================
    }
    //=======================================================================
    protected boolean toSSLIfLoggedIn (FlowBeta flowbeta) {
        //=========================================================
        //If not using SSL nothing to do
        if (!LifeTimeValues.usingssl) return false;
        //=========================================================
        //Is already secure.
        if (flowbeta.getRequest().isSecure()) return false;
        //=========================================================
        //Is not logged in
        if (!flowbeta.getCookiesFlow().isLoggedIn()) return false;
        //=========================================================
        HttpServletRequest rq = flowbeta.getRequest();
        String qstring = rq.getQueryString();
        StringBuilder redirto = new StringBuilder("HTTPS://");
        redirto.append(flowbeta.getHost());
        redirto.append(rq.getRequestURI());
        if (qstring != null) {
            redirto.append("?");
            redirto.append(qstring);
        }
        //---------------------------------------------------------
        HttpServletResponse response = flowbeta.getResponse();
        flowbeta.setStatusResponse(302);
        response.setHeader("Location", redirto.toString());
        //=========================================================
        this.fleeRequest(flowbeta);
        //=========================================================
        return true;
        //=========================================================
    }
    //=======================================================================
    protected boolean toNonSSLIfNotLoggedIn (FlowBeta flowbeta) {
        //=========================================================
        //Is already secure.
        if (!flowbeta.getRequest().isSecure()) return false;
        //=========================================================
        //Is not logged in
        if (flowbeta.getCookiesFlow().isLoggedIn()) return false;
        //=========================================================
        HttpServletRequest rq = flowbeta.getRequest();
        String qstring = rq.getQueryString();
        StringBuilder redirto = new StringBuilder("HTTP://");
        redirto.append(flowbeta.getHost());
        redirto.append(rq.getRequestURI());
        //----------------------------------
        if (qstring != null) {
            redirto.append("?");
            redirto.append(qstring);
        }
        //---------------------------------------------------------
        HttpServletResponse response = flowbeta.getResponse();
        flowbeta.setStatusResponse(302);
        response.setHeader("Location", redirto.toString());
        //=========================================================
        this.fleeRequest(flowbeta);
        //=========================================================
        return true;
        //=========================================================
    }    
    //***********************************************************************
    /**
     * Redirects to Login Page if the session is not valid
     * TRUST THIS ONE WORKS.
     * @param flowbeta 
     */
    protected void toAuthPage (FlowBeta flowbeta) {
        StringBuilder redirto = new StringBuilder();
        if (flowbeta.getRequest().isSecure()) redirto.append("HTTPS://");
        else redirto.append("HTTP://");
        redirto.append(flowbeta.getHost());
        
        
        //redirto.append(WebFrontAuth.PAGE);
        
        
        //---------------------------------------------------------
        HttpServletResponse response = flowbeta.getResponse();
        flowbeta.setStatusResponse(302);
        response.setHeader("Location", redirto.toString());
        //=========================================================
        this.fleeRequest(flowbeta);
        //=========================================================
    }
    //***********************************************************************    
    /**
     * Distpatch the request and responds as it is (No markup modified)
     * @param dispatchto
     * @param request
     * @param response 
     */
    protected void dispatchNormal (String dispatchto, HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        RequestDispatcher reqd;
        reqd = request.getRequestDispatcher(dispatchto);
        //===================================================================
        try { reqd.forward(request, response); }
        catch (IOException | ServletException e)
        {
            try { response.getWriter().write("There is an error in the design of this page. Please report it"); } 
            catch (IOException we){
                System.out.println(we.getMessage());
            }
            System.out.println("JSP Error");
            System.out.println(e.getMessage());
        }
        //===================================================================
    }
    //=======================================================================
    protected void sendBinaryResponse (HttpServletResponse response, byte[] buff, String cnttype) {
        OutputStream out = null;
        try {
            response.setHeader("Content-type", cnttype);
            response.setHeader("Cache-Control", "max-age=30, max-stale=30");
            out = response.getOutputStream();
            out.write(buff);
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try { if (out != null) out.close();}
            catch (Exception e){}
        }
    }
    //***********************************************************************
}
//***************************************************************************
