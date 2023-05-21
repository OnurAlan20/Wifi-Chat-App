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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ServerSocket
import java.net.Socket
import java.util.*

class AnaYurtViewModel:ViewModel() {


    val sendMessage = mutableStateOf("")

    val messageList = mutableStateListOf<Message>()

    val serverLocalHostPort = mutableStateOf("")

    val localHostIp:MutableState<String> = mutableStateOf("")

    private val _serverSocket = MutableLiveData<ServerSocket?>()
    val serverSocket: LiveData<ServerSocket?> = _serverSocket



    fun startServer() {
        viewModelScope.launch {
            val serverSocket = ServerSocket(serverLocalHostPort.value.toInt())
            _serverSocket.postValue(serverSocket)
            while (true) {
                connectFun(serverSocket)
            }
        }
    }

    suspend fun connectFun(serverSocket:ServerSocket){
         withContext(Dispatchers.IO){
             val socket = serverSocket.accept()
            val scanner = Scanner(socket.getInputStream())
            while (scanner.hasNextLine()){
                val a = scanner.nextLine()
                messageList.add(Message("Computer",a))
                break
            }
            socket.close()
        }
    }
    suspend fun sendMessageFun(){
        withContext(Dispatchers.IO){

            val socket = serverSocket.value!!.accept()
            socket.getOutputStream().write(sendMessage.value.toByteArray())
            socket.close()

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



    /*
    suspend fun  sendMessageFun(){
        withContext(Dispatchers.IO){
            val connection = Socket(serverLocalHost.value,1000)
            val writer: OutputStream = connection.getOutputStream()
            writer.write(sendMessage.value.toByteArray())
            messageList.add(Message("Android",sendMessage.value))
            connection.close()
        }
    }
    suspend fun listenMessageFun(){
        withContext(Dispatchers.IO){
            val connection = Socket(serverLocalHost.value,1000)
            val scanner = Scanner(connection.getInputStream())
            while (scanner.hasNextLine()){
                val getMessage = scanner.nextLine()
                messageList.add(Message("Computer",getMessage))
                break
            }
            connection.close()
            listenMessageFun()
        }
    }

     */
    }