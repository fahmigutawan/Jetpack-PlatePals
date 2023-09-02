package com.example.hackjam2023.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hackjam2023.R

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 57.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold))
    ),
    displayMedium = TextStyle(
        fontSize = 45.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold))
    ),
    displaySmall = TextStyle(
        fontSize = 40.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold))
    ),
    headlineLarge = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold))
    ),
    headlineMedium = TextStyle(
        fontSize = 26.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold))
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro))
    ),
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold))
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium))
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro))
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro))
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro))
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro))
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium))
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium))
    ),
    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro))
    )
)