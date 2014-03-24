package ry.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ry.wwm.frage.Fragen;

/**
 * Utilities zum Einlesen der Frage und Antwort Datei
 * 
 * Dateiformat
 * Frage;Antwort A;Antwort B;Antwort C;Antwort D;Richtige Antwort;Schierigkeitsgrad;
 * @author ry
 */
public final class WWMImport {

    public static final List<Fragen> importFile() {
        final List<Fragen> qList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(WWMImport.class.getResourceAsStream("/doc/FragenAntworten.txt")))) {
            String line = br.readLine(); // Spaltenname wird ignoriert
            while((line = br.readLine()) != null) {
                final Fragen f = getFrage(Arrays.asList(line.split(";")));
                if(f != null) {
                    qList.add(f);
                }
            }
        } catch (IOException e) {
            
        }
        return qList;
    }
    
    private static Fragen getFrage(List<String> sp) {
        Fragen f = null;
        if(sp.size() == 7) {
            final String frage = sp.get(0);
            final int antIndex = getAntwortIndex(sp.get(5));
            final int lvlIndex = getSchwerigkeitsgrad(sp.get(6));
            final List<String> auswahl = new ArrayList<>();
            auswahl.add(sp.get(1));
            auswahl.add(sp.get(2));
            auswahl.add(sp.get(3));
            auswahl.add(sp.get(4));
            if((antIndex != -1) && (lvlIndex != -1)) {
                final String antwort = sp.get(antIndex);
                f = new Fragen(frage, antwort, auswahl, lvlIndex);
            }
        }
        return f;
    }
    
    private static int getAntwortIndex(String antwortIdx) {
        int idx;
        idx = getNumberOf(antwortIdx);
        idx = idx == -1 ? idx : idx;
        return idx;
    }
    
    private static int getSchwerigkeitsgrad(String schwerigkeitsgrad) {
        return getNumberOf(schwerigkeitsgrad);
    }
    
    private static int getNumberOf(String nummmer) {
        int idx;
        try {
            idx = Integer.valueOf(nummmer);
        } catch (NumberFormatException e) {
            idx = -1;
        }
        return idx;
    }
}
