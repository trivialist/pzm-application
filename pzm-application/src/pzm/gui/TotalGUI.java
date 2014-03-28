/*
 * TotalGUI.java
 *
 * Created on 30. Oktober 2006, 12:43
 */

package pzm.gui;

import pzm.tablemodel.TotalTableModel;
import pzm.dbcon.DB_projektzeit_Connect;
import pzm.export.ExcelExporter;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter.*;
import java.sql.*;
import java.lang.Integer;
import java.util.Calendar;
import java.util.Enumeration;
import pzm.tablemodel.VerticalTableHeaderCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author  hertel
 */
public class TotalGUI extends javax.swing.JFrame {
    
    private static Connection con;
    protected String userLogin = "";
    protected static int tmpYear = 2005;
    private PrinterJob printerJob = PrinterJob.getPrinterJob();
    private PrintDialog pDialog = new PrintDialog();
    
    /** Creates new form TotalGUI */
    public TotalGUI(int year) {
        this.tmpYear = year;
        initComponents();
        centerScreen();
        setComboBoxUsers();
        setComboBoxYear();
        jLabelYear.setText(String.valueOf(year));
        jLabelUsers.setText("Alle Mitarbeiter");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBoxUsers = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelUsers = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButtonPrint = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Auswertung ");
        setMinimumSize(new java.awt.Dimension(800, 725));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        jComboBoxUsers.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxUsersItemStateChanged(evt);
            }
        });

        jLabel1.setText("Auswahl Mitarbeiter");

        jLabel2.setText("Aktuelle Auswahl");

        jLabelUsers.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelUsers.setForeground(new java.awt.Color(0, 204, 0));
        jLabelUsers.setText(" ");

        jLabelYear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelYear.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelYear.setText("Jahr");

        for(int i=2005; i<=2100; i++) {
            jComboBoxYear.addItem(i);
        }
        jComboBoxYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxYearItemStateChanged(evt);
            }
        });

        jLabel4.setText("Auswahl Jahr");

        jButtonPrint.setText("Drucken...");
        jButtonPrint.setToolTipText("�ffnet den Druck-Dialog");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jButtonExport.setText("Export");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Abfragen k�nnen einige Zeit in Anspruch nehmen");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBoxUsers, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(100, 100, 100)
                        .add(jLabel3))
                    .add(layout.createSequentialGroup()
                        .add(340, 340, 340)
                        .add(jButtonPrint))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(jComboBoxYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(10, 10, 10)
                            .add(jLabel2))
                        .add(layout.createSequentialGroup()
                            .add(200, 200, 200)
                            .add(jLabelUsers, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(48, 48, 48)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(54, 54, 54)
                        .add(jLabelYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jButtonExport))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(6, 6, 6)
                        .add(jComboBoxUsers, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .add(6, 6, 6)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jComboBoxYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jLabel2))))
                    .add(jLabel3)
                    .add(layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(jLabelUsers))
                    .add(jButtonPrint)
                    .add(jLabelYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonExport))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
         pDialog.t1.setText("Mitarbeiter: " 
                       + jLabelUsers.getText() +", " + tmpYear);
        Object[] o = pDialog.showPrintDialog();
        if (o != null) {
            printerJob.setPrintable(jTable1.getPrintable((JTable.PrintMode) o[0],
                    (MessageFormat) o[1], (MessageFormat) o[2]),
                    (PageFormat) o[3]);
            try {
                printerJob.print();
            } catch (PrinterException ee) {
                ee.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jComboBoxYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxYearItemStateChanged
        Object tempUser = jComboBoxUsers.getSelectedItem();
        String user = String.valueOf(tempUser);
        Object tempYear = jComboBoxYear.getSelectedItem();
        String t = String.valueOf(tempYear);
        tmpYear = Integer.valueOf(t).intValue();
        if(user != "Alle") {
            jLabelUsers.setText(getUserByLogin(user));
            jTable1.setModel(new TotalTableModel(user, tmpYear));
            TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
            Enumeration tabColumns = jTable1.getColumnModel().getColumns();
            while (tabColumns.hasMoreElements()) {
                TableColumn col = (TableColumn) tabColumns.nextElement();
                col.setHeaderRenderer(headerRenderer);
            }
            jLabelYear.setText(String.valueOf(tempYear));
        }
        else {
            jLabelUsers.setText("Alle");
            jTable1.setModel(new TotalTableModel("Alle", tmpYear));
            TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
            Enumeration tabColumns = jTable1.getColumnModel().getColumns();
            while (tabColumns.hasMoreElements()) {
                TableColumn col = (TableColumn) tabColumns.nextElement();
                col.setHeaderRenderer(headerRenderer);
            }
            jLabelYear.setText(String.valueOf(tempYear));
        }
    }//GEN-LAST:event_jComboBoxYearItemStateChanged

    private void jComboBoxUsersItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxUsersItemStateChanged
        Object temp = evt.getItem();
        String user = String.valueOf(temp);
        if(user != "Alle") {
            jLabelUsers.setText(getUserByLogin(user));
            jTable1.setModel(new TotalTableModel(user, tmpYear));
            TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
            Enumeration tabColumns = jTable1.getColumnModel().getColumns();
            while (tabColumns.hasMoreElements()) {
                TableColumn col = (TableColumn) tabColumns.nextElement();
                col.setHeaderRenderer(headerRenderer);
            }
        }
        else {
            jLabelUsers.setText("Alle");
            jTable1.setModel(new TotalTableModel("Alle", tmpYear));
            TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
            Enumeration tabColumns = jTable1.getColumnModel().getColumns();
            while (tabColumns.hasMoreElements()) {
                TableColumn col = (TableColumn) tabColumns.nextElement();
                col.setHeaderRenderer(headerRenderer);
            }
        }
    }//GEN-LAST:event_jComboBoxUsersItemStateChanged

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
         /**jTable1.setModel(new TotalTableModel("Alle", tmpYear));
         TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
         Enumeration tabColumns = jTable1.getColumnModel().getColumns();
         while (tabColumns.hasMoreElements()) {
             TableColumn col = (TableColumn) tabColumns.nextElement();
             col.setHeaderRenderer(headerRenderer);
         }*/
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        try {
            ExcelExporter exp = new ExcelExporter();
            exp.exportTable(jTable1, new File("R:\\PSA_pzm Jahresauswertung " + jLabelYear.getText() + jLabelUsers.getText() + ".xls"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jButtonExportActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TotalGUI(tmpYear).setVisible(true);
            }
        });
    }
    
    // ComboBox zur Wahl des Miarbeiters wird mit allen Mitarbeiterlogins bef�llt
    public void setComboBoxUsers() {
        jComboBoxUsers.addItem("Alle");
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT Login FROM User";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                String login = rst.getString("Login");
                jComboBoxUsers.addItem(login);
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
    
    public void setComboBoxYear() {
        jComboBoxYear.setSelectedItem(tmpYear);
    }
    
    public String getUserByLogin(String userLogin) {
        String userString = "";
        this.userLogin = userLogin;
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT Name, LastName, Login FROM User WHERE login='" + userLogin + "'";
            ResultSet rst = stmt.executeQuery(sql);

            while(rst.next()) {
                userString = rst.getString("LastName") + ", " + rst.getString("Name");
            }
            rst.close();
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con); 
        return userString;
    }

    public void centerScreen() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2,
        (dim.height - abounds.height) / 2);
        super.setVisible(true);
        requestFocus();
    }
    

class PrintDialog extends JDialog implements ActionListener {
        Object[] o;
        private JLabel l1 = new JLabel("Orientation:   ");
        private JLabel l2 = new JLabel("Print Mode:   ");
        private JLabel l3 = new JLabel("Extras:   ");
        private JCheckBox c1 = new JCheckBox("Header");
        private JCheckBox c2 = new JCheckBox("Footer");
        private JRadioButton r1 = new JRadioButton("Hochformat");
        private JRadioButton r2 = new JRadioButton("Querformat");
        private JRadioButton r3 = new JRadioButton("Normal");
        private JRadioButton r4 = new JRadioButton("An Breite anpassen");
        private JButton b1 = new JButton("Abbrechen");
        private JButton b2 = new JButton("Drucken");
        private ButtonGroup bg1 = new ButtonGroup();
        private ButtonGroup bg2 = new ButtonGroup();
        private JTextField t1 = new JTextField(30);
        private JTextField t2 = new JTextField(30);
        
        
        public PrintDialog() {
            
            super(TotalGUI.this, "Print Settings", true);
            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setLocationByPlatform(true);

            bg1.add(r1);
            bg1.add(r2);
            bg2.add(r3);
            bg2.add(r4);
            r2.setSelected(true);
            r3.setSelected(true);
            c1.setSelected(true);
            c2.setSelected(false);
 

            b1.addActionListener(this);
            b2.addActionListener(this);
            b2.setToolTipText("Druckt das Dokument auf dem Standarddrucker");
            c1.addActionListener(this);
            c2.addActionListener(this);
 
            GridBagLayoutManager gMan = new GridBagLayoutManager(this
                    .getContentPane());
 
            gMan.setInsets(10, 10, 0, 10);
            gMan.addComponent(gMan.getLabWSep(l1), 0, 0, 3, 1, 1, 1);
            gMan.setInsets(0, 10, 0, 10);
            gMan.addComponent(r1, 1, 1, 1, 1, 1, 1);
            gMan.addComponent(r2, 2, 1, 1, 1, 1, 1);
            gMan.setInsets(10, 10, 10, 10);
            gMan.addComponent(gMan.getLabWSep(l2), 0, 2, 3, 1, 1, 1);
            gMan.setInsets(0, 10, 0, 10);
            gMan.addComponent(r3, 1, 3, 1, 1, 1, 1);
            gMan.addComponent(r4, 2, 3, 1, 1, 1, 1);
            gMan.setInsets(10, 10, 10, 10);
            gMan.addComponent(gMan.getLabWSep(l3), 0, 4, 3, 1, 1, 1);
            gMan.setInsets(0, 10, 0, 10);
            gMan.addComponent(c1, 0, 5, 1, 1, 1, 1);
            gMan.setInsets(0, 10, 0, 10);
            gMan.addComponent(t1, 0, 6, 3, 1, 1, 1);
            gMan.setInsets(0, 10, 0, 10);
            gMan.addComponent(c2, 0, 7, 1, 1, 1, 1);
            gMan.setInsets(0, 10, 10, 10);
            gMan.addComponent(t2, 0, 8, 3, 1, 1, 1);
            gMan.setInsets(10, 10, 10, 10);
            gMan.addComponent(new JSeparator(JSeparator.HORIZONTAL), 0, 9, 3,
                    1, 1, 1);
            gMan.addComponent(b1, 1, 10, 1, 1, 1, 1);
            gMan.addComponent(b2, 2, 10, 1, 1, 1, 1);
            this.pack();
        }
 
        public Object[] showPrintDialog() {
            this.setVisible(true);
            return o;
        }
       
        
        class GridBagLayoutManager {

            private Container cont;
            private GridBagLayout gbl;
            private GridBagConstraints gbc;
            public final int CENTER = GridBagConstraints.CENTER;
            public final int EAST = GridBagConstraints.EAST;
            public final int WEST = GridBagConstraints.WEST;
            public final int NORTH = GridBagConstraints.NORTH;
            public final int SOUTH = GridBagConstraints.SOUTH;
            public final int BOTH = GridBagConstraints.BOTH;
            public final int VERTICAL = GridBagConstraints.VERTICAL;
            public final int HORIZONTAL = GridBagConstraints.HORIZONTAL;
            public final int NONE = GridBagConstraints.NONE;

            public GridBagLayoutManager(Container cont) {
                this.cont = cont;
                gbl = new GridBagLayout();
                gbc = new GridBagConstraints();
                cont.setLayout(gbl);
            }

            public void addComponent(Component c, int x, int y, int width,
                   int height, double weightx, double weighty, int fill, int anchor) {
                gbc.fill = fill;
                gbc.anchor = anchor;
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = width;
                gbc.gridheight = height;
                gbc.weightx = weightx;
                gbc.weighty = weighty;
                cont.add(c, gbc);
            }

            public void addComponent(Component c, int x, int y, int width,
                   int height, double weightx, double weighty) {
                gbc.fill = GridBagConstraints.BOTH;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = width;
                gbc.gridheight = height;
                gbc.weightx = weightx;
                gbc.weighty = weighty;
                cont.add(c, gbc);
            }


            public void setInsets(int top, int left, int bottom, int right) {
                gbc.insets = new Insets(top, left, bottom, right);
            }


            public JPanel getLabWSep(JLabel l) {
                JPanel p = new JPanel();
                p.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                p.add(l, c);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 1;
                c.gridx = 1;
                p.add(new JSeparator(JSeparator.HORIZONTAL), c);
                return p;
            }
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                this.dispose();
            }
            else if (e.getSource() == b2) {
                validatePrintSettings();
            }
            else if (e.getSource() == c1) {
                t1.setEnabled(c1.isSelected());
            }
            else if (e.getSource() == c2) {
                t2.setEnabled(c2.isSelected());
            }
         }


         public void validatePrintSettings() {
            PageFormat pf = new PageFormat();
            MessageFormat header = null;
            MessageFormat footer = null;
            pf.setOrientation(r1.isSelected() ? PageFormat.PORTRAIT
                   : PageFormat.LANDSCAPE);
            JTable.PrintMode printMode = r3.isSelected() ? JTable.PrintMode.NORMAL
                   : JTable.PrintMode.FIT_WIDTH;
            if (c1.isSelected()) {
               header = new MessageFormat(t1.getText());
            }
            if (c2.isSelected()) {
               footer = new MessageFormat(t2.getText());
            }
            o = new Object[] { printMode, header, footer, pf };
            this.dispose();
         }

    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JComboBox jComboBoxUsers;
    private javax.swing.JComboBox jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelUsers;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}