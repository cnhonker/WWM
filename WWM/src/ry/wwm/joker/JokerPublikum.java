package ry.wwm.joker;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import ry.util.BasicUtil;
import ry.wwm.frage.Fragen;

/**
 *
 * @author ry
 */
public final class JokerPublikum extends JPanel {

    public JokerPublikum() {
        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        int[] nr = BasicUtil.genNumbers(4, 90);
        
        MatrixProgressBar neoB = new MatrixProgressBar("A",nr[0]);
        MatrixProgressBar triB = new MatrixProgressBar("B",nr[1]);
        MatrixProgressBar smiB = new MatrixProgressBar("C",nr[2]);
        MatrixProgressBar ryuB = new MatrixProgressBar("D",nr[3]+10);
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.25f;
        c.weighty = 0.3f;
        add(new JLabel(BasicUtil.getImageIcon("neo.jpg", 100)), c);
        c.weighty = 0.7f;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        add(neoB, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0.3f;
        add(new JLabel(BasicUtil.getImageIcon("morpheus.jpg", 100)), c);
        c.weighty = 0.7f;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        add(triB, c);

        c.gridx = 2;
        c.gridy = 0;
        c.weighty = 0.3f;
        add(new JLabel(BasicUtil.getImageIcon("smith.jpg", 100)), c);
        c.weighty = 0.7f;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        add(smiB, c);

        c.gridx = 3;
        c.gridy = 0;
        c.weighty = 0.3f;
        add(new JLabel(BasicUtil.getImageIcon("me.jpg", 100)), c);
        c.weighty = 0.7f;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        add(ryuB, c);
    }
    
    private class MatrixProgressBar extends JProgressBar {

        MatrixProgressBar(String auswahl, int value) {
            super(JProgressBar.VERTICAL, 0, 100);
            setBackground(Color.BLACK);
            setForeground(Color.GREEN);
            setValue(value);
            setString(auswahl + ": "+getString());
            setStringPainted(true);
            setBorderPainted(false);
        }
    }
}
