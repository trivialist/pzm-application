/*
 * LoginGUI.java
 *
 * Created on 14. Dezember 2005, 11:12
 */

package pzm.gui;

import pzm.dbcon.DB_projektzeit_Connect;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Point;
import java.sql.*; 
import java.awt.event.*;
/**
 *
 * @author  administrator
 */
public class LoginGUI extends java.awt.Dialog {
    
    private static Connection con;
    private String login = "";
    private int userID = 0;
    public MainGUI m = new MainGUI();
    /** Creates new form LoginGUI */
    public LoginGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        borderLayout1 = new java.awt.BorderLayout();
        flowLayout1 = new java.awt.FlowLayout();
        jTextFieldLogin = new javax.swing.JTextField();
        jLabelLogin = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jPasswordFieldPass = new javax.swing.JPasswordField();
        jButtonLogin = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();

        setBackground(new java.awt.Color(212, 208, 200));
        setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        setTitle("Benutzeranmeldung PZM");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                actionWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jTextFieldLogin.setAlignmentX(300.0F);
        jTextFieldLogin.setAlignmentY(200.0F);
        jTextFieldLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyPressedEnter(evt);
            }
        });

        jLabelLogin.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelLogin.setText("Benutzername");

        jLabelPass.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelPass.setText("Passwort");

        jPasswordFieldPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyPressedEnter(evt);
            }
        });

        jButtonLogin.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButtonLogin.setText("OK");
        jButtonLogin.setToolTipText("Anmelden");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonCancel.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButtonCancel.setText("Abbrechen");
        jButtonCancel.setToolTipText("Hiermit beenden Sie das Programm");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabelError.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabelError.setPreferredSize(new java.awt.Dimension(100, 20));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(jLabelLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jTextFieldLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(jLabelPass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(jPasswordFieldPass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(60, 60, 60)
                        .add(jButtonLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(40, 40, 40)
                        .add(jButtonCancel))
                    .add(layout.createSequentialGroup()
                        .add(60, 60, 60)
                        .add(jLabelError, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(40, 40, 40)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelPass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPasswordFieldPass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(60, 60, 60)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButtonLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonCancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(30, 30, 30)
                .add(jLabelError, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_actionWindowClosed
        System.exit(1);
    }//GEN-LAST:event_actionWindowClosed

    private void keyPressedEnter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyPressedEnter
        if((evt.getKeyCode() == KeyEvent.VK_ENTER) && (userValid() == true)) {
            m.setActiveUser(login, userID);
            setVisible(false);
            m.setVisible(true);
        }
    }//GEN-LAST:event_keyPressedEnter
    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        if(userValid() == true) {
            m.setActiveUser(login, userID);
            setVisible(false);
            m.setVisible(true);
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        System.exit(1);
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI(new java.awt.Frame(), true).setVisible(true);
            }
        });
    }
    
    /*
     * �berpr�fung von Benutzername und Kennwort
     */
    public boolean userValid() {
        boolean valid = false;
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();

        try {
            if(getLogin() != "") {
                Statement stmt = con.createStatement();
                String sql = "SELECT Login, Pass, UserID FROM User WHERE Login='" + getLogin() + "'";
                ResultSet rst = stmt.executeQuery(sql);

                //Passwort-Eingabe wird mit Passwort in Datenbank verglichen
                while(rst.next()) {
                    String pass = rst.getString("Pass");
                    if(pass.equals(getPass())) {
                        userID = rst.getInt("UserID");
                        valid = true;
                    }
                    else {
                        jLabelError.setText("<HTML>Benutzername oder Kennwort falsch!<BR>Bitte achten Sie auf Gro�- und Kleinschreibung</HTML>");
                    }
                }
                rst.close();
                stmt.close();
            }else {
                jLabelError.setText("Kein Benutzername eingegeben!");
            }
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }

        dbCon.closeDB(con);
        return valid;
    }
    
    
    public String getLogin() {
        login = jTextFieldLogin.getText();
        return login;
    }
    
    
    public String getPass() {
        String pass = new String(jPasswordFieldPass.getPassword());
        return pass;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.BorderLayout borderLayout1;
    private java.awt.FlowLayout flowLayout1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JPasswordField jPasswordFieldPass;
    private javax.swing.JTextField jTextFieldLogin;
    // End of variables declaration//GEN-END:variables
    
}