package ry.wwm.frage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import ry.wwm.swingx.Hexagon;
import ry.wwm.swingx.JButtonEx;
import ry.wwm.swingx.JLabelEx;

/**
 * AuswahlButton
 * 
 * @author ry
 */
final class AuswahlButton extends JPanel {

    private final JButtonEx button;
    private final JLabelEx label;
    private Hexagon hex;
    private Color polyColor;
    
    /**
     * Der AuswahlButton besteht eigentlich aus einem JLabel und einem JButtonEx
     * 
     * @param tag Zähler 1,2,3 ... A,B,C ...
     * @param b 
     */
    AuswahlButton(final String tag, final JButtonEx b) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        button = b;
        label = new JLabelEx(tag);
        add(label, C_LABEL);
        add(button, C_KNOPF);
        addMouseListener(new MouseAdapterEx());
    }

    /**
     * Swing rendert das Button, anschließend wird einen Kopie von Graphics g
     * erzeugt. Damit wird einen Hexagon gezeichnet. Sodass als ob man einen
     * JButton im Form eines transparenten Hexagon hat.
     * 
     * Transparenz wird hier bewusst vorgetäuscht.
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g.create();
        polyColor = polyColor == null ? DEF_BG : polyColor;
        g2d.setColor(polyColor);
        final Hexagon hx = new Hexagon(getWidth(), getHeight());
        g2d.fillPolygon(hx);
        g2d.setColor(Color.GREEN);
        g2d.drawPolygon(hx);
        g2d.dispose();
    }

    /**
     * Neue Grenze wegen Hexagon festlegen. 
     * Wichtig für Mouse-Interaktionen
     * 
     * @param p
     * @return 
     */
    @Override
    public boolean contains(int x, int y) {
        hex = new Hexagon(getWidth(), getHeight());
        return hex.contains(x, y);
    }

    String getText() {
        return button.getText();
    }

    void setText(String txt) {
        button.setText(txt);
    }

    void clearButton() {
        button.setText("");
        label.setText("");
    }

    void setButtonEnabled(boolean b) {
        button.setEnabled(b);
    }

    private static final Insets I_LABEL = new Insets(0, 40, 0, 0);
    private static final Insets I_KNOPF = new Insets(0, 0, 0, 10);
    private static final GridBagConstraints C_LABEL = new GridBagConstraints();
    private static final GridBagConstraints C_KNOPF = new GridBagConstraints();
    private static final Color DEF_BG = Color.BLACK;
    private static final Color MOUSE_ENTER = Color.BLUE.brighter().brighter();
    private static final Color MOUSE_EXIT = Color.BLACK;
    
    static {
        C_LABEL.gridx = 0;
        C_LABEL.gridy = 0;
        C_LABEL.weightx = 0.1d;
        C_LABEL.weighty = 1.0d;
        C_LABEL.anchor = GridBagConstraints.CENTER;
        C_LABEL.fill = GridBagConstraints.NONE;
        C_LABEL.insets = I_LABEL;
        //
        C_KNOPF.gridx = 1;
        C_KNOPF.gridy = 0;
        C_KNOPF.weightx = 0.9d;
        C_KNOPF.weighty = 1.0d;
        C_KNOPF.anchor = GridBagConstraints.LINE_START;
        C_KNOPF.fill = GridBagConstraints.NONE;
        C_KNOPF.insets = I_KNOPF;
    }

    /**
     * JButton simulieren
     * 
     */
    private final class MouseAdapterEx extends MouseAdapter {

        private Timer tBlink;
        private int counter = 6;
        private boolean change = false;

        /**
         * Blinkende JButton simulieren
         * @param e 
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (button.isEnabled()) {
                tBlink = new Timer(500, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        counter--;
                        if (counter == 0) {
                            tBlink.stop();
                            button.doClick();
                            return;
                        }
                        if (change) {
                            polyColor = Color.RED;
                        } else {
                            polyColor = MOUSE_EXIT;
                        }
                        repaint();
                        change = !change;
                    }
                });
                tBlink.setInitialDelay(0);
                tBlink.start();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (button.isEnabled()) {
                polyColor = MOUSE_ENTER;
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (button.isEnabled()) {
                polyColor = MOUSE_EXIT;
                repaint();
            }
        }
    }
}
