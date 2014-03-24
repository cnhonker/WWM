import ry.wwm.MainFrame;

/**
 * Main von Wer wird Million‰r
 * 
 * Gem‰ﬂ der Single-Thread Regel l‰uft JFrame mit Hilfe von SwingUtilities
 * auf der Event-Dispatch-Thread (EDT)
 * @author ry
 */
public class JStart {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
