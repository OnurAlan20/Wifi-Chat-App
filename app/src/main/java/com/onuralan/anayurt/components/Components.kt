package com.onuralan.anayurt.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onuralan.anayurt.R
import com.onuralan.anayurt.screens.AnaYurtViewModel
import com.onuralan.anayurt.model.Message

@Composable
fun MyChatTextField(value:MutableState<String>, onClick: () -> Unit){


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value.value,
        placeholder = { Text(text = "Enter Your Message") },
        onValueChange = {
            value.value = it
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.black),
            cursorColor = Color.Red,
            focusedBorderColor = colorResource(id = R.color.light_blue),
            unfocusedBorderColor = colorResource(id = R.color.light_blue),
            backgroundColor = colorResource(id = R.color.white)

        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Trailing Icon",
                modifier = Modifier.clickable {
                    onClick()
                },
                tint = colorResource(id = R.color.light_blue)
            )
        }

    )


}
@Composable
fun MyIpTextField(value:MutableState<String>){


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value.value,
        placeholder = { Text(text = "Enter Local Host Ip") },
        onValueChange = {
            value.value = it
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.black),
            cursorColor = Color.Red,
            focusedBorderColor = colorResource(id = R.color.light_blue),
            unfocusedBorderColor = colorResource(id = R.color.light_blue),
            backgroundColor = colorResource(id = R.color.white)

        ),
         leadingIcon = {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Leading Icon",
                tint = colorResource(id = R.color.light_blue)
            )
        }

    )


}
@Composable
fun MyIpButton(onClick: () -> Unit){
    Button(onClick = { onClick() },
        modifier = Modifier.fillMaxWidth()
        ,
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.light_blue)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(text ="Enter Your Host Ip", fontSize = 25.sp, color = colorResource(id = R.color.dark_gray) )
    }

}

@Composable
fun MyMessageCard(message: Message){
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = colorResource(id = R.color.card_background),
        shape = RoundedCornerShape(12.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text =message.user, fontSize = 20.sp, color = colorResource(id = R.color.user_name) )
            Text(text =message.message, fontSize = 18.sp, color = colorResource(id = R.color.user_message) )
        }
        
    }
}

@Preview
@Composable
fun Default(){
    MyMessageCard(AnaYurtViewModel().messageList[0])
}