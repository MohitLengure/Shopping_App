package com.example.shoppingapp_user.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp_user.domain.models.UserData

@Composable
fun loginScreen(viewModel: MyViewModel = hiltViewModel()) {

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    when{
        loginState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                Alignment.Center
            )
            {
                CircularProgressIndicator()
            }
        }
        loginState.error != null -> {
            Toast.makeText(context, loginState.error, Toast.LENGTH_SHORT).show()
        }
        loginState.userdata != null -> {
            Toast.makeText(context, loginState.userdata, Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val email = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = {
                Text(text = "Email")
            }
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = {
                Text(text = "Password")
            }
        )

        Button(
            onClick = {
                viewModel.loginwithemailpassword(
                    email.value,
                    password.value
                )
            }
        ) {
            Text(text ="Login")
        }

    }

}