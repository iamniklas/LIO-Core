package com.github.iamniklas.liocore.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private NetworkCallback callback;
    private static ArrayList<ClientService> clients = new ArrayList<>();

    private int port;

    public Server(int _port) {
        port = _port;
    }

    @Override
    public void run() {
        super.run();

        try {
            System.out.println("Network Init START");
            serverSocket = new ServerSocket(port);
            System.out.println("Network Init DONE");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        while (true) {
            try {
                clients.add(new ClientService(this, serverSocket.accept()));
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    ClientService getClient(int _id) {
        return clients.get(_id);
    }

    public void setListener(NetworkCallback _callback) {
        callback = _callback;
    }

    void receiveMessage(String _msg) {
        callback.onReceiveMessage(_msg);
    }
}
