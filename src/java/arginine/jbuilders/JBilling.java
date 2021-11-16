package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.billing.ComunityTransfer;
import methionine.billing.UsagePeriod;
import methionine.billing.LedgerItem;
import methionine.billing.SystemCharge;
//***************************************************************************
public class JBilling {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String USERID = "userid";
    public static final String FROMUSER = "fromuser";
    public static final String TOUSER = "touser";
    public static final String USERNAME = "username";
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
    public static final String COST = "cost";
    public static final String PROJECTNAME = "projectname";
    public static final String EVENT = "event";
    public static final String FINALMINUTES = "finalminutes";
    public static final String FINALCOST = "finalcost";
    public static final String BILLINGREF = "billingref";
    public static final String DIRECTION = "direction";
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
    public static JsonObject getTransfer (ComunityTransfer transfer) {
        JsonObject jtransfer = new JsonObject();
        jtransfer.addPair(new JsonPair(IDECODE, transfer.idCode()));
        jtransfer.addPair(new JsonPair(DATE, transfer.getDate()));
        jtransfer.addPair(new JsonPair(FROMUSER, transfer.fromUser()));
        jtransfer.addPair(new JsonPair(TOUSER, transfer.toUser()));
        jtransfer.addPair(new JsonPair(USERNAME, transfer.getDisplayUser()));
        jtransfer.addPair(new JsonPair(DESCRIPTION, transfer.getDescription()));
        jtransfer.addPair(new JsonPair(AMOUNT, transfer.getAmount()));
        jtransfer.addPair(new JsonPair(CONVERTCURRENCY, transfer.conversionCurrency()));
        jtransfer.addPair(new JsonPair(CONVERTAMOUNT, transfer.getConversionAmount()));
        jtransfer.addPair(new JsonPair(DIRECTION, transfer.getDirection()));
        return jtransfer;
    }
    //=======================================================================
    public static JsonObject getTransfers (ComunityTransfer[] transfers) {
        JsonObject jtransfers = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (ComunityTransfer transfer : transfers) {
            jarray.addPair(new JsonPair(IDECODE, transfer.idCode()));
            jarray.addPair(new JsonPair(DATE, transfer.getDate()));
            jarray.addPair(new JsonPair(FROMUSER, transfer.fromUser()));
            jarray.addPair(new JsonPair(TOUSER, transfer.toUser()));
            jarray.addPair(new JsonPair(USERNAME, transfer.getDisplayUser()));
            jarray.addPair(new JsonPair(DESCRIPTION, transfer.getDescription()));
            jarray.addPair(new JsonPair(AMOUNT, transfer.getAmount()));
            jarray.addPair(new JsonPair(CONVERTCURRENCY, transfer.conversionCurrency()));
            jarray.addPair(new JsonPair(CONVERTAMOUNT, transfer.getConversionAmount()));
            jarray.addPair(new JsonPair(DIRECTION, transfer.getDirection()));
            jarray.addToArray();
        }
        jtransfers.addPair(new JsonPair(COUNT, jarray.getCount()));
        jtransfers.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jtransfers;
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
    public static JsonObject systemCharges (SystemCharge[] charges) {
        JsonObject jcharges = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (SystemCharge charge : charges) {
            jarray.addPair(new JsonPair(IDECODE, charge.idCode()));
            jarray.addPair(new JsonPair(DATE, charge.getDate()));
            jarray.addPair(new JsonPair(USERID, charge.userID()));
            jarray.addPair(new JsonPair(PROJECTID, charge.projectID()));
            jarray.addPair(new JsonPair(PROJECTNAME, charge.projectName()));
            jarray.addPair(new JsonPair(DESCRIPTION, charge.getDescription()));
            jarray.addPair(new JsonPair(COST, charge.getCost()));
            jarray.addPair(new JsonPair(BILLINGREF, charge.ledgerRef()));
            jarray.addToArray();
        }
        jcharges.addPair(new JsonPair(COUNT, jarray.getCount()));
        jcharges.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jcharges;
    }
    //***********************************************************************
}
//***************************************************************************
