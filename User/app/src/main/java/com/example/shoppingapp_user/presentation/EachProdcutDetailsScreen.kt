package com.example.shoppingapp_user.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EachProductDetailsScreen( viewModel: MyViewModel, navController: NavController, productId: String)
{

    val state = viewModel.getProductByIdState.collectAsState()

    LaunchedEffect(key1 = Unit)  {

        viewModel.GetProductById(productId)
    }

    when{

        state.value.isLoading -> {
            CircularProgressIndicator()
        }

        state.value.error != null -> {
            Text(text = state.value.error!!)
        }

        state.value.product != null -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Text(text = state.value.product!!.name)
                Text(text = state.value.product!!.price)
                Text(text = state.value.product!!.description)
            }
        }


    }

}