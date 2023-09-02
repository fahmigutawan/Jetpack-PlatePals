package com.example.hackjam2023.presentation.home.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hackjam2023.data.Repository
import com.example.hackjam2023.model.category.CategoryModel
import com.example.hackjam2023.model.product.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val searchState = mutableStateOf("")
    val categories = mutableStateListOf<CategoryModel>()
    val products = mutableStateListOf<ProductModel>()
    val showRequestMapsPermissionDialog = mutableStateOf(false)
    val showManualMapPermissionDialog = mutableStateOf(false)

    init {
        repository.getAllCategory(
            onSuccess = {
                categories.clear()
                categories.addAll(it)
            },
            onFailed = {
                //TODO
            }
        )

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