package com.onuralan.anayurt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.onuralan.anayurt.screens.AnaYurtViewModel
import com.onuralan.anayurt.screens.IpScreen
import com.onuralan.anayurt.screens.MainScreen


@Composable
fun Navigation(anaYurtViewModel: AnaYurtViewModel, navController: NavHostController){

    NavHost(navController = navController, startDestination = Screens.IpScreen.route){
        composable(route = Screens.IpScreen.route ){
            IpScreen(anaYurtViewModel = anaYurtViewModel,navController)
        }
        composable(route =Screens.MainScreen.route ){
            MainScreen(anaYurtViewModel = anaYurtViewModel,navController)
        }
    }
}