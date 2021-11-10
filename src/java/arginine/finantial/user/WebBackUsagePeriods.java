package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.billing.UsagePeriod;
//***************************************************************************
public class WebBackUsagePeriods extends WebBackAlpha {
    //***************************************************************
    UsagePeriod[] periods = null;
    void setUsagePeriods (UsagePeriod[] periods) { this.periods = periods; }
    public UsagePeriod[] getPeriods () {
        if (periods == null) return new UsagePeriod[0];
        return periods;
    }
    public String jUsagePeriods () {
        return JBilling.usagePeriods(getPeriods()).toString();
    }
    //***************************************************************
}
//***************************************************************************
