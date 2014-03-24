package ry.wwm.table;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author ry
 */
public final class PunkteTabellePanel extends javax.swing.JPanel {

    private static final PunkteTabelle table = new PunkteTabelle();

    public PunkteTabellePanel() {
        setBackground(java.awt.Color.BLACK);
        setLayout(new GridBagLayout());
        add(table, getTableConstraints());
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int panelHeight = getHeight();
                int numOfRows = table.getRowCount();
                int desiredRowHeight = panelHeight / (numOfRows + 1);
                if (desiredRowHeight < 1) {
                    desiredRowHeight = 1;
                }
                table.setRowHeight(desiredRowHeight);
                table.revalidate();
                table.repaint();
            }
        });
    }

    private GridBagConstraints getTableConstraints() {
        final GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }
    
    public void stufehoch() {
        table.naechsteStufen();
    }
    
    public int getTableStufe() {
        return table.getStufe();
    }
    
    public String getTableStufeSumme(int stufe) {
        if(stufe > -1) {
            return (String)table.getValueAt(stufe, 0);
        } else {
            return "";
        }
    }
}
