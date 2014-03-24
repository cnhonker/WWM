package ry.wwm.swingx;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * AntwortButton
 * 
 * @author ry
 */
public class JButtonEx extends JButton {

    public JButtonEx(String antwort) {
        setText(antwort);
        setForeground(new Color(0, 255, 0));
        setHorizontalAlignment(SwingConstants.LEFT);
        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }
}
