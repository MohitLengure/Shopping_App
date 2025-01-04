package com.example.shoppingapp_admin.domain.models

data class productDataModel (
    val name : String = "",
    val description : String = "",
    val price : Double = 0.00,
    val finalPrice :Double = 0.00,
    val discount : Double = 0.00,
    val category : String = "",
    val image : String = "",
    val date : Long = System.currentTimeMillis(),
    val availableUnits : Int = 0,
    //val isAvailable : Boolean = true,
    val Createdby : String =""
)