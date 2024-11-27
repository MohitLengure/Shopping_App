package com.example.shoppingapp_user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.shoppingapp_user.presentation.HomeScreen
import com.example.shoppingapp_user.presentation.SignUpScreen
import com.example.shoppingapp_user.presentation.loginScreen
import com.example.shoppingapp_user.ui.theme.ShoppingApp_UserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingApp_UserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                 Box(modifier = Modifier.padding(innerPadding)) {
                     loginScreen()
                 }
                }
            }
        }
    }
}

