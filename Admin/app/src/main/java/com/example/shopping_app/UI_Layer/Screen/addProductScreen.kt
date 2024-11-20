package com.example.shopping_app.UI_Layer.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import com.example.shopping_app.Navigation.AC
import com.example.shopping_app.Navigation.AP
import com.example.shopping_app.domain.models.productDataModel

@Composable

fun addProductScreen(navController: NavHostController,viewModel: myviewModels = hiltViewModel()) {
    val state = viewModel.addProduct.collectAsState()
    val context = LocalContext.current

    when {
        state.value.isLoading -> {
            Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
        }

        state.value.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            )
            {
                Text(text = state.value.error)
            }
        }

        state.value.data != null -> {
            Toast.makeText(context, "Successfull Add", Toast.LENGTH_LONG).show()

        }
    }

    Row (modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Start
    ){
        Button(
            onClick = {
                navController.navigate(AC)
            }
        ) {
            Text("Add Category Screen")
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        val name = remember {
            mutableStateOf("")
        }
        val description = remember {
            mutableStateOf("")
        }
        val price = remember {
            mutableStateOf("")
        }
        val finalPrice = remember {
            mutableStateOf("")
        }
        val discount = remember {
            mutableStateOf("")
        }
        val image = remember {
            mutableStateOf("")
        }
        val category = remember {
            mutableStateOf("")
        }
        val availableUnits = remember {
            mutableStateOf("")
        }
        val isAvailable = remember {
            mutableStateOf("")
        }
        val createdBy = remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            label = {
                Text(text = "Name")
            }
        )
        OutlinedTextField(
            value = description.value,
            onValueChange = {
                description.value = it
            },
            label = {
                Text(text = "Description")
            }
        )

        OutlinedTextField(
            value = price.value,
            onValueChange = {
                price.value = it
            },
            label = {
                Text(text = "Price")
            }
        )
        OutlinedTextField(
            value = finalPrice.value,
            onValueChange = {
                finalPrice.value = it
            },
            label = {
                Text(text = "Final Price")
            }
        )
        OutlinedTextField(
            value = discount.value,
            onValueChange = {
                discount.value = it
            },
            label = {
                Text(text = "Discount")
            })

        OutlinedTextField(
            value = category.value,
            onValueChange = {
                category.value = it
            },
            label = {
                Text(text = "Category")
            }
        )
        OutlinedTextField(
            value = image.value,
            onValueChange = {
                image.value = it
            },
            label = {
                Text(text = "Image")
            })
        OutlinedTextField(
            value = availableUnits.value,
            onValueChange = {
                availableUnits.value = it
            },
            label = {
                Text(text = "Available Units")
            })
        OutlinedTextField(
            value = isAvailable.value,
            onValueChange = {
                isAvailable.value = it
            },
            label = {
                Text(text = "Is Available")
            })
        OutlinedTextField(
            value = createdBy.value,
            onValueChange = {
                createdBy.value = it
            },
            label = {
                Text(text = "Created By")
            })

        Button(
            onClick =
            {
                val data= productDataModel(
                    name = name.value,
                    description = description.value,
                    price = price.value,
                    finalPrice = finalPrice.value,
                    discount = discount.value.toInt(),
                    category = category.value,
                    image = image.value,
                    availableUnits = availableUnits.value.toInt(),
                    isAvailable = isAvailable.value.toBoolean(),
                    Createdby = createdBy.value
                )
                viewModel.addProduct(
                    data
                )

            }
        ) {
            Text("Add Product")
        }

    }

}




