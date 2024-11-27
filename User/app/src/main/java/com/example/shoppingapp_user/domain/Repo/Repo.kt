package com.example.shoppingapp_user.domain.Repo

import com.example.shoppingapp_user.ResultState
import com.example.shoppingapp_user.domain.models.Category
import com.example.shoppingapp_user.domain.models.ProductModels
import com.example.shoppingapp_user.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface Repo {

    suspend fun getAllCategory() : Flow<ResultState<List<Category>>>

    suspend fun addCategories(): Flow<ResultState<List<Category>>>

    suspend fun addCategory(productModels: ProductModels): Flow<ResultState<List<Category>>>

    suspend fun getAllProducts() : Flow<ResultState<List<ProductModels>>>

    suspend fun registerUser(UserData: UserData): Flow<ResultState<String>>

    suspend fun loginwithemailpassword(userEmail: String, userPassword: String): Flow<ResultState<String>>


}