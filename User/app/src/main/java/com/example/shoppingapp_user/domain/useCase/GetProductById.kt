package com.example.shoppingapp_user.domain.useCase

import com.example.shoppingapp_user.domain.Repo.Repo
import javax.inject.Inject

class GetProductById @Inject constructor(private val repo: Repo) {

    suspend fun getProductsByIDUseCase(productId: String) = repo.getProductById(productId)

}