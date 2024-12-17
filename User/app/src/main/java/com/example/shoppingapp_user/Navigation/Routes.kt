package com.example.shoppingapp_user.Navigation

import kotlinx.serialization.Serializable

sealed class SubNavigationItem {


    @Serializable
    object LoginScreen : SubNavigationItem()

    object SignUpScreen : SubNavigationItem()

    @Serializable
    object HomeScreen : SubNavigationItem()

    @Serializable
    object MainHomeScreen : SubNavigationItem()

    @Serializable
    object RegistrationComplited
}

sealed class Routes {

    @Serializable
    object LoginScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object HomeScreen

    @Serializable
    object ProfileScreen

    @Serializable
    object CartScreen

    @Serializable
    object FavouriteScreen

    @Serializable
    object CheckOutScreen

    @Serializable
    object PaymentScreen

    @Serializable
    object SeeAllProductScreen

    @Serializable
    object RegistrationComplited

    @Serializable
    data class EachProductDetailsScreen(
        val productId: String
    )

    @Serializable
    object EachCategory

    @Serializable
    object AllCategoryScreen

    @Serializable
    object MainHomeScreen




}