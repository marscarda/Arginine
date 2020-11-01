package arginine;
//**************************************************************************
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.AppException;
import methionine.Electra;
import methionine.auth.Session;
//**************************************************************************
/**
 * Holds all needed data to process a request and response flow.
 * The IP of the client, The auth user if any. The client id.
 * And the database back end interface.
 * @author marian
 */
public class FlowAlpha {
    //**********************************************************************
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    //======================================================================
    private String ipaddress = null;
    protected String host = null;
    private String useragent = null;
    private boolean issecure = false;
    protected String rooturl = null;
    int status = 200;
    //----------------------------------------------------------------------
    AurigaObject auriga = null;
    //======================================================================
    Session session = null;
    //**********************************************************************
    void setRequest (HttpServletRequest request) { this.request = request; }
    void setResponse (HttpServletResponse response) { this.response = response; }
    //----------------------------------------------------------------------
    public HttpServletRequest getRequest () { return this.request; }
    public HttpServletResponse getResponse () { return response; }
    //**********************************************************************
    /**
     * Initializes all the flow to carryout the request.
     */
    void initialize () {
        //---------------------------------------------------------
        ipaddress = request.getRemoteAddr();
        host = request.getHeader("Host");
        useragent = request.getHeader("User-Agent");
        issecure = request.isSecure();
        //---------------------------------------------------------
        initAuriga();
        //---------------------------------------------------------
    }
    //----------------------------------------------------------------------
    private void initAuriga () {
        if (auriga != null) return; 
        auriga = new AurigaObject();
    }
    //**********************************************************************
    public String getIpAddress () { 
        if (ipaddress == null) return "-"; 
        return ipaddress; 
    }
    //----------------------------------------------------------------------
    String getHost () {
        if (host == null) return "";
        return host;
    }
    //----------------------------------------------------------------------
    String getUserAgent () {
        if (useragent == null) return "";
        return useragent;
    }
    //----------------------------------------------------------------------
    boolean isSecure () { return issecure; }
    //**********************************************************************
    public AurigaObject getAurigaObject () { 
        initAuriga();
        return auriga;
    }
    //======================================================================
    /**
     * Tries to set the session for the current request.
     * @param token
     * @return 
     */
    public boolean trySetLogin (String token) {
        //-----------------------------------------------
        if (session != null) return session.isValid();
        //-----------------------------------------------
        try {
            session = this.getAurigaObject().getAuthLambda().getSession(ipaddress, token);
            return session.isValid();
        }
        catch (AppException e) {
            if (e.getErrorCode() != AppException.SESSIONNOTFOUND) {
                System.out.println("Trying to set a session. Unexpected error");
                System.out.println(e.getErrorCode() + " " + e.getMessage());
            }
            return false;
        }
        catch (Exception e) {
            System.out.println("Trying to set a session.");
            System.out.println(e.getMessage());
            return false;
        }
    }
    //======================================================================
    public Session getSession () {
        if (session == null) return new Session();
        return session;
    }
    //**********************************************************************
    public void setStatusResponse (int status) {
        this.status = status;
        response.setStatus(status);
    }
    //----------------------------------------------------------------------
    public void setHeader (String header, String value) {
        response.setHeader(header, value);
    }
    //*************************************************************************
    public String getRootURL () {
        if (rooturl != null) return rooturl;
        StringBuilder rurl = new StringBuilder();
        if (issecure) rurl.append("HTTPS://");
        else rurl.append("HTTP://");
        rurl.append(host);
        rooturl = rurl.toString();
        return rooturl;
    }
    //**********************************************************************
    /**
     * Cleans the electra object where master and slave connection to database are.
     */
    public void cleanUp () {
        Electra electra = auriga.getElectra();
        electra.disposeDBConnection();
    }
    //**********************************************************************
}
//**************************************************************************
