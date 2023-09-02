package com.example.hackjam2023.presentation.login.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hackjam2023.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")

    fun login(
        onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        if(emailState.value.isEmpty() || passwordState.value.isEmpty()){
            onFailed("Isi semua data dengan benar")
            return
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()){
            onFailed("Format email tidak dikenali")
            return
        }

        repository.login(emailState.value, passwordState.value, onSuccess, onFailed)
    }
}