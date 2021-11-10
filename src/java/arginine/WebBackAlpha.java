package arginine;
//***************************************************************************
import arginine.finantial.WebFrontFinancialPanel;
import arginine.main.WebFrontAuth;
import arginine.main.WebFrontHome;
import arginine.main.WebFrontLogout;
import arginine.user.WebFrontUsers;
import methionine.auth.User;
import methionine.project.Project;
//***************************************************************************
public class WebBackAlpha {
    //***********************************************************************
    public static final int ERRINTERNAL = 1;
    //-----------------------------------------------------------------------
    int errmessagecode = 0;
    public void setErrorMessageCode (int error) { this.errmessagecode = error; }
    public int getErrorMessageCode () { return this.errmessagecode; }
    //***********************************************************************
    
    //***********************************************************************
    String logintoken = null;
    public void setLoginToken (String logintoken) { this.logintoken = logintoken; }
    public String loginToken () {
        if (logintoken == null) return "";
        return logintoken;
    }
    //***********************************************************************
    String rooturl = null;    
    public void setRootURL (String u) { rooturl = u; }
    public String getRootURL () {
        if (rooturl == null) return "";
        return rooturl;
    }
    //***********************************************************************
    User loggedinuser = null;
    Project project = null;
    public void setLoggedInUser (User loggedinuser) { this.loggedinuser = loggedinuser; }
    public void setDisplayCustom (User loggedinuser, Project project) { 
        this.loggedinuser = loggedinuser;
        this.project = project;
    }
    public User getLoggedInUser () {
        if (loggedinuser == null) return new User();
        return loggedinuser;
    }
    //-----------------------------------------------------------------------
    public Project getCurrentProject () {
        if (project == null) return new Project();
        return project;
    }
    //***********************************************************************
    public String getAuthURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontAuth.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String logOutURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontLogout.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String usersURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontUsers.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String financialURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontFinancialPanel.PAGE);
        return url.toString();
    }
    //***********************************************************************

    
    //=======================================================================
    public String getHomeURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontHome.PAGE);
        return url.toString();
    }
    //***********************************************************************
}
//***************************************************************************
