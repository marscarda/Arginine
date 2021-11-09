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
    
    
    //***************************************************************
}
//***************************************************************************
