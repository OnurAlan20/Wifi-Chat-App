package com.onuralan.anayurt.screens


import android.content.Context
import android.net.wifi.WifiManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.onuralan.anayurt.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.logging.Logger.global

class AnaYurtViewModel:ViewModel() {


    val sendMessage = mutableStateOf("")

    val messageList = mutableStateListOf<Message>()

    val serverLocalHostPort = mutableStateOf("")

    val localHostIp:MutableState<String> = mutableStateOf("")

    private lateinit var serverSocket: ServerSocket
    private lateinit var socket: Socket







    fun startServer() {
        CoroutineScope(Dispatchers.IO).launch {
            serverSocket = ServerSocket(serverLocalHostPort.value.toInt())
            socket = serverSocket.accept()
            while (true) {
                connectFun(socket)
            }
        }
    }

    suspend fun connectFun(socket:Socket){
         withContext(Dispatchers.IO){
            val scanner = Scanner(socket.getInputStream())
            while (scanner.hasNextLine()){
                val a = scanner.nextLine()
                messageList.add(Message("Computer",a))
                break
            }

        }
    }


    @Suppress("DEPRECATION")
    fun getWifiIPv4Address(context: Context): String {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ipAddress = wifiManager.connectionInfo.ipAddress
        val ipAddressString = String.format(
            "%d.%d.%d.%d",
            ipAddress and 0xff,
            ipAddress shr 8 and 0xff,
            ipAddress shr 16 and 0xff,
            ipAddress shr 24 and 0xff
        )
        return ipAddressString
    }

    suspend fun sendMessageFun(){
        withContext(Dispatchers.IO){
            messageList.add(Message("Android",sendMessage.value))
            socket.getOutputStream().write(sendMessage.value.toByteArray())
        }
    }


}