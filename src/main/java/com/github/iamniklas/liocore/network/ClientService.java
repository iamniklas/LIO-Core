package com.github.iamniklas.liocore.network;

import java.io.IOException;
import java.net.Socket;

public class ClientService extends Thread implements NetworkCallback {

    public int id = 0;
    public static int nextId = 0;

    public Server server;
    public Socket socket;

    public Sender sender;
    public Receiver receiver;

    public ClientService(Server _server, Socket _socket) {
        id = nextId;
        nextId++;
        server = _server;
        socket = _socket;
        try {
            sender = new Sender(socket.getOutputStream());
            receiver = new Receiver(socket, socket.getInputStream(), this);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        receiver.start();
    }

    @Override
    public void onReceiveMessage(String _message) {
        server.receiveMessage(_message);
    }
}
