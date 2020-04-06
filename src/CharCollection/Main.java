package CharCollection;

public class Main {

    public static void main(String[] args) {

        //CharCollection test = new CharCollection("hallo");
        //CharCollection test2 = new CharCollection('A', 'B', 'C');
        //CharCollection test3 = new CharCollection('A', 'B', 'C');
        CharCollection test4 = new CharCollection("");
        CharCollection b = new CharCollection('H', 'O', 'C', 'H', 'S', 'C', 'H', 'U', 'L', 'E');

        System.out.println(test4.toString());
        //  System.out.println(test.toString());
        //System.out.println(test.size());
        //System.out.println(test.different());
        //System.out.println(test.top());
        //System.out.println(test.moreThan(2));
        System.out.println(b.moreThan(3));
    }

}
