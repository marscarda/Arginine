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
import methionine.finance.ComunityTransfer;
//***************************************************************************
@WebServlet(name = "ApiTransferFrom", urlPatterns = {ApiTransferFrom.URL}, loadOnStartup=1)
public class ApiTransferFrom extends ApiAlpha {
    public static final String URL = "/finantial/user/transferfrom";
    public static final String TRANSFERFROM = "transferfrom";
    public static final String CONVERTCURRENCY = "currency";
    public static final String CONVERTAMOUNT = "moneyamount";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "uramount";
    public static final String JTRANSFER = "transfer";
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
        float convertamount = 0;
        float amount = 0;
        String convertcurrency = req.getParameter(CONVERTCURRENCY);
        String description = req.getParameter(DESCRIPTION);
        try { userid = Long.parseLong(req.getParameter(USERID)); } catch (Exception e) {}
        try { convertamount = Float.parseFloat(req.getParameter(CONVERTAMOUNT)); } catch (Exception e) {}
        try { amount = Float.parseFloat(req.getParameter(AMOUNT)); } catch (Exception e) {}
        try {
            ComunityTransfer transfer = new ComunityTransfer();
            transfer.setFromUserId(userid);
            transfer.setAmount(amount);
            transfer.setConversionCurrency(convertcurrency);
            transfer.setConversionAmount(convertamount);
            transfer.setDescription(description);
            ExcLedgerBackOfice exec = new ExcLedgerBackOfice();
            exec.setAuriga(flowalpha.getAurigaObject());
            exec.addSystemTransfer(transfer, session.getUserId());
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Transfer from done"));
            //-------------------------------------------------------
            jsonresp.addPair(new JsonPair(JTRANSFER, JBilling.getTransfer(transfer)));
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
