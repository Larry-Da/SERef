package org.qmbupt.grp105.UI.MyUIComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


public class TableList extends JTable
{
    public JPopupMenu popmenu;
    public int focusedRowIndex;
    public TableList(String[] columnNames, String[][] tableValues, Color textColor, Color backgroundColor, Color selectionColor, int x, int y, int width, int height)
    {
        super(tableValues, columnNames);
        this.setBounds(x, y, width, height);
        JTable table = new JTable(tableValues, columnNames);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        setSelectionForeground(textColor);// set text color
        setBackground(backgroundColor);
        setSelectionBackground(selectionColor);// selection color
        setRowHeight(30);// row height
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        popmenu = new JPopupMenu();
        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("Delete");
        delMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });
        popmenu.add(delMenItem);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                mouseRightButtonClick(e);
            }
        });
    }



    public boolean isCellEditable(int row, int column)// decide whether the content is can be edited
    {
        if(column == 0)
            return false;
        else
            return true;
    }

    private void mouseRightButtonClick(java.awt.event.MouseEvent e) {
        // check if the event is right click(button3 is right click)
        if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            //find the row by mouse location
            focusedRowIndex = this.rowAtPoint(e.getPoint());
            if (focusedRowIndex == -1) {
                return;
            }
            // set the selection to be the mouse location
            this.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
            //pop a menu
            popmenu.show(this, e.getX(), e.getY());
        }
    }



}


