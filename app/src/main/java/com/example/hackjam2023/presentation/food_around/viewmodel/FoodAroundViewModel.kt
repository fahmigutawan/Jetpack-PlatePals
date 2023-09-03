package com.example.hackjam2023.presentation.food_around.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.hackjam2023.data.Repository
import com.example.hackjam2023.model.product.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodAroundViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val products = mutableStateListOf<ProductModel>()

    init {
        repository.devGetAllProduct(
            onSuccess = {
                products.clear()
                products.addAll(it)
            },
            onFailed = {
                //TODO
            }
        )
    }
}