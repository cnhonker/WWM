package ry.wwm.frage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;
import ry.wwm.joker.JokerDialog;
import ry.wwm.joker.JokerPublikum;
import ry.wwm.joker.JokerTelefon;
import ry.wwm.swingx.JButtonEx;
import ry.wwm.swingx.WaitingUI;

/**
 *
 * @author ry
 */
public final class AntwortPanel extends JPanel {

    public AntwortPanel(List<Fragen> fragen) {
        l = fragen;
        it = l.iterator();
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        init();
    }

    private void init() {
        if (it.hasNext()) {
            f = it.next();
            final List<String> auswahl = f.getAuswahl();
            button.add(new AuswahlButton("A:", getButton(auswahl.get(0))));
            button.add(new AuswahlButton("B:", getButton(auswahl.get(1))));
            button.add(new AuswahlButton("C:", getButton(auswahl.get(2))));
            button.add(new AuswahlButton("D:", getButton(auswahl.get(3))));
            //
            addButton();
        }
    }

    private void addButton() {
        c = new GridBagConstraints();
        add(new FragePanel(f), getFrageConstraints(c));
        add(button.get(0), getFirst(c));
        add(button.get(1), getSecond(c));
        add(button.get(2), getThird(c));
        add(button.get(3), getFourth(c));
    }

    private JButtonEx getButton(String text) {
        final JButtonEx b = new JButtonEx(text);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (f.isRichtig(b.getText())) {
                    firePropertyChange("richtig", "", true);
                    getNextFrage();
                } else {
                    firePropertyChange("richtig", "", false);
                }
            }
        });
        return b;
    }

    private void getNextFrage() {
        if (it.hasNext()) {
            f = it.next();
            final List<String> auswahl = f.getAuswahl();
            button.clear();
            button.add(new AuswahlButton("A:", getButton(auswahl.get(0))));
            button.add(new AuswahlButton("B:", getButton(auswahl.get(1))));
            button.add(new AuswahlButton("C:", getButton(auswahl.get(2))));
            button.add(new AuswahlButton("D:", getButton(auswahl.get(3))));
            //
            removeAll();
            addButton();
            refresh();
        }
    }

    private void refresh() {
        revalidate();
        repaint();
    }

    public void joker5050() {
        String aw = f.getAntwort();
        int i = 0;
        for (AuswahlButton b : button) {
            if (!b.getText().equals(aw) && i != 2) {
                b.clearButton();
                b.setButtonEnabled(false);
                i++;
            }
        }
    }

    public void jokerPublikum() {
        final String txt = "Still only human!";
        new JokerDialog(new JokerPublikum(), this, txt).setVisible(true);
    }

    public void jokerTelefon() {
        final JokerTelefon tel = new JokerTelefon(f);
        final LayerUI<JPanel> layerui = new WaitingUI();
        final JLayer<JPanel> layer = new JLayer<>(tel, layerui);
        new JokerDialog(layer, this, "Trinity! Help me!").setVisible(true);
    }

    private GridBagConstraints getFrageConstraints(GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0d;
        c.weighty = 1.0d;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    private GridBagConstraints getFirst(GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = left;
        return c;
    }

    private GridBagConstraints getSecond(GridBagConstraints c) {
        c.gridx = 1;
        c.gridy = 1;
        c.insets = right;
        return c;
    }

    private GridBagConstraints getThird(GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        c.insets = left;
        return c;
    }

    private GridBagConstraints getFourth(GridBagConstraints c) {
        c.gridx = 1;
        c.gridy = 2;
        c.insets = right;
        return c;
    }

    private final List<Fragen> l;
    private final Iterator<Fragen> it;
    private final List<AuswahlButton> button = new ArrayList<>();
    private GridBagConstraints c = new GridBagConstraints();
    private final Insets left = new Insets(5, 0, 0, 5);
    private final Insets right = new Insets(5, 0, 0, 0);
    private Fragen f;
}
