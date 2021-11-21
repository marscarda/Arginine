package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.auth.User;
import methionine.billing.CommerceTransfer;
//***************************************************************************
public class WebBackCommerce extends WebBackAlpha {
    //***************************************************************
    User user = null;
    void setUser (User user) { this.user = user; }
    public User getUser () {
        if (user == null) return new User();
        return user;
    }
    //***************************************************************
    CommerceTransfer[] transfers = null;
    void setTransfers (CommerceTransfer[] transfers) { this.transfers = transfers; }
    public CommerceTransfer[] getTransfers () {
        if (transfers == null) return new CommerceTransfer[0];
        return transfers;
    }
    public String jTransfers () {
        return JBilling.commerceTransfers(getTransfers()).toString();
    }
    //***************************************************************
}
//***************************************************************************
