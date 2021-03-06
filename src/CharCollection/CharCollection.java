package CharCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CharCollection {

    List<Character> chars = new ArrayList<>();

    CharCollection(char... args) {
        for (char c : args) {
            chars.add(c);
        }
    }

    CharCollection(String input) {

        this(input.toCharArray());

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
        int max = 0;

        for (char c : chars) {

            for (char c2 : chars) {
                if (c == c2) {
                    counter1++;
                }
            }
            if (counter1 > max) {
                top = c;
                max = counter1;
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
            CharCollection cc = c.Sort();
            CharCollection cc1 = Sort();
            if (cc1.chars.size() == cc.chars.size()) {
                for (int i = 0; i < chars.size(); i++) {
                    if (cc1.chars.contains(cc.chars.get(i))) {
                        //int test =count(chars.get(i));
                        //int test1 = cc.count(cc.chars.get(i));
                        if (count(cc1.chars.get(i)) == c.count(cc.chars.get(i))) {
                            gleich = true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
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

                        remove.remove(c);
                        abgleich.remove(c);

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
        ArrayList<Character> copy = new ArrayList<>(chars);
        Collections.sort(copy);
        StringBuilder sb = new StringBuilder();
        for (Character c : copy) {
            sb.append(c);
        }
        return new CharCollection(sb.toString());
    }


}
