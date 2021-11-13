package arginine.finantial.user;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JBilling;
import methionine.billing.SystemCharge;
//***************************************************************************
public class WebBackSystemCharge extends WebBackAlpha {
    //***************************************************************
    SystemCharge[] systemcharges = null;
    void setSystemCharges (SystemCharge[] systemcharges) { this.systemcharges = systemcharges; }
    public SystemCharge[] getSystemCharges () {
        if (systemcharges == null) return new SystemCharge[0];
        return systemcharges;
    }
    public String jSystemCharges () {
        return JBilling.systemCharges(getSystemCharges()).toString();
    }
    //***************************************************************
}
//***************************************************************************
