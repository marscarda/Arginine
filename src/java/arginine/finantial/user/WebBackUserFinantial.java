package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import methionine.auth.User;
//***************************************************************************
public class WebBackUserFinantial extends WebBackAlpha {
    //***************************************************************
    User user = null;
    void setUser (User user) { this.user = user; }
    public User getUser () {
        if (user == null) return new User();
        return user;
    }
    //***************************************************************
    public String addCreditURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiAddCredit.URL);
        return url.toString();
    } 
    //===============================================================
    //***************************************************************
}
//***************************************************************************
