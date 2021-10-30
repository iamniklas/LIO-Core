package com.github.iamniklas.liocore.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receiver extends Thread {

    private DataInputStream dataInputStream;
    private NetworkCallback callback;
    private Socket socket;

    public Receiver(Socket _socket, InputStream _inputStream, NetworkCallback _callback) {
        socket = _socket;
        dataInputStream = new DataInputStream(_inputStream);
        callback = _callback;
    }

    @Override
    public synchronized void run() {
        while (!socket.isClosed()) {
            try {
                String buffer = "";
                int incomingSize = dataInputStream.readByte();
                String request = dataInputStream.readUTF();
                callback.onReceiveMessage(request);
            } catch (IOException ignored) {
                try {
                    socket.close();
                }
                catch (IOException ignored2) {}
            }
        }
    }
}
