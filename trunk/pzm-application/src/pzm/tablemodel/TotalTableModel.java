/*
 * TotalTableModel.java
 *
 * Created on 30. Oktober 2006, 12:44
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
 * @author hertel
 */
public class TotalTableModel extends AbstractTableModel {
    
    protected Object[][] data = new Object[32][500];
    protected String columnNames[] = new String[100];// Anzahl der Proj ist auf 100 begrenzt
    protected Vector colNam = new Vector();
    private static Connection con;
    protected int monthType = 0; // MUSS WIEDER AUF 0 GESETZT WERDEN
    //protected int month;
    //protected int year;
    protected String login;
    protected int year;
    
    /** Creates a new instance of TotalTableModel */
    public TotalTableModel() {
        this.loadData();
        setVectorAlto();
        setTotalForMonth();
        setTotalForProject();
        
    }
    
    public TotalTableModel(String login, int year) {
        this.login = login;
        this.year = year;
        //this.month = cal.get(cal.MONTH)+1;
        //this.year = cal.get(cal.YEAR);
        this.loadData();
        setVectorAlto();
        setTotalForMonth();
        setTotalForProject();
    }
    
    public Object getValueAt(final int zeile, final int spalte) {
        if(spalte == 0) {
            if(zeile !=  getRowCount()-1) {
                //erste Spalte wird mit monatstagen gefüllt
                return getMonth(zeile);
            }
            else {
                //erste Spalte, letzte Zeile enthält Gesamt/Projekt
                return "Jahr";
            }
        }
        else {
            if(spalte == getColumnCount()-1) {
                return getMonth(zeile);
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
    }
    
    public int getRowCount() {
        // 12 Spalten für Monat + 1 Spalte für Gesamt
        return 13;
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
        int month = 0;
        int tcID = 0;
        if(login == "Alle") {
            try {
                Statement stmt = con.createStatement();
                String sql = "SELECT * FROM Term LEFT JOIN Project ON Term.ProjectID=Project.ProjectID " +
                             "WHERE TermDate LIKE '%.%." + year + "' AND Project.notActive=0 AND Project.ProjYear=" + year;
                ResultSet rst = stmt.executeQuery(sql);


                while(rst.next()) {
                    int projectID = rst.getInt("ProjectID");
                    String date = rst.getString("TermDate");
                    month = getMonthOfDate(date);
                    // aktueller Wert für Projekt an diesem Tag
                    tcID = getTableColumnByProjectID(projectID);
                    Integer t = (Integer)getElementAt(month-1, tcID);
                    int temp = t.intValue();
                    // aktualisiere Wert für Projekt an diesem Tag
                    // (Gesamt + Einzeldauer = neuer gesamt)
                    setElementAt(temp + rst.getInt("Duration"), month-1, tcID);
                }
                rst.close();
                stmt.close();
            }
            catch(Exception e) {
                System.out.println(e.toString()); 
                System.exit(1); 
            }
        }
        else {
            try {
                Statement stmt = con.createStatement();
                String sql = "SELECT * FROM Term LEFT JOIN Project ON Term.ProjectID=Project.ProjectID " +
                             "WHERE TermDate LIKE '%.%."+ year + "' AND UserID=" + getUserIdByLogin() +
                             " AND Project.notActive=0 AND Project.ProjYear=" + year;
                ResultSet rst = stmt.executeQuery(sql);

                while(rst.next()) {
                    int projectID = rst.getInt("ProjectID");
                    String date = rst.getString("TermDate");
                    month = getMonthOfDate(date);
                    // aktueller Wert für Projekt an diesem Tag
                    tcID = getTableColumnByProjectID(projectID);
                    Integer t = (Integer)getElementAt(month-1, tcID);
                                                
                    int temp = t.intValue();
                    // aktualisiere Wert für Projekt an diesem Tag
                    // (Gesamt + Einzeldauer = neuer gesamt)
                    setElementAt(temp + rst.getInt("Duration"), month-1, tcID);
                }
                rst.close();
                stmt.close();
            }
            catch(Exception e) {
                System.out.println(e.toString()); 
                System.exit(1); 
            }
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
        colNam.add("");
        colNam.add("Datum");
        
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
        colNam.add("Gesamt");
        colNam.add("Monat");
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
    
    public int getMonthOfDate(String date) {
        int t = 0;
        StringTokenizer tokenizer = new StringTokenizer(date,".");
        while(tokenizer.hasMoreTokens()) {
            int cT = tokenizer.countTokens();
            if(tokenizer.countTokens() == 3) {
                tokenizer.nextToken();
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
    
    public void setTotalForMonth() {
        for(int i=0; i < getRowCount()-1; i++) {
            int total = 0;
            for(int j=0; j < getColumnCount()-2; j++) {
                Integer t = (Integer)data[i][j];
                total = total + t.intValue();
            }
            setElementAt(total, i, getColumnCount()-2);
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
    
    public String getMonth(int zeile) {
        switch(zeile) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mär";
            case 3:
                return "Apr";
            case 4:
                return "Mai";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Okt";
            case 10:
                return "Nov";
            case 11:
                return "Dez";
            case 12: 
                return "Jahr";
            default:
                return "Error";
        }
    }
    
    public int getUserIdByLogin() {
        int userID = 0;
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT UserID, Login FROM User WHERE Login='" + login + "'";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                userID = rst.getInt("UserID");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con);
        return userID;
    }
    
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
                          + prID + " AND notActive=0 AND ProjYear=" + year;
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
        return arrayPosOfProj;
    }
}
