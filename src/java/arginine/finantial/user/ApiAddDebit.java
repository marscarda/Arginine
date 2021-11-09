package arginine.finantial.user;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JBilling;
import histidine.finance.ExcLedgerBackOfice;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.Session;
import methionine.billing.LedgerItem;
//***************************************************************************
@WebServlet(name = "ApiAddDebit", urlPatterns = {ApiAddDebit.URL}, loadOnStartup=1)
public class ApiAddDebit extends ApiAlpha {
    public static final String URL = "/finantial/user/adddebit";
    public static final String CONVERTCURRENCY = "currency";
    public static final String CONVERTAMOUNT = "moneyamount";
    public static final String DESCRIPTION = "description";
    public static final String URAMOUNT = "uramount";
    public static final String JENTRY = "entry";
   //************************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //==========================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //==========================================================
        Session session = flowalpha.getSession();
        boolean allowed = false;
        if (session.isAdmin()) allowed = true;
        if (!allowed) {
            this.sendErrorResponse(resp, "Unuthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        long userid = 0;
        float moneyamount = 0;
        float uramount = 0;
        String currency = req.getParameter(CONVERTCURRENCY);
        String description = req.getParameter(DESCRIPTION);
        try { userid = Long.parseLong(req.getParameter(USERID)); } catch (Exception e) {}
        try { moneyamount = Float.parseFloat(req.getParameter(CONVERTAMOUNT)); } catch (Exception e) {}
        try { uramount = Float.parseFloat(req.getParameter(URAMOUNT)); } catch (Exception e) {}
        try {
            LedgerItem ledgeritem = new LedgerItem();
            ledgeritem.setUserId(userid);
            ledgeritem.setCurrency(currency);
            ledgeritem.setMoney(moneyamount);
            ledgeritem.setDescription(description);
            ledgeritem.setDebit(uramount);
            ExcLedgerBackOfice exec = new ExcLedgerBackOfice();
            exec.setAuriga(flowalpha.getAurigaObject());
            exec.addEnty(ledgeritem, session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Ledger Entry Added"));
            //-------------------------------------------------------
            jsonresp.addPair(new JsonPair(JENTRY, JBilling.getLedgerEntry(ledgeritem)));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
        }
        catch (AppException e) { this.sendErrorResponse(resp, e.getMessage(), e.getErrorCode()); }        
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Failed to add credit");
            System.out.println(e.getMessage());
            //----------------------------------------------
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
   }
   //************************************************************************
}
//***************************************************************************
