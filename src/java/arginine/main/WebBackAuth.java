package arginine.main;
//***************************************************************************
import arginine.WebBackAlpha;
//***************************************************************************
public class WebBackAuth extends WebBackAlpha {
    //***********************************************************************
    public static final int ERRINVALIDCREDENTIALS = 2;
    public static final int ERRUSERALREADYEXISTS = 3;
    public static final int ERRUSERNOTACCEPTED = 4;
    public static final int ERRINVALIDEMAIL = 5;
    public static final int ERREMAILALREADYUSED = 6;
    public static final int ERRPASSNOTMATCH = 7;
    public static final int ERRPASSNOTACCEPTED = 8;
    public static final int ERRSESSION = 9;
    //***********************************************************************
    String loginuser = null;
    void setLoginUser (String loginuser) { this.loginuser = loginuser; } 
    public String getLoginUser () {
        if (loginuser == null) return "";
        return loginuser;
    }
    //=======================================================================
    String signupuser = null;
    void setSignUpUser (String signupuser) { this.signupuser = signupuser; } 
    public String getSignUpUser () {
        if (signupuser == null) return "";
        return signupuser;
    }
    //=======================================================================
    String email = null;
    void setEmail (String email) { this.email = email; } 
    public String getEmail () {
        if (email == null) return "";
        return email;
    }
    //=======================================================================
    boolean autherror = false;
    boolean sgnupusererror = false;
    boolean sgnemailerror = false;
    boolean sgnuppasserror = false;
    void setAuthError () { autherror = true; }
    public boolean authError () { return autherror; }
    void setSignUpUserError () { sgnupusererror = true; }
    public boolean signUpUserErrorError () { return sgnupusererror; }
    void setEmailError () { sgnemailerror = true; }
    public boolean emailErrorError () { return sgnemailerror; }
    void setSignUpPasswordError () { sgnuppasserror = true; }
    public boolean signUpPasswordError () { return sgnuppasserror; }
    //=======================================================================
}
//***************************************************************************

