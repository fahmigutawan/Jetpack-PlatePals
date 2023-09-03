package com.example.hackjam2023.presentation.payment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hackjam2023.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
}