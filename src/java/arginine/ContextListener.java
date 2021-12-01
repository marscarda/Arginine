package arginine;
//****************************************************************************
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import methionine.finance.BillingLambda;
import methionine.Electra;
import methionine.auth.AuthLamda;
import methionine.project.ProjectLambda;
import histidine.AurigaObject;
import tryptophan.design.DesignTabs;
import threonine.map.QueryMapTabs;
import threonine.universe.UniverseAtlas;
import tryptophan.trial.TrialTabs;
import tryptophan.sample.SampleTabs;
//****************************************************************************
@WebListener
public class ContextListener implements ServletContextListener {
    //***********************************************************************
    //These properties are directly initialized into Electra.
    static final String PROP_MASTER_DBHOST = "db_master_host";
    static final String PROP_SLAVE_DBHOST = "db_slave_host";
    static final String PROP_DBUSER = "db_user";
    static final String PROP_DBPASS = "db_pass";
    static final String PROP_DBMASTER = "db_ismaster";
    //-----------------------------------------------------------------------
    boolean ismaster = false;
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
        String user = properties.getProperty(PROP_DBUSER);
        String pass = properties.getProperty(PROP_DBPASS);
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
        if (user == null) System.out.println("Warning: Property " + PROP_DBUSER + " Not set");
        else Electra.setDefaultDBUser(user);
        //-----------------------------------------------------
        if (pass == null) System.out.println("Warning: Property " + PROP_DBPASS + " Not set");
        else Electra.setDefaultDBPass(pass);
        //-----------------------------------------------------
        //Below. If this is the master DB server.
        if (master == null) System.out.println("Warning: Property " + PROP_DBMASTER + " Not set");
        else {
            ismaster = master.equalsIgnoreCase("Y");
            Electra.setDefaultMaster(ismaster);
        }
        //-----------------------------------------------------
        System.out.println("Electra initialized");
        //-----------------------------------------------------
    }
    //***********************************************************************
    /**
     * Ensures all tables are created.
     */
    private void ensureTables () {
        //----------------------------------------------------------
        System.out.println("Ensuring tables");
        if (!ismaster) return;
        //----------------------------------------------------------
        Electra electra = new Electra();
        //----------------------------------------------------------
        //Ensures the auth database.
        if (AurigaObject.dbauth != null) {
            AuthLamda auth = new AuthLamda();
            auth.setElectraObject(electra);
            auth.setDataBaseName(AurigaObject.dbauth);
            try { 
                auth.ensureTables();
                System.out.println("Tables in " + AurigaObject.dbauth + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbauth + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        //We ensure Project tables exists 
        if (AurigaObject.dbauth != null) {
            ProjectLambda workteam = new ProjectLambda();
            workteam.setElectraObject(electra);
            workteam.setDataBaseName(AurigaObject.dbauth);
            try { 
                workteam.ensureTables();
                System.out.println("Workteam Tables in " + AurigaObject.dbauth + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbauth + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        //We ensure Billing tables exist 
        if (AurigaObject.dbbilling != null) {
            BillingLambda billing = new BillingLambda();
            billing.setElectraObject(electra);
            billing.setDataBaseName(AurigaObject.dbbilling);
            try { 
                billing.ensureTables();
                System.out.println("Billing Tables in " + AurigaObject.dbbilling + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbbilling + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        //We ensure Universe tables exists
        if (AurigaObject.dbuniverse != null) {
            UniverseAtlas univ = new UniverseAtlas();
            univ.setElectraObject(electra);
            univ.setDataBaseName(AurigaObject.dbuniverse);
            try { 
                univ.ensureTables();
                System.out.println("Tables in " + AurigaObject.dbuniverse + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbuniverse + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        //We ensure Maps tables exists
        if (AurigaObject.dbmaps != null) {
            QueryMapTabs maps = new QueryMapTabs();
            maps.setElectraObject(electra);
            maps.setDataBaseName(AurigaObject.dbmaps);
            try { 
                maps.ensureTables();
                System.out.println("Tables in " + AurigaObject.dbmaps + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbmaps + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        if (AurigaObject.dbdesign != null) {
            DesignTabs design = new DesignTabs();
            design.setElectraObject(electra);
            design.setDataBaseName(AurigaObject.dbdesign);
            try { 
                design.ensureTables();
                System.out.println("Tables in " + AurigaObject.dbdesign + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbdesign + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        if (AurigaObject.dbsample != null) {
            SampleTabs sample = new SampleTabs();
            sample.setElectraObject(electra);
            sample.setDataBaseName(AurigaObject.dbsample);
            try { 
                sample.ensureTables();
                System.out.println("Tables in " + AurigaObject.dbsample + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbsample + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        if (AurigaObject.dbspace != null) {
            TrialTabs environment = new TrialTabs();
            environment.setElectraObject(electra);
            environment.setDataBaseName(AurigaObject.dbspace);
            try { 
                environment.ensureTables();
                System.out.println("Tables in " + AurigaObject.dbspace + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + AurigaObject.dbspace + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        //----------------------------------------------------------
        /*
        if (LifeTimeValues.dbsample != null) {
            DesignTabs design = new DesignTabs();
            design.setElectraObject(electra);
            design.setDataBaseName(LifeTimeValues.dbsample);
            try { 
                design.ensureTables();
                System.out.println("Tables in " + LifeTimeValues.dbsample + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + LifeTimeValues.dbsample + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        */
        //----------------------------------------------------------
        // Below here deprecated.
        //----------------------------------------------------------
        /*
        if (LifeTimeValues.dbsurvey != null) {
            DesignTabs variable = new DesignTabs();
            variable.setElectraObject(electra);
            variable.setDataBaseName(LifeTimeValues.dbsurvey);
            try { 
                variable.ensureTables();
                System.out.println("Tables in " + LifeTimeValues.dbsurvey + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + LifeTimeValues.dbsurvey + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        */
        //----------------------------------------------------------
        /*
        if (LifeTimeValues.dbmetrics != null) {
            MetricQueryInterface metric = new MetricQueryInterface();
            metric.setElectraObject(electra);
            metric.setDataBaseName(LifeTimeValues.dbmetrics);
            try { 
                metric.ensureTables();
                System.out.println("Tables in " + LifeTimeValues.dbmetrics + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + LifeTimeValues.dbmetrics + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        */
        //----------------------------------------------------------
        /*
        if (LifeTimeValues.dbsurvey != null) {
            SurveyTabs survey = new SurveyTabs();
            survey.setElectraObject(electra);
            survey.setDataBaseName(LifeTimeValues.dbsurvey);
            try { 
                survey.ensureTables();
                System.out.println("Tables in " + LifeTimeValues.dbsurvey + " Ensured");
            }
            catch (Exception e) {
                System.out.println("ERROR: Ensuring " + LifeTimeValues.dbsurvey + " tables error");
                System.out.println(e.getMessage());
            }            
        }
        */
        //----------------------------------------------------------
        electra.disposeDBConnection();
        //----------------------------------------------------------
    }
    //***********************************************************************
}
//****************************************************************************
