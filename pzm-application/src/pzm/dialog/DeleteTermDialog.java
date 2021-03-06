/*
 * DeleteTermDialog.java
 *
 * Created on 17. Februar 2006, 15:46
 */

package pzm.dialog;

import pzm.dbcon.DB_projektzeit_Connect;
import pzm.core.Term;
import pzm.core.Project;
import pzm.core.User;
import java.sql.*;
import java.util.Calendar;
import pzm.gui.TermEditGUI;
/**
 *
 * @author  hertel
 */
public class DeleteTermDialog extends javax.swing.JDialog {
    
    private static Calendar cal;
    private static Term actTerm = new Term(0,0,0,0);
    private static Project actProject = new Project(0, "", "", 0);
    private static User activeUser = new User("", "", "");
    private static Connection con;
    private java.awt.Frame parent = new java.awt.Frame(); 
    
    /** Creates new form DeleteTermDialog */
    public DeleteTermDialog(java.awt.Frame parent, boolean modal, Term term, Project project, User user, Calendar cal) {
        super(parent, modal);
        this.parent = parent;
        this.actTerm = term;
        this.actProject = project;
        this.activeUser = user;
        this.cal = cal;
        
        initComponents();
        jLabelProject.setText(actProject.getProject());
        jLabelDuration.setText(String.valueOf(actTerm.getDuration()));
        jLabel7.setVisible(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelProject = new javax.swing.JLabel();
        jLabelDuration = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("    wirklich l�schen?");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 140, 20));

        jButtonOk.setText("Ja");
        jButtonOk.setActionCommand("jbutton1");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jButtonCancel.setText("Nein");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("M�chten Sie diesen Zeiteintrag");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 190, 20));

        jLabelProject.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelProject.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelProject.setText("Fehler");
        getContentPane().add(jLabelProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 160, 20));

        jLabelDuration.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelDuration.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDuration.setText("Fehler");
        getContentPane().add(jLabelDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 160, 20));

        jLabel4.setText("Projekt:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 60, 20));

        jLabel6.setText("Dauer:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 70, 20));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 250, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        setVisible(false);
        openTermEditWindow();
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        deleteTerm(actTerm.getTermID());
        setVisible(false);
        openTermEditWindow();
        dispose();
    }//GEN-LAST:event_jButtonOkActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteTermDialog(new javax.swing.JFrame(), true, actTerm,
                        actProject, activeUser, cal).setVisible(true);
            }
        });
    }
    
    public void deleteTerm(int termID) {
        DB_projektzeit_Connect dbCon = new DB_projektzeit_Connect();
        dbCon.openDB();
        con = dbCon.getCon();
        
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM Term WHERE TermID=" + termID;
            
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        dbCon.closeDB(con);
    } 
    
    public void openTermEditWindow() {
        TermEditGUI termEditGUI = new TermEditGUI(actTerm, activeUser, cal, actProject);
        termEditGUI.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDuration;
    private javax.swing.JLabel jLabelProject;
    // End of variables declaration//GEN-END:variables
    
}
