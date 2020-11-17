package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import serine.blogging.publication.PostRecord;
//***************************************************************************
public class JPosts {
    //***********************************************************************
    public static JsonObject getPosts (PostRecord[] posts) {
        JsonObject jposts = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (PostRecord post : posts) {
            jarray.addPair(new JsonPair("postid", post.postID()));
            jarray.addPair(new JsonPair("title", post.postTitle()));
            jarray.addToArray();
        }        
        jposts.addPair(new JsonPair("count", jarray.getCount()));
        jposts.addPair(new JsonPair("items", jarray.getArray()));
        return jposts;
    }    
    //***********************************************************************
}
//***************************************************************************
