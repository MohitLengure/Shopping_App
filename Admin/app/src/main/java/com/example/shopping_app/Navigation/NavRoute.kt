package com.example.shopping_app.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopping_app.UI_Layer.Screen.addCategoryScreen
import com.example.shopping_app.UI_Layer.Screen.addProductScreen
import com.example.shopping_app.UI_Layer.Screen.myviewModels


@Composable
fun App()
{
    val navController = rememberNavController()
    val appViewModel: myviewModels = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = AC
    ) {
        composable<AC> {
            addCategoryScreen(navController,appViewModel)
        }
        composable<AP> {
            addProductScreen(navController,appViewModel)
        }
    }

}