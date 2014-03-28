package pzm.core;
/*
 * Project.java
 *
 * Created on 13. Dezember 2005, 13:04
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author hertel
 */
public class Project {
    
    private int projectID;
    private String prGroup;
    private String project;
    private int projYear;
    private int costLoc;            //Kostenstelle 
    private int notActive;          //notActive=0->aktives Projekt; notActive=1->deaktiviertes Projekt;
    private boolean isNotActive;
    /** Creates a new instance of Project */
    
    public Project(int projectID, String project, String prGroup, int costLoc) {
        this.project = project;
        this.prGroup = prGroup;
        this.costLoc = costLoc;
    }
    
    public Project(int projectID, String project, String prGroup, int costLoc, int notActive) {
        this.project = project;
        this.prGroup = prGroup;
        this.costLoc = costLoc;
        this.notActive = notActive;
    }

    public Project(int projectID, String project, String prGroup, int costLoc, int notActive, boolean isNotActive) {
        this.project = project;
        this.prGroup = prGroup;
        this.costLoc = costLoc;
        this.notActive = notActive;
        this.isNotActive = isNotActive;
    }

    public Project(int projectID, String project, String prGroup, int costLoc, boolean isNotActive) {
        this.project = project;
        this.prGroup = prGroup;
        this.costLoc = costLoc;
        this.isNotActive = isNotActive;
    }
    
    public Project(int projectID, String project, int projYear, String prGroup, int costLoc, int notActive, boolean isNotActive) {
        this.project = project;
        this.projYear = projYear;
        this.prGroup = prGroup;
        this.costLoc = costLoc;
        this.notActive = notActive;
        this.isNotActive = isNotActive;
    }

    public int getProjectID() {
        return projectID;
    }

    public String getPrGroup() {
        return prGroup;
    }
    
    public String getProject() {
        return project;
    }

    public int getProjYear() {
        return projYear;
    }

    public int getCostLocation() {
        return costLoc;
    }

    public int getIntPrNotActive() {
        return notActive;
    }

    public boolean IsProjectUnactive() {
        return isNotActive;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setprGroup(String prGroup) {
        this.prGroup = prGroup;
    }
    
    public void setProject(String project) {
        this.project = project;
    }

    public void setProjYear(int projYear) {
        this.projYear = projYear;
    }

    public void setCostLocation(int costLoc) {
        this.costLoc = costLoc;
    }

    public void setIntPrNotActive(int notActive) {
        this.notActive = notActive;
    }

    public void setProjectUnactive(boolean isNotActive) {
        this.isNotActive = isNotActive;
    }

}
