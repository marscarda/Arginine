package arginine;
//***************************************************************************
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
//***************************************************************************
public class ApiAlpha extends HttpServlet {
    //***********************************************************************
    public static final String CREDENTIALTOKEN = "token";
    public static final String SESSIONID = "sessionid";
    public static final String USERID = "userid";
    public static final String SESSIONVALID = "sessionvalid";
    public static final String APPLICATION = "application";
    public static final String PROJECTID = "projectid";
    //=======================================================================
    public static final String RESULT = "result";
    public static final String RESULTOK = "OK";
    public static final String RESULTERROR = "failure";
    public static final String RESULTERRORCODE = "errorcode";
    public static final String RESULTDESCRIPTION = "description";
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final long SERVERERR = 500;
    public static final long UNAUTHORIZED = 403;
    //***********************************************************************
    protected FlowAlpha createFlowAlpha (HttpServletRequest req, HttpServletResponse resp) {
        
        FlowAlpha flowalpha = new FlowAlpha();
        flowalpha.setRequest(req);
        flowalpha.setResponse(resp);
        flowalpha.initialize();
        //------------------------
        //More tasks here.
        //------------------------
        return flowalpha;
    }
    //=======================================================================
    protected void destroyFlowAlpha (FlowAlpha flowalpha) {
        if (flowalpha == null) return;
        flowalpha.cleanUp();
    }
    //***********************************************************************
    protected void initializeJob (FlowAlpha flowalpha) {
        //------------------------------------------------------------------
        try { flowalpha.getRequest().setCharacterEncoding("utf-8"); }
        catch (UnsupportedEncodingException e) { 
            System.out.println("When setting to utf8"); 
            System.out.println(e.getMessage());
        }
        //------------------------------------------------------------------
        //CHECK USER LOGIN
        String token = flowalpha.getRequest().getParameter(CREDENTIALTOKEN);
        if (token != null) {
            flowalpha.trySetLogin(token);
        }
        //------------------------------------------------------------------
    }
    //=======================================================================
    protected void fleeRequest (FlowAlpha flowalpha) {
        finalizeJob(flowalpha);
        destroyFlowAlpha(flowalpha);
    }
    //=======================================================================
    protected void finalizeJob (FlowAlpha flowalpha) {
        if (flowalpha == null) return;
        //libInterface.LogHTTPHit();
        //libInterface.logRequest();
    }
    //***********************************************************************
    /**
     * Returns the url part after the last "/"
     * Used as a parameter.
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
        return "";
        //======================================================
    }
    //***********************************************************************
    /**
     * Sends the json response to the client (Requester)
     * @param response The response object used to send the response.
     * @param jsonresp The json object containing the data to send.
     */
    protected void sendResponse (HttpServletResponse response, JsonObject jsonresp) {
        //====================================================================
        //Se envia la respuesta final sea Ok o Error.
        PrintWriter out = null;
        try {
            response.setHeader("Content-type", "application/json");
            response.setHeader("Cache-Control", "max-age=0");
            out = response.getWriter();
            out.print(jsonresp.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try { if (out != null) out.close();}
            catch (Exception e){}
        }
        //====================================================================
    }
    //=======================================================================
    /**
     * Sends an error response 
     * @param response
     * @param errdesc
     * @param errcode 
     */
    protected void sendErrorResponse (HttpServletResponse response, String errdesc, long errcode) {
        JsonObject jsonresp = new JsonObject();
        JsonPair jpair; //Pair to send response.
        jpair = new JsonPair(RESULT, RESULTERROR);
        jsonresp.addPair(jpair);
        jpair = new JsonPair(RESULTDESCRIPTION, errdesc);
        jsonresp.addPair(jpair);
        jpair = new JsonPair(RESULTERRORCODE, errcode);
        jsonresp.addPair(jpair);            
        this.sendResponse(response, jsonresp);
    }
    //=======================================================================
    /**
     * 
     * @param response 
     */
    protected void sendServerErrorResponse (HttpServletResponse response) {
        sendErrorResponse(response, "Internal Server error", SERVERERR);
    }
    //=======================================================================
    /**
     * Sends a binary response.
     * @param response
     * @param buff
     * @param cnttype 
     */
    protected void sendBinaryResponse (HttpServletResponse response, byte[] buff, String cnttype) {
        OutputStream out = null;
        try {
            response.setHeader("Content-type", cnttype);
            response.setHeader("Cache-Control", "max-age=3600, max-stale=3600");
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
