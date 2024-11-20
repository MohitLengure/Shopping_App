package com.example.shopping_app.domain.models

data class Category(

    var name : String = "",
    var date : Long = System.currentTimeMillis(),
    var imageUrl : String = ""

)
