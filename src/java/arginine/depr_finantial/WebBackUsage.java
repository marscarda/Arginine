package arginine.depr_finantial;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.billing.UsagePeriod;
//***************************************************************************
public class WebBackUsage extends WebBackAlpha {
    //***************************************************************
    UsagePeriod[] periods = null;
    void setPeriods (UsagePeriod[] periods) { this.periods = periods; }
    public UsagePeriod[] getPeriods () {
        if (periods == null) return new UsagePeriod[0];
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
