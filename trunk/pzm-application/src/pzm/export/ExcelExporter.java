/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pzm.export;

/**
 *
 * @author hertel
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;

public class ExcelExporter {

    public ExcelExporter() {
    }

    public void exportTable(JTable table, File file) throws IOException {

        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);

        for (int i = 0; i < model.getColumnCount(); i++) {
            out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                out.write(model.getValueAt(i, j).toString() + "\t");
            }
            out.write("\n");
        }
        out.close();
        System.out.println("write out to: " + file);
    }
}