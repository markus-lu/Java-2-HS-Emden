package A3.vorlesung;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Vorlesungsverzeichnis eins = new Vorlesungsverzeichnis();


            System.out.println(eins.multipleTitles());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TextFileFormatException e) {
            e.printStackTrace();
        }


    }


}
