package com.onuralan.anayurt.screens


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.onuralan.anayurt.model.Message
import com.onuralan.anayurt.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.net.Socket
import java.util.*

class AnaYurtViewModel:ViewModel() {

    val sendMessage = mutableStateOf("")

    val messageList = mutableStateListOf<Message>()

    val serverLocalHost = mutableStateOf("")

    lateinit var navController:NavHostController


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
            val connection = Socket(serverLocalHost.value,1001)
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






}