package com.example.shoppingapp_admin.UI_Layer.Screen

import android.annotation.SuppressLint
import java.text.DecimalFormat
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.shoppingapp_admin.Navigation.AC
import com.example.shoppingapp_admin.Navigation.AP
import com.example.shoppingapp_admin.domain.models.productDataModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.forEach
import kotlin.math.log


@SuppressLint("StateFlowValueCalledInComposition")
@Composable

fun addProductScreen(navController: NavHostController, viewModel: myviewModels = hiltViewModel()) {

    val addProductState = viewModel.addProduct.collectAsState()
    val context = LocalContext.current

    var menuItemData by remember {
        mutableStateOf(
            viewModel.getAllCategoryState.value.data
        )
    }


    var uploadProductImage = viewModel.uploadProductImageState.collectAsState()

    var getcategory = viewModel.getAllCategoryState.collectAsState()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }

    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var finalPrice by remember {
        mutableStateOf("")
    }
    var category by remember {
        mutableStateOf("")
    }
    var availableUnits by remember {
        mutableStateOf("")
    }
    var isAvailable by remember {
        mutableStateOf("")
    }
    var createdBy by remember {
        mutableStateOf("")
    }
    val decimalFormat = DecimalFormat("#.##")
    var Discount by remember { mutableStateOf("") }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.uploadProductImage(it)
            imageUri = it
            /*viewModel.getAllCategoryState.value.data.also { it -> menuItemData = it }*/
        }
    }

    var isLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        // Wait for 3 seconds, then stop the progress bar
        delay(3000)
        isLoading = false
    }


    LaunchedEffect(addProductState.value.data) {
        if (addProductState.value.data.isNotBlank()) {
            Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_LONG).show()
            name = ""
            description = ""
            price = ""
            finalPrice = ""
            Discount = ""
            category = ""
            availableUnits = ""
            isAvailable = ""
            createdBy = ""
            imageUri = null
            imageUri = null

            viewModel.resetAddProductState()
            viewModel.resetUploadProductImageState()
        }
    }

    when {
        addProductState.value.isLoading || uploadProductImage.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                Log.d("TAG", "Uploading Image or Adding Category...")
            }
        }

        uploadProductImage.value.Success.isNotEmpty() -> {
            imageUrl = uploadProductImage.value.Success
            Log.d("TAG", "Image uploaded successfully: $imageUrl")
        }

        addProductState.value.data.isNotEmpty() -> {
            Toast.makeText(context, "Category Added Successfully", Toast.LENGTH_LONG).show()
            navController.navigate(AP)
            viewModel.resetAddProductState()
            viewModel.resetUploadCategoryImageState()
        }

        addProductState.value.error.isNotEmpty() || uploadProductImage.value.error.isNotEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = addProductState.value.error.ifEmpty { uploadProductImage.value.error })
            }
        }
    }
    Box()
    {
        LazyColumn {

            item {
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
                    Spacer(modifier = Modifier.height(10.dp))

                    if (imageUri != null) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(0.40f)
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center
                        )
                        {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        launcher.launch(
                                            PickVisualMediaRequest(
                                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        launcher.launch(
                                            PickVisualMediaRequest(
                                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                                )
                                Text(text = "Select Image")
                            }
                        }
                    }

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text(text = "Name")
                        }
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        label = {
                            Text(text = "Description")
                        }
                    )

                    OutlinedTextField(
                        value = price,
                        onValueChange = {
                            price = it
                        },
                        label = {
                            Text(text = "Price")
                        }
                    )
                    OutlinedTextField(
                        value = finalPrice,
                        onValueChange = {
                            finalPrice = it
                        },
                        label = {
                            Text(text = "Final Price")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextButton(
                        onClick = {
                            if (price.isNotBlank() && finalPrice.isNotBlank()) {
                                Discount = decimalFormat.format(
                                    discount(
                                        price.toDouble(),
                                        finalPrice.toDouble()
                                    )
                                )
                            } else {
                                Toast.makeText(context, "Please Enter Price", Toast.LENGTH_LONG)
                            }
                        }
                    ) {
                        if (Discount.isNotBlank()) {
                            Text(text = "Discount :  $Discount")
                        } else {
                            Text(text = "Discount Calculating...")
                        }
                    }


                    OutlinedTextField(
                        value = category,
                        onValueChange = {
                            category = it
                        },
                        label = {
                            Text(text = "Category")
                        }
                    )

                    /*   var expanded by remember { mutableStateOf(false) }
                       val selectedCategory = remember { mutableStateOf("") }

                       Box(modifier = Modifier.fillMaxSize().fillMaxWidth(0.5f))
                       {
                           Text(text = "Select Category", modifier = Modifier.clickable {
                               expanded = true
                           })
                           DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

                               menuItemData.forEach {
                                   DropdownMenuItem(
                                       text = { Text(text = it?.name.toString()) },
                                       onClick = {
                                           selectedCategory.value = it?.name.toString()
                                           expanded = false
                                       })

                               }


                           }
                       }
   */

                    OutlinedTextField(
                        value = availableUnits,
                        onValueChange = {
                            availableUnits = it
                        },
                        label = {
                            Text(text = "Available Units")
                        })

                    OutlinedTextField(
                        value = createdBy,
                        onValueChange = {
                            createdBy = it
                        },
                        label = {
                            Text(text = "Created By")
                        })

                    Button(
                        onClick =
                        {

                            if (name.isNotBlank() && description.isNotBlank() && price.isNotBlank() && imageUrl.isNotBlank() && finalPrice.isNotBlank() && Discount.isNotBlank() && category.isNotBlank() && availableUnits.isNotBlank() && createdBy.isNotBlank()) {
                                val data = productDataModel(
                                    name = name,
                                    description = description,
                                    price = price.toDouble(),
                                    finalPrice = finalPrice.toDouble(),
                                    discount = Discount.toDouble(),
                                    category = category,
                                    availableUnits = availableUnits.toInt(),
                                    // isAvailable = isAvailable.value.toBoolean(),
                                    Createdby = createdBy,
                                    image = imageUrl
                                )
                                viewModel.addProduct(data)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please Enter All Details",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    ) {
                        Text("Add Product")
                    }
                }

            }

        }
    }

}

fun discount(price: Double, finalPrice: Double): Double {
    var discountamount = price - finalPrice
    return discountamount / price * 100
}