package com.example.hackjam2023.presentation.splash.screen

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hackjam2023.R
import com.example.hackjam2023.presentation.splash.viewmodel.SplashViewModel
import com.example.hackjam2023.routing.NavRoutes

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SplashViewModel>()

    LaunchedEffect(key1 = true) {
        viewModel.preCheck { isLogin ->
            if (isLogin) {
                navController.navigate(NavRoutes.HOME.name) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(NavRoutes.ONBOARDING.name) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(model = R.drawable.splash_top, contentDescription = "")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Unspecified,
                            MaterialTheme.colorScheme.onPrimary,
                            MaterialTheme.colorScheme.onPrimary,
                            MaterialTheme.colorScheme.onPrimary
                        )
                    )
                )
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp),
            model = R.drawable.splash_icon_primary,
            contentDescription = ""
        )
    }
}