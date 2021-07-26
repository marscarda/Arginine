package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.billing.UsagePeriod;
import methionine.billing.LedgerEntry;
import methionine.billing.Payment;
//***************************************************************************
public class JBilling {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String PAYMENTID = "paymentid";
    public static final String PAYMENTCODE = "paymentcode";
    public static final String USERID = "userid";
    public static final String DATE = "date";
    public static final String SIZE = "size";
    //public static final String SPENT = "spent";
    //public static final String REMAIN = "remain";
    public static final String CURRENCY = "currency";
    public static final String AMOUNT = "amount";
    //public static final String SPENTAMOUNT = "spentamount";
    //public static final String REMAINAMOUNT = "remainamount";
    public static final String DESCRIPTION = "description";
    //public static final String STATUS = "status";
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
    public static JsonObject getLedgerEntryList (LedgerEntry[] entries) {
        JsonObject jentries = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (LedgerEntry entry : entries) {
            jarray.addPair(new JsonPair(DATE, entry.getDate()));
            jarray.addPair(new JsonPair(USERID, entry.userID()));
            jarray.addPair(new JsonPair(PAYMENTCODE, entry.paymentCode()));
            jarray.addPair(new JsonPair(DESCRIPTION, entry.description()));
            jarray.addPair(new JsonPair(SIZE, entry.entrySize()));
            jarray.addPair(new JsonPair(BILLINGREF, entry.billingRef()));
            jarray.addToArray();
        }
        jentries.addPair(new JsonPair(COUNT, jarray.getCount()));
        jentries.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jentries;
    }
    //=======================================================================
    public static JsonObject getLedgerEntry (LedgerEntry entry) {
        JsonObject jentry = new JsonObject();
        jentry.addPair(new JsonPair(DATE, entry.getDate()));
        jentry.addPair(new JsonPair(USERID, entry.userID()));
        jentry.addPair(new JsonPair(PAYMENTCODE, entry.paymentCode()));
        jentry.addPair(new JsonPair(DESCRIPTION, entry.description()));
        jentry.addPair(new JsonPair(SIZE, entry.entrySize()));
        jentry.addPair(new JsonPair(BILLINGREF, entry.billingRef()));
        return jentry;
    }
    //***********************************************************************
    public static JsonObject getPaymentList (Payment[] payments) {
        JsonObject jpayments = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (Payment payment : payments) {
            jarray.addPair(new JsonPair(PAYMENTID, payment.paymentID()));
            jarray.addPair(new JsonPair(PAYMENTCODE, payment.getCode()));
            jarray.addPair(new JsonPair(DATE, payment.getDate()));
            jarray.addPair(new JsonPair(CURRENCY, payment.getCurrency()));
            jarray.addPair(new JsonPair(AMOUNT, payment.getAmount()));
            jarray.addPair(new JsonPair(SIZE, payment.getSize()));
            jarray.addToArray();
        }
        jpayments.addPair(new JsonPair(COUNT, jarray.getCount()));
        jpayments.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jpayments;
    }
    //=======================================================================
    public static JsonObject getPayment (Payment payment) {
        JsonObject jpayment = new JsonObject();
        jpayment.addPair(new JsonPair(PAYMENTID, payment.paymentID()));
        jpayment.addPair(new JsonPair(PAYMENTCODE, payment.getCode()));
        jpayment.addPair(new JsonPair(DATE, payment.getDate()));
        jpayment.addPair(new JsonPair(CURRENCY, payment.getCurrency()));
        jpayment.addPair(new JsonPair(AMOUNT, payment.getAmount()));
        jpayment.addPair(new JsonPair(SIZE, payment.getSize()));
        return jpayment;
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
