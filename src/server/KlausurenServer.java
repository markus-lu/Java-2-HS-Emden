package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KlausurenServer extends Thread {

    ServerSocket serverSo;
    private Map<String, List<Integer>> values = new HashMap<>();


    KlausurenServer(Integer port) {
        try {
            serverSo = new ServerSocket(port);
            System.out.println("Server Online");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {


        try {
            while (true) {
                ClientManager client = new ClientManager(serverSo.accept(), values);
                client.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
