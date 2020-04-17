package Vorlesung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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


}
