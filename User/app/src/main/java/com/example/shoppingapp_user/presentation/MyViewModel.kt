package com.example.shoppingapp_user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping_app_user.domain.useCase.getProductsInLimitUseCase
import com.example.shoppingapp_user.ResultState
import com.example.shoppingapp_user.domain.models.Category
import com.example.shoppingapp_user.domain.models.ProductModels
import com.example.shoppingapp_user.domain.models.UserData
import com.example.shoppingapp_user.domain.useCase.GetAllCategoryUseCase
import com.example.shoppingapp_user.domain.useCase.GetAllProducts
import com.example.shoppingapp_user.domain.useCase.GetProductById
import com.example.shoppingapp_user.domain.useCase.LoginWithEmailPassUseCase
import com.example.shoppingapp_user.domain.useCase.RegisterUserWithEmailPassUseCase
import com.example.shoppingapp_user.domain.useCase.getCategoryinlimit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(
    private val GetAllCategory: GetAllCategoryUseCase,
    private val getCategoryInLimit: getCategoryinlimit,
    private val getAllProducts: GetAllProducts,
    private val getProductsInLimitUseCase: getProductsInLimitUseCase,
    private val registerUserWithEmailPassUseCase : RegisterUserWithEmailPassUseCase,
    private val loginWithEmailPassUseCase: LoginWithEmailPassUseCase,
    private val GetProductById : GetProductById
) : ViewModel() {

    private val _getAllCategoryState = MutableStateFlow(GetCategoryState())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()

    private val _getAllProductState = MutableStateFlow(GetAllProductsState())
    val getAllProductState = _getAllProductState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

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


    init {

        loadHomeScreenData()

    }

    fun loadHomeScreenData() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                getCategoryInLimit.getcategoryinlimit(),
                getProductsInLimitUseCase.getproductsinlimit(),
            ) { categoriesResult, productResult ->
                when {
                    categoriesResult is ResultState.Error ->
                        HomeScreenState(isLoading = false, errorMessage = categoriesResult.error)

                    productResult is ResultState.Error ->
                        HomeScreenState(isLoading = false, errorMessage = productResult.error)

                    categoriesResult is ResultState.Success &&
                            productResult is ResultState.Success
                        ->
                        HomeScreenState(
                            isLoading = false,
                            categories = categoriesResult.data,
                            products = productResult.data
                        )

                    else -> HomeScreenState(isLoading = true)
                }

            }.collect { state ->
                _homeScreenState.value = state
            }

        }
    }

    private val _registerUserState = MutableStateFlow(RegisterUserState())
    val registerUserState = _registerUserState.asStateFlow()

    fun registerUser(userData: UserData) {
        viewModelScope.launch {
            registerUserWithEmailPassUseCase.registerUserusecase(userData).collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        _registerUserState.value = RegisterUserState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _registerUserState.value = RegisterUserState(userdata = it.data)
                    }

                    is ResultState.Error -> {
                        _registerUserState.value = RegisterUserState(error = it.error)
                    }

                    else -> {
                        _registerUserState.value = RegisterUserState(error = "Unknown Error")
                    }
                }
            }

        }

    }

    private val _loginWithEmailPassState = MutableStateFlow(LoginWithEmailPassState())
    val loginState = _loginWithEmailPassState.asStateFlow()

    fun loginwithemailpassword(userEmail: String, userPassword: String) {
        viewModelScope.launch {
            loginWithEmailPassUseCase.loginWithEmailPassUseCase(userEmail, userPassword)
                .collectLatest {
                    when (it) {
                        is ResultState.Loading -> {
                            _loginWithEmailPassState.value =
                                LoginWithEmailPassState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            _loginWithEmailPassState.value =
                                LoginWithEmailPassState(userdata = it.data)
                        }

                        is ResultState.Error -> {
                            _loginWithEmailPassState.value =
                                LoginWithEmailPassState(error = it.error)
                        }

                        else -> {
                            _loginWithEmailPassState.value =
                                LoginWithEmailPassState(error = "Unknown Error")
                        }
                    }
                }

        }
    }

    private val _GetProductByIdState = MutableStateFlow(GetProductByIdState())
    val getProductByIdState = _GetProductByIdState.asStateFlow()

    fun GetProductById(productId: String) {
        viewModelScope.launch {
            GetProductById.getProductsByIDUseCase(productId).collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        _GetProductByIdState.value = GetProductByIdState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _GetProductByIdState.value = GetProductByIdState(product = it.data)
                    }
                    is ResultState.Error -> {
                        _GetProductByIdState.value = GetProductByIdState(error = it.error)
                    }
                    else -> {
                        _GetProductByIdState.value = GetProductByIdState(error = "Unknown Error")
                }
            }
        }
    }
}
}

data class GetCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Category?> = emptyList()
)

data class GetAllProductsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<ProductModels?> = emptyList()
)

data class HomeScreenState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val categories: List<Category>? = null,
    val products: List<ProductModels>? = null
)
data class RegisterUserState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userdata: String? = null
)
data class LoginWithEmailPassState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userdata: String? = null
)
data class GetProductByIdState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val product: ProductModels? = null
)