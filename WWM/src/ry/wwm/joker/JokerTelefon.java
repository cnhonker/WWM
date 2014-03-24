package ry.wwm.joker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ry.util.BasicUtil;
import ry.wwm.frage.Fragen;

/**
 *
 * @author ry
 */
public final class JokerTelefon extends JPanel {

    private final Fragen frage;
    private boolean show = false;
    private static final Color MATRIX = new Color(0, 255, 0);
    private static final Image img = BasicUtil.getImageFromStream("trinity.jpg");

    public JokerTelefon(final Fragen f) {
        frage = f;
        Dimension d = new Dimension(img.getWidth(null), img.getHeight(null));
        setMinimumSize(d);
        setPreferredSize(d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(img, 0, 0, null);
        if (show) {
            final String antwort = frage.getAntwort();
            g2d.setColor(MATRIX);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD | Font.ITALIC, 24));
            int w = getX() + getWidth() / 2;
            int h = getY() + getHeight() / 2;
            g2d.drawString(antwort, w, h);
        }
        g2d.dispose();
    }

    public void setShow(boolean b) {
        show = true;
    }
}
