package Vorlesung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Vorlesungsverzeichnis {

    private Set<Vorlesung> verzeichnis = new HashSet();


    Vorlesungsverzeichnis() throws IOException {
        String filname = "src\\Vorlesung\\db_junit.txt";
        verzeichnis = load(filname);
        // int test = 0;
    }


    Vorlesungsverzeichnis(String filename) throws IOException {
        verzeichnis = load(filename);
    }


    private HashSet load(String filename) throws IOException {

        HashSet<Vorlesung> erg = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));


        String line = br.readLine();
        while (line != null) {
            String[] veranstaltung = line.split(":");

            Vorlesung vorlesung = new Vorlesung(veranstaltung[0], veranstaltung[1], veranstaltung[2], veranstaltung[3]);

            erg.add(vorlesung);
            line = br.readLine();
        }

        br.close();
        return erg;
    }

    public List<String> titles() {

        List<String> erg = new ArrayList<>();
        for (Vorlesung v : verzeichnis)
            erg.add(v.getTitel());

        Collections.sort(erg);

        return erg;
    }


    public Set<String> workaholics() {

        Set<String> erg = new HashSet<>();
        Set<Vorlesung> copy = this.verzeichnis;
        int counter = 0;

        for (Vorlesung vc : copy) {
            for (Vorlesung v : verzeichnis) {
                if (vc.getDozent().equals(v.getDozent()) && !vc.getTitel().equals(v.getTitel()))
                    counter++;
            }
            if (counter > 1)
                erg.add(vc.getDozent());
        }
        return erg;
    }

    public Map<String, List<String>> multipleTitles() {
        Map<String, List<String>> erg = new HashMap<>();
        Set<Vorlesung> copy = new HashSet<>(verzeichnis);
        Set<String> buffer = new HashSet<>();
        int dozentCounter = 1;

        for (Vorlesung vc : copy) {
            for (Vorlesung v : verzeichnis) {
                if (vc.getTitel().equals(v.getTitel()) && !v.getDozent().equals(vc.getDozent())) {
                    buffer.add(vc.getDozent());
                    buffer.add(v.getDozent());
                    dozentCounter++;
                }
            }
            if (dozentCounter > 1)
                erg.put(vc.getTitel(), new ArrayList<String>(buffer));
            dozentCounter = 1;
        }


        return erg;
    }

    public Map<String, List<String>> groupToTitles() {

        Map<String, List<String>> erg = new HashMap();
        Set<Vorlesung> copy = this.verzeichnis;
        List<String> buffer = new ArrayList<>();


        for (Vorlesung c : copy) {
            for (Vorlesung v : verzeichnis) {
                if (c.getStudiengruppe().equals(v.getStudiengruppe())) {
                    buffer.add(v.getTitel());
                }
            }

            erg.put(c.getStudiengruppe(), new ArrayList<String>(buffer));

            buffer.clear();
        }

        return erg;
    }

    public List<String> descendingTitles() {
        List<String> erg = new ArrayList<>();
        Map<Integer, Vorlesung> map = new HashMap<>();
        List<Integer> keySort = new ArrayList<>();
        for (Vorlesung v : verzeichnis) {
            map.put(Integer.parseInt(v.getTeilnehmer()), v);
            keySort.add(Integer.parseInt(v.getTeilnehmer()));
        }
        Collections.sort(keySort);

        Vorlesung buffer;
        for (int i = keySort.size() - 1; i >= 0; i--) {
            buffer = map.get(keySort.get(i));
            erg.add(buffer.getTitel());
        }

        return erg;
    }


}
