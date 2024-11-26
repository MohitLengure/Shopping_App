package com.example.shoppingapp_admin.UI_Layer.Screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.shoppingapp_admin.Navigation.AC
import com.example.shoppingapp_admin.domain.models.productDataModel

@Composable

fun addProductScreen(navController: NavHostController, viewModel: myviewModels = hiltViewModel()) {
    val state = viewModel.addProduct.collectAsState()
    val context = LocalContext.current

    val uploadProductImage = viewModel.uploadImage.collectAsState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.uploadProductImage(it)
            imageUri = it

        }
    }

    when {
        state.value.isLoading  || uploadProductImage.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        state.value.error != null || uploadProductImage.value.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            )
            {
                Text(text = state.value.error)
            }
        }
        uploadProductImage.value.data != null -> {
            imageUrl = uploadProductImage.value.data
            viewModel.resetUploadImageState()
        }
        state.value.data != null -> {
            Toast.makeText(context, "Successfull Add", Toast.LENGTH_LONG).show()
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Start
    ) {
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

        if(imageUri != null)
        {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                ,contentScale = ContentScale.Crop
            )
        }
        else
        {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                , contentAlignment = Alignment.Center
            )
            {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize().clickable {
                       launcher.launch(
                           PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                       )
                    }
                ){

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.clickable {

                    }
                )
                Text(text = "Select Image")
            }
            }
        }
        LazyColumn {
            item {

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
                        if (name != null && description != null && price != null && finalPrice != null && discount != null && category != null && availableUnits != null && isAvailable != null && createdBy != null) {
                            val data = productDataModel(
                                name = name.value,
                                description = description.value,
                                price = price.value,
                                finalPrice = finalPrice.value,
                                discount = discount.value.toInt(),
                                category = category.value,
                                availableUnits = availableUnits.value.toInt(),
                                isAvailable = isAvailable.value.toBoolean(),
                                Createdby = createdBy.value,
                                image = imageUrl
                            )

                            viewModel.addProduct(
                                data
                            )
                        } else {
                            Toast.makeText(context, "Please Enter All Details", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                ) {
                    Text("Add Product")
                }
            }

        }


    }


}

