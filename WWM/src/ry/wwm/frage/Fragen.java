package ry.wwm.frage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fragen aus der FragenAntworten.txt
 * 
 * Jede Zeile aus der FrageAntworten.txt wird als ein Objekt gespeichert.
 * @author ry
 */
public final class Fragen {
    
    private final String q;
    private final String a;
    private final List<String> l;
    private final int lvl;
    
    public Fragen(String frage, String aw, List<String> auswahl, int level) {
        q = frage;
        a = aw;
        l = auswahl;
        lvl = level;
    }

    public String getFrage() {
        return q == null ? "" : q.trim();
    }

    public String getAntwort() {
        return a == null ? "" : a.trim();
    }

    /**
     * Die Auswahlmöglichkeiten sind random sortiert.
     * 
     * @return Die Antwortmöglichkeiten
     */
    public List<String> getAuswahl() {
        if(l == null) {
            return new ArrayList<>();
        } else {
            Collections.shuffle(l);
            return Collections.unmodifiableList(l);
        }
    }
    
    public int getSchwerigkeitsgrad() {
        return lvl;
    }
    
    public boolean isRichtig(String antwort) {
        return antwort.equals(a);
    }
}
