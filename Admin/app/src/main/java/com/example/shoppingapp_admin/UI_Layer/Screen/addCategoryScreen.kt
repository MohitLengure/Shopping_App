package com.example.shoppingapp_admin.UI_Layer.Screen

import android.net.Uri
import android.util.Log
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

@Composable
fun addCategoryScreen(navController: NavHostController, viewModel: myviewModels = hiltViewModel()) {

    val addCategoryState = viewModel.addCategorystate.collectAsState()
    val uploadImage = viewModel.uploadCategoryImage_state.collectAsState()
    val context = LocalContext.current

    var categoryName by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null)
        {
            viewModel.uploadCategoryImage(it)
            imageUri = it
            /*viewModel.getAllCategoryState.value.data.also { it -> menuItemData = it }*/
        }
        else{
            Toast.makeText(context,"Please Select Image",Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(addCategoryState.value.data) {
        if (addCategoryState.value.data.isNotBlank()) {
            Toast.makeText(context, "Category Added Successfully", Toast.LENGTH_LONG).show()
            categoryName = ""
            imageUri = null
            imageUrl = ""

            viewModel.resetAddCategoryState()
            viewModel.resetUploadCategoryImageState()
        }
    }
    when {
        addCategoryState.value.isLoading || uploadImage.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uploadImage.value.Success.isNotEmpty() -> {
            imageUrl = uploadImage.value.Success
            Log.d("TAG", "Image uploaded successfully: $imageUrl")
        }

        addCategoryState.value.data.isNotEmpty()-> {
            Toast.makeText(context, "Category Added Successfully", Toast.LENGTH_LONG).show()
/*
            LaunchedEffect(Unit) {
                categoryName = ""
                imageUri = null
                imageUrl = ""
            }


            viewModel.resetAddCategoryState()
            viewModel.resetUploadCategoryImageState()*/
        }


        addCategoryState.value.error.isNotEmpty() || uploadImage.value.error.isNotEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = addCategoryState.value.error.ifEmpty { uploadImage.value.error })
            }
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
                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                launcher.launch(
                                    PickVisualMediaRequest(
                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
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
                onClick = {
                    if (categoryName.isBlank()) {
                        Toast.makeText(context, "Please enter a category name", Toast.LENGTH_SHORT).show()
                    } else if (imageUri == null) {
                        Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
                    } else if (imageUrl.isBlank()) {
                        Toast.makeText(context, "Please wait for the image to upload", Toast.LENGTH_SHORT).show()
                    } else {
                        val data = Category(
                            name = categoryName,
                            imageUrl = imageUrl
                        )
                        viewModel.addCategory(data)
                        Toast.makeText(context, "Category added successfully!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Add Category")
            }


        }
    }
}