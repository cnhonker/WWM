package ry.wwm.table;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * PunkteTabelle mit eigenen TableModel
 *
 * @author ry
 */
final class PunkteTabelle extends JTable {

    PunkteTabelle() {
        setBackground(Color.BLACK);
        setModel(new PunkteTableModel());
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return stufen.get(row);
    }
    
    void naechsteStufen() {
        if(step > 0) {
            Punkte p = stufen.get(step);
            p.setBackground(Color.PINK);
            setRowSelectionInterval(step, step);
            repaint();
            step--;
        }
    }
    
    int getStufe() {
       return step;
    }
    
    private static final List<Punkte> stufen = new ArrayList<>();
    private static int step;

    static {
        for (int i = 1000000; i > 65000; i = i / 2) {
            if (i == 1000000) {
                stufen.add(Punkte.getPunkteOutline(i));
            } else {
                stufen.add(Punkte.getPunkte(i));
            }
        }
        for (int i = 64000; i > 1000; i = i / 2) {
            if (i == 64000) {
                stufen.add(Punkte.getPunkteOutline(i));
            } else {
                stufen.add(Punkte.getPunkte(i));
            }
        }
        stufen.add(Punkte.getPunkteOutline(1000));
        stufen.add(Punkte.getPunkte(500));
        stufen.add(Punkte.getPunkte(300));
        stufen.add(Punkte.getPunkte(200));
        stufen.add(Punkte.getPunkte(100));
        step = stufen.size()-1;
    }

    /**
     * MVC-Entsprechend getrennt und verarbeitet die Daten von Listen
     */
    private final class PunkteTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return stufen.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return stufen.get(rowIndex).getText();
        }
    }
}
