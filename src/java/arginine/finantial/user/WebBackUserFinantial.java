package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.auth.User;
import methionine.finance.BalanceInfo;
import methionine.finance.LedgerItem;
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
    BalanceInfo balance = null;
    void setBalance (BalanceInfo balance) { this.balance = balance; }
    public BalanceInfo getBalance() {
        if (balance == null) return new BalanceInfo();
        return balance;
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
    public String usagePeriodsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontUsagePeriods.PAGE);
        return url.toString();
    }
    //===============================================================
    public String systemChargeURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontSystemCharges.PAGE);
        return url.toString();
    }
    //===============================================================
    public String transferURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontTransfer.PAGE);
        return url.toString();
    }
    //===============================================================
    public String commerciaTransferURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontCommerce.PAGE);
        return url.toString();
    }
    //***************************************************************
}
//***************************************************************************
