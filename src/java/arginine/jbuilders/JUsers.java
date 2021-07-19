package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.User;
//***************************************************************************
public class JUsers {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String ISADMIN = "isadmin";
    //***********************************************************************
    public static JsonObject getUserList (User[] users) {
        JsonObject jrecords = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (User user : users) {
            jarray.addPair(new JsonPair(ISADMIN, user.userID()));
            jarray.addPair(new JsonPair(USERNAME, user.loginName()));
            jarray.addPair(new JsonPair(ISADMIN, user.isAdmin()));
            jarray.addToArray();
        }        
        jrecords.addPair(new JsonPair(COUNT, jarray.getCount()));
        jrecords.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jrecords;
    }
    //***********************************************************************
}
//***************************************************************************
