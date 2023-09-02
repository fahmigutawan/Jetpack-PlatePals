package com.example.hackjam2023.helper

import com.example.hackjam2023.mainViewModel


object LoadingHandler{
    fun loading() {
        mainViewModel.loading.value = true
    }

    fun dismiss() {
        mainViewModel.loading.value = false
    }
}