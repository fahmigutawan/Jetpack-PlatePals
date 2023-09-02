package com.example.hackjam2023.model.product

data class ProductModel(
    val product_id:String,
    val merchant_id:String,
    val thumbnail:String,
    val title:String,
    val price:Long
)
