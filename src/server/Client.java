package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Client extends Thread {


    private Socket clientSocket;
    private Map<String, List<Integer>> values;


    Client(Socket clientSocket, Map<String, List<Integer>> values) {
        this.clientSocket = clientSocket;

        this.values = values;
    }


    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String test = br.readLine();
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
            output.println(test);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
