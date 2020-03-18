package GrosseZahl;

import java.util.ArrayList;

public class GrosseZahl {

    private int[] intArray;

    GrosseZahl(int zahl) {
        intArray = IntToArray(zahl);
    }

    GrosseZahl(String zahl) {

        intArray = StringToArray(zahl);
    }

    public boolean less(GrosseZahl b) {

        if (intArray.length > b.intArray.length) {
            return false;
        } else if (intArray.length < b.intArray.length) {
            return true;
        } else {
            for (int i = 0; i < intArray.length; i++) {
                if (intArray[i] > b.intArray[i]) {
                    return false;
                }
                if (intArray[i] < b.intArray[i]) {
                    return true;
                }
            }


        }
        return false;
    }

    public GrosseZahl add(GrosseZahl a) {

        int leangth = 0;
        GrosseZahl b;
        boolean state;
        if (intArray.length > a.intArray.length) {
            leangth = intArray.length;
            b = arrayAdjust(a.intArray, intArray.length);
            state = true;
        } else {
            leangth = a.intArray.length;
            b = arrayAdjust(intArray, a.intArray.length);
            state = false;
        }

        ArrayList<Integer> erg = new ArrayList<>();
        int buffer = 0;
        for (int i = leangth - 1; i >= 0; i--) {
            if (!state) {
                //  int test = a.getZahl(i);

                buffer = a.getZahl(i) + b.getZahl(i) + buffer;
            } else {
                buffer = b.getZahl(i) + getZahl(i) + buffer;
            }

            if (buffer >= 10) {
                erg.add(buffer - 10);
                buffer = 1;
            } else {
                erg.add(buffer);
                buffer = 0;
            }

        }
        if (buffer > 0) {
            erg.add(buffer);
        }
        String build = listToString(erg);


        return new GrosseZahl(build);
    }

    public GrosseZahl mult(GrosseZahl a) {
        String erg = "";
        GrosseZahl ergebnis = new GrosseZahl(0);
        int buffer = 0;

        for (int i = 0; i < intArray.length; i++) {
            for (int j = i; j < intArray.length - 1; j++) {
                erg = "0" + erg;
            }
            for (int j = a.intArray.length - 1; j >= 0; j--) {
                buffer = getZahl(i) * a.getZahl(j) + buffer;

                if (buffer >= 10) {
                    erg = buffer % 10 + erg;
                    buffer = buffer / 10;
                } else {
                    erg = buffer + erg;
                    buffer = 0;
                }

            }
            if (buffer > 0) {
                erg = buffer + erg;
                buffer = 0;
            }
            GrosseZahl zwischen = new GrosseZahl(erg);
            ergebnis = ergebnis.add(zwischen);
            erg = "";


        }


        return ergebnis;
    }

    public GrosseZahl sub(GrosseZahl a) {

        GrosseZahl ergebnis = new GrosseZahl(0);
        ArrayList<Integer> erg = new ArrayList<>();
        int buffer = 0;
        boolean state = less(a);

        if (state) {
        }


        return ergebnis;
    }


    private String listToString(ArrayList<Integer> a) {
        String erg = "";

        for (int i = a.size() - 1; i >= 0; i--) {
            erg = erg + a.get(i);
        }

        return erg;
    }


    private int getZahl(int i) {
        if (i < intArray.length) {
            return intArray[i];
        }
        return 0;
    }


    private GrosseZahl fillArrayWithZeros(int lenght) {

        String numbers = "";
        for (int i = 0; i <= lenght; i++) {
            numbers = numbers + "0";
        }
        GrosseZahl Zero = new GrosseZahl(numbers);

        return Zero;
    }

    private GrosseZahl arrayAdjust(int[] array, int lenght) {

        String erg = "";
        int diff = lenght - array.length;

        for (int i = 0; i < array.length; i++) {
            erg = erg + array[i];
        }
        for (int i = 0; i < diff; i++) {
            erg = "0" + erg;
        }

        return new GrosseZahl(erg);
    }


    private int[] StringToArray(String zahl) {

        int[] returnArray = new int[zahl.length()];
        char[] charArray = zahl.toCharArray();


        for (int i = 0; i <= zahl.length() - 1; i++) {


            returnArray[i] = charArray[i] - '0';
        }

        return returnArray;
    }


    public int[] IntToArray(int zahl) {

        String buffer = "";
        buffer = buffer.valueOf(zahl);

        return StringToArray(buffer);
    }

    public String toString() {

        String returnString = "";
        for (int i = 0; i < intArray.length; i++) {
            returnString = returnString + intArray[i];
        }
        return returnString;
    }


}
