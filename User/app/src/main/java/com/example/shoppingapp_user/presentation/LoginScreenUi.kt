package com.example.shoppingapp_user.presentation

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shoppingapp_user.Navigation.Routes
import com.example.shoppingapp_user.R

/*
@Composable
fun loginScreen(viewModel: MyViewModel = hiltViewModel()) {

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    when {
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFDECEC)) // Light pink background
    ) {
        // Decorative Circles
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFf68b8b),
                radius = size.width * 0.4f,
                center = Offset(x = size.width * 0.95f, y = size.height * 0f)
            )
            drawCircle(
                color = Color(0xFFf68b8b),
                radius = size.width * 0.2f,
                center = Offset(x = size.width * 0.10f, y = size.height * 0.99f)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Login Title
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )
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
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Button(
                    onClick = {
                        viewModel.loginwithemailpassword(
                            email.value,
                            password.value
                        )
                    }
                ) {
                    Text(text = "Login")
                }

            }

        }
    }
*/



@Composable
fun loginScreen(navController: NavController,viewModel: MyViewModel = hiltViewModel(), ) {


    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    when {
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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val email = remember {
                mutableStateOf("")
            }
            val password = remember {
                mutableStateOf("")
            }
            // Login Title
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Email Field
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },  textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Password Field
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },  textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Forgot Password
            Text(
                text = "Forgot Password?",
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 24.dp)
            )

            // Login Button
            Button(
                onClick = {
                    if(email.value.isEmpty() || password.value.isEmpty())
                    {
                       Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        viewModel.loginwithemailpassword(
                            email.value,
                            password.value
                        )
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9AA2),
                    contentColor = Color.White
                )
            )
                {
                Text(text = "Login")
            }

            // Sign Up
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Don't have an account?", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign Up",
                    color = Color(0xFFFF9AA2),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.SignUpScreen)
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

            // Social Login Buttons
            SocialLoginButton(
                text = "Log in with Facebook",
                icon = painterResource(id = R.drawable.ic_facebook),
                backgroundColor = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            SocialLoginButton(
                text = "Log in with Google",
                icon = painterResource(id = R.drawable.ic_google),
                backgroundColor = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun SocialLoginButton(
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
            modifier = Modifier.size(24.dp),
            tint = if (backgroundColor == Color.White) Color.Unspecified else Color.White
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = text,
            color = if (backgroundColor == Color.White) Color.Black else Color.White
        )
    }
}