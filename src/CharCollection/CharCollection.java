package CharCollection;

import java.util.ArrayList;
import java.util.Collections;
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

        if (chars.size() == 0) {
            return 0;
        }

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

        String ergebnis = "(";

        for (int i = 0; i < chars.size(); i++) {
            ergebnis = ergebnis + chars.get(i);

            if (i != chars.size() - 1) {
                ergebnis = ergebnis + ", ";
            }

        }
        ergebnis = ergebnis + ")";
        return ergebnis;
    }

    public int size() {
        return chars.size();
    }

    public CharCollection moreThan(int n) {
        String erg = "";
        int counter = 0;
        for (char c : chars) {
            for (char c1 : chars) {
                if (c == c1) {
                    counter++;
                }
            }
            if (counter > n) {
                erg = erg + c;
            }
            counter = 0;
        }
        CharCollection rückgabe = new CharCollection(erg);
        rückgabe.Sort();

        return rückgabe;
    }

    public boolean equals(Object o) {
        boolean gleich = false;

        if (o instanceof CharCollection) {
            CharCollection c = (CharCollection) o;

            if (c.size() == 0 && size() == 0) {
                return true;
            }
            if (chars.size() == c.chars.size()) {
                for (int i = 0; i < chars.size(); i++) {
                    if (chars.contains(c.chars.get(i))) {
                        if (count(chars.get(i)) == c.count(c.chars.get(i))) {
                            gleich = true;
                        }
                    }
                }
            }
        }
        return gleich;
    }

    public CharCollection except(CharCollection cc) {
        ArrayList<Character> remove = new ArrayList<>(cc.chars);

        String erg = "";


        for (Character c : chars) {
            if (remove.contains(c.charValue())) {
                remove.remove(c);
            } else {
                erg += c;
            }

        }

        return new CharCollection(erg);
    }


    public boolean isSubset(CharCollection cc) {

        ArrayList<Character> remove = new ArrayList<>(cc.chars);
        ArrayList<Character> abgleich = new ArrayList<>(chars);

        for (Character c : cc.chars) {
            if (chars.contains(c.charValue())) {
                if (abgleich.contains(c.charValue())) {
                    try {
                        remove.remove(c);
                        abgleich.remove(c);
                    } catch (Exception e) {
                        return false;
                    }
                }

            } else {
                return false;
            }
        }
        if (remove.size() > 0) {
            return false;
        }

        return true;

    }


    private CharCollection Sort() {
        Collections.sort(chars);
        return new CharCollection(toString());
    }


}
