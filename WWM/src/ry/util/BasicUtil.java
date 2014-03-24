package ry.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Utility-Klasse
 *
 * @author ry
 */
public class BasicUtil {

    /**
     * Laden von GIF-Bilder
     *
     * @param name pfad innerhalb vom JAR
     * @return GIF-Bild
     */
    public static Image getImageMulti(String name) {
        final URL res = BasicUtil.class.getResource("/img/" + name);
        return Toolkit.getDefaultToolkit().createImage(res);
    }

    /**
     * Laden von normale Bilder
     *
     * @param name pfad innerhalb vom JAR
     * @return
     */
    public static Image getImageFromStream(String name) {
        Image img;
        try {
            InputStream is = BasicUtil.class.getResourceAsStream("/img/" + name);
            img = ImageIO.read(is);
        } catch (IOException ex) {
            img = null;
        }
        return img;
    }

    /**
     * Image als ICON laden
     *
     * @param name
     * @param sF Skalierungsfaktor
     * @return
     */
    public static ImageIcon getImageIcon(String name, int sF) {
        Image img;
        try {
            InputStream is = BasicUtil.class.getResourceAsStream("/img/" + name);
            img = ImageIO.read(is);
            img = img.getScaledInstance(sF, sF, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            img = null;
        }
        return new ImageIcon(img);
    }

    public static ImageIcon getImageIcon(String name) {
        return getImageIcon(name, 50);
    }

    /**
     * Generiert n Zahlen welche Zusammen der Summe sum ergibt
     * @param n
     * @param sum
     * @return 
     */
    public static int[] genNumbers(int n, int sum) {
        int[] nums = new int[n];
        int upperbound = Long.valueOf(Math.round(sum * 1.0 / n)).intValue();
        int offset = Long.valueOf(Math.round(0.5 * upperbound)).intValue();

        int cursum = 0;
        Random random = new Random(new Random().nextInt());
        for (int i = 0; i < n; i++) {
            int rand = random.nextInt(upperbound) + offset;
            if (cursum + rand > sum || i == n - 1) {
                rand = sum - cursum;
            }
            cursum += rand;
            nums[i] = rand;
            if (cursum == sum) {
                break;
            }
        }
        return nums;
    }
}
