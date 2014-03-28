/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pzm.tablemodel;

import javax.swing.JTextArea;
import javax.swing.table.*;
import javax.swing.UIManager;
import javax.swing.JTable;
import java.awt.Component;
/**
 *
 * @author hertel
 */
public class MultiLineHeaderRenderer extends JTextArea implements TableCellRenderer
{
	private static final long serialVersionUID = 1L;

	public MultiLineHeaderRenderer()
	{
		this.setOpaque(true);
		this.setForeground(UIManager.getColor("TableHeader.foreground"));
		this.setBackground(UIManager.getColor("TableHeader.background"));
		this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		this.setFont(UIManager.getFont("TableHeader.font"));
	}

    @Override
     /*
      *
      */
    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		this.setText(value.toString());
		return this;
	}
}
