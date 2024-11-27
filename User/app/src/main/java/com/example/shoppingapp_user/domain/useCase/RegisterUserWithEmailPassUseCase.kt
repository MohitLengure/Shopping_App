package com.example.shoppingapp_user.domain.useCase

import com.example.shoppingapp_user.domain.Repo.Repo
import com.example.shoppingapp_user.domain.models.UserData
import javax.inject.Inject

class RegisterUserWithEmailPassUseCase @Inject constructor(
    private val repo: Repo
) {

    suspend fun registerUserusecase(userData: UserData) = repo.registerUser(userData)

}