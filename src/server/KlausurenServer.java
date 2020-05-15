package server;

import java.io.*;
import java.net.ServerSocket;
import java.util.*;

public class KlausurenServer extends Thread {

    ServerSocket serverSo;
    private Map<String, List<Integer>> values = new HashMap<>();
    boolean running = true;

    KlausurenServer(Integer port) {
        try {
            serverSo = new ServerSocket(port);
            loadDB();
            System.out.println("Server Online");
            System.out.println("Port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDB() throws IOException {
        Map<String, List<Integer>> erg = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("src\\server\\Database"));
        String line = br.readLine();

        while (line != null) {
            String[] input = line.split(" ");
            String[] ids;
            if (input.length == 2) {
                ids = input[1].split(",");
            } else {
                List<String> buffer = new ArrayList<>();

                for (int i = 1; i < input.length; i++) {
                    String[] inputBuffer = input[i].split(",");
                    buffer.addAll(Arrays.asList(inputBuffer));
                }
                String[] idsBuffer = new String[buffer.size()];
                for (int i = 0; i < buffer.size(); i++) {
                    idsBuffer[i] = buffer.get(i);
                }

                ids = idsBuffer;
            }

            System.out.println(ids);
            List<Integer> keyvalues = new ArrayList<>();
            for (int i = 0; i < ids.length; i++)
                keyvalues.add(Integer.parseInt(ids[i]));

            values.put(input[0], new ArrayList<>(keyvalues));
            System.out.println(keyvalues);
            System.out.println(values);

            line = br.readLine();
        }
        br.close();
    }

    public void run() {

        try {
            while (running) {
                ClientManager client = new ClientManager(serverSo.accept(), values, this);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void stopRunning() throws FileNotFoundException {
        running = false;
        PrintWriter out = new PrintWriter(new FileOutputStream("src\\server\\Database", false));

        List<String> keys = new ArrayList<>(values.keySet());

        for (int i = 0; i < keys.size(); i++) {
            String line = keys.get(i) + " ";
            List<Integer> buffer = new ArrayList<>(values.get(keys.get(i)));
            for (int j = 0; j < buffer.size(); j++) {
                line += buffer.get(j) + ",";
            }
            out.println(line);
        }
        out.close();

        try {
            serverSo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
