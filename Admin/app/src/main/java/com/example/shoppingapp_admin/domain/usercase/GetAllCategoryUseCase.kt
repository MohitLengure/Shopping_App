package com.example.shoppingapp_admin.domain.usercase

import com.example.shoppingapp_admin.domain.repo.Repo
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(private val repo: Repo) {

    suspend fun getAllCategory() = repo.getCategories()

}