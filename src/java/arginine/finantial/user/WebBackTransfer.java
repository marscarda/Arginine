package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.auth.User;
import methionine.billing.ComunityTransfer;
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
    ComunityTransfer[] transfers = null;
    void setTransfers (ComunityTransfer[] transfers) { this.transfers = transfers; }
    public ComunityTransfer[] getTransfers () {
        if (transfers == null) return new ComunityTransfer[0];
        return transfers;
    }
    public String jTransfers () {
        return JBilling.getTransfers(getTransfers()).toString();
    }
    //***************************************************************
    public String transferToURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiTransferTo.URL);
        return url.toString();
    }
    //===============================================================
    public String transferFromURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiTransferFrom.URL);
        return url.toString();
    }
    //***************************************************************
}
//***************************************************************************
