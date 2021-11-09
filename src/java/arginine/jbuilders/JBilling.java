package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.billing.UsagePeriod;
import methionine.billing.LedgerItem;
//***************************************************************************
public class JBilling {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String USERID = "userid";
    public static final String DATE = "date";
    public static final String CONVERTCURRENCY = "conversioncurrency";
    public static final String CONVERTAMOUNT = "conversionamount";
    public static final String AMOUNT = "amount";
    public static final String DESCRIPTION = "description";
    public static final String REFERENCE = "reference";
    public static final String IDECODE = "idcode";
    public static final String PROJECTID = "projectid";
    public static final String DATESTART = "datestart";
    public static final String DATEEND = "dateend";
    public static final String COSTPERDAY = "costperday";    
    public static final String PROJECTNAME = "projectname";
    public static final String EVENT = "event";
    public static final String FINALMINUTES = "finalminutes";
    public static final String FINALCOST = "finalcost";
    public static final String BILLINGREF = "billingref";
    //***********************************************************************
    public static JsonObject getLedger (LedgerItem[] ledger) {
        JsonObject jledger = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (LedgerItem item : ledger) {
            jarray.addPair(new JsonPair(DATE, item.getDate()));
            jarray.addPair(new JsonPair(USERID, item.userID()));
            jarray.addPair(new JsonPair(CONVERTCURRENCY, item.conversionCurrency()));
            jarray.addPair(new JsonPair(CONVERTAMOUNT, item.conversionAmount()));
            jarray.addPair(new JsonPair(DESCRIPTION, item.getDescription()));
            jarray.addPair(new JsonPair(AMOUNT, item.getAmount()));
            jarray.addPair(new JsonPair(REFERENCE, item.getReference()));
            jarray.addToArray();
        }
        jledger.addPair(new JsonPair(COUNT, jarray.getCount()));
        jledger.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jledger;
    }
    //=======================================================================
    public static JsonObject getLedgerEntry (LedgerItem item) {
        JsonObject jentry = new JsonObject();
        jentry.addPair(new JsonPair(DATE, item.getDate()));
        jentry.addPair(new JsonPair(USERID, item.userID()));
        jentry.addPair(new JsonPair(CONVERTCURRENCY, item.conversionCurrency()));
        jentry.addPair(new JsonPair(CONVERTAMOUNT, item.conversionAmount()));
        jentry.addPair(new JsonPair(DESCRIPTION, item.getDescription()));
        jentry.addPair(new JsonPair(AMOUNT, item.getAmount()));
        jentry.addPair(new JsonPair(REFERENCE, item.getReference()));
        return jentry;
    }
    //***********************************************************************
    public static JsonObject usagePeriods (UsagePeriod[] periods) {
        JsonObject jentries = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (UsagePeriod period : periods) {
            jarray.addPair(new JsonPair(IDECODE, period.idCode()));
            jarray.addPair(new JsonPair(USERID, period.userID()));
            jarray.addPair(new JsonPair(PROJECTID, period.projectID()));
            jarray.addPair(new JsonPair(DATESTART, period.dateStart()));
            jarray.addPair(new JsonPair(DATEEND, period.dateEnd()));
            jarray.addPair(new JsonPair(COSTPERDAY, period.costPerDay()));
            jarray.addPair(new JsonPair(PROJECTNAME, period.projectName()));
            jarray.addPair(new JsonPair(EVENT, period.startingEvent()));
            jarray.addPair(new JsonPair(FINALMINUTES, period.finalMinutes()));
            jarray.addPair(new JsonPair(FINALCOST, period.finalCost()));
            jarray.addPair(new JsonPair(BILLINGREF, period.billReference()));
            jarray.addToArray();
        }
        jentries.addPair(new JsonPair(COUNT, jarray.getCount()));
        jentries.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jentries;
    }
    //***********************************************************************
}
//***************************************************************************
