package com.example.shopping_app.domain.models

data class productDataModel (
    val name : String = "",
    val description : String = "",
    val price : String = "",
    val finalPrice : String = "",
    val discount : Int=0,
    val category : String = "",
    val image : String = "",
    val date : Long = System.currentTimeMillis(),
    val availableUnits : Int = 0,
    val isAvailable : Boolean = true,
    val Createdby : String =""
)