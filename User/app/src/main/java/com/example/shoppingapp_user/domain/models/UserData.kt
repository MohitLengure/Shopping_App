package com.example.shoppingapp_user.domain.models

data class UserData(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val userImageUrl: String = "",
)
