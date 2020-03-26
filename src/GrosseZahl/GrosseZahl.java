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
        return false;     // gleich gross
    }

    public GrosseZahl add(GrosseZahl a) {

        //kleinere zahhl wird mit nullen aufgefüllt
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

        // nullen hinten anfügen für pluss rechnung nacher
        for (int i = 0; i < intArray.length; i++) {
            for (int j = i; j < intArray.length - 1; j++) {
                erg = "0" + erg;
            }

            for (int j = a.intArray.length - 1; j >= 0; j--) {
                buffer = getZahl(i) * a.getZahl(j) + buffer;

                if (buffer >= 10) {
                    erg = buffer % 10 + erg; // hintere zahl
                    buffer = buffer / 10; // vordere zahl
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

    private GrosseZahl sub(GrosseZahl a) {

        ArrayList<Integer> erg = new ArrayList<>();
        int buffer = 0;
        int testBuffer = 0;

        // this ist immer gößer
        if (a.intArray.length < intArray.length) {
            a = arrayAdjust(a.intArray, intArray.length);
        }


        for (int i = intArray.length - 1; i >= 0; i--) {
            testBuffer = a.intArray[i] + buffer;

            if (testBuffer > intArray[i]) {
                buffer = buffer + a.intArray[i];
                erg.add((intArray[i] + 10) - buffer);
                buffer = 1;
                testBuffer = 0;

            } else {
                erg.add(getZahl(i) - (a.getZahl(i) + buffer));
                buffer = 0;
                testBuffer = 0;
            }
        }

        for (int i = erg.size() - 1; i >= 0; i--) {
            if (erg.get(i) == 0) {
                erg.remove(i);
            } else {
                break;
            }
        }
        if (erg.isEmpty()) {
            erg.add(0);
        }

        return new GrosseZahl(listToString(erg));
    }

   public GrosseZahl ggT(GrosseZahl a) {

        GrosseZahl buffer;
        if (gleich(a)) {
            return a;
        }

        if (a.less(this)) {
            buffer = sub(a);
            return a.ggT(buffer);
        } else {
            buffer = a.sub(this);
            return ggT(buffer);
        }


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

    public boolean gleich(GrosseZahl a) {

        if (intArray.length != a.intArray.length) {
            return false;
        } else {
            for (int i = 0; i <= intArray.length - 1; i++) {
                if (intArray[i] != a.intArray[i]) {
                    return false;
                }

            }

        }

        return true;
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
