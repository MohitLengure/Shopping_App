package com.example.shoppingapp_user.presentation.Login

import android.graphics.drawable.Icon
import android.media.Image
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppingapp_user.Navigation.SubNavigationItem
import com.example.shoppingapp_user.R


@Composable
fun ForgotPassword(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFDECEC))
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .fillMaxWidth(fraction = 0.9f)
            .fillMaxHeight(fraction = 0.3f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White))
        {
            val email = remember {
                mutableStateOf("")
            }
            val isEmailValid = remember { mutableStateOf(true) }
                   Column(modifier = Modifier
                       .fillMaxSize()
                       , verticalArrangement = Arrangement.Center
                       , horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Row(
                           modifier = Modifier.fillMaxWidth()
                           , horizontalArrangement = Arrangement.Start
                           , verticalAlignment = Alignment.CenterVertically
                       ) {
                           IconButton(onClick = {
                               navController.navigate(SubNavigationItem.LoginScreen)
                           }) {
                               Image(
                                   imageVector = Icons.Default.ArrowBack, // Replace with your back arrow drawable
                                   contentDescription = "Back Button",
                                   modifier = Modifier.size(22.dp),
                                   colorFilter = ColorFilter.tint(Color.Black) // Optional tint
                               )
                           }
                       }

                       Text(
                           text = "Forgot Password"
                           , style = TextStyle(color = Color.Black, fontSize = 18.sp)

                       )

                       OutlinedTextField(
                           value = email.value,
                           onValueChange = {
                               email.value = it
                               isEmailValid.value = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                           },
                           label = {
                               Text(text = "Email")
                           },
                           isError = isEmailValid.value
                           ,   textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
                           , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                       )
                       if ( !isEmailValid.value ) {
                           Text(
                               text = "Invalid email address",
                               color = Color.Red,
                               fontSize = 12.sp,
                               modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                           )
                       }
                       Button(
                           onClick = {

                           }
                       ) {
                           Text(
                               text = "Submit"
                           )
                       }

            }
        }

    }
}