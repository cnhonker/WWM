package ry.wwm;

import ry.util.BasicUtil;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

/**
 * JLayerUI für MainPanel
 *
 * @author ry
 */
final class MainPanelUI extends LayerUI<JComponent> {
    
    private static final Image bg = BasicUtil.getImageMulti("bg.gif");

    /**
     * Mit 70% Prozent Transparenz
     * @param g
     * @param c 
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Graphics2D g2d = (Graphics2D) g;
        int alpha = AlphaComposite.SRC_OVER;
        AlphaComposite comp = AlphaComposite.getInstance(alpha, 0.3f);
        g2d.setComposite(comp);
        g2d.drawImage(bg, 0, 0, c.getWidth(), c.getHeight(), c);
        g2d.dispose();
    }
}
