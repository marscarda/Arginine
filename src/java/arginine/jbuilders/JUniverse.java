package arginine.jbuilders;
//***************************************************************************
import arginine.Shield;
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import threonine.universe.SubSet;
import threonine.universe.Universe;
//***************************************************************************
public class JUniverse {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String UNIVERESEID = "universeid";
    public static final String PROJECTID = "projectid";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String UNIVERESENAME = "universe_name";
    public static final String UNIVERESEDESCRIPTION = "universe_description";
    public static final String SUBSETID = "subsetid";
    public static final String UNIVERSEID = "universeid";
    public static final String PARENTSUBSET = "parent_subsetid";
    public static final String SUBSETNAME = "subset_name";
    public static final String SUBSETDESCRIPTION = "subset_description";
    public static final String SUBSETPOPULATION = "population";
    public static final String SUBSETWEIGHT = "weight";
    public static final String ROOT = "root";
    public static final String ISPUBLIC = "ispublic";
    public static final String PRICE = "price";
    public static final String ALLOWPUB = "puballowed";
    public static final String NOTPUBUNTIL = "notpubuntil";
    //***********************************************************************
    public static JsonObject getUniverses (Universe[] universes) {
        String aux;
        JsonObject juniverses = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (Universe universe : universes) {
            jarray.addPair(new JsonPair(UNIVERESEID, universe.universeID()));
            jarray.addPair(new JsonPair(PROJECTID, universe.projectID()));
            jarray.addPair(new JsonPair(USERID, universe.userID()));
            jarray.addPair(new JsonPair(USERNAME, universe.getUserName()));
            aux = Shield.stopInjection(universe.getName());
            jarray.addPair(new JsonPair(UNIVERESENAME, aux));
            aux = Shield.stopInjection(universe.getDescription());
            jarray.addPair(new JsonPair(UNIVERESEDESCRIPTION, aux));
            jarray.addPair(new JsonPair(ISPUBLIC, universe.isPublic()));
            jarray.addPair(new JsonPair(PRICE, universe.getPrice()));
            jarray.addToArray();
        }        
        juniverses.addPair(new JsonPair(COUNT, jarray.getCount()));
        juniverses.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return juniverses;
    } 
    //=======================================================================
    public static JsonObject getUniverse (Universe universe) {
        String aux;
        JsonObject juniverse = new JsonObject();
        juniverse.addPair(new JsonPair(UNIVERESEID, universe.universeID()));
        juniverse.addPair(new JsonPair(PROJECTID, universe.projectID()));
        juniverse.addPair(new JsonPair(USERID, universe.userID()));
        juniverse.addPair(new JsonPair(USERNAME, universe.getUserName()));
        aux = Shield.stopInjection(universe.getName());
        juniverse.addPair(new JsonPair(UNIVERESENAME, aux));
        aux = Shield.stopInjection(universe.getDescription());
        juniverse.addPair(new JsonPair(UNIVERESEDESCRIPTION, aux));
        juniverse.addPair(new JsonPair(ISPUBLIC, universe.isPublic()));
        juniverse.addPair(new JsonPair(PRICE, universe.getPrice()));
        return juniverse;
    }
    //***********************************************************************
    public static JsonObject getSubsets (SubSet[] subsets) {
        String aux;
        JsonObject jsubsets = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (SubSet subset : subsets) {
            jarray.addPair(new JsonPair(SUBSETID, subset.getSubsetID()));
            jarray.addPair(new JsonPair(UNIVERSEID, subset.getUniverseID()));
            jarray.addPair(new JsonPair(PARENTSUBSET, subset.getParentSubSet()));
            aux = Shield.stopInjection(subset.getName());
            jarray.addPair(new JsonPair(SUBSETNAME, aux));
            jarray.addPair(new JsonPair(SUBSETPOPULATION, subset.getPopulation()));
            jarray.addPair(new JsonPair(SUBSETWEIGHT, subset.getWeight()));
            jarray.addToArray();
        }
        jsubsets.addPair(new JsonPair(COUNT, jarray.getCount()));
        jsubsets.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jsubsets;
    }
    //=======================================================================
    public static JsonObject getSubset (SubSet subset) {
        String aux;
        JsonObject jsubset = new JsonObject();
        jsubset.addPair(new JsonPair(SUBSETID, subset.getSubsetID()));
        jsubset.addPair(new JsonPair(UNIVERSEID, subset.getUniverseID()));
        jsubset.addPair(new JsonPair(PARENTSUBSET, subset.getParentSubSet()));
        aux = Shield.stopInjection(subset.getName());
        jsubset.addPair(new JsonPair(SUBSETNAME, aux));
        jsubset.addPair(new JsonPair(SUBSETPOPULATION, subset.getPopulation()));
        jsubset.addPair(new JsonPair(SUBSETWEIGHT, subset.getWeight()));
        jsubset.addPair(new JsonPair(ROOT, subset.isROOT()));
        return jsubset;
    }
    //***********************************************************************
}
//***************************************************************************
