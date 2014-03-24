package ry.wwm.joker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JDialog;
import ry.util.BasicUtil;

/**
 * Dialogfenster zum Anzeigen der Jokermeldungen
 *
 * @author ry
 *
 */
public final class JokerDialog extends JDialog {

    /**
     *
     * @param panel Inhalt für JokerDialog
     * @param parent Der aufrufende Komponent
     * @param title Title von JokerDialog. "Enter the matrix!" ist standard.
     */
    public JokerDialog(JComponent panel, JComponent parent, String title) {
        if ((title == null) || (title.trim().isEmpty())) {
            title = "Enter the matrix!";
        }
        setTitle(title);
        setModal(true);
        setBackground(Color.BLACK);
        setIconImage(BasicUtil.getImageMulti("matrix.gif"));
        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
}
