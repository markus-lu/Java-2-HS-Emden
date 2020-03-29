package CharCollection;

import java.util.ArrayList;
import java.util.HashSet;

public class CharCollection {

    ArrayList<Character> chars = new ArrayList<>();

    CharCollection(char... args) {
        for (char c : args) {
            chars.add(c);
        }
    }

    CharCollection(String input) {

        char[] buffer;
        buffer = input.toCharArray();

        for (int i = 0; i < buffer.length; i++) {
            chars.add(buffer[i]);

        }
    }

    public int count(char input) {
        int counter = 0;

        for (char c : chars) {
            if (c == input) {
                counter++;
            }
        }
        return counter;
    }

    public int different() {

        HashSet<Character> set = new HashSet<>();

        for (char c : chars) {
            set.add(c);
        }

        return set.size();
    }

    public char top() {

        char top = chars.get(0);
        int counter1 = 0;
        int counter2 = 0;

        for (char c : chars) {

            for (char c2 : chars) {
                if (c == c2) {
                    counter1++;
                }
            }
            if (counter1 > counter2) {
                top = c;
                counter2 = counter1;
            }
            counter1 = 0;
        }
        return top;
    }


    public String toString() {

        String ergebnis = "";

        for (int i = 0; i < chars.size(); i++) {
            ergebnis = ergebnis + chars.get(i);

            if (i != chars.size() - 1) {
                ergebnis = ergebnis + ",";
            }

        }
        return ergebnis;
    }

    public int size() {
        return chars.size();
    }


}