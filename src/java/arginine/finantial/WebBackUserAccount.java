package arginine.finantial;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methinine.billing.LedgerEntry;
import methinine.billing.Payment;
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
    Payment[] payments = null;
    void setPayments (Payment[] payments) { this.payments = payments; }
    public Payment[] getPayments () {
        if (payments == null) return new Payment[0];
        return payments;
    }
    public String jPayments () {
        return JBilling.getPaymentList(this.getPayments()).toString();
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
    public String getLedgerURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiFetchLedger.URL);
        return url.toString();
    }
    //===============================================================
    /*
    public String setTicketInactiveURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiSetTicketInactive.URL);
        return url.toString();
    }
    */
    //***************************************************************
}
//***************************************************************************
