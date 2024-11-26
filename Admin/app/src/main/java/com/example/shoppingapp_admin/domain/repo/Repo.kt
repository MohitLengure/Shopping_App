package com.example.shoppingapp_admin.domain.repo

import android.net.Uri
import com.example.shoppingapp_admin.Common.ResultState
import com.example.shoppingapp_admin.domain.models.Category
import com.example.shoppingapp_admin.domain.models.productDataModel
import kotlinx.coroutines.flow.Flow

interface Repo {

    fun addCategory(category: Category): Flow<ResultState<String>>

    suspend fun getCategories(): Flow<ResultState<List<Category>>>

    suspend fun addProduct(productmodels : productDataModel): Flow<ResultState<String>>

    suspend fun UploadImage(image: Uri): Flow<ResultState<String>>

}