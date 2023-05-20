package com.onuralan.anayurt.navigation

sealed class Screens(val route: String){
    object MainScreen: Screens("main_screen")
    object IpScreen: Screens("register_screen")
}
