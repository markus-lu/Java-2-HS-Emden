package CharCollection;

public class Main {

    public static void main(String[] args) {

        CharCollection test = new CharCollection("hallooooo");
        CharCollection test2 = new CharCollection('A', 'B', 'C');

        System.out.println(test.toString());
        System.out.println(test.size());
        System.out.println(test.different());
        System.out.println(test2.top());

    }

}
