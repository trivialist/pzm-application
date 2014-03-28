/*
 * MainTableModel.java
 *
 * Created on 4. Januar 2006, 20:11
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pzm.tablemodel;

import pzm.dbcon.DB_projektzeit_Connect;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.sql.*;
import java.util.Vector;
import java.sql.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Calendar;

/**
 *
 * @author T
 */
public class MainTableModel extends AbstractTableModel {
    
    protected Object[][] data = new Object[32][500];
    protected String columnNames[] = new String[100]; //auf 100 Projekte begrenzt
    protected Vector colNam = new Vector();
    private static Connection con;
    protected int monthType = 0; // MUSS WIEDER AUF 0 GESETZT WERDEN
    protected int month;
    protected int year;
    protected int userID;
    /** Creates a new instance of MainTableModel */
    public MainTableModel(Calendar cal) {
        this.loadData();
        this.year = cal.get(cal.YEAR);
    }
    
    public MainTableModel(int userID, Calendar cal) {
        this.userID = userID;
        this.month = (cal.get(cal.MONTH))+1;
        this.year = cal.get(cal.YEAR);
        setMonthType(cal);
        this.loadData();
        setVectorAlto();
        setTotalForDay();
        setTotalForProject();
    }
    
    
    public Object getValueAt(final int zeile, final int spalte) {
        if(spalte==0) {
            if(zeile !=  getRowCount()-1) {
                //erste Spalte wird mit monatstagen gefüllt
                return zeile+1;
            }
            else {
                //erste Spalte, letzte Zeile enthält Gesamt/Projekt
                return "Monat";
            }
        }
        else {
            if(!getElementAt(zeile, spalte).equals(0)) {
                if(zeile != getRowCount()-1) {
                    return convertMinuteToTimeString(getElementAt(zeile, spalte));
                }
                else {
                    // das sind die Gesamtwerte/Projekt
                    return convertMinuteToTimeString(getElementAt(zeile, spalte));
                }
            }
            else {
                return getElementAt(zeile, spalte);
            }
        }
    }
    
    public int getRowCount() {
        switch(monthType) {
            case 0:     // keine Monatsauswahl verfügbar
                return 0;
            case 1:     // Monat hat 31 Tage -> 31 Zeilen
                return 32;
            case 2:     // Monat hat 30 Tage -> 30 Zeilen
                return 31;
            case 3:     // Monat hat 28 Tage (Feb. - k. Schaltj.)-> 28 Zeilen
                return 29;
            case 4:     // Monat hat 29 Tage (Feb. - Schaltjahr)-> 29 Zeilen
                return 30;
            default:    // keine Monatsauswahl verfügbar
                return 0;
        }
    }
    
    /*
     * gibt Anzahl der wirklich in der DB existierenden Projekte zurück
     * Angabe aus Vektor colNam
     */
    public int getColumnCount() {
        return this.colNam.size();
    }
    
    public String getColumnName(final int spalte) {
        if(spalte < this.getColumnCount()) {
            return columnNames[spalte];
        }
        else {
            return super.getColumnName(spalte);
        }
    }
    
    /*
     * ermittelt und setzt Tabellenüberschrift/Zeile
     * und ruft Fkt. zur Daten-Array-Vorbereitung auf
     *
     */
    public void loadData() {
        setColumnNames();
        initiateData();
    }
    
    /*
     *  füllt Daten-Array mit 0-en auf
     *
     *
     */
    public void initiateData() {
        for(int i=0; i < 32; i++) {
            for(int j=0; j < 500; j++) {
                data[i][j] = 0;
            }
        }
    }
    
    /*
     * ermittelt alle Zeiteinträge für den aktuellen User im aktuellen Monat
     * aus der Datenbank (Tabelle 'Term') und speichert die Zeitwerte im Vector
     * alto ab
     *
     * @param:
     */
    public void setVectorAlto() {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        int total = 0;              //Wert für Spalte Gesamt
        int day = 0;
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Term LEFT JOIN Project ON Term.ProjectID=Project.ProjectID " +
                         "WHERE Term.UserID=" + userID + " AND Term.TermDate LIKE '%." + month +
                         "." + year + "' AND Project.notActive=0";
            ResultSet rst = stmt.executeQuery(sql);
            
            while(rst.next()) {
                int projectID = rst.getInt("ProjectID");
                String date = rst.getString("TermDate");
                day = getDayOfDate(date);
                // aktueller Wert für Projekt an diesem Tag
                int prCol = getTableColumnByProjectID(projectID);
                if(prCol != -1) {
                    Integer t = (Integer)getElementAt(day-1,
                                                        prCol);
                    int temp = t.intValue();
                    // aktualisiere Wert für Projekt an diesem Tag
                    int dur = rst.getInt("Duration");
                    setElementAt(temp + dur, day-1, prCol);
                }
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
     * ermittelt alle Projekte aus der Datenbank und speichert diese im vektor colNam
     * und im Array columnNames um diese als Spaltennamen zu nutzen
     *
     * @param:
     */
    public void setColumnNames() {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        colNam.add(" " + "Datum");
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT Project FROM Project WHERE notActive=0 AND ProjYear=" + year + " ORDER BY PrGroupID ASC, tablePos ASC";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                colNam.add(rst.getString("Project"));
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con);
        colNam.add(" " + "Gesamt");
        colNam.toArray(columnNames);
    }
    
    /*
     * wandelt einen Integer-Wert in einen String der Form 
     * Std:Min um
     *
     * @param: int
     */
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
    
    public int getDayOfDate(String date) {
        int t = 0;
        StringTokenizer tokenizer = new StringTokenizer(date,".");
        while(tokenizer.hasMoreTokens()) {
            int cT = tokenizer.countTokens();
            if(tokenizer.countTokens() == 3) {
                t = Integer.valueOf(tokenizer.nextToken()).intValue();
            }
            else {
                tokenizer.nextToken();
            }
        }
        return t;
    }
    
    public void setElementAt(Object obj,int x,int y){
        this.data[x][y] = obj;
    }
    
    public Object getElementAt(int x,int y){
        return this.data[x][y];
    }
    
    public void setTotalForDay() {
        for(int i=0; i < getRowCount()-1; i++) {
            int total = 0;
            for(int j=0; j < getColumnCount()-1; j++) {
                Integer t = (Integer)data[i][j];
                total = total + t.intValue();
            }
            setElementAt(total, i, getColumnCount()-1);
        }
    }
    
    public void setTotalForProject() {
        for(int i=1; i < getColumnCount(); i++) {
            int total = 0;
            for(int j=0; j < getRowCount(); j++) {
                Integer t = (Integer)data[j][i];
                total = total + t.intValue();
            }
            setElementAt(total, getRowCount()-1,  i);
        }
    }
    
    public String getColumnNames(int index) {
        return (String)colNam.elementAt(index);
    }
    
    /*
     * bestimmt die Anzahl der Tage des Monats
     * 
     */
    public void setMonthType(Calendar cal) {
        int temp = cal.get(cal.MONTH)+1;
        if(temp==1|temp==3|temp==5|temp==7|temp==8|temp==10|temp==12) {
            this.monthType = 1;
        }
        if(temp==4|temp==6|temp==9|temp==11) {
            this.monthType = 2;
        }
        if(temp==2) {
            if( ((cal.get(cal.YEAR)) % 4) == 0 ) {
                this.monthType = 4;
            }
            if( ((cal.get(cal.YEAR)) % 4) != 0 ) {
                this.monthType = 3;
            }
        }
    }
    
    /*
     *  Aufgrund von Lücken der ProjectID in der Datenbank muss richtige Spalte 
     *  für Projekt in der Tabelle ermittelt werden
     *
     *  @param projectID
     */
    public int getTableColumnByProjectID(int prID) {
        String tempPrName = "";
        int arrayPosOfProj = -1;
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        
        //Name des Projekts anhand der ID ermitteln
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Project WHERE ProjectID="
                          + prID + " AND notActive=0";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                tempPrName = rst.getString("Project");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con);
        
        // Position des Projekts in der Tabelle ermitteln
        // anhand der Postion des Projekts im Array der Spaltenüberschriften
        for(int i=0; i < this.colNam.size(); i++) {
            if(String.valueOf(this.colNam.get(i)).equals(tempPrName)) {
                arrayPosOfProj = i;
                break;
            }
        } 
        if(arrayPosOfProj == -1) {
            return -1;
        } else {
            return arrayPosOfProj;
        }
    }
}
