package arginine.finantial;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import arginine.jbuilders.JBilling;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.billing.Payment;
import methionine.auth.Session;
//***************************************************************************
@WebServlet(name = "ApiCreatePayment", urlPatterns = {ApiCreatePayment.URL}, loadOnStartup=1)
public class ApiCreatePayment extends ApiAlpha {
    public static final String URL = "/users/createpayment";
    public static final String CURRENCY = "currency";
    public static final String AMOUNT = "amount";
    public static final String SIZE = "size";
    public static final String JPAYMENT = "payment";
    //***********************************************************************
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
            this.sendErrorResponse(resp, "Unauthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        long userid = 0;
        float amount = 0;
        int size = 0;
        String currency = req.getParameter(CURRENCY);
        try { userid = Long.parseLong(req.getParameter(USERID)); } catch (Exception e) {}
        try { amount = Float.parseFloat(req.getParameter(AMOUNT)); } catch (Exception e) {}
        try { size = Integer.parseInt(req.getParameter(SIZE)); } catch (Exception e) {}
        try {
            Payment payment = new Payment();
            payment.setUserID(userid);
            payment.setCurrency(currency);
            payment.setAmount(amount);
            payment.setSize(size);
            flowalpha.getAurigaObject().getBillingLambda().createPayment(payment);
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Payment created"));
            //-------------------------------------------------------
            jsonresp.addPair(new JsonPair(JPAYMENT, JBilling.getPayment(payment)));
            //-------------------------------------------------------
            this.sendResponse(resp, jsonresp);
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to create payment");
            System.out.println(e.getMessage());            
        }
        //==========================================================
        this.finalizeJob(flowalpha);
        this.destroyFlowAlpha(flowalpha);
        //==========================================================
    }
    //***********************************************************************
}
//***************************************************************************
