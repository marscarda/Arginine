package arginine;
//**************************************************************************
import java.util.Properties;
//**************************************************************************
public class LifeTimeValues {
    //*****************************************************************
    //DATABASES PROPERTIES NAME.
    static final String PROP_DATABASE_UNIVERSE = "db_universe";
    static final String PROP_DATABASE_MAPS = "db_maps";
    static final String PROP_DATABASE_DESIGN = "db_design";
    static final String PROP_DATABASE_SAMPLE = "db_sample";
    static final String PROP_DATABASE_ENVIRONMENT = "db_environment";
    //=================================================================
    static final String PROP_USING_SSL = "using_ssl";
    //*****************************************************************
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
    }    
    //*****************************************************************
    public static final String PANELHOST = "https://survey.radareleven.com";
    //*****************************************************************
}
//**************************************************************************
