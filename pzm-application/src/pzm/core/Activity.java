package pzm.core;
/*
 * Activity.java
 *
 * Created on 13. Dezember 2005, 13:05
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author hertel
 */
public class Activity {
    
    private int activityID;
    private String activity;
    private String project;
    private String prGroup;
    
    /** Creates a new instance of Activity */
    public Activity(int activityID, String activity) {
        this.activityID = activityID;
        this.activity = activity;
    }
    
    
    
    
    
    public int getActivityID() {
        return activityID;
    }
    
    public String getActivity() {
        return activity;
    }
    
    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }
    
    public void setActivity(String activity) {
        this.activity = activity;
    }
    
}
