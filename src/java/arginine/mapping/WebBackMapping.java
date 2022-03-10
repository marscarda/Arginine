package arginine.mapping;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JMaps;
import threonine.mapping.MapLayer;
//***************************************************************************
public class WebBackMapping extends WebBackAlpha {
    //***************************************************************
    MapLayer[] layers = null;
    void setLayers (MapLayer[] layers) { this.layers = layers; }
    public MapLayer[] getLayers () {
        if (layers == null) return new MapLayer[0];
        return layers;
    }
    public String jLayers () {
        return JMaps.getLayerList(getLayers()).toString();
    }
    //***************************************************************
    public String pageForPublish () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontForPublishLayer.PAGE);
        return url.toString();
    }    
    //***************************************************************
}
//***************************************************************************
