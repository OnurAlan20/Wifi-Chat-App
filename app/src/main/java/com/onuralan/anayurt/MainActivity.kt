package com.onuralan.anayurt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.onuralan.anayurt.navigation.Navigation
import com.onuralan.anayurt.screens.AnaYurtViewModel
import com.onuralan.anayurt.screens.IpScreen


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bcv = AnaYurtViewModel()


        setContent {
            val navController = rememberNavController()
            Navigation(anaYurtViewModel = bcv, navController = navController)

        }

    }



}



