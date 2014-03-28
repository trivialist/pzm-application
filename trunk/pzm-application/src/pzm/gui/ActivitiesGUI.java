/*
 * ActivitiesGUI.java
 *
 * Created on 20. Dezember 2005, 12:41
 */

package pzm.gui;

import pzm.subgui.ActivitiesSubGUI;
import pzm.tablemodel.ActivitiesTableModel;
import pzm.dialog.DeleteActivityDialog;



/**
 *
 * @author  hertel
 */
public class ActivitiesGUI extends javax.swing.JFrame {
    
    private static ActivitiesGUI actWin = new ActivitiesGUI();
    
    /** Creates new form ActivitiesGUI */
    public ActivitiesGUI() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonNewActivity = new javax.swing.JButton();
        jButtonEditActivity = new javax.swing.JButton();
        jButtonDeleteActivity = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stammdaten - T�tigkeiten");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonNewActivity.setText("Neu");
        jButtonNewActivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActivityActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonNewActivity, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jButtonEditActivity.setText("Bearbeiten");
        jButtonEditActivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActivityActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEditActivity, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jButtonDeleteActivity.setText("L�schen");
        jButtonDeleteActivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActivityActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDeleteActivity, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 50));

        table.setModel(new ActivitiesTableModel());
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 470, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if(evt.WINDOW_GAINED_FOCUS != 1) {
            table.setModel(new ActivitiesTableModel());
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButtonDeleteActivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActivityActionPerformed
        if(table.getSelectedRow() != -1) {
            Object activity = table.getValueAt(table.getSelectedRow(), 1);
            Object activityID = table.getValueAt(table.getSelectedRow(), 0);
            Integer temp = new Integer(String.valueOf(activityID));
            int actID = temp.intValue();
            if(activity != null) {
                DeleteActivityDialog delAct = new DeleteActivityDialog(actWin, true, String.valueOf(activity), actID);
            delAct.setVisible(true);
            }
        }        
    }//GEN-LAST:event_jButtonDeleteActivityActionPerformed

    private void jButtonEditActivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActivityActionPerformed
         if(table.getSelectedRow() != -1) {
            Object activity = table.getValueAt(table.getSelectedRow(), 1);
            Object activityID = table.getValueAt(table.getSelectedRow(), 0);
            Integer temp = new Integer(String.valueOf(activityID));
            int actID = temp.intValue();
            if(activity != null) {
                ActivitiesSubGUI newAct = new ActivitiesSubGUI(1, String.valueOf(activity), actID);
                newAct.setVisible(true);
            }
         }
    }//GEN-LAST:event_jButtonEditActivityActionPerformed

    private void jButtonNewActivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActivityActionPerformed
        String activity = "";
        int activityID = 0;
        ActivitiesSubGUI newAct = new ActivitiesSubGUI(0, activity, activityID);
        newAct.setVisible(true);
    }//GEN-LAST:event_jButtonNewActivityActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                actWin.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeleteActivity;
    private javax.swing.JButton jButtonEditActivity;
    private javax.swing.JButton jButtonNewActivity;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    
}
