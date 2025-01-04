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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.shoppingapp_admin.Navigation.AP
import com.example.shoppingapp_admin.domain.models.Category
import kotlinx.coroutines.delay

@Composable
fun addCategoryScreen(navController: NavHostController, viewModel: myviewModels = hiltViewModel()) {

    var addCategoryState = viewModel.addCategory.collectAsState()
    val context = LocalContext.current

    var uploadImage = viewModel.uploadProductImageState.collectAsState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }

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

    when {
        addCategoryState.value.isLoading || uploadImage.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                    /*viewModel.getAllCategoryState.value.data.forEach {
                        menuItemData.value = it?.name.toString()
                    }*/
                } else {
                    Toast.makeText(context, "Successfull ", Toast.LENGTH_LONG).show()
                }
            }
        }

        addCategoryState.value.error != null || uploadImage.value.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            )
            {
                Text(text = addCategoryState.value.error)
            }
        }

        addCategoryState.value.data != null -> {
            Toast.makeText(context, "Successfull Add", Toast.LENGTH_LONG).show()
            viewModel.resetcategory()
            //viewModel.resetUploadImageState()
        }

        uploadImage.value.Success != null -> {
            imageUrl = uploadImage.value.Success
            viewModel.resetUploadImageState()
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = {
                navController.navigate(AP)
            }
        ) {
            Text("Add Product Screen")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var categoryName by remember {
            mutableStateOf("")
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
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                launcher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                        )
                        Text(text = "Select Image")
                    }
                }
            }

            OutlinedTextField(
                value = categoryName,
                onValueChange = {
                    categoryName= it
                },
                label = {
                    Text(text = "Category Name")
                }
            )



           /* OutlinedTextField(
                value = categoryImageUrl.value,
                onValueChange = {
                    categoryImageUrl.value = it
                },
                label = {
                    Text(text = "Category Image Url")
                }
            )*/

            Button(
                onClick =
                {
                    val data = Category(
                        name = categoryName,
                        imageUrl = imageUri.toString()
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
}