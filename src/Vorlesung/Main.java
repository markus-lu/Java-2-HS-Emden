package Vorlesung;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Vorlesungsverzeichnis eins = new Vorlesungsverzeichnis();


            System.out.println(eins.multipleTitles());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
