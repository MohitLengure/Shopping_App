package com.example.shoppingapp_admin.UI_Layer.Screen

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp_admin.Common.ResultState
import com.example.shoppingapp_admin.domain.models.Category
import com.example.shoppingapp_admin.domain.models.productDataModel
import com.example.shoppingapp_admin.domain.repo.Repo
import com.example.shoppingapp_admin.domain.usercase.GetAllCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class myviewModels @Inject constructor(
    private val GetAllCategory: GetAllCategoryUseCase,
    private val repo: Repo
) : ViewModel() {

    private val _addCategory = MutableStateFlow(addCategoryState())
    val addCategorystate = _addCategory.asStateFlow()

    init {
        suspend {
            getAllCategory()
        }
    }

 /*   fun fetchcategory()
    {
        viewModelScope.launch(Dispatchers.IO) {
            GetAllCategory.getAllCategory().collectLatest {
                when(it)
                {
                    is ResultState.Loading -> {
                        _addCategory.value = addCategoryState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _addCategory.value = addCategoryState(data = it.data)
                    }
                    is ResultState.Error -> {
                        _addCategory.value = addCategoryState(error = it.error)
            }
        }
    }
*/
    fun addCategory(category: Category){
        viewModelScope.launch {
            repo.addCategory(category).collectLatest {
                when(it)
                {
                    is ResultState.Loading -> {
                        _addCategory.value = addCategoryState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _addCategory.value = addCategoryState(data = it.data)
                    }
                    is ResultState.Error -> {
                        _addCategory.value = addCategoryState(error = it.error)
                    }
                }
            }
        }
    }


    //Add Product

    private val _addProduct = MutableStateFlow(addProductState())
    val addProduct = _addProduct.asStateFlow()

    fun addProduct(productmodels: productDataModel)
    {
        viewModelScope.launch {
            repo.addProduct(productmodels).collectLatest {
                when(it)
                {
                    is ResultState.Loading -> {
                        _addProduct.value = addProductState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _addProduct.value = addProductState(data = it.data)
                }
                    is ResultState.Error -> {
                        _addProduct.value = addProductState(error = it.error)
                    }
                }
            }
        }
    }

    private val _uploadProductImage = MutableStateFlow(UploadProductImageState())
    val uploadProductImageState = _uploadProductImage.asStateFlow()

    fun resetUploadProductImageState(){
        _uploadProductImage.value = UploadProductImageState()
    }
    fun resetUploadCategoryImageState(){
        _uploadCategoryImageState.value = UploadCategoryImageState()
    }
    fun resetAddProductState(){
        _addProduct.value = addProductState()
    }
    fun resetAddCategoryState(){
        _addCategory.value= addCategoryState()
    }

    fun uploadProductImage(imageUri: Uri)
    {
        viewModelScope.launch {
            repo.UploadProductimage(image = imageUri).collectLatest {
                when(it){
                    is ResultState.Error ->{
                        _uploadProductImage.value = UploadProductImageState(error = it.error)
                    }
                    is ResultState.Loading ->{
                        _uploadProductImage.value = UploadProductImageState(isLoading = true)
                    }
                    is ResultState.Success ->{
                        _uploadProductImage.value = UploadProductImageState(Success = it.data)
                    }
                }
            }

        }

    }

    private val _uploadCategoryImageState = MutableStateFlow(UploadCategoryImageState())
    val uploadCategoryImage_state = _uploadCategoryImageState.asStateFlow()

    fun uploadCategoryImage(imageUri: Uri)
    {
        viewModelScope.launch {
            repo.UploadCategoryimage(image = imageUri).collectLatest {
                when(it){
                    is ResultState.Error ->{
                        _uploadCategoryImageState.value = UploadCategoryImageState(error = it.error)
                    }
                    is ResultState.Loading ->{
                        _uploadCategoryImageState.value = UploadCategoryImageState(isLoading = true)
                    }
                    is ResultState.Success ->{
                        Log.d(
                            "TAG2",
                            "uploadCategoryImage: ${it.data}"
                        )
                        _uploadCategoryImageState.value = UploadCategoryImageState(Success = it.data)
                    }

                }
            }

        }

    }


    private val _getAllCategoryState = MutableStateFlow(GetCategoryState())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()
    fun getAllCategory() {
        viewModelScope.launch {
            GetAllCategory.getAllCategory().collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        _getAllCategoryState.value = GetCategoryState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getAllCategoryState.value = GetCategoryState(data = it.data)
                    }

                    is ResultState.Error -> {
                        _getAllCategoryState.value = GetCategoryState(error = it.error)
                    }

                    else -> {
                        _getAllCategoryState.value = GetCategoryState(error = "Unknown Error")
                    }
                }


            }
        }
    }


}



data class addCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)

data class addProductState(
    var isLoading: Boolean = false,
    var error: String = "",
    var data: String = ""
)
data class UploadProductImageState(
    var isLoading: Boolean = false,
    var Success: String = "",
    var error: String = ""
)
data class UploadCategoryImageState(
    var isLoading: Boolean = false,
    var Success: String = "",
    var error: String = ""
)

data class GetCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Category?> = emptyList()
)
