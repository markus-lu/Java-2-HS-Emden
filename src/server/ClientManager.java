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

                    List<List<Integer>> toRemove = new ArrayList<>();

                    for (List<Integer> list : onlyValues) {
                        for (List<Integer> list2 : onlyValues) {
                            if (list != list2) {
                                if (list.containsAll(list2)) {
                                    if (!toRemove.contains(list2)) {
                                        toRemove.add(list2);
                                    }
                                }
                            }
                        }
                    }

                    onlyValues.removeAll(toRemove);
                    String getall = "";
                    for (int i = 0; i < onlyValues.size(); i++) {
                        if (i == 0) {
                            getall += onlyValues.get(i);
                        } else {
                            getall += "," + onlyValues.get(i);
                        }

                    }
                    output.println("1 " + getall.replaceAll(" ", ""));

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

            br.close();
            output.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
