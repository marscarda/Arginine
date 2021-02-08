package arginine.jbuilders;
//***************************************************************************
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