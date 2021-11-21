package arginine.finantial;
//***************************************************************************
import arginine.WebBackAlpha;
//***************************************************************************
public class WebBackFinancialPanel extends WebBackAlpha {
    //***************************************************************
    public String closePeriodsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCloseOpenPeriods.URL);
        return url.toString();
    }
    //===============================================================
    public String billPeriodsURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiBillUsage.URL);
        return url.toString();
    }
    //===============================================================
    public String billSystemChargesURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiBillSystemCharge.URL);
        return url.toString();
    }
    //===============================================================
    public String billTansfersURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiBillTransfers.URL);
        return url.toString();
    }    
    //===============================================================
    public String billComerceTransferURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiBillCommerce.URL);
        return url.toString();
    }
    //***************************************************************
}
//***************************************************************************
