package arginine.jbuilders;
//***************************************************************************
import arginine.Shield;
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import threonine.map.MapLayer;
import threonine.map.MapRecord;
import threonine.map.PointLocation;
import threonine.midlayer.MapView;
import threonine.midlayer.MapObjectGraphic;
import threonine.midlayer.MapRecordDraw;
//***************************************************************************
public class JMaps {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String MAPSCALE = "mapscale";
    public static final String CENTERLATITUDE = "ctrlatitude";
    public static final String CENTERLONGITUDE = "ctrlongitude";
    //-----------------------------------------------------------------------
    public static final String PROJECTID = "projectid";
    //-----------------------------------------------------------------------
    public static final String LAYERID = "layerid";
    public static final String LAYERNAME = "layername";
    public static final String DESCRIPTION = "description";
    //-----------------------------------------------------------------------
    public static final String PARENTFOLDERID = "parentiid";
    public static final String RECORDID = "recordid";
    public static final String NAME = "name";
    public static final String PUBLICNAME = "publicname";
    public static final String COSTPERUSE = "costperuse";
    public static final String EXTRADATA = "extradata";
    public static final String ADMINDIV = "admindiv";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    //***********************************************************************
    public static JsonObject getLayer (MapLayer layer) {
        String aux;
        JsonObject jlayer = new JsonObject();
        jlayer.addPair(new JsonPair(LAYERID, layer.layerID()));
        jlayer.addPair(new JsonPair(PROJECTID, layer.projectID()));
        aux = Shield.stopInjection(layer.layerName());
        jlayer.addPair(new JsonPair(LAYERNAME, aux));
        aux = Shield.stopInjection(layer.layerDescription());
        jlayer.addPair(new JsonPair(DESCRIPTION, aux));
        return jlayer;
    }
    //=======================================================================
    public static JsonObject getLayerList (MapLayer[] layers) {
        String aux;
        JsonObject jlayers = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (MapLayer layer : layers) {
            jarray.addPair(new JsonPair(LAYERID, layer.layerID()));
            jarray.addPair(new JsonPair(PROJECTID, layer.projectID()));
            aux = Shield.stopInjection(layer.layerName());
            jarray.addPair(new JsonPair(LAYERNAME, aux));
            aux = Shield.stopInjection(layer.layerDescription());
            jarray.addPair(new JsonPair(DESCRIPTION, aux));
            jarray.addToArray();
        }
        jlayers.addPair(new JsonPair(COUNT, jarray.getCount()));
        jlayers.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jlayers;
    }
    //***********************************************************************
    //***********************************************************************
    //***********************************************************************
    //***********************************************************************
    //***********************************************************************
    public static JsonObject getRecordList (MapRecord[] records) {
        String aux;
        JsonObject jrecords = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (MapRecord record : records) {
            jarray.addPair(new JsonPair(RECORDID, record.getID()));
            jarray.addPair(new JsonPair(LAYERID, record.layerID()));
            aux = Shield.stopInjection(record.getName());
            jarray.addPair(new JsonPair(NAME, aux));
            aux = Shield.stopInjection(record.getExtraData());
            jarray.addPair(new JsonPair(EXTRADATA, aux));
            aux = Shield.stopInjection(record.getAdminDivision());
            jarray.addPair(new JsonPair(ADMINDIV, aux));
            jarray.addToArray();
        }
        jrecords.addPair(new JsonPair(COUNT, jarray.getCount()));
        jrecords.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jrecords;
    }
    //=======================================================================
    public static JsonObject getRecord (MapRecord record) {
        String aux;
        JsonObject jrecord = new JsonObject();
        jrecord.addPair(new JsonPair(RECORDID, record.getID()));
        jrecord.addPair(new JsonPair(LAYERID, record.layerID()));
        aux = Shield.stopInjection(record.getName());        
        jrecord.addPair(new JsonPair(NAME, aux));
        aux = Shield.stopInjection(record.getExtraData());
        jrecord.addPair(new JsonPair(EXTRADATA, aux));
        aux = Shield.stopInjection(record.getAdminDivision());
        jrecord.addPair(new JsonPair(ADMINDIV, aux));
        return jrecord;
    }
    //***********************************************************************
    public static JsonObject getDraw (MapView view) {
        MapRecordDraw[] records = view.getRecords();
        MapObjectGraphic[] objs;
        PointLocation[] points;
        JsonObject jdraw = new JsonObject();
        jdraw.addPair(new JsonPair(CENTERLATITUDE, view.getCenterLatitude()));
        jdraw.addPair(new JsonPair(CENTERLONGITUDE, view.getCenterLongitude()));
        jdraw.addPair(new JsonPair(MAPSCALE, view.getScale()));
        //===================================================================
        JsonArray jrecords = new JsonArray();
        for (MapRecordDraw record : records) {
            objs = record.getMapObjects();
            JsonArray jobjects = new JsonArray();
            for (MapObjectGraphic obj : objs) {
                try { points = obj.getPoints(); }
                catch (Exception e) { continue; }
                JsonArray jpoints = new JsonArray();
                for (PointLocation point : points) {
                    jpoints.addPair(new JsonPair("x", point.getPlaneX()));
                    jpoints.addPair(new JsonPair("y", point.getPlaneY()));
                    jpoints.addToArray();
                }
                jobjects.addPair(new JsonPair("ptcount", jpoints.getCount()));
                jobjects.addPair(new JsonPair("points", jpoints.getArray()));
                jobjects.addToArray();
            }
            jrecords.addPair(new JsonPair("objcount", jobjects.getCount()));
            jrecords.addPair(new JsonPair("objects", jobjects.getArray()));
            jrecords.addToArray();
        }
        //===================================================================
        jdraw.addPair(new JsonPair("reccount", jrecords.getCount()));
        jdraw.addPair(new JsonPair("records", jrecords.getArray()));
        return jdraw;
        //===================================================================
    }
    //***********************************************************************
    public static JsonObject getIdList (long[] ids) {
        JsonObject jids = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (long id : ids) {
            jarray.addPair(new JsonPair(RECORDID, id));
            jarray.addToArray();
        }
        jids.addPair(new JsonPair(COUNT, jarray.getCount()));
        jids.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jids;
    }
    //***********************************************************************
}
//***************************************************************************
