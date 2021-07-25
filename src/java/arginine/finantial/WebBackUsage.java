package arginine.finantial;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methinine.billing.BillingPeriod;
//***************************************************************************
public class WebBackUsage extends WebBackAlpha {
    //***************************************************************
    BillingPeriod[] periods = null;
    void setPeriods (BillingPeriod[] periods) { this.periods = periods; }
    public BillingPeriod[] getPeriods () {
        if (periods == null) return new BillingPeriod[0];
        return periods;
    }
    public String jperiods () {
        return JBilling.usagePeriods(getPeriods()).toString();
    }
    //***************************************************************
    public String fetchUsagePeriodsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiFetchUsagePeriods.URL);
        return url.toString();
    } 
    //===============================================================
    public String cutUsagePeriodsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCutUsagePeriods.URL);
        return url.toString();
    } 
    //===============================================================
    public String doBillingURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiDoBilling.URL);
        return url.toString();
    }
    //***************************************************************    
}
//***************************************************************************
