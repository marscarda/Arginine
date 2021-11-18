package arginine.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.finantial.user.WebFrontUserFinantial;
import methionine.auth.User;
//***************************************************************************
public class WebBackUsers extends WebBackAlpha {
    //***************************************************************
    User[] users = null;
    void setUsers (User[] users) { this.users = users; }
    public User[] getUsers () {
        if (users == null) return new User[0];
        return users;
    }
    //***************************************************************
    public String userDetailURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontUserDet.PAGE);
        return url.toString();
    }
    //===============================================================
    public String userListURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiGetUserList.URL);
        return url.toString();
    }
    //===============================================================
    public String userFinantialURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontUserFinantial.PAGE);
        return url.toString();
    }
    //***************************************************************
}
//***************************************************************************