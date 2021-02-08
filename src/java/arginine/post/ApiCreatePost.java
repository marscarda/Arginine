package arginine.post;
//***************************************************************************
import arginine.ApiAlpha;
import arginine.FlowAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mars.jsonsimple.JsonObject;
import mars.jsonsimple.JsonPair;
import methionine.auth.Session;
import serine.blogging.publication.PostRecord;
//***************************************************************************
@WebServlet(name = "ApiCreatePost", urlPatterns = {ApiCreatePost.URL}, loadOnStartup=1)
public class ApiCreatePost extends ApiAlpha {
    public static final String URL = "/post/createpost";
    public static final String POSTID = "postid";
    public static final String TITLE = "title";
    public static final String SUMARY = "sumary";
    public static final String PUBLISHED = "published";
    public static final String JPOST = "post";
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
        String title = req.getParameter(TITLE);
        String sumary = req.getParameter(SUMARY);
        try {
            //----------------------------------------
            PostRecord post = new PostRecord();
            post.setTitle(title);
            post.setSumary(sumary);
            flowalpha.getAurigaObject().getPubsLambda().createPost(post);
            //----------------------------------------
            JsonObject jsonresp = new JsonObject();
            jsonresp.addPair(new JsonPair(RESULT, RESULTOK));
            jsonresp.addPair(new JsonPair(RESULTDESCRIPTION, "post created"));
            //----------------------------------------
            JsonObject jpost = new JsonObject();
            jpost.addPair(new JsonPair(POSTID, post.postID()));
            jpost.addPair(new JsonPair(TITLE, post.postTitle()));
            jpost.addPair(new JsonPair(SUMARY, post.postSumary()));
            jpost.addPair(new JsonPair(PUBLISHED, post.isPublished()));
            jsonresp.addPair(new JsonPair(JPOST, jpost));
            this.sendResponse(resp, jsonresp);
        }        
        catch (Exception e) {
            sendServerErrorResponse(resp);
            System.out.println("Unable to create post");
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
