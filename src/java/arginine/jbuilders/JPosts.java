package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import serine.blogging.publication.PostPart;
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
            jarray.addPair(new JsonPair("sumary", post.postSumary()));
            jarray.addPair(new JsonPair("published", post.isPublished()));
            jarray.addToArray();
        }        
        jposts.addPair(new JsonPair("count", jarray.getCount()));
        jposts.addPair(new JsonPair("items", jarray.getArray()));
        return jposts;
    }    
    //***********************************************************************
    public static JsonObject getParts (PostPart[] parts) {
        JsonObject jposts = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (PostPart part : parts) {
            jarray.addPair(new JsonPair("partid", part.getID()));
            jarray.addPair(new JsonPair("type", part.partType()));
            jarray.addPair(new JsonPair("postid", part.postID()));
            jarray.addPair(new JsonPair("text", part.getText()));
            jarray.addToArray();
        }        
        jposts.addPair(new JsonPair("count", jarray.getCount()));
        jposts.addPair(new JsonPair("items", jarray.getArray()));
        return jposts;
    }    
    //***********************************************************************
}
//***************************************************************************
