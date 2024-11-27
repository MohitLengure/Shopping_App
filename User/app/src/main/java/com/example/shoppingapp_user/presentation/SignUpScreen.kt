package com.example.shoppingapp_user.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp_user.domain.models.UserData


@Composable
fun SignUpScreen(viewModel: MyViewModel = hiltViewModel())
{

    val state = viewModel.registerUserState.collectAsState()
    val context = LocalContext.current

    when{
        state.value.isLoading -> {
            CircularProgressIndicator()
        }
        state.value.userdata != null -> {
            Toast.makeText(context, state.value.userdata, Toast.LENGTH_SHORT).show()
        }
        state.value.error != null -> {
            Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val firstname = remember {
            mutableStateOf("")
        }
        val lastname = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val phone = remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = firstname.value,
            onValueChange = {
                firstname.value = it
            },
            label = {
                Text( text = "First Name")
            }
        )

        OutlinedTextField(
            value = lastname.value,
            onValueChange = {
                lastname.value = it
            },
            label = {
                Text(text ="Last Name" )
            }
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = {
                Text(text ="Email" )
            }
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = {
                Text(text ="Password" )
            }
        )

        OutlinedTextField(
            value = phone.value,
            onValueChange = {
                phone.value = it
                },
            label = {
               Text(text ="Phone" )
            }
        )

        Button(
            onClick = {
                val data = UserData(
                    firstName = firstname.value,
                    lastName =  lastname.value,
                    email = email.value,
                    password = password.value,
                    phoneNumber = phone.value
                )
                viewModel.registerUser(data)
            }
        ) {
            Text(text ="Sign Up")
        }

    }
}