package com.onuralan.anayurt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.onuralan.anayurt.R
import com.onuralan.anayurt.components.MyMessageCard
import com.onuralan.anayurt.components.MyChatTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(anaYurtViewModel: AnaYurtViewModel,navController: NavController){
    val coroutineScope:CoroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            anaYurtViewModel.listenMessageFun()
        }
    }
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.dark_gray)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LazyColumn {
                    items(items=anaYurtViewModel.messageList) { item ->
                        MyMessageCard(message = item)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                MyChatTextField(value = anaYurtViewModel.sendMessage) {
                    if(anaYurtViewModel.sendMessage.value != ""){
                        CoroutineScope(Dispatchers.IO).launch {

                              anaYurtViewModel.sendMessageFun()
                              anaYurtViewModel.sendMessage.value =""


                          }


                    }



                }

                
            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultMainScreen(){
}