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
import com.example.shoppingapp_user.Navigation.App
import com.example.shoppingapp_user.ui.theme.ShoppingApp_UserTheme
import com.google.firebase.auth.FirebaseAuth
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
                     App(firebaseAuth = FirebaseAuth.getInstance())
                 }
                }
            }
        }
    }
}

