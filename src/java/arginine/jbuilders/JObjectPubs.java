package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import serine.pubs.object.ObjectPub;
//***************************************************************************
public class JObjectPubs {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String OBJPUBID = "objpubid";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String ACCESSID = "accessid";
    public static final String ACCESSNAME = "accessname";
    //***********************************************************************
    public static JsonObject getObjectPubList (ObjectPub[] pubs) {
        JsonObject jrecords = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (ObjectPub objectpub : pubs) {
            jarray.addPair(new JsonPair(OBJPUBID, objectpub.getID()));
            jarray.addPair(new JsonPair(TITLE, objectpub.getTitle()));
            jarray.addPair(new JsonPair(TEXT, objectpub.getText()));
            jarray.addPair(new JsonPair(ACCESSID, objectpub.accessID()));
            jarray.addPair(new JsonPair(ACCESSNAME, objectpub.accessName()));
            jarray.addToArray();
        }
        jrecords.addPair(new JsonPair(COUNT, jarray.getCount()));
        jrecords.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jrecords;
    }
    //***********************************************************************
    public static JsonObject getObjectPub (ObjectPub objectpub) {
        JsonObject jobjectpub = new JsonObject();
        jobjectpub.addPair(new JsonPair(OBJPUBID, objectpub.getID()));
        jobjectpub.addPair(new JsonPair(TITLE, objectpub.getTitle()));
        jobjectpub.addPair(new JsonPair(TEXT, objectpub.getText()));
        jobjectpub.addPair(new JsonPair(ACCESSID, objectpub.accessID()));
        jobjectpub.addPair(new JsonPair(ACCESSNAME, objectpub.accessName()));
        return jobjectpub;
    }
    //***********************************************************************
}
//***************************************************************************