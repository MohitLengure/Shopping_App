package com.example.shopping_app.UI_Layer.Screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopping_app.Common.ResultState
import com.example.shopping_app.domain.models.Category
import com.example.shopping_app.domain.models.productDataModel
import com.example.shopping_app.domain.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class myviewModels @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _addCategory = MutableStateFlow(addCategoryState())
    val addCategory = _addCategory.asStateFlow()

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

}



data class addCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)

data class addProductState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)

