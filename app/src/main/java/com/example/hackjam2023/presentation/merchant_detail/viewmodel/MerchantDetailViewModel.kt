package com.example.hackjam2023.presentation.merchant_detail.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackjam2023.data.Repository
import com.example.hackjam2023.model.merchant.MerchantGeocodingResponse
import com.example.hackjam2023.model.merchant.MerchantModel
import com.example.hackjam2023.model.product.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MerchantDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val merchant = mutableStateOf<MerchantModel?>(null)
    val merchantPlaceFormatted = mutableStateOf(". . .")
    val products = mutableStateListOf<ProductModel>()
    val pickedProduct = mutableStateOf<ProductModel?>(null)

    fun getMerchant(merchant_id: String) {
        repository.devGetMerchantByMerchantId(
            merchant_id,
            onSuccess = {
                merchant.value = it
            },
            onFailed = {
                //TODO
            }
        )
    }

    fun getProducts(
        merchant_id: String
    ){
        repository.devGetAllProductByMerchantId(
            merchant_id,
            onSuccess = {
                products.clear()
                products.addAll(it)
            },
            onFailed = {
                //TODO
            }
        )
    }

    fun getMerchantPlaceFormatted(
        long:Double,
        lat:Double
    ){
        viewModelScope.launch {
            val res = repository.devGetMerchantByLongLat(long, lat)

            if(res.status == HttpStatusCode.OK){
                val body = res.body<MerchantGeocodingResponse>()

                merchantPlaceFormatted.value = body.features[0].properties.place_formatted
            }
        }
    }
}