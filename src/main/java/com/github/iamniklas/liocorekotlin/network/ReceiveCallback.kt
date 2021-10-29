package com.github.iamniklas.liocorekotlin.network

interface ReceiveCallback {
    fun onReceiveMessage(_message: String?)
}