package arginine.objectpub;
//***************************************************************************
import arginine.ApiAlpha;
import static arginine.ApiAlpha.UNAUTHORIZED;
import arginine.FlowAlpha;
import arginine.jbuilders.JObjectPubs;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lycine.pubs.PubsCenter;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.AppException;
import methionine.auth.AuthLamda;
import methionine.auth.Session;
import serine.access.AccessLambda;
import serine.pubs.object.ObjectPub;
import serine.pubs.object.ObjectPubLambda;
//***************************************************************************
@WebServlet(name = "ApiCreateObjectPub", urlPatterns = {ApiCreateObjectPub.URL}, loadOnStartup=1)
public class ApiCreateObjectPub extends ApiAlpha {
    public static final String URL = "/objpubs/createpub";
    public static final String ACCESSNAME = "accessname";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String JOBJECTPUB = "objectpub";
    //***********************************************************************
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //==========================================================
        FlowAlpha flowalpha = this.createFlowAlpha(req, resp);
        this.initializeJob(flowalpha);
        //==========================================================
        Session session = flowalpha.getSession();
        if (!session.isValid()) {
            this.sendErrorResponse(resp, "Unuthorized", UNAUTHORIZED);
            fleeRequest(flowalpha);
            return;
        }
        //==========================================================
        String accessname = req.getParameter(ACCESSNAME);
        String title = req.getParameter(TITLE);
        String text = req.getParameter(TEXT);
        ObjectPub objpub = new ObjectPub();
        objpub.setAccessName(accessname);
        objpub.setTitle(title);
        objpub.setText(text);
        try {
            //------------------------------------------------------
            AuthLamda authlambda = flowalpha.getAurigaObject().getAuthLambda();
            AccessLambda acclambda = flowalpha.getAurigaObject().getAccessLambda();
            ObjectPubLambda objpublambda = flowalpha.getAurigaObject().getObjectPubsLambda();
            //------------------------------------------------------
            PubsCenter pubscenter = new PubsCenter();
            pubscenter.setAuthLambda(authlambda);
            pubscenter.setAccessLambda(acclambda);
            pubscenter.setObjectPubLambda(objpublambda);
            //------------------------------------------------------
            pubscenter.createObjectPub(objpub);
            //------------------------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "Publication created"));
            jsonresp.addPair(new JsonPair(JOBJECTPUB, JObjectPubs.getObjectPub(objpub)));
            this.sendResponse(resp, jsonresp);
            //------------------------------------------------------
        }
        catch (AppException e) {
            sendServerErrorResponse(resp);
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());            
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to create object pub");
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
