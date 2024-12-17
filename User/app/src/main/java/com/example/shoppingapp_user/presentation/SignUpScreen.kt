package com.example.shoppingapp_user.presentation

import android.annotation.SuppressLint
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shoppingapp_user.Navigation.Routes
import com.example.shoppingapp_user.Navigation.SubNavigationItem
import com.example.shoppingapp_user.R
import com.example.shoppingapp_user.domain.models.UserData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
@Composable
fun SignUpScreen(navController: NavController,viewModel: MyViewModel = hiltViewModel())
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
}*/


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignUpScreen(navController: NavController,viewModel: MyViewModel = hiltViewModel()) {

    val state = viewModel.registerUserState.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    when {
        state.value.isLoading -> {
            CircularProgressIndicator()
        }

        state.value.userdata != null -> {
            coroutineScope.launch {
                Toast.makeText(context, state.value.userdata, Toast.LENGTH_SHORT).show()
                delay(1000)
                navController.navigate(Routes.RegistrationComplited)
            }
        }

        state.value.error != null -> {
            Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFDECEC)) // Light pink background
    ) {
        // Decorative Circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFFF9AA2),
                radius = size.width * 0.3f,
                center = Offset(x = size.width * 0.95f, y = size.height * 0f)
            )
            drawCircle(
                color = Color(0xFFFF9AA2),
                radius = size.width * 0.3f,
                center = Offset(x = 0f, y = size.height * 0.9f)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val isEmailValid = remember { mutableStateOf(true) }
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
            var passwordvalid = remember {
                mutableStateOf(false)
            }
            val passwordVisible = remember {
                mutableStateOf(true)
            }
            val phone = remember {
                mutableStateOf("")
            }

            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Row (
                modifier = Modifier.fillMaxWidth(0.84f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = firstname.value,
                    onValueChange = {
                        firstname.value = it
                    },
                    label = {
                        Text( text = "First Name")
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp)
                )

                OutlinedTextField(
                    value = lastname.value,
                    onValueChange = {
                        lastname.value = it
                    },
                    label = {
                        Text(text ="Last Name" )
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp))
            }

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                 email.value=it
                    isEmailValid.value = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                label = {  Text(text ="Email" ) },
                isError = isEmailValid.value ,
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            )

            // Error Message
            if ( !isEmailValid.value ) {
                Text(
                    text = "Invalid email address",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    passwordvalid.value = validatePasswordStrength(password.value) == "Strong Password"
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                label = {
                    Text(text ="Password" )
                },
                visualTransformation = if (!passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                    // Toggle icon for visibility
                    val image = if (!passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (!passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            )
           if (password.value!="" && !passwordvalid.value) {
                    Text(
                        text = validatePasswordStrength(password.value),
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
            }

            OutlinedTextField(
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                label = {
                    Text(text ="Phone" )
                }
            )
            if (phone.value!== "" && phone.value.count { it.isDigit()}!=10)
            {
                Text(
                    text = "Phone Number Should 10 Digit",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9AA2),
                    contentColor = Color.White
                ),
                onClick = {
                    if (
                        firstname.value != "" &&
                        lastname.value != "" &&
                        email.value != "" &&
                        password.value != "" &&
                        phone.value != "" &&
                        passwordvalid.value &&
                        phone.value.count { it.isDigit() }==10 &&
                        isEmailValid.value
                    )
                    {
                            val data = UserData(
                                firstName = firstname.value,
                                lastName =  lastname.value,
                                email = email.value,
                                password = password.value,
                                phoneNumber = phone.value
                            )
                            viewModel.registerUser(data)
                    }
                    else
                   {
                       Toast.makeText(context, "Please Check Your Data", Toast.LENGTH_SHORT).show()
                   }
                }

            ) {
                Text(text ="Sign Up")
            }
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Already have an account?", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Login",
                    color = Color(0xFFFF9AA2),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.LoginScreen)
                    }
                )
            }

            // Divider with OR
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f), color = Color.Gray)
                Text(
                    text = "OR",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(modifier = Modifier.weight(1f), color = Color.Gray)

            }
            SocialSignUpButton(
                text = "Log in with Facebook",
                icon = painterResource(id = R.drawable.ic_facebook),
                backgroundColor = Color.White,
                modifier = Modifier.padding(vertical = 7.dp)
            )
            SocialSignUpButton(
                text = "Log in with Google",
                icon = painterResource(id = R.drawable.ic_google),
                backgroundColor = Color.White,
                modifier = Modifier.padding(vertical = 7.dp)
            )


        }



    }


}

@Composable
fun SocialSignUpButton(
    text: String,
    icon: Painter,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /* TODO: Add login logic */ },
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = if (backgroundColor == Color.White) Color.Black else Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = if (backgroundColor == Color.White) Color.Unspecified else Color.White
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = text,
            color = if (backgroundColor == Color.White) Color.Black else Color.White
        )
    }
}


fun validatePasswordStrength(password: String): String {
    if (password.length < 8) return "Password must be at least 8 characters"
    if (password.count { it.isDigit() } < 1) return "Password must have at least 1 number"
    if (password.count { it.isLetter() } < 1) return "Password must have at least 1 letter"
    if (password.count { it.isUpperCase() } < 1) return "Password must have at least 1 capital letter"
    if (password.count { it.isLowerCase() } < 1) return "Password must have at least 1 small letter"
    return "Strong Password"
}