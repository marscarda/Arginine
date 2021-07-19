package arginine.user;
//***************************************************************************
import arginine.WebBackAlpha;
import methionine.auth.User;
//***************************************************************************
public class WebBackUserDet extends WebBackAlpha{
    //***********************************************************************
    public static final int USERNOTFOUND = 2;
    //***********************************************************************
    User user = null;
    void setUser (User user) { this.user = user; }
    public User getUser () {
        if (user == null) return new User();
        return user;
    }
    //***********************************************************************
    public String apiSetPermissionsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiSetUserPermissions.URL);
        return url.toString();
    }
    //=======================================================================
}
//***************************************************************************
