package ry.wwm.joker;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import ry.util.BasicUtil;

/**
 *
 * @author ry
 */
public final class JokerPanel extends JPanel{

    private Timer timer;
    private final JProgressBar bar;
    private int counter = 30;
    
    public void resetTimer() {
        counter = 30;
        bar.setValue(30);
        timer.restart();
    }
    
    public void stopTimer() {
        timer.stop();
    }

    public JokerPanel() {
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        // Fortschrittanzeige
        bar = new JProgressBar(SwingConstants.VERTICAL, 0, 30);
        bar.setBackground(Color.BLACK);
        bar.setForeground(Color.GREEN.brighter().brighter().brighter());
        bar.setBorderPainted(true);
        bar.setValue(30);
        ActionListener timerListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                bar.setValue(counter);
                if (counter < 1) {
                    timer.stop();
                    firePropertyChange("richtig", "", false);
                }
            }
        };
        timer = new Timer(1000, timerListener);
        timer.start();
        // JokerButton
        JokerButton h50 = new JokerButton(BasicUtil.getImageIcon("pill.jpg"));
        JokerButton pub = new JokerButton(BasicUtil.getImageIcon("agents.jpg"));
        JokerButton tel = new JokerButton(BasicUtil.getImageIcon("op.jpg"));
        h50.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("joker.5050", "", true);
            }
        });
        pub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("joker.publikum", "", true);
            }
        });
        tel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("joker.telefon", "", true);
            }
        });
        // 
        add(bar, getProgressConstraints(c));
        add(h50, getPillConstraints(c));
        add(pub, getAgentConstraints(c));
        add(tel, getTeleConstraints(c));
    }


    private GridBagConstraints getProgressConstraints(GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.weighty = 1;
        c.gridheight = 3;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    private GridBagConstraints getPillConstraints(GridBagConstraints c) {
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.7;
        c.weighty = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    private GridBagConstraints getAgentConstraints(GridBagConstraints c) {
        c.gridy = 1;
        return c;
    }

    private GridBagConstraints getTeleConstraints(GridBagConstraints c) {
        c.gridy = 2;
        return c;
    }
}
