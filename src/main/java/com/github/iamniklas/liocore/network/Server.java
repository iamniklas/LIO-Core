package com.github.iamniklas.liocore.network;

import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private NetworkCallback callback;
    private static ArrayList<ClientService> clients = new ArrayList<>();

    @Override
    public void run() {
        super.run();
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
