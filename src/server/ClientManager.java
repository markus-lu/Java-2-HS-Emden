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


    ClientManager(Socket clientSocket, Map<String, List<Integer>> values) {
        this.clientSocket = clientSocket;

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
                case ("put"):
                    String[] ids = input[2].split(",");
                    System.out.println(ids);
                    List<Integer> keyvalues = new ArrayList<>();
                    for (int i = 0; i < ids.length; i++)
                        keyvalues.add(Integer.parseInt(ids[i]));

                    values.put(input[1], new ArrayList<>(keyvalues));
                    System.out.println(keyvalues);
                    System.out.println(values);
                    output.println("1");
                    break;
                case ("get"):
                    if (values.containsKey(input[1])) {
                        output.println("1 " + values.get(input[1]));
                    } else {
                        output.println("0");
                    }
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


}
