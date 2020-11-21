package arginine.post;
//***************************************************************************
import arginine.ApiAlpha;
import static arginine.ApiAlpha.RESULT;
import static arginine.ApiAlpha.RESULTDESCRIPTION;
import static arginine.ApiAlpha.RESULTOK;
import arginine.FlowAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
import serine.blogging.publication.PostPart;
//***************************************************************************
@WebServlet(name = "ApiCreatePostPart", urlPatterns = {ApiCreateTextPart.URL}, loadOnStartup=1)
public class ApiCreateTextPart extends ApiAlpha {
    public static final String URL = "/post/createtxtpart";
    public static final String POSTID = "postid";
    public static final String TEXT = "text";
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
        long postid = 0;
        try { postid = Long.parseLong(req.getParameter(POSTID)); } catch (Exception e) {}
        String text = req.getParameter(TEXT);
        try {
            PostPart part = new PostPart();
            part.setType(PostPart.PARAGRAPH);
            part.setPostID(postid);
            part.setText(text);
            flowalpha.getAurigaObject().getPubsLambda().addPostPart(part);
            //----------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "post created"));
            //----------------------------------------
            //----------------------------------------
            this.sendResponse(resp, jsonresp);
        }
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to create post part");
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
