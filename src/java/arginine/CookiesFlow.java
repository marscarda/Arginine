package arginine;
//**************************************************************************
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import methionine.Celaeno;
//**************************************************************************
/**
 * This class manages the in and out flow of cookies.
 * It is initialized fetching the cookies from the request.
 * And counts with a method to add the cookies to the response
 * CommitCookies must be call before start sending response data.
 * @author marian
 */
public class CookiesFlow {
    private static final int TIMELOGIN = 86400 * 7;
    private static final int TIMETRACK = 86400 * 90;
    //================================================================
    public static final String CKCLIENTID = "clientid";
    public static final String CKLOGINTOKEN = "logintoken";
    public static final String CKLOGGEDIN = "loggedin";
    public static final String CKLANGUAGE = "language";
    public static final String CKGOBACKTO = "gobackto";
    //----------------------------------------------------------------
    public static final String LOGEDIN = "Y";
    public static final String LANGENG = "en";
    public static final String LANGSPN = "sp";
    //================================================================
    public CookiesFlow (HttpServletRequest request, HttpServletResponse response) { 
        this.request = request;
        this.response = response;
        initCookies ();
    }
    //================================================================
    String dompattern = null;
    void setDomain (String domain) { dompattern = domain; }
    //================================================================
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    //----------------------------------------------------------------
    String clientid = null;
    String logintoken = null;
    boolean loggedin = false;
    String gobackto = null;
    String commitgobackto = null;
    boolean setgoback = true;
    LANGUAGE lang = LANGUAGE.ENGLISH;
    //****************************************************************
    /**
     * Fetches the cookies from the request and fills 
     * the fields in the current instance
     */
    private void initCookies () {
        //============================================================
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String value;
            //============================================================
            //We get all expected values that came in the request.
            for (Cookie ck : cookies) {
                //-------------------------
                if (ck.getName().equals(CKCLIENTID)) {
                    value = ck.getValue();
                    clientid = value;
                }
                //-------------------------
                if (ck.getName().equals(CKLOGGEDIN)) {
                    value = ck.getValue();
                    if (value.equals(LOGEDIN)) loggedin = true;
                }
                //-------------------------
                //The login token
                if (ck.getName().equals(CKLOGINTOKEN)) {
                    value = ck.getValue();
                    logintoken = value;
                }
                //-------------------------
                if (ck.getName().equals(CKGOBACKTO)) {
                    value = ck.getValue();
                    gobackto = value;
                }
                //==========================================
            }
        }
        //============================================================
        if (clientid == null) 
            clientid = Celaeno.randomString(50);
        //============================================================
    }
    //================================================================
    /**
     * Sets the cookies in the response taking 
     * values from the current instance fields.
     */
    public void commitCookies () {
        //==============================================
        Cookie ck;
        //==============================================
        if (clientid != null) {
            ck = new Cookie(CKCLIENTID, clientid);
            ck.setHttpOnly(true);
            ck.setMaxAge(TIMETRACK);
            ck.setPath("/");
            if (dompattern != null) ck.setDomain(dompattern);
            response.addCookie(ck);
        }
        //==============================================        
        if (logintoken != null) {
            ck = new Cookie(CKLOGINTOKEN, logintoken);
            //----------------------------
            if (LifeTimeValues.usingssl) ck.setSecure(loggedin);
            //----------------------------            
            ck.setHttpOnly(true);
            ck.setMaxAge(TIMELOGIN);
            ck.setPath("/");
            if (dompattern != null) ck.setDomain(dompattern);
        }
        else {
            ck = new Cookie(CKLOGINTOKEN, null);
            ck.setMaxAge(0);
            ck.setPath("/");
            if (dompattern != null) ck.setDomain(dompattern);
        }
        response.addCookie(ck);
        //==============================================
        if (loggedin) {
            ck = new Cookie(CKLOGGEDIN, LOGEDIN);
            ck.setHttpOnly(true);
            ck.setMaxAge(TIMELOGIN);
            ck.setPath("/");
            if (dompattern != null) ck.setDomain(dompattern);
        }
        else {
            ck = new Cookie(CKLOGGEDIN, null);
            ck.setMaxAge(0);
            ck.setPath("/");
            if (dompattern != null) ck.setDomain(dompattern);
        }
        response.addCookie(ck);
        //==============================================
        if (setgoback && commitgobackto != null) {
            ck = new Cookie(CKGOBACKTO, commitgobackto);
            ck.setHttpOnly(true);
            ck.setMaxAge(-1);
            ck.setPath("/");
            if (dompattern != null) ck.setDomain(dompattern);
            response.addCookie(ck);
        }
        //==============================================
        switch (lang) {
            case ENGLISH: ck = new Cookie(CKLANGUAGE, LANGENG); break;
            case SPANISH: ck = new Cookie(CKLANGUAGE, LANGSPN); break;
        }
        //==============================================        
    }
    //****************************************************************
    public void setLoginToken (String logintoken) { this.logintoken = logintoken; }
    public void setLoggedIn (boolean loggedin) { this.loggedin = loggedin; }
    public void setLanguage (LANGUAGE lang) { this.lang = lang; }
    public void setGoBackTo (String gobackto) { this.commitgobackto = gobackto; }
    public void blockGoBackTo () { setgoback = false; }
    //================================================================
    public boolean hasClientId () { return clientid != null; }
    public String clientId() { return clientid; }
    public boolean hasLoginToken () { return logintoken != null; }
    public String loginToken () { return logintoken; }
    public boolean isLoggedIn () { return loggedin; }
    public String goBackTo () {
        if (gobackto == null) return "/";
        return gobackto;
    }
    //================================================================
    public LANGUAGE getLanguage () { return lang; }
    //****************************************************************
    public enum LANGUAGE {
        ENGLISH,
        SPANISH
    }
    //****************************************************************
}
//**************************************************************************

