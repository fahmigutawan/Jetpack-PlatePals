package com.example.hackjam2023.model.merchant

data class MerchantModel(
    val merchant_id:String,
    val name:String,
    val thumbnail:String,
    val banner:String,
    val lat:Double,
    val long:Double
)
