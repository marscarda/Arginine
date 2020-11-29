package arginine.main;
//***************************************************************************
import arginine.WebBackAlpha;
import serine.blogging.publication.PostRecord;
//***************************************************************************
public class WebBackHome extends WebBackAlpha {
    //***********************************************************************
    PostRecord[] posts = new PostRecord[0];
    void setPosts (PostRecord[] posts) { this.posts = posts; }
    public PostRecord[] getPosts () { return posts; }
    //***********************************************************************
    

}
//***************************************************************************
