package server;

import java.io.InputStream;
import java.net.Socket;

public class Client implements Runnable {


    private Socket clientSocket;
    private int clNr;

    Client(Socket clientSocket, int clNr) {
        this.clientSocket = clientSocket;
        this.clNr = clNr;

    }


    @Override
    public void run() {

    }

    public InputStream getInputstarm() {

    }
}
