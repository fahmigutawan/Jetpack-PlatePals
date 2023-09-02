package com.example.hackjam2023.helper

import androidx.compose.runtime.Composable

object SnackbarHandler {
    fun showSnackbar (
        message: String
    ) {
        com.example.hackjam2023.showSnackbar(message)
    }

    fun showSnackbarWithAction(
        message: String,
        actionLabel:String = "Tutup",
        action:() -> Unit
    ){
        com.example.hackjam2023.showSnackbarWithAction(
            message,
            actionLabel,
            action
        )
    }
}