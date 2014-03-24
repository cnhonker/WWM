package ry.wwm.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Die Punktanzahl in der Tabelle
 *
 * @author ry
 */
final class Punkte extends JLabel implements TableCellRenderer {

    private static final NumberFormat f;
    private static final Color STUFE = Color.GREEN;
    private static final Color ABSCHNITT = Color.RED;

    static {
        f = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        f.setMaximumFractionDigits(0);
    }
    
    static Punkte getPunkte(long number) {
        return new Punkte(number);
    }
    
    static Punkte getPunkteOutline(long number) {
        return new Punkte(number, ABSCHNITT);
    }

    /**
     * Normale Stufen
     *
     * @param number Werte der Stufe
     */
    private Punkte(long number) {
        setText(f.format(number));
        setBackground(Color.BLACK);
        setForeground(Punkte.STUFE);
        setOpaque(true);
    }

    /**
     * Sicherheitsstufen
     * <p>
     * Die Werte werden kursiv, fett und mit der Größe 14 dargestellt
     *
     * @param number Werte der Stufe
     * @param bg Schriftffarbe
     */
    private Punkte(long number, Color bg) {
        setFont(getFont().deriveFont(Font.ITALIC | Font.BOLD, 14f));
        setText(f.format(number));
        setBackground(Color.BLACK);
        setForeground(bg);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}
