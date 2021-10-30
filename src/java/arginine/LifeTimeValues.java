package arginine;
//**************************************************************************
import java.util.Properties;
import methionine.DataBaseName;
import histidine.AurigaObject;
//**************************************************************************
public class LifeTimeValues {
    //*****************************************************************
    //DATABASES PROPERTIES NAME.
    static final String PROP_DATABASE_USERS = "db_users";
    static final String PROP_DATABASE_BILLING = "db_billing";
    static final String PROP_DATABASE_UNIVERSE = "db_universe";
    static final String PROP_DATABASE_MAPS = "db_maps";
    static final String PROP_DATABASE_DESIGN = "db_design";
    static final String PROP_DATABASE_SAMPLE = "db_sample";
    static final String PROP_DATABASE_ENVIRONMENT = "db_environment";
    //----------------------------------
    //@Deprecated
    //static final String PROP_DATABASE_METRICS = "db_metrics";
    //@Deprecated
    //static final String PROP_DATABASE_SURVEY = "db_survey";
    //=================================================================
    static final String PROP_USING_SSL = "using_ssl";
    //*****************************************************************
    //@Deprecated
    //public static String dbmetrics = PROP_DATABASE_METRICS;
    //@Deprecated
    //public static String dbsurvey = PROP_DATABASE_SURVEY;
    //=================================================================
    public static boolean usingssl = false;
    //*****************************************************************
    public static void initValues (Properties props) {
        //========================================================
        String aux;        
        //========================================================
        aux = props.getProperty(PROP_USING_SSL);
        if (aux == null) System.out.println("Warning: Property " + PROP_USING_SSL + " Not set");
        else usingssl = aux.equalsIgnoreCase("Y");
        //========================================================
        //Databases
        aux = props.getProperty(PROP_DATABASE_USERS);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_USERS + " Not set");
        else AurigaObject.dbauth = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_BILLING);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_BILLING + " Not set");
        else AurigaObject.dbbilling = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_UNIVERSE);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_UNIVERSE + " Not set");
        else AurigaObject.dbuniverse = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_MAPS);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_MAPS + " Not set");
        else AurigaObject.dbmaps = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_MAPS);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_MAPS + " Not set");
        else AurigaObject.dbmaps = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_DESIGN);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_DESIGN + " Not set");
        else AurigaObject.dbdesign = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_SAMPLE);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_SAMPLE + " Not set");
        else AurigaObject.dbsample = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_ENVIRONMENT);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_ENVIRONMENT + " Not set");
        else AurigaObject.dbspace = aux;
        //========================================================
    }    
    //*****************************************************************
    public static DataBaseName getDataBaseName () {
        DataBaseName dbname = new DataBaseName();
        dbname.auth = AurigaObject.dbauth;
        dbname.project = AurigaObject.dbauth;
        dbname.billing = AurigaObject.dbbilling;
        dbname.universe = AurigaObject.dbuniverse;
        dbname.maps = AurigaObject.dbmaps;
        dbname.design = AurigaObject.dbdesign;
        dbname.sample = AurigaObject.dbsample;
        dbname.trial = AurigaObject.dbspace;
        return dbname;
    }
    //*****************************************************************
}
//**************************************************************************
