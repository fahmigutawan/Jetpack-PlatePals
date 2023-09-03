package com.example.hackjam2023.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary= Color(0xFFFE724C),
    onPrimary= Color(0xFFFFFFFF),
    primaryContainer= Color(0xFFFEA58D),
    onPrimaryContainer= Color(0xFF5E1500),
    inversePrimary= Color(0xFFFEA58D),
    secondary= Color(0xFFFFC529),
    onSecondary= Color(0xFFFFFFFF),
    secondaryContainer= Color(0xFFFFC529),
    onSecondaryContainer= Color(0xFF8F6900),
    tertiary= Color(0xFF46617A),
    onTertiary= Color(0xFF46617A),
    tertiaryContainer= Color(0xFFCCE5FF),
    onTertiaryContainer= Color(0xFF001D31),
    background= Color(0xFFFFFFFF),
    onBackground= Color(0xFF000000),
    surface= Color(0xFFF4F5F6),
    onSurface= Color(0xFF191C1B),
    surfaceVariant= Color(0xFFD8DBD9),
    onSurfaceVariant= Color(0xFF3F4947),
    surfaceTint= Color(0xFFD8DBD9),
    inverseSurface= Color(0xFF2D3130),
    inverseOnSurface= Color(0xFFEFF1EF),
    error= Color(0xFFBA1A1A),
    onError= Color(0xFFFFFFFF),
    errorContainer= Color(0xFFFFDAD6),
    onErrorContainer= Color(0xFF410002),
    outline= Color(0xFF6F7977),
    outlineVariant= Color(0xFFBEC9C6),
    scrim= Color(0xFFBEC9C6)
)

@Composable
fun Hackjam2023Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//        else -> LightColorScheme
//    }
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}