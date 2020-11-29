package arginine.post;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JPosts;
import serine.blogging.publication.PostRecord;
//***************************************************************************
public class WebBackPost extends WebBackAlpha {
    //***********************************************************************
    PostRecord post = null;
    void setPostRecord (PostRecord post) { this.post = post; }
    public PostRecord getPostRecord () {
        if (post == null) return new PostRecord();
        return post;
    }
    //***********************************************************************
    public String jParts () {
        return JPosts.getParts(post.postParts()).toString();
    }
    //***********************************************************************
    public String getPostingURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontPosting.PAGE);
        return url.toString();
    }
    //=======================================================================
    public String getCreateTextPartURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreateTextPart.URL);
        return url.toString();
    }
    //***********************************************************************
}
//***************************************************************************

