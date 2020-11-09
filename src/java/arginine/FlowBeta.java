package arginine;
//**************************************************************************
import methionine.AppException;
import methionine.auth.User;
import methionine.project.Project;
//**************************************************************************
public class FlowBeta extends FlowAlpha {
    //**********************************************************************
    private CookiesFlow cookieflow = null;
    User logeduser = null;
    Project project = null;
    //**********************************************************************
    @Override
    public void initialize () {
        super.initialize();
        cookieflow = new CookiesFlow(request, response);
        cookieflow.setDomain(this.host);
        //-----------------------------------------------------------------
        StringBuilder gobackto = new StringBuilder();
        gobackto.append(request.getRequestURI());
        String qstr = request.getQueryString();
        if (qstr != null) {
            gobackto.append("?");
            gobackto.append(qstr);
        }
        cookieflow.setGoBackTo(gobackto.toString());
        //-----------------------------------------------------------------
    }
    //**********************************************************************
    public User getLogedUser () {
        if (logeduser != null) return logeduser;
        if (session == null) return new User();
        if (!session.isValid()) { return new User(); }
        try {
            logeduser = this.getAurigaObject().getAuthLambda().getUser(session.getUserId(), true);
            return logeduser;
        }
        catch (Exception e) {
            System.out.println("Could not recover logged in user. ghrtrgfgfg (FlowAlpha)");
            return new User();
        }
    }
    //**********************************************************************
    public Project getCurrentProject () {
        if (project != null) return project;
        if (session == null) return new Project();
        if (!session.isValid()) { return new Project(); }
        if (session.getCurrentProject() == 0) return new Project();
        try {
            project = this.getAurigaObject().getProjectLambda().getWorkTeam(session.getCurrentProject(), session.getUserId());
            return project;
        }
        catch (AppException e) {
            if (e.getErrorCode() != AppException.WORKTEAMNOTFOUND) 
                System.out.println("Could not recover current project for session. gdrtrtfdgdg (FlowAlpha)");
            return new Project();
        }
        catch (Exception e) {
            System.out.println("Could not recover current project for session. gdrtrtfdgdg (FlowAlpha)");
            return new Project();
        }
    }
    //**********************************************************************
    public CookiesFlow getCookiesFlow () { return cookieflow; }
    public CookiesFlow.LANGUAGE getLanguage () { return cookieflow.getLanguage(); }
    public void setLanguage (CookiesFlow.LANGUAGE lang) { cookieflow.setLanguage(lang); }
    //**********************************************************************
}
//**************************************************************************
