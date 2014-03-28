/*
 * TermEditTableModel.java
 *
 * Created on 16. Februar 2006, 14:43
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pzm.tablemodel;

import pzm.dbcon.DB_projektzeit_Connect;
import pzm.core.Term;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.Calendar;
import java.sql.*;
    
/**
 *
 * @author hertel
 */
public class TermEditTableModel extends AbstractTableModel{
    
    protected ArrayList<Term> termObjects = new ArrayList<Term>();
    private String[] columnNames = new String[3];
    private Vector colNam = new Vector();
    private int userID = 0;
    private Calendar cal;
    private static Connection con;
    
    /** Creates a new instance of TermEditTableModel */
    public TermEditTableModel(int userID, Calendar cal) {
        this.userID = userID;
        this.cal = cal;
        this.loadData();
    }
    
    public Object getValueAt(final int zeile, final int spalte) {
        switch(spalte) {
            case 0:
                return this.termObjects.get(zeile).getTermID();
            case 1:
                return this.termObjects.get(zeile).getProject(termObjects.get(zeile).getProjectID());
            case 3:
                return convertMinuteToTimeString(this.termObjects.get(zeile).getDuration());
            default:
                return null;
        }
    }
    
    public int getColumnCount() {
        if(colNam.size() == 0) {
            setColumnNames();
        }
        return this.colNam.size();
    }
    
    public String getColumnName(final int spalte) {
        if(colNam.size() == 0) {
            setColumnNames();
        }
        if(spalte < this.getColumnCount()) {
            return columnNames[spalte];
        }
        else {
            return super.getColumnName(spalte);
        }
    }
    
    public int getRowCount() {
        return this.termObjects.size();
    }
    
    public void loadData() {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        int tempMonth = cal.get(cal.MONTH);
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Term WHERE UserID=" + userID + " AND TermDate='" + cal.get(cal.DATE) +
                         "." + (tempMonth + 1) + 
                         "." + cal.get(cal.YEAR) + "'";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                int id = rst.getInt("TermID");
                int prID = rst.getInt("ProjectID");
                int dur = rst.getInt("Duration");
                termObjects.add(new Term(id, userID, prID, dur));
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
    
    public void setColumnNames() {
        colNam.add("ID");
        colNam.add("Projekt");
        colNam.add("Dauer");
        colNam.toArray(columnNames);
    }
    
    public Object convertMinuteToTimeString(Object min) {
        Integer t = (Integer)min;
        int minutes = t.intValue();
        int modulo = minutes % 60;
        int rest = (minutes - modulo) / 60;
        if(modulo == 0) {
            return rest + ":" + modulo + "0";
        }
        else {
            return rest + ":" + modulo;
        }
    }
    
}
