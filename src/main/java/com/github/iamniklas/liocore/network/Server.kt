package com.github.iamniklas.liocore.network

import java.io.IOException
import java.net.ServerSocket
import java.util.*

class Server(private val mPort: Int) : Thread() {
    var mServerSocket: ServerSocket? = null
    var mCallback: ReceiveCallback? = null
    override fun run() {
        try {
            System.out.println("Network \tINIT \tSTART")
            mServerSocket = ServerSocket(mPort)
            System.out.println("Network \tINIT \tDONE")
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        while (true) {
            try {
                mClients.add(ClientService(this, mServerSocket!!.accept()))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getClient(_id: Int): ClientService {
        return mClients[_id]
    }

    fun setListener(_callback: ReceiveCallback?) {
        mCallback = _callback
    }

    fun receiveMessage(_message: String?) {
        mCallback!!.onReceiveMessage(_message)
    }

    companion object {
        var mClients = ArrayList<ClientService>()
    }
}