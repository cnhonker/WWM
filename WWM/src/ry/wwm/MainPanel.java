package ry.wwm;

import java.awt.Color;
import ry.wwm.frage.AntwortPanel;
import ry.wwm.joker.JokerPanel;
import ry.wwm.table.PunkteTabellePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import ry.util.BasicUtil;
import ry.util.WWMImport;

/**
 * MainPanel mit MigLayout
 * <p>
 * MainPanel besteht aus
 * <ul>
 * <li>PunkteTabellePanel</li>
 * <li>AntwortPanel</li>
 * <li>JokerPanel</li>
 * </ul>
 *
 * @author ry
 */
final class MainPanel extends JPanel {

    private final AntwortPanel ant;
    private final PunkteTabellePanel pkt;
    private final JokerPanel jkr;
    private boolean fail = false;
    private boolean sieg = false;

    MainPanel() {
        setLayout(new MigLayout("gap 0,insets 0", "[15%][70%][15%]"));
        setOpaque(false);
        //
        pkt = new PunkteTabellePanel();
        jkr = new JokerPanel();
        ant = new AntwortPanel(WWMImport.importFile());
        pkt.addPropertyChangeListener(new FailureListener());
        jkr.addPropertyChangeListener(new FailureListener());
        ant.addPropertyChangeListener(new FailureListener());
        //
        add(pkt, "grow, push");
        add(ant, "grow, push");
        add(jkr, "grow, push");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fail) {
            drawImage(g, "fail.jpg");
        }
        if (sieg) {
            drawImage(g, "neo_stop.jpg");
        }
    }

    private void drawImage(Graphics g, String name) {
        Graphics2D g2d = (Graphics2D) g.create();
        Image img = BasicUtil.getImageFromStream(name);
        if (img != null) {
            g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        } else {
            setBackground(Color.BLACK);
            setOpaque(true);
        }
        g2d.dispose();

    }

    private void fail() {
        fail = true;
        removeAll();
        String r;
        if (pkt.getTableStufe() == 14) {
            r = "0 €";
        } else {
            r = pkt.getTableStufeSumme(pkt.getTableStufe()+1);
        }
        showDiaglog(r, "Failure");
    }

    private void win() {
        sieg = true;
        removeAll();
        String r = pkt.getTableStufeSumme(0);
        if (!r.isEmpty()) {
            showDiaglog(r, "The matrix has you ...");
        }
    }

    /**
     * Zeigt dem Benutzer eine Meldung an.
     *
     * @param r Ergebnis
     * @param t Title
     */
    private void showDiaglog(final String r, final String t) {
        JOptionPane.showMessageDialog(this, r, t, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * An Hand der Properties werden Aktionen ausgeführt.
     * 
     * <ul>
     * <li>richtig</li>
     * <li>joker.500</li>
     * <li>joker.publikum</li>
     * <li>joker.telefon</li>
     * </ul>
     */
    private class FailureListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case ("richtig"):
                    //System.out.println("HALLO");
                    if (evt.getNewValue().equals(true)) {
                        jkr.resetTimer();
                        if (pkt.getTableStufe() == 0) {
                            jkr.stopTimer();
                            win();
                        } else {
                            pkt.stufehoch();
                        }
                    } else {
                        jkr.stopTimer();
                        fail();
                    }
                    break;
                case ("joker.5050"):
                    ant.joker5050();
                    break;
                case ("joker.publikum"):
                    ant.jokerPublikum();
                    break;
                case ("joker.telefon"):
                    ant.jokerTelefon();
                    break;
                default:
            }
        }
    }
}
