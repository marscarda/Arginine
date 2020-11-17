package arginine.post;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JPosts;
import serine.blogging.publication.PostRecord;
//***************************************************************************
public class WebBackPosting extends WebBackAlpha {
    //***********************************************************************
    PostRecord[] posts = new PostRecord[0];
    void setPosts (PostRecord[] posts) { this.posts = posts; }
    public PostRecord[] getPosts () { return posts; }
    //***********************************************************************
    public String getCreatePostURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(ApiCreatePost.URL);
        return url.toString();
    }
    //=======================================================================
    public String jPosts () {
        return JPosts.getPosts(posts).toString();
    }
    //***********************************************************************
    public String postDetailURL () {
        StringBuilder url = new StringBuilder(this.getRootURL());
        url.append(WebFrontPost.PAGE);
        return url.toString();
    }        
    //=======================================================================
    //***********************************************************************
}
//***************************************************************************
