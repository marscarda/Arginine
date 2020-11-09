package arginine.main;
//***************************************************************************
import arginine.FlowBeta;
import arginine.WebFrontAlpha;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//***************************************************************************
@WebServlet(name = "WebRequestHost", urlPatterns = {""})
public class WebRequestHost extends WebFrontAlpha {
    //***********************************************************************
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //===================================================================
        FlowBeta flowbeta  = this.createFlowBeta(request, response);
        this.initialJob(flowbeta);
        //===================================================================
        flowbeta.setStatusResponse(302);
        flowbeta.setHeader("Location", WebFrontHome.PAGE);
        //===================================================================
        this.finallJob(flowbeta);
        this.destroyFlowBeta(flowbeta);
        //===================================================================
    }
    //***********************************************************************
}
//***************************************************************************
