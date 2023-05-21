package com.onuralan.anayurt

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.onuralan.anayurt.navigation.Navigation
import com.onuralan.anayurt.screens.AnaYurtViewModel
import com.onuralan.anayurt.screens.IpScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.net.NetworkInterface


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bcv = AnaYurtViewModel()
        GlobalScope.launch {
            bcv.localHostIp.value= bcv.getWifiIPv4Address(applicationContext)
        }

        setContent {
            val navController = rememberNavController()
            Navigation(anaYurtViewModel = bcv, navController = navController)
        }
    }
}



