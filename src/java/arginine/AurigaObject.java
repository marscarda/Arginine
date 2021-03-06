package arginine;
//**************************************************************************
import methinine.billing.BillingLambda;
import methionine.Electra;
import methionine.auth.AuthLamda;
import methionine.project.ProjectLambda;
import serine.access.AccessLambda;
import serine.blogging.publication.PubsLambda;
import serine.pubs.object.ObjectPubLambda;
import serine.webmedia.WebMediaList;
import threonine.map.MapsLambda;
import tryptophan.survey.action.ActionSetLambda;
import tryptophan.survey.metric.MetricQueryInterface;
import tryptophan.survey.publicview.PublicViewLambda;
import tryptophan.survey.reaction.ReactionLambda;
import tryptophan.survey.sampling.SampleLamda;
import tryptophan.universe.UniverseQryInterface;
//**************************************************************************
/**
 * Holds instances of interfaces to interact with the database.
 * @author marian
 */
public class AurigaObject {
    //*************************************************************
    private final Electra electra = new Electra();
    public Electra getElectra () { return electra; }
    //*************************************************************
    /**
     * The direct connection to the auth data in DB.
     */
    AuthLamda authlambda = null;
    public AuthLamda getAuthLambda () throws Exception {
        if (authlambda == null) {
            authlambda = new AuthLamda();
            authlambda.setElectraObject(electra);
            authlambda.setDataBaseName(LifeTimeValues.dbauth);
        }
        return authlambda;
    }
    //*************************************************************
    /**
     * The direct connection to projects data
     */
    ProjectLambda workteamlamda = null;
    public ProjectLambda getProjectLambda () throws Exception {
        if (workteamlamda == null) {
            workteamlamda = new ProjectLambda();
            workteamlamda.setElectraObject(electra);
            workteamlamda.setDataBaseName(LifeTimeValues.dbauth);
        }
        return workteamlamda;
    }
    //*************************************************************
    BillingLambda billinglambda = null;
    public BillingLambda getBillingLambda () {
        if (billinglambda == null) {
            billinglambda = new BillingLambda();
            billinglambda.setElectraObject(electra);
            billinglambda.setDataBaseName(LifeTimeValues.dbbilling);
        }
        return billinglambda;
    }
    //*************************************************************
    UniverseQryInterface universeqryinterface = null;
    public UniverseQryInterface getUniverseQryInterface () throws Exception {
        if (universeqryinterface == null) {
            universeqryinterface = new UniverseQryInterface();
            universeqryinterface.setElectraObject(electra);
            universeqryinterface.setDataBaseName(LifeTimeValues.dbuniverse);
        }
        return universeqryinterface;
    }
    //*************************************************************
    MapsLambda mapslambda = null;
    public MapsLambda getMapsLambda () {
        if (mapslambda == null) {
            mapslambda = new MapsLambda();
            mapslambda.setElectraObject(electra);
            mapslambda.setDataBaseName(LifeTimeValues.dbmaps);
        }
        return mapslambda;
    }
    //*************************************************************
    MetricQueryInterface metricsinterface = null;
    public MetricQueryInterface getMetricsQryInterface () throws Exception {
        if (metricsinterface == null) {
            metricsinterface = new MetricQueryInterface();
            metricsinterface.setElectraObject(electra);
            metricsinterface.setDataBaseName(LifeTimeValues.dbmetrics);
        }
        return metricsinterface;
    }
    //*************************************************************
    ActionSetLambda surveylambda = null;
    public ActionSetLambda getSurveyLambda () {
        if (surveylambda == null) {
            surveylambda = new ActionSetLambda();
            surveylambda.setElectraObject(electra);
            surveylambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return surveylambda;
    }
    //*************************************************************
    PublicViewLambda ifacepublicvie = null;
    public PublicViewLambda getPubViewInterface () throws Exception {
        if (ifacepublicvie == null) {
            ifacepublicvie = new PublicViewLambda();
            ifacepublicvie.setElectraObject(electra);
            ifacepublicvie.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return ifacepublicvie;
    }
    //*************************************************************
    SampleLamda samplelamda = null;
    public SampleLamda getSampleLambda () throws Exception {
        if (samplelamda == null) {
            samplelamda = new SampleLamda();
            samplelamda.setElectraObject(electra);
            samplelamda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return samplelamda;
    }
    //*************************************************************
    ReactionLambda responcelambda = null;
    public ReactionLambda getResponseLambda () throws Exception {
        if (responcelambda == null) {
            responcelambda = new ReactionLambda();
            responcelambda.setElectraObject(electra);
            responcelambda.setDataBaseName(LifeTimeValues.dbsurvey);
        }
        return responcelambda;
    }
    //*************************************************************
    AccessLambda accesslambda = null;
    public AccessLambda getAccessLambda () throws Exception {
        if (accesslambda == null) {
            accesslambda = new AccessLambda();
            accesslambda.setElectraObject(electra);
            accesslambda.setDataBaseName(LifeTimeValues.dbaccess);
        }
        return accesslambda;
    }
    //*************************************************************
    PubsLambda pubslambda = null;
    public PubsLambda getPubsLambda () throws Exception {
        if (pubslambda == null) {
            pubslambda = new PubsLambda();
            pubslambda.setElectraObject(electra);
            pubslambda.setDataBaseName(LifeTimeValues.dbpublication);
        }
        return pubslambda;
    }
    //*************************************************************
    ObjectPubLambda objpubslambda = null;
    public ObjectPubLambda getObjectPubsLambda () throws Exception {
        if (objpubslambda == null) {
            objpubslambda = new ObjectPubLambda();
            objpubslambda.setElectraObject(electra);
            objpubslambda.setDataBaseName(LifeTimeValues.dbpublication);
        }
        return objpubslambda;
    }
    //*************************************************************
    static final WebMediaList MEDIALIST = new WebMediaList();
    public WebMediaList mediaList () { return MEDIALIST; }
    //*************************************************************
}
//**************************************************************************
