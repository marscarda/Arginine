package arginine;
//**************************************************************************
import java.util.Properties;
//**************************************************************************
public class LifeTimeValues {
    //*****************************************************************
    //DATABASES PROPERTIES NAME.
    static final String PROP_DATABASE_USERS = "db_users";
    static final String PROP_DATABASE_UNIVERSE = "db_universe";
    static final String PROP_DATABASE_METRICS = "db_metrics";
    static final String PROP_DATABASE_SURVEY = "db_survey";
    //=================================================================
    static final String PROP_USING_SSL = "using_ssl";
    //*****************************************************************
    //Databases constants.
    public static String dbauth = null;
    public static String dbuniverse = null;
    public static String dbmetrics = null;
    public static String dbsurvey = null;
    //=================================================================
    public static boolean usingssl = false;
    //*****************************************************************
    public static void initValues (Properties props) {
        //--------------------------------------------------------
        String aux;        
        //--------------------------------------------------------
        aux = props.getProperty(PROP_USING_SSL);
        if (aux == null) System.out.println("Warning: Property " + PROP_USING_SSL + " Not set");
        else usingssl = aux.equalsIgnoreCase("Y");
        //========================================================
        //Databases
        aux = props.getProperty(PROP_DATABASE_USERS);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_USERS + " Not set");
        else dbauth = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_UNIVERSE);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_UNIVERSE + " Not set");
        else dbuniverse = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_METRICS);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_METRICS + " Not set");
        else dbmetrics = aux;
        //--------------------------------------------------------
        aux = props.getProperty(PROP_DATABASE_SURVEY);
        if (aux == null) System.out.println("Warning: Property " + PROP_DATABASE_SURVEY + " Not set");
        else dbsurvey = aux;
        //========================================================
    }    
    //*****************************************************************
}
//**************************************************************************
