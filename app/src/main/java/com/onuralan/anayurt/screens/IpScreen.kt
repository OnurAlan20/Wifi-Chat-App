package com.onuralan.anayurt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.onuralan.anayurt.R
import com.onuralan.anayurt.components.MyIpButton
import com.onuralan.anayurt.components.MyIpTextField
import com.onuralan.anayurt.navigation.Screens

@Composable
fun IpScreen(anaYurtViewModel: AnaYurtViewModel,navController: NavController){

    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.dark_gray)) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MyIpTextField(value = anaYurtViewModel.serverLocalHost)
            Spacer(modifier = Modifier.height(15.dp))
            MyIpButton {
                if (anaYurtViewModel.serverLocalHost.value != ""&& anaYurtViewModel.serverLocalHost.value.length >= 8){
                    navController.navigate(Screens.MainScreen.route)
                }

            }


        }
        
        
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultIpScreen(){
}
