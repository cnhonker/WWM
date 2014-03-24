package ry.wwm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;
import ry.util.BasicUtil;

/**
 * HauptFenster
 * 
 * @author ry
 */
public final class MainFrame extends JFrame {

    private boolean sound = true;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setTitle("Wer wird Millionär?");
        setIconImage(BasicUtil.getImageMulti("matrix.gif"));
        //
        final Dimension d = new Dimension(550, 300);
        setPreferredSize(d);
        setMinimumSize(d);
        final GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        //
        final LayerUI<JComponent> layerUI = new MainPanelUI();
        getContentPane().add(new JLayer<>(new MainPanel(), layerUI), c);
        pack();
        setLocationRelativeTo(null);
        midi();
    }
    
    /**
     * Abspielen von Hintergrundmusik
     * 
     * Leertaste pausiert / setzt die Musik fort
     */
    void midi() {
        AudioInputStream audioInputStream;
        try {
            InputStream is = getClass().getResourceAsStream("/midi/fs.mid");
            audioInputStream = AudioSystem.getAudioInputStream(is);
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            final FloatControl gainControl = getControl(clip);
            addMouseWheelListener(new MouseWheelListener() {

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {

                    if (e.getWheelRotation() > 0) {
                        final float vol = gainControl.getValue() - 2;
                        final float min = gainControl.getMinimum();
                        if (vol < min) {
                            gainControl.setValue(min);
                        } else {
                            gainControl.setValue(vol);
                        }

                    }

                    if (e.getWheelRotation() < 0) {
                        final float vol = gainControl.getValue() + 2;
                        final float max = gainControl.getMaximum();
                        if (vol > max) {
                            gainControl.setValue(max);
                        } else {
                            gainControl.setValue(vol);
                        }
                    }
                }
            });
            KeyboardFocusManager manage = getKeyMGR();
            manage.addKeyEventDispatcher(new KeyEventDispatcher() {

                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            if (sound) {
                                clip.stop();
                                e.consume();
                            } else {
                                clip.start();
                                e.consume();
                            }
                            sound = !sound;
                        }
                        
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            System.exit(0);
                        }
                    }
                    return false;
                }
            });
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
            
        } catch (IOException ex) {
            
        } catch (LineUnavailableException ex) {
            //
        }
    }
    
    
    private KeyboardFocusManager getKeyMGR() {
        return KeyboardFocusManager.getCurrentKeyboardFocusManager();
    }
    
    private FloatControl getControl(Clip c) {
        return (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
    }
}
