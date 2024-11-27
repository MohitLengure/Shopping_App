package com.example.shopping_app_user.domain.useCase


import com.example.shoppingapp_user.domain.Repo.Repo
import javax.inject.Inject

class getProductsInLimitUseCase @Inject constructor(private val repo: Repo) {

    suspend fun getproductsinlimit() = repo.getAllProducts()


}