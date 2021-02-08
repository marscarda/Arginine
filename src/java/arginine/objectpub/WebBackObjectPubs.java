package arginine.objectpub;
//***************************************************************************
import arginine.WebBackAlpha;
//***************************************************************************
public class WebBackObjectPubs extends WebBackAlpha {
    //***********************************************************************
    //=======================================================================
    public String getCreateObjectPubURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreateObjectPub.URL);
        return url.toString();
    }
    //***********************************************************************
}
//***************************************************************************
