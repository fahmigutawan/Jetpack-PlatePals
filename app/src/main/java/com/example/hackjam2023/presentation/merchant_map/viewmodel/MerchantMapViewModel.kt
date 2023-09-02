package com.example.hackjam2023.presentation.merchant_map.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackjam2023.data.Repository
import com.example.hackjam2023.model.merchant.MerchantGeocodingResponse
import com.example.hackjam2023.model.merchant.MerchantModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MerchantMapViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val userLong = mutableStateOf(112.615355)
    val userLat = mutableStateOf(-7.953813)
    val merchants = mutableStateListOf<MerchantModel>()
    val pickedMerchant = mutableStateOf<MerchantModel?>(null)
    val pickedPlaceName = mutableStateOf<String?>(null)

    fun devGetMerchantPlaceName(){
        viewModelScope.launch {
            val res = repository.devGetMerchantByLongLat(
                userLong.value,
                userLat.value
            )

            if(res.status == HttpStatusCode.OK){
                val body = res.body<MerchantGeocodingResponse>()
                if(body.features.isNotEmpty()){
                    pickedPlaceName.value = body.features[0].properties.place_formatted
                }
            }
        }
    }

    init {
        repository.devGetAllMerchant(
            onSuccess = {
                merchants.clear()
                merchants.addAll(it)
            },
            onFailed = {
                //TODO
            }
        )
    }
}