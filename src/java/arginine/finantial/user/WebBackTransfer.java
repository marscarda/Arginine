package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import methionine.auth.User;
//***************************************************************************
public class WebBackTransfer extends WebBackAlpha {
    
    //***************************************************************
    User user = null;
    void setUser (User user) { this.user = user; }
    public User getUser () {
        if (user == null) return new User();
        return user;
    }
    //***************************************************************
    
    
    //***************************************************************
    public String transferToURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiTransferTo.URL);
        return url.toString();
    }
    //===============================================================
    //***************************************************************
}
//***************************************************************************
