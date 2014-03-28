package pzm.core;
/*
 * Term.java
 *
 * Created on 13. Dezember 2005, 13:05
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
import java.sql.*;
import java.util.Date;
import pzm.dbcon.DB_projektzeit_Connect;
import java.util.Calendar;
/**
 *
 * @author hertel
 */
public class Term {
    
    private int duration;
    private int userID;
    private int activityID;
    private int projectID;
    private int prGroupID;
    private int termID;
    private int costLocation;
    private Calendar prEnd;
    private static Connection con;
    
    
    /** Creates a new instance of Term */
    public Term(int userID, int projectID, int duration) {
        this.userID = userID;
        this.projectID = projectID;
        this.duration = duration;
       // setPrEnd();
    }
    
    public Term(int termID, int userID, int projectID, int duration) {
        this.termID = termID;
        this.userID = userID;
        this.projectID = projectID;
        this.duration = duration;
       // setPrEnd();
        
    }
    
    public Calendar getPrEnd(){
        return prEnd;
    }

    public int getCostLocation(){
        return costLocation;
    }
    
    public int getTermID() {
        return termID;
    }
    
    public int getUserID() {
        return userID;
    }

    public int getActivityID() {
        return activityID ;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrGroupID() {
        return prGroupID;
    }
    
    public String getPrGroup(int prGroupID) {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        String prGr = "";
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT PrGroupID, PrGroup FROM PrGroup WHERE PrGroupID=" + prGroupID;
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                prGr = rst.getString("PrGroup");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            prGr = "#FEHLER#";
            System.exit(1); 
        }
        dbCon.closeDB(con);
        return prGr;
    }
    
    public String getProject(int projectID) {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        String pr = "";
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ProjectID, Project FROM Project WHERE ProjectID=" + projectID;
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                pr = rst.getString("Project");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            pr = "#FEHLER#";
            System.exit(1); 
        }
        dbCon.closeDB(con);
        return pr;
    }
    
    //zieht costLocation aus DB
     public int getProjectCostLocationFromDB(int projectID) {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        int prCostLocation = 0;
        
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ProjectID, CostLocation, FROM Project WHERE ProjectID=" + projectID;
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
              
                prCostLocation = rst.getInt("CostLocation");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            prCostLocation = -1;
            System.exit(1); 
        }
        dbCon.closeDB(con);
        return prCostLocation;
    }
    
    
    public String getActivity(int activityID) {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        String ac = "";
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ActivityID, Activity FROM Activity WHERE ActivityID=" + activityID;
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                ac = rst.getString("Activity");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            ac = "#FEHLER#";
            System.exit(1); 
        }
        dbCon.closeDB(con);
        return ac;
    }
    
    
    //Projektende wird aus Datenbank geladen und im Konstruktor hinzugefügt
    // !!!!!Es wird hier schon ein Monat auf das Projektende addiert
    public void setPrEnd(){
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        Date temp;
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ProjectEnd, ProjectID FROM Project WHERE ProjectID=" + projectID ;
            ResultSet rst = stmt.executeQuery(sql);
            
            while(rst.next()) {
              
              temp =rst.getDate("ProjectEnd");
              this.prEnd.setTime(temp);
              
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con);
        
       
        
        System.err.println(prEnd.getTime());
       
    }
    
    public void setTermID(int termID) {
        this.termID = termID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrGroupID(int prGroupID) {
        this.prGroupID = prGroupID;
    }



}