package arginine;
//****************************************************************************
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import methionine.Electra;
import histidine.AurigaObject;
//****************************************************************************
@WebListener
public class ContextListener implements ServletContextListener {
    //***********************************************************************
    //These properties are directly initialized into Electra.
    static final String PROP_MASTER_DBHOST = "db_master_host";
    static final String PROP_SLAVE_DBHOST = "db_slave_host";
    static final String PROP_DBMASTER = "db_ismaster";
    //-----------------------------------------------------------------------
    boolean ismaster = false;
    //-----------------------------------------------------------------------
    static final String DBUSER = "arginine";
    static final String DBPASS = "wprdsf9f8d6fd53rdfsdvrt56dq1rr";
    //***********************************************************************
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application starting");
        initFromProperties();
        ensureTables();
        System.out.println("Application Started");
    }    
    //=======================================================================
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application ended normally");
    }
    //***********************************************************************
    private void initFromProperties () {
        System.out.println("Loading Properties");
        try{
            //---------------------------------------------------------------
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            //---------------------------------------------------------------
            initElectra(properties);
            LifeTimeValues.initValues(properties);
            System.out.println("Properties Loaded");
        }
        catch (Exception e) {
            System.out.println("ERROR: Properties error");
            System.out.println(e.getMessage());
        }
    }
    //=======================================================================
    /**
     * Inits the static part of Electra class in Methionine
     * @param properties 
     */
    private void initElectra (Properties properties) {
        //-----------------------------------------------------
        String masterhost = properties.getProperty(PROP_MASTER_DBHOST);
        String slavehost = properties.getProperty(PROP_SLAVE_DBHOST);
        String master = properties.getProperty(PROP_DBMASTER);
        //-----------------------------------------------------
        if (masterhost == null) System.out.println("Warning: Property " + PROP_MASTER_DBHOST + " Not set");
        Electra.setDefaultMasterHost(masterhost);
        //-----------------------------------------------------
        if (slavehost == null) { 
            System.out.println("Warning: Property " + PROP_SLAVE_DBHOST + " Not set");
            slavehost = masterhost;
        }
        Electra.setDefaultSlaveHost(slavehost);
        //-----------------------------------------------------
        //Below. If this is the master DB server.
        if (master == null) System.out.println("Warning: Property " + PROP_DBMASTER + " Not set");
        else {
            ismaster = master.equalsIgnoreCase("Y");
            Electra.setDefaultMaster(ismaster);
        }
        //-----------------------------------------------------
        Electra.setDefaultDBUser(DBUSER);
        Electra.setDefaultDBPass(DBPASS);
        //-----------------------------------------------------
        System.out.println("Electra initialized");
        //-----------------------------------------------------
    }
    //***********************************************************************
    /**
     * Ensures all tables are created.
     */
    private void ensureTables () {
        //*******************************************************************
        if (ismaster) { System.out.println("Ensuring tables"); }
        else {
            System.out.println("This is not a master");
            System.out.println("Ensuring tables not applying");
            return;
        }
        //*******************************************************************
        AurigaObject auriga = new AurigaObject();
        //*******************************************************************
        try { 
            auriga.getAuthLambda().ensureTables(); 
            System.out.println("Tables in "+ methionine.DBNames.AUTHDATA + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + methionine.DBNames.AUTHDATA + " tables error");
            System.out.println(e.getMessage());
        }
        //===================================================================
        try {
            auriga.projectAtlas().ensureTables(); 
            System.out.println("Tables in "+ methionine.DBNames.PROJECT + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + methionine.DBNames.PROJECT + " tables error");
            System.out.println(e.getMessage());
        }
        //===================================================================
        try {
            auriga.messagingAtlas().ensureTables(); 
            System.out.println("Tables in "+ methionine.DBNames.MESSAGING + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + methionine.DBNames.MESSAGING + " tables error");
            System.out.println(e.getMessage());
        }
        //===================================================================
        try {
            auriga.getBillingLambda().ensureTables(); 
            System.out.println("Tables in "+ methionine.DBNames.FINANCE + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + methionine.DBNames.FINANCE + " tables error");
            System.out.println(e.getMessage());
        }        
        //*******************************************************************
        //We ensure Universe tables exists
        try {
            auriga.getUniverseAtlas().ensureTables();
            System.out.println("Tables in "+ threonine.DBNames.UNIVERSE + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + threonine.DBNames.UNIVERSE + " tables error");
            System.out.println(e.getMessage());
        }
        //===================================================================   
        //We ensure Maps tables exists
        try {
            auriga.getMapsLambda().ensureTables();
            System.out.println("Tables in "+ threonine.DBNames.MAPPING + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + threonine.DBNames.MAPPING + " tables error");
            System.out.println(e.getMessage());
        }
        //*******************************************************************
        try {
            auriga.getDesignLambda().ensureTables();
            System.out.println("Tables in "+ tryptophan.DBNames.DESIGN + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + tryptophan.DBNames.DESIGN + " tables error");
            System.out.println(e.getMessage());
        }
        //===================================================================   
        try {
            auriga.getSampleLambda().ensureTables();
            System.out.println("Tables in "+ tryptophan.DBNames.SAMPLE + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + tryptophan.DBNames.SAMPLE + " tables error");
            System.out.println(e.getMessage());
        }
        //===================================================================   
        try {
            auriga.getTrialAtlas().ensureTables();
            System.out.println("Tables in "+ tryptophan.DBNames.TRIAL + " Ensured");
        }
        catch (Exception e) {
            System.out.println("ERROR: Ensuring " + tryptophan.DBNames.TRIAL + " tables error");
            System.out.println(e.getMessage());
        }        
        //*******************************************************************
    }
    //***********************************************************************
}
//****************************************************************************
