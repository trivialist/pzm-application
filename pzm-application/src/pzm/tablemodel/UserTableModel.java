/*
 * UserTableModel.java
 *
 * Created on 21. Dezember 2005, 10:31
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pzm.tablemodel;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Vector;
import pzm.dbcon.DB_projektzeit_Connect;
import pzm.core.User;
/**
 *
 * @author hertel
 */
public class UserTableModel extends AbstractTableModel {
    
   /* Userobjekte welche zeilenweise agezeigt werden sollen */
   protected ArrayList<User> userObjects = new ArrayList<User>();
   private String[] columnNames = new String[3];
   private Vector colNam = new Vector();
   private static Connection con; 
     
    
    /** Creates a new instance of UserTableModel */
    public UserTableModel() {
        this.loadData();
    }
    
    public Object getValueAt(final int zeile, final int spalte) {
        switch (spalte) {
        case 0 :
            return this.userObjects.get(zeile).getUserName();
        case 1 :
            return this.userObjects.get(zeile).getUserLastName();
        case 2 :
            return this.userObjects.get(zeile).getUserLogin();
        default:
          return null;
        }
    }
    
    
    
    /*
     * return Anzahl der Mitarbeiter
     */
    public int getRowCount() {
        return this.userObjects.size();
    }
    
    public int getColumnCount() {
        return  this.columnNames.length;
    }
    
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
    
    protected void loadData() {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT Name, LastName, Login FROM User";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                String name = rst.getString("Name");
                String lastName = rst.getString("LastName");
                String login = rst.getString("Login");
                userObjects.add(new User(name, lastName, login));
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
        colNam.add("Vorname");
        colNam.add("Nachname");
        colNam.add("Benutzername");
        colNam.toArray(columnNames);
    }

    
}
