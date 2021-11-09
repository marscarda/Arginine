package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.auth.User;
import methionine.billing.LedgerItem;
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
    LedgerItem[] ledger = null;
    void setLedger (LedgerItem[] ledger) { this.ledger = ledger; }
    public LedgerItem[] getLedger () {
        if (ledger == null) return new LedgerItem[0];
        return ledger;
    }
    public String jLedgerEntries () {
        return JBilling.getLedger(getLedger()).toString();
    }
    //***************************************************************
    public String addCreditURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiAddCredit.URL);
        return url.toString();
    }
    //===============================================================
    public String addDebitURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiAddDebit.URL);
        return url.toString();
    }
    //***************************************************************
}
//***************************************************************************
