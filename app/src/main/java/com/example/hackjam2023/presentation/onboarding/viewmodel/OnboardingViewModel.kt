package com.example.hackjam2023.presentation.onboarding.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackjam2023.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val isChecked = mutableStateOf(false)
    val isLogin = MutableStateFlow(false)
    fun preCheck(){
        viewModelScope.launch {
            delay(1500)
            isLogin.value = repository.isLogin()
            isChecked.value = true
        }
    }

    init {
        preCheck()
    }
}