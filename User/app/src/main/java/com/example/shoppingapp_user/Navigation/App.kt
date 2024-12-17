package com.example.shoppingapp_user.Navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shoppingapp_user.presentation.EachProductDetailsScreen
import com.example.shoppingapp_user.presentation.HomeScreen
import com.example.shoppingapp_user.presentation.MyViewModel
import com.example.shoppingapp_user.presentation.RegistrationComplited
import com.example.shoppingapp_user.presentation.SignUpScreen
import com.example.shoppingapp_user.presentation.loginScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun App(firebaseAuth : FirebaseAuth) {

    val navController = rememberNavController()

    val appViewModel: MyViewModel = hiltViewModel()


    val startScreen = if (firebaseAuth.currentUser == null) {
        SubNavigationItem.LoginScreen
    } else {
        SubNavigationItem.HomeScreen
    }

    NavHost(navController, startDestination = startScreen) {


        navigation<SubNavigationItem.LoginScreen>(startDestination = Routes.LoginScreen)
        {
            composable<Routes.LoginScreen> {
                loginScreen(navController = navController)
            }


            composable<Routes.EachProductDetailsScreen> {
                val data = it.toRoute<Routes.EachProductDetailsScreen>()

                EachProductDetailsScreen(
                    viewModel = appViewModel,
                    navController = navController,
                    productId = data.productId
                )

            }

        /*    composable<Routes.RegistrationComplited> {
                RegistrationComplited(navController = navController)
            }
            composable<Routes.HomeScreen> {
                HomeScreen(navController = navController, viewModel = appViewModel)

            }*/

        }
        navigation<SubNavigationItem.HomeScreen>(startDestination = Routes.HomeScreen)
        {
            composable<Routes.HomeScreen> {
                HomeScreen(navController = navController, viewModel = appViewModel)
            }
        }

        composable<Routes.RegistrationComplited> {
            RegistrationComplited(navController = navController)
        }
        composable<Routes.SignUpScreen> {
            SignUpScreen(navController = navController)
        }
    }
}

/*
data class BottomNavItem(val name :String, val icon : ImageVector)


@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
   Box(
       modifier = Modifier.fillMaxWidth().height(70.dp).background(Color.Green , RoundedCornerShape(8.dp))
   )
   {



   }
}

*/
