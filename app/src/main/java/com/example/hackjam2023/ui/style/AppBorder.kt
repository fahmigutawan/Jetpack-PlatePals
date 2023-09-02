package com.example.hackjam2023.ui.style

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object AppBorder {
    @Composable
    fun thinPrimary() = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
}