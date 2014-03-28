/*
 * DB_projektzeit_Connect.java
 *
 * Created on 13. Dezember 2005, 15:41
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pzm.dbcon;

import pzm.gui.GlobalError;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pzm.gui.MainGUI;





/**
 *
 * @author hertel
 */
public class DB_projektzeit_Connect
{

	private static Connection con = null;

	/** Creates a new instance of DB_ToDo_Connect */
	public DB_projektzeit_Connect()
	{
	}

	public static void openDB()
	{
		try
		{
			//Class.forName(MainGUI.applicationProperties.getProperty("DatabaseDriver")).newInstance();
                        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Class.forName( "com.mysql.jdbc.Driver" );
			//String url = MainGUI.applicationProperties.getProperty("DatabaseDsnProjektzeit");
                        String url = "jdbc:odbc:pzm";
			con = DriverManager.getConnection(url);
		} catch (Exception ex)
		{
			Logger.getLogger(DB_projektzeit_Connect.class.getName()).log(Level.SEVERE, null, ex);
			GlobalError.showErrorAndExit();
		}

	}

	public static void closeDB(Connection con)
	{
		try
		{
			con.close();
		} catch (Exception ex)
		{
			Logger.getLogger(DB_projektzeit_Connect.class.getName()).log(Level.SEVERE, null, ex);
			GlobalError.showErrorAndExit();
		}
	}

	public static Connection getCon()
	{
		return con;
	}
}
