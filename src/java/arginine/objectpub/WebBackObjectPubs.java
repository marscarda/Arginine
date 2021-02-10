package arginine.objectpub;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JObjectPubs;
import serine.pubs.object.ObjectPub;
//***************************************************************************
public class WebBackObjectPubs extends WebBackAlpha {
    //***********************************************************************
    ObjectPub[] objectpubs = null;
    void setObjectPubs (ObjectPub[] pubs) { objectpubs = pubs; }
    public ObjectPub[] getObjectPubs () {
        if (objectpubs == null) return new ObjectPub[0];
        return objectpubs;
    }
    public String jObjectPubs () {
        return JObjectPubs.getObjectPubList(this.getObjectPubs()).toString();
    }
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
