package com.example.shopping_app.UI_Layer.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavHostController
import com.example.shopping_app.Common.ResultState
import com.example.shopping_app.Navigation.AP
import com.example.shopping_app.domain.models.Category

@Composable
fun addCategoryScreen(navController: NavHostController, viewModel: myviewModels = hiltViewModel()) {

    val state = viewModel.addCategory.collectAsState()
    val context = LocalContext.current

    when {
        state.value.isLoading -> {
            Toast.makeText(context,"Loading",Toast.LENGTH_LONG).show()
        }

        state.value.error != null -> {
            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            )
            {
                Text(text = state.value.error)
            }
        }

        state.value.data != null -> {
           Toast.makeText(context,"Successfull Add",Toast.LENGTH_LONG).show()

        }
    }

    Row (modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Start
        ){
        Button(
            onClick = {
                navController.navigate(AP)
            }
        ) {
            Text("Add Product Screen")
        }
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val categoryName = remember {
            mutableStateOf("")
        }
        val categoryImageUrl = remember {
            mutableStateOf("")

        }

        OutlinedTextField(
            value = categoryName.value,
            onValueChange = {
                categoryName.value = it
            },
            label = {
                Text(text = "Category Name")
            }
        )
        OutlinedTextField(
            value = categoryImageUrl.value,
            onValueChange = {
                categoryImageUrl.value = it
            },
            label = {
                Text(text = "Category Image Url")
            }
        )

        Button(
            onClick =
            {
                val data= Category(
                    name = categoryName.value,
                    imageUrl = categoryImageUrl.value
                )
                viewModel.addCategory(
                    data
                )
            }
        ) {
            Text("Add Category")
        }
    }


}