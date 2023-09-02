package com.example.hackjam2023.presentation.register.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hackjam2023.R
import com.example.hackjam2023.component.auth.OtherAuthMethodButtons
import com.example.hackjam2023.helper.LoadingHandler
import com.example.hackjam2023.presentation.register.viewmodel.RegisterViewModel
import com.example.hackjam2023.routing.NavRoutes
import com.example.hackjam2023.showSnackbar
import com.example.hackjam2023.ui.style.AppShape
import com.example.hackjam2023.ui.style.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<RegisterViewModel>()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, top = 64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        model = R.drawable.logo_nodescription,
                        contentDescription = ""
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "Masuk",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.nameState.value,
                            onValueChange = { viewModel.nameState.value = it },
                            placeholder = {
                                Text(text = "Nama Lengkap")
                            },
                            shape = AppShape.textField
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.emailState.value,
                            onValueChange = { viewModel.emailState.value = it },
                            placeholder = {
                                Text(text = "Email")
                            },
                            shape = AppShape.textField
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.passwordState.value,
                            onValueChange = { viewModel.passwordState.value = it },
                            placeholder = {
                                Text(text = "Kata Sandi")
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            shape = AppShape.textField
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.confirmPassword.value,
                            onValueChange = { viewModel.confirmPassword.value = it },
                            placeholder = {
                                Text(text = "Konfirmasi Kata Sandi")
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            shape = AppShape.textField
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                LoadingHandler.loading()
                                viewModel.register(
                                    onSuccess = {
                                        LoadingHandler.dismiss()
                                        showSnackbar("Registrasi berhasil")
                                        navController.navigate(NavRoutes.HOME.name) {
                                            popUpTo(navController.graph.id) {
                                                inclusive = true
                                            }
                                        }
                                    },
                                    onFailed = {
                                        LoadingHandler.dismiss()
                                        showSnackbar(it)
                                    }
                                )
                            },
                            colors = ButtonColor.primaryButton()
                        ) {
                            Text(text = "Daftar")
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                            )

                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 32.dp),
                                    text = "Atau",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }

                        OtherAuthMethodButtons(
                            onGoogleClicked = {},
                            onFacebookClicked = {}
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Sudah mempunyai akun ? ", style = MaterialTheme.typography.labelLarge)
                Text(
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                        navController.navigate(NavRoutes.LOGIN.name)
                    },
                    text = " Masuk",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}