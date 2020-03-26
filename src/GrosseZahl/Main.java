package GrosseZahl;

public class Main {
    public static void main(String[] args) {

        GrosseZahl a = new GrosseZahl(400);
        GrosseZahl b = new GrosseZahl(225);
        //System.out.println(a.add(b));


        //System.out.println(a.add(b));
        //System.out.println(a.mult(b));
        System.out.println(a.ggT(b));
        System.out.println(a.gleich(b));
        System.out.println(a);
    }
}

