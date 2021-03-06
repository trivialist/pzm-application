/*
 * DeleteActivityDialog.java
 *
 * Created on 1. Januar 2006, 23:25
 */

package pzm.dialog;

import pzm.dbcon.DB_projektzeit_Connect;
import java.sql.*;

/**
 *
 * @author  freak
 */
public class DeleteActivityDialog extends javax.swing.JDialog {

    private static String activity = "";
    private static int activityID = 0;
    private Connection con;
    
    /** Creates new form DeleteActivityDialog */
    public DeleteActivityDialog(java.awt.Frame parent, boolean modal, String activity, int activityID) {
        super(parent, modal);
        this.activity = activity;
        this.activityID = activityID;
        initComponents();
        setTitle("T�tigkeit " + activity + " l�schen?");
        jLabelActivity.setText(activity);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelActivity = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("T�tigkeit l�schen");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("M�chten Sie die T�tigkeit");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 190, 20));

        jLabelActivity.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelActivity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelActivity.setText("Fehler");
        getContentPane().add(jLabelActivity, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 160, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("    wirklich l�schen?");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 140, 20));

        jButtonOk.setText("Ja");
        jButtonOk.setActionCommand("jbutton1");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jButtonCancel.setText("Nein");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        deleteActivity(activityID);
        setVisible(false);
    }//GEN-LAST:event_jButtonOkActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteActivityDialog(new javax.swing.JFrame(), true, activity, activityID).setVisible(true);
            }
        });
    }
    
    public void deleteActivity(int activityID) {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        //REFERENTIELLE INTEGRIT�T EINTRAGEN IN DATENBANK F�R ALLE VERKN�PFUNGEN MIT USER
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM Activity WHERE ActivityID=" + activityID;
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString()); 
            System.exit(1); 
        }
        dbCon.closeDB(con);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelActivity;
    // End of variables declaration//GEN-END:variables
    
}
