package ry.wwm.frage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class FragePanel extends JPanel {

    private Polygon poly;
    private Color polyColor;
    private static final Color DEF_BG = new Color(72, 137, 179);

    public FragePanel(String text) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = 0.8f;
        c.ipadx = 100;
        add(getTextPane(text), c);
    }

    public FragePanel(Fragen f) {
        this(f.getFrage());
    }

    private JTextPane getTextPane(String text) {
        JTextPane textPane = new JTextPane();
        textPane.setText(text);
        textPane.setEditable(false);
        textPane.setForeground(new Color(0, 255, 0));
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textPane.setBackground(Color.BLACK);
        return textPane;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D d2 = (Graphics2D) g;
        polyColor = polyColor == null ? DEF_BG : polyColor;
        d2.setColor(Color.BLACK);
        Polygon p = getHexPolygon();
        d2.fillPolygon(p);
        d2.setColor(Color.GREEN);
        d2.drawPolygon(p);
    }

    @Override
    public boolean contains(Point p) {
        poly = getHexPolygon();
        return poly.contains(p);
    }

    @Override
    public boolean contains(int x, int y) {
        poly = getHexPolygon();
        return poly.contains(x, y);
    }

    private Polygon getHexPolygon() {
        final Polygon hex = new Polygon();
        final int w = getWidth() - 1;
        final int h = getHeight() - 1;
        final int ratio = (int) (h * .25);
        hex.addPoint(0, h / 2);
        hex.addPoint(ratio, h);
        hex.addPoint(w - ratio, h);
        hex.addPoint(w, h / 2);
        hex.addPoint(w - ratio, 0);
        hex.addPoint(ratio, 0);
        return hex;
    }
}
