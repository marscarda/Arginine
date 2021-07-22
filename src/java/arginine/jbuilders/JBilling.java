package arginine.jbuilders;
//***************************************************************************
import mars.jsonsimple.JsonArray;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methinine.billing.BillingPeriod;
import methinine.billing.FundTicket;
import methinine.billing.LedgerEntry;
//***************************************************************************
public class JBilling {
    //***********************************************************************
    public static final String COUNT = "count";
    public static final String ITEMS = "items";
    //-----------------------------------------------------------------------
    public static final String TICKETID = "ticketid";
    public static final String USERID = "userid";
    public static final String DATE = "date";
    public static final String SIZE = "size";
    //public static final String SPENT = "spent";
    //public static final String REMAIN = "remain";
    //public static final String CURRENCY = "currency";
    //public static final String AMOUNT = "amount";
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
    public static final String BILLED = "billed";
    //***********************************************************************
    public static JsonObject getLedgerEntryList (LedgerEntry[] entries) {
        JsonObject jentries = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (LedgerEntry entry : entries) {
            jarray.addPair(new JsonPair(DATE, entry.getDate()));
            jarray.addPair(new JsonPair(USERID, entry.userID()));
            jarray.addPair(new JsonPair(TICKETID, entry.tiketID()));
            jarray.addPair(new JsonPair(DESCRIPTION, entry.description()));
            jarray.addPair(new JsonPair(SIZE, entry.entrySize()));
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
        jentry.addPair(new JsonPair(TICKETID, entry.tiketID()));
        jentry.addPair(new JsonPair(DESCRIPTION, entry.description()));
        jentry.addPair(new JsonPair(SIZE, entry.entrySize()));
        return jentry;
    }
    //***********************************************************************
    public static JsonObject usagePeriods (BillingPeriod[] periods) {
        JsonObject jentries = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (BillingPeriod period : periods) {
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
            jarray.addPair(new JsonPair(BILLED, period.billed()));
            jarray.addToArray();
        }
        jentries.addPair(new JsonPair(COUNT, jarray.getCount()));
        jentries.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jentries;
    }
    //***********************************************************************
    /*
    public static JsonObject getFundPostsList (FundTicket[] posts) {
        JsonObject jposts = new JsonObject();
        JsonArray jarray = new JsonArray();
        for (FundTicket post : posts) {
            jarray.addPair(new JsonPair(POSTID, post.postID()));
            jarray.addPair(new JsonPair(DATE, post.getDate()));
            jarray.addPair(new JsonPair(DESCRIPTION, post.getDescription()));
            jarray.addPair(new JsonPair(SIZE, post.getSize()));
            jarray.addPair(new JsonPair(SPENT, post.getSpent()));
            jarray.addPair(new JsonPair(REMAIN, post.getRemain()));
            jarray.addPair(new JsonPair(CURRENCY, post.getCurrency()));
            jarray.addPair(new JsonPair(AMOUNT, post.getAmount()));
            jarray.addPair(new JsonPair(SPENTAMOUNT, post.getSpentAmount()));
            jarray.addPair(new JsonPair(REMAINAMOUNT, post.getRemainAmount()));
            jarray.addPair(new JsonPair(STATUS, post.getStatus()));
            jarray.addToArray();
        }
        jposts.addPair(new JsonPair(COUNT, jarray.getCount()));
        jposts.addPair(new JsonPair(ITEMS, jarray.getArray()));
        return jposts;
    }
    */
    //=======================================================================
    /*
    public static JsonObject getFundPost (FundTicket post) {
        JsonObject jrecord = new JsonObject();
        jrecord.addPair(new JsonPair(POSTID, post.postID()));
        jrecord.addPair(new JsonPair(DATE, post.getDate()));
        jrecord.addPair(new JsonPair(DESCRIPTION, post.getDescription()));
        jrecord.addPair(new JsonPair(SIZE, post.getSize()));
        jrecord.addPair(new JsonPair(SPENT, post.getSpent()));
        jrecord.addPair(new JsonPair(REMAIN, post.getRemain()));
        jrecord.addPair(new JsonPair(CURRENCY, post.getCurrency()));
        jrecord.addPair(new JsonPair(AMOUNT, post.getAmount()));
        jrecord.addPair(new JsonPair(SPENTAMOUNT, post.getSpentAmount()));
        jrecord.addPair(new JsonPair(REMAINAMOUNT, post.getRemainAmount()));
        jrecord.addPair(new JsonPair(STATUS, post.getStatus()));
        return jrecord;
    }
    */
    //***********************************************************************
    /*
    */
    //=======================================================================
    /*
    */
    //***********************************************************************
}
//***************************************************************************
