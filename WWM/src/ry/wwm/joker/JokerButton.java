package ry.wwm.joker;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * JOKER Buttons
 * <p>
 * 50/50, Publikum, Telefon
 * 
 * @author ry
 * 
 */
public final class JokerButton extends JButton {

    /**
     * @param ico Bilddatei für JokerButton
     */
    public JokerButton(ImageIcon ico) {
        setIcon(ico);
        setBorder(null);
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
    }

    /**
     * Automatische Anpassung der ICON-Größe
     * <p>
     * Swing rendert den Button. Mit einer Kopie von Graphics g wird der
     * das ICON an der aktuellen Größe des Buttons gezeichnet.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g.create();
        Object hintValue = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hintValue);
        Image img = ((ImageIcon)getIcon()).getImage();
        g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
    }
}
