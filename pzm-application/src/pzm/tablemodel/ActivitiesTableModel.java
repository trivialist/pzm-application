/*
 * ActivitiesTableModel.java
 *
 * Created on 1. Januar 2006, 15:58
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pzm.tablemodel;

import pzm.dbcon.DB_projektzeit_Connect;
import pzm.core.Activity;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author freak
 */
public class ActivitiesTableModel extends AbstractTableModel{
    
    protected ArrayList<Activity> activityObjects = new ArrayList<Activity>();
    private String[] columnNames = new String[2];
    private Vector colNam = new Vector();
    private static Connection con; 
    
    /** Creates a new instance of ActivitiesTableModel */
    public ActivitiesTableModel() {
        this.loadData();
    }
    
    public Object getValueAt(final int zeile, final int spalte) {
        switch (spalte) {
        case 0 :
            return this.activityObjects.get(zeile).getActivityID();
        case 1 :
            return this.activityObjects.get(zeile).getActivity();
        default:
          return null;
        }
    }
    
    
    
    /*
     * return Anzahl der Tätigkeiten
     */
    public int getRowCount() {
        return this.activityObjects.size();
    }
    
    /*
     *  return Anzahl der Spalten
     */
    public int getColumnCount() {
        return  this.columnNames.length;
    }
    
    /*
     *  liefert Spaltenname für Tabelle
     *  return Spaltenname
     *  
     *  @param int spalte -- Spaltenindex
     */
    public String getColumnName(final int spalte) {
        setColumnNames();
        if(spalte < this.getColumnCount()) {
            return columnNames[spalte];
        }
        else {
            return super.getColumnName(spalte);
        }
    }
    
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    /*
     *
     *  lädt Tätigkeiten aus DB, Tabelle: Activity
     *  in ArrayList activityObjects
     *  activityObjects enthält Activity-Objekte
     *
     */
    protected void loadData() {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        String project = "";
        String prGroup = "";
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT ActivityID, Activity FROM Activity";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                String activity = rst.getString("Activity");
                int activityID = rst.getInt("ActivityID");
                
                // Projektname zur ProjektID aus Datenbank ermitteln
                
                activityObjects.add(new Activity(activityID, activity));
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con);
    }
    
    /*
     *  liefert Spaltennamen
     *  füllt Vektor colNam
     */
    public void setColumnNames() {
        colNam.add("ID");
        colNam.add("Tätigkeit");
        colNam.toArray(columnNames);
    }

    
}
