package arginine.finantial;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methinine.billing.FundTicket;
import methinine.billing.LedgerEntry;
import methionine.auth.User;
//***************************************************************************
public class WebBackUserAccount extends WebBackAlpha {
    //***************************************************************
    User user = null;
    void setUser (User user) { this.user = user; }
    public User getUser () { 
        if (user == null) return new User();
        return user; 
    }
    //===============================================================
    int totalbalance = 0;
    void setTotalBalance (int balance) { totalbalance = balance; }
    public int getTotalBalance () { return totalbalance; }
    //===============================================================
    LedgerEntry [] entries = null;
    void setLedger (LedgerEntry[] entries) { this.entries = entries; }
    public LedgerEntry[] getEntries () {
        if (entries == null) return new LedgerEntry[0];
        return entries;
    }
    public String jLedgerEntries () {
        return JBilling.getLedgerEntryList(getEntries()).toString();
    }
    //===============================================================
    FundTicket[] fundposts = null;
    @Deprecated
    void setFundPosts (FundTicket[] posts) { fundposts = posts; }
    @Deprecated
    public FundTicket[] getfFundPosts () {
        if (fundposts == null) return new FundTicket[0];
        return fundposts;
    }
    @Deprecated
    public String jFundPosts () {
        //return JBilling.getFundPostsList(getfFundPosts()).toString();
        return "";
    }
    //***************************************************************
    public String addLedgerEntryURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiAddLedgerEntry.URL);
        return url.toString();
    } 
    //===============================================================
    public String createPaymentURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreatePayment.URL);
        return url.toString();
    }
    //===============================================================
    /*
    public String createFundPostURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreateFundPost.URL);
        return url.toString();
    }
    //===============================================================
    public String setTicketInactiveURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiSetTicketInactive.URL);
        return url.toString();
    }
    */
    //***************************************************************
}
//***************************************************************************
