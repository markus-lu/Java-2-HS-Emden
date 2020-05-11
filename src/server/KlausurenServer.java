package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KlausurenServer extends Thread {

    ServerSocket serverSo;
    private Map<String, List<Integer>> values = new HashMap<>();
    boolean running = true;

    KlausurenServer(Integer port) {
        try {
            serverSo = new ServerSocket(port);
            System.out.println("Server Online");
            System.out.println("Port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {


        try {
            while (running) {
                ClientManager client = new ClientManager(serverSo.accept(), values,this);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void stopRunning(){
        running = false;
        try {
            serverSo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
