package com.example.hackjam2023.model.merchant

data class MerchantGeocodingResponse(
    val features:List<MerchantGeocodingData>
)

data class MerchantGeocodingData(
    val properties:MerchantGeocodingProperties
)

data class MerchantGeocodingProperties(
    val place_formatted:String
)