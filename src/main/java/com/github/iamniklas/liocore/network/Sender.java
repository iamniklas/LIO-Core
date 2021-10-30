package com.github.iamniklas.liocore.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Sender extends Thread {

    private DataOutputStream outputStream;

    public Sender(OutputStream _outStream) {
        outputStream = new DataOutputStream(_outStream);
    }

    public void send(String _message) {
        try {
            outputStream.writeUTF(_message);
        }
        catch (IOException ignored) {}
    }
}
