package com.example.hackjam2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hackjam2023.component.layout.LoadingLayout
import com.example.hackjam2023.routing.AppNavHost
import com.example.hackjam2023.ui.theme.Hackjam2023Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var mainViewModel: MainViewModel
lateinit var showSnackbar: (message: String) -> Unit
lateinit var showSnackbarWithAction: (
    message: String,
    actionLabel: String,
    action: () -> Unit
) -> Unit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainViewModel = viewModel()
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            showSnackbar = {
                coroutineScope.launch(Dispatchers.IO) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(it)
                }
            }
            showSnackbarWithAction = { msg, label, action ->
                coroutineScope.launch(Dispatchers.IO) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    val snackbarData = snackbarHostState
                        .showSnackbar(
                            message = msg,
                            actionLabel = label
                        )

                    if (snackbarData == SnackbarResult.ActionPerformed) {
                        if (label == "Tutup") {
                            snackbarHostState.currentSnackbarData?.dismiss()
                        } else {
                            action()
                        }
                    }
                }
            }

            Hackjam2023Theme {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) {
                    LoadingLayout() {
                        AppNavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    bottom = it.calculateBottomPadding()
                                ),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}