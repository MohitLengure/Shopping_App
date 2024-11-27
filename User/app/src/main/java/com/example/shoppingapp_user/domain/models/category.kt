package com.example.shoppingapp_user.domain.models

data class Category(

    var name : String = "",
    var date : Long = System.currentTimeMillis(),
    var imageUrl : String = ""

)
