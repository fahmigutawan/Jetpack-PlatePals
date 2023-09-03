package com.example.hackjam2023

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hackjam2023.data.Repository
import com.example.hackjam2023.routing.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val loading = mutableStateOf(false)
    val currentRoute = mutableStateOf(NavRoutes.SPLASH.name)
    val showBottomBar = mutableStateOf(false)
}