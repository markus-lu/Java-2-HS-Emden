package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientManager extends Thread {


    private Socket clientSocket;
    private Map<String, List<Integer>> values;
    private KlausurenServer server;

    ClientManager(Socket clientSocket, Map<String, List<Integer>> values, KlausurenServer klausurenServer) {
        this.clientSocket = clientSocket;
        this.server = klausurenServer;
        this.values = values;
    }


    @Override
    public void run() {
        try {
            System.out.println("client Online");
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line = br.readLine();
            String[] input = line.split(" ");


            // System.out.println(input[0]+" "+input[1]);
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            switch (input[0]) {
                case "hello ":
                    output.println("General Kenobi");
                    output.println(input[1]);
                    break;
                case ("engage"):
                    output.println("Couse Earth Warp 9");
                    break;
                case ("test"):
                    output.println(input[1]);
                case ("PUT"):
                case ("put"):
                    String[] ids = input[2].split(",");
                    System.out.println(ids);
                    List<Integer> keyvalues = new ArrayList<>();
                    for (int i = 0; i < ids.length; i++)
                        keyvalues.add(Integer.parseInt(ids[i]));
                    if (!values.containsKey(input[1])) {
                        values.put(input[1], new ArrayList<>(keyvalues));
                        System.out.println(keyvalues);
                        System.out.println(values);
                        output.println("1");
                    } else {
                        List<Integer> old = new ArrayList<>(values.get(input[1]));
                        values.put(input[1], new ArrayList<>(keyvalues));
                        output.println("1 " + old.toString().replace("[", "").replace("]", "").replaceAll(" ", ""));
                    }
                    break;
                case ("GET"):
                case ("get"):
                    if (values.containsKey(input[1])) {
                        output.println("1 " + values.get(input[1]).toString().replace("[", "").replace("]", "").replaceAll(" ", ""));
                    } else {
                        output.println("0");
                    }
                    break;
                case ("DEL"):
                case ("del"):
                    if (values.containsKey(input[1])) {
                        output.println("1 " + values.get(input[1]).toString().replace("[", "").replace("]", "").replaceAll(" ", ""));
                        values.remove(input[1]);
                    } else {
                        output.println("0");
                    }
                    break;
                case ("GETALL"):
                case ("getall"):
                    List<String> keys = new ArrayList<>(values.keySet());
                    List<List<Integer>> onlyValues = new ArrayList<>();

                    for (String k : keys) {
                        onlyValues.add(values.get(k));
                    }
                    List<List<Integer>> buffer = new ArrayList<>(onlyValues);
                    for (List<Integer> l : onlyValues) {
                        buffer.remove(l);
                        for (List<Integer> ls : buffer) {

                            if (isSubList(l, ls)) {
                                onlyValues.remove(l);
                            }

                        }

                    }
                    output.println(onlyValues);

                    break;
                case ("STOP"):
                case ("stop"):
                    output.println("1");
                    server.stopRunning();
                    break;
                default:
                    output.println("test failed");

            }
            //output.println("General Kenobi");
            //output.println("test");


            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean isSubList(List<Integer> a, List<Integer> b) {

        List<Integer> kleiner = new ArrayList<>();
        List<Integer> groesser = new ArrayList<>();
        if (a.size() > b.size()) {
            kleiner = b;
            groesser = a;
        } else if (a.size() < b.size()) {
            kleiner = a;
            groesser = b;
        } else {

            kleiner = a;
            groesser = b;
        }
        List<Integer> remove = kleiner;
        for (Integer k : kleiner) {
            for (Integer g : groesser) {
                if (k == g) {
                    remove.remove(k);
                }
            }
        }
        if (remove.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
