package com.example.shoppingapp_user.domain.useCase

import com.example.shoppingapp_user.domain.Repo.Repo
import javax.inject.Inject

class getCategoryinlimit@Inject constructor(private val repo: Repo) {

    suspend fun getcategoryinlimit() = repo.getAllCategory()

}

