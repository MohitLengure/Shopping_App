package com.example.shoppingapp_user.domain.useCase

import com.example.shoppingapp_user.domain.Repo.Repo
import javax.inject.Inject


class LoginWithEmailPassUseCase @Inject constructor(private val repo: Repo)
{

    suspend fun loginWithEmailPassUseCase(
        userEmail: String,
        userPassword: String) = repo.loginwithemailpassword(
        userEmail,
        userPassword)

}