package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.project.Project;
//***************************************************************************
public class JProject {
    public static JsonObject getProjectArray (Project[] projects) {
        JsonObject jprojects = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (Project project : projects) {
            if (!project.isValid()) continue;
            jarray.addPair(new JsonPair("projectid", project.workTeamID()));
            jarray.addPair(new JsonPair("name", project.getName()));
            jarray.addPair(new JsonPair("owner", project.getOwner()));
            jarray.addPair(new JsonPair("ownername", project.ownerName()));
            jarray.addPair(new JsonPair("isowner", project.isOwner()));
            jarray.addPair(new JsonPair("accesslevel", project.accessLevel()));
            jarray.addToArray();
        }
        jprojects.addPair(new JsonPair("count", jarray.getCount()));
        jprojects.addPair(new JsonPair("items", jarray.getArray()));
        return jprojects;
    }    
}
//***************************************************************************
