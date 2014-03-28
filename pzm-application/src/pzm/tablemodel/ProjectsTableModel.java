/*
 * ProjectsTableModel.java
 *
 * Created on 1. Januar 2006, 22:26
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pzm.tablemodel;

import pzm.dbcon.DB_projektzeit_Connect;
import pzm.core.Project;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Vector;
import java.util.logging.*;

/**
 *
 * @author hertel
 */
public class ProjectsTableModel extends AbstractTableModel{
    
    /* Userobjekte welche zeilenweise agezeigt werden sollen */
    protected ArrayList<Project> projectObjects = new ArrayList<Project>();
    private String[] columnNames = new String[5];
    private Vector colNam = new Vector();
    private static Connection con; 

    /** Creates a new instance of ProjectsTableModel */
    public ProjectsTableModel() {
        this.loadData();
    }
    
    @Override
    /*
     * Overrides method AbstractTableModel.getValueAt() to show Checkboxes in the Table
     * for column 4
     */
    public Object getValueAt(final int zeile, final int spalte) {
        switch (spalte) {
        case 0 :
            return this.projectObjects.get(zeile).getProject();
        case 1:
            return this.projectObjects.get(zeile).getProjYear();
        case 2 :
            return this.projectObjects.get(zeile).getPrGroup();
        case 3:
            return this.projectObjects.get(zeile).getCostLocation();
        case 4:
            return this.projectObjects.get(zeile).IsProjectUnactive();
        case 5:
            return this.projectObjects.get(zeile).getProjectID();
        default:
          return null;
        }
    }


    @Override
    public Class getColumnClass(int column) {
        return this.getValueAt(0, column).getClass();
    }
    
    /*
     * return Anzahl der Mitarbeiter
     */
    @Override
    public int getRowCount() {
        return this.projectObjects.size();
    }
    
    @Override
    public int getColumnCount() {
        return  this.columnNames.length;
    }
    
    @Override
    public String getColumnName(final int spalte) {
        setColumnNames();
        if(spalte < this.getColumnCount()) {
            return columnNames[spalte];
        }
        else {
            return super.getColumnName(spalte);
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    protected void loadData() {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        String prGroup = "";
        boolean isNotActive = false;
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Project LEFT JOIN PrGroup ON Project.PrGroupID=PrGroup.PrGroupID ORDER BY Project ASC";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                String project = rst.getString("Project");
                int projectID = rst.getInt("ProjectID");
                int projYear = rst.getInt("ProjYear");
                prGroup = rst.getString("PrGroup");
                int costLoc = rst.getInt("CostLocation");
                int notActive = rst.getInt("notActive");
                if(notActive == 0) {
                    isNotActive = false;
                } else {
                    isNotActive = true;
                }
                projectObjects.add(new Project(projectID, project, projYear, prGroup, costLoc, notActive, isNotActive));
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            Logger.getLogger(ProjectsTableModel.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error in Class ProjectsTableModel.loadData().\n"
					+ "Eine SQL-Abfrage hat einen Fehler erzeugt!\n "
                                        + "Bitte kontaktieren Sie Ihren Ansprechpartner unter Angabe des Class-Eintrags.\n"
					+ "Die Anwendung wird nun beendet.", "Fehler", JOptionPane.ERROR_MESSAGE);

            System.exit(1); 
        }
        dbCon.closeDB(con);
    }
    
    public void setColumnNames() {
        colNam.add("Projekt");
        colNam.add("Jahr");
        colNam.add("Projektgruppe");
        colNam.add("Kostenstelle");
        colNam.add("deaktiviert");
        colNam.toArray(columnNames);
    }

}
