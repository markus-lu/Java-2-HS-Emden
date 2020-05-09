package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;

public class KlausurenServer extends Thread {

    ServerSocket serverSo;
    private Map<String, List<Integer>> values;


    KlausurenServer(Integer port) {
        try {
            serverSo = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int clNr = 0;
        try {
            Client client = new Client(serverSo.accept(), clNr);
            clNr++;
            BufferedReader input = new BufferedReader(new InputStreamReader());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
