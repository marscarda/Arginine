package arginine.universe;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JUniverse;
import threonine.universe.Universe;
//***************************************************************************
public class WebBackUniverses extends WebBackAlpha {
    //***********************************************************************
    Universe[] universes = null;
    void setUniverses (Universe[] universes) { this.universes = universes; }
    public Universe[] getUniverses () {
        if (universes == null) return new Universe[0];
        return universes;
    }
    public String jUniverses () {
        return JUniverse.getUniverses(getUniverses()).toString();
    }
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
