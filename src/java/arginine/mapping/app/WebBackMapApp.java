package arginine.mapping.app;
//***************************************************************************
import arginine.WebBackAlpha;
//***************************************************************************
public class WebBackMapApp extends WebBackAlpha {
    //***********************************************************************
    public String apiGetDrawingURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiMapGetDrawLayer.URL);
        return url.toString();
    }
    //=======================================================================
    public String apiChangeZoom () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiMapChangeZoomLayer.URL);
        return url.toString();
    }
    //=======================================================================
    public String apiDisplaceMapURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiMapDisplaceLayer.URL);
        return url.toString();
    }
    //=======================================================================
    public String apiFindRecordInPoint () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiPointInRecordLayer.URL);
        return url.toString();
    }
    //***********************************************************************
}
//***************************************************************************
