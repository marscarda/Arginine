package arginine.universe;
//***************************************************************************
import arginine.WebBackAlpha;
//***************************************************************************
public class WebBackUniverses extends WebBackAlpha {
    //***************************************************************
    //===============================================================
    public String apiCreateTemplate () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreateTemplate.URL);
        return url.toString();
    }
    //***************************************************************
}
//***************************************************************************
