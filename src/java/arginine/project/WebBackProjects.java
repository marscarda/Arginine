package arginine.project;
//***************************************************************************
import arginine.WebBackAlpha;
import arginine.jbuilders.JProject;
import methionine.project.Project;
//***************************************************************************
public class WebBackProjects extends WebBackAlpha {
    //***********************************************************************
    Project[] projects = new Project[0];
    void setProjects (Project[] projects) { this.projects = projects; }
    public Project[] getProjects () { return projects; }
    //=======================================================================
    public String jProjects () {
        return JProject.getProjectArray(projects).toString();
    }    
    //***********************************************************************
    
    
    //***********************************************************************
}
//***************************************************************************
