package arginine.post;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JPosts;
import serine.blogging.publication.PostPart;
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
    PostPart[] parts = new PostPart[0];
    void setParts (PostPart[] parts) { this.parts = parts; }
    //=======================================================================
    public String jParts () {
        return JPosts.getParts(parts).toString();
    }
    //***********************************************************************
    public String getCreateTextPartURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreateTextPart.URL);
        return url.toString();
    }
    //***********************************************************************
}
//***************************************************************************

