package arginine.mapping;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JMaps;
import arginine.mapping.app.WebFrontMapApp;
import threonine.mapping.MapLayer;
//***************************************************************************
public class WebBackMapView extends WebBackAlpha {
    //***********************************************************************
    private MapLayer layer = null;
    void setLayer (MapLayer layers) { this.layer = layers; }
    public MapLayer getLayer () {
        if (layer == null) return new MapLayer();
        return layer;
    }
    public String jMapLayer () {
        return JMaps.getLayer(getLayer()).toString();
    }    
    //***********************************************************************
    public String mapAppURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontMapApp.PAGE);
        return url.toString();
    }
    //=======================================================================
    //***********************************************************************
}
//***************************************************************************
