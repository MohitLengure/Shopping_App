package com.example.shopping_app.domain.repo

import com.example.shopping_app.Common.ResultState
import com.example.shopping_app.domain.models.Category
import com.example.shopping_app.domain.models.productDataModel
import kotlinx.coroutines.flow.Flow

interface Repo {

    fun addCategory(category: Category): Flow<ResultState<String>>

    suspend fun getCategories(): Flow<ResultState<List<Category>>>

    suspend fun addProduct(productmodels : productDataModel): Flow<ResultState<String>>

}