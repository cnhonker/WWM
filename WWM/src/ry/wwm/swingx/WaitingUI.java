package ry.wwm.swingx;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;
import ry.wwm.joker.JokerTelefon;

public final class WaitingUI extends LayerUI<JPanel> implements ActionListener {

    private boolean mIsRunning;
    private boolean mIsFadingOut;
    private Timer mTimer;

    private int mAngle;
    private int mFadeCount;
    private int mFadeLimit = 15;

    public WaitingUI() {
        
        final Timer stopper = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                stop();
            }
        });
        stopper.setRepeats(false);
        start();
        if(!stopper.isRunning()) {
            stopper.start();
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        int w = c.getWidth();
        int h = c.getHeight();

        // Paint the view.
        super.paint(g, c);

        if (!mIsRunning) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        float fade = (float) mFadeCount / (float) mFadeLimit;
        // Gray it out.
        Composite urComposite = g2.getComposite();
        g2.setComposite(getComposite(.5f * fade));
        g2.fillRect(0, 0, w, h);
        g2.setComposite(urComposite);

        // Paint the wait indicator.
        int s = Math.min(w, h) / 5;
        int cx = w / 2;
        int cy = h / 2;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int bs = BasicStroke.JOIN_ROUND;
        g2.setStroke(new BasicStroke(s / 4, BasicStroke.CAP_ROUND, bs));
        g2.setPaint(new Color(0, 255, 0));
        g2.rotate(Math.PI * mAngle / 180, cx, cy);
        for (int i = 0; i < 12; i++) {
            float scale = (11.0f - (float) i) / 11.0f;
            g2.drawLine(cx + s, cy, cx + s * 2, cy);
            g2.rotate(-Math.PI / 6, cx, cy);
            g2.setComposite(getComposite(scale * fade));
        }

        g2.dispose();
    }
    
    private AlphaComposite getComposite(float sf) {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, sf);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mIsRunning) {
            firePropertyChange("tick", 0, 1);
            mAngle += 3;
            if (mAngle >= 360) {
                mAngle = 0;
            }
            if (mIsFadingOut) {
                if (--mFadeCount == 0) {
                    mIsRunning = false;
                    mTimer.stop();
                    firePropertyChange("fertig", "", true);
                }
            } else if (mFadeCount < mFadeLimit) {
                mFadeCount++;
            }
        }
    }

    public void start() {
        if (mIsRunning) {
            return;
        }

        // Run a thread for animation.
        mIsRunning = true;
        mIsFadingOut = false;
        mFadeCount = 0;
        int fps = 24;
        int tick = 1000 / fps;
        mTimer = new Timer(tick, this);
        mTimer.start();
    }

    public void stop() {
        mIsFadingOut = true;
    }

    @Override
    public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
        if ("tick".equals(pce.getPropertyName())) {
            l.repaint();
        }
        if("fertig".equals(pce.getPropertyName())) {
            Component comp = l.getView();
            if(comp instanceof JokerTelefon) {
                JokerTelefon tel = (JokerTelefon)comp;
                tel.setShow(true);
                tel.repaint();
            }
        }
    }
}
