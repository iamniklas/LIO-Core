package com.github.iamniklas.liocore.network

interface ReceiveCallback {
    fun onReceiveMessage(_message: String?)
}