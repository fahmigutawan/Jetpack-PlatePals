package com.example.hackjam2023.presentation.register.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hackjam2023.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val nameState = mutableStateOf("")
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPassword = mutableStateOf("")

    fun register(onSuccess: () -> Unit,
        onFailed: (String) -> Unit
    ) {
        if(nameState.value.isEmpty() || emailState.value.isEmpty() || passwordState.value.isEmpty()){
            onFailed("Masukkan semua data dengan benar")
            return
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()){
            onFailed("Format email tidak dikenali")
            return
        }

        if(passwordState.value != confirmPassword.value){
            onFailed("Password dan Konfirmasi harus sama")
            return
        }
        repository.register(emailState.value, passwordState.value, nameState.value, onSuccess, onFailed)
    }
}