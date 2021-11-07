package arginine.finantial.user;
//***************************************************************************
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
//***************************************************************************
@WebServlet(name = "WebFrontUserFinantial", urlPatterns = {WebFrontUserFinantial.URLPATTERN}, loadOnStartup=1)
public class WebFrontUserFinantial extends WebFrontAlpha {
    //=======================================================================
    public static final String PAGE = "/finantial/user";
    public static final String URLPATTERN = PAGE + "/*";
    //=======================================================================
    
}
//***************************************************************************
