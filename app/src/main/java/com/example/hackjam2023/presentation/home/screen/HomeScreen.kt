package com.example.hackjam2023.presentation.home.screen

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackjam2023.component.home.HomeBanner
import com.example.hackjam2023.component.home.HomeCategory
import com.example.hackjam2023.component.home.HomeExploreMakanan
import com.example.hackjam2023.component.home.HomePopular
import com.example.hackjam2023.component.home.HomeRecommendation
import com.example.hackjam2023.presentation.home.viewmodel.HomeViewModel
import com.example.hackjam2023.routing.NavRoutes
import com.example.hackjam2023.ui.style.AppShape
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val mapsPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val context = LocalContext.current

    if (viewModel.showRequestMapsPermissionDialog.value) {
        AlertDialog(
            onDismissRequest = { viewModel.showRequestMapsPermissionDialog.value = false },
            title = {
                Text(text = "Anda Memerlukan Ijin Map")
            },
            text = {
                Text(text = "Untuk menggunakan fitur ini, anda harus menyalakan ijin map di device anda")
            },
            confirmButton = {
                TextButton(onClick = {
                    mapsPermissions.launchMultiplePermissionRequest()

                    viewModel.showRequestMapsPermissionDialog.value = false
                }) {
                    Text(text = "Minta Ijin")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.showRequestMapsPermissionDialog.value = false }) {
                    Text(text = "Batal")
                }
            }
        )
    }

    if (viewModel.showManualMapPermissionDialog.value) {
        AlertDialog(
            onDismissRequest = { viewModel.showManualMapPermissionDialog.value = false },
            title = {
                Text(text = "Anda Memerlukan Ijin Map")
            },
            text = {
                Text(text = "Untuk menggunakan fitur ini, anda harus menyalakan ijin map di device anda secara manual")
            },
            confirmButton = {
                TextButton(onClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)

                    viewModel.showManualMapPermissionDialog.value = false
                }) {
                    Text(text = "Buka Pengaturan")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.showManualMapPermissionDialog.value = false }) {
                    Text(text = "Batal")
                }
            }
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.secondary)
                        )

                        Text(
                            text = "Fahmi Noordin",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-24).dp)
                    .shadow(elevation = 8.dp, shape = AppShape.textField),
                value = viewModel.searchState.value,
                onValueChange = { viewModel.searchState.value = it },
                placeholder = {
                    Text(
                        text = "Ikut menjaga bumi, pilih makanan apa hari ini?",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = AppShape.textField,
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                maxLines = 1
            )

            HomeBanner()

            if (viewModel.categories.isNotEmpty()) {
                HomeCategory(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    items = viewModel.categories,
                    onItemClick = {
                        //TODO
                    }
                )
            }

            if (viewModel.products.isNotEmpty()) {
                HomePopular(
                    modifier = Modifier.padding(top = 16.dp),
                    items = viewModel.products,
                    onItemClicked = {
                        navController.navigate("${NavRoutes.MERCHANT_DETAIL.name}/${it.merchant_id}")
                    }
                )
            }

            HomeExploreMakanan(
                onListClick = {
                    navController.navigate(NavRoutes.FOOD_AROUND.name)
                },
                onMapClick = {
                    mapsPermissions.launchMultiplePermissionRequest()

                    if (mapsPermissions.allPermissionsGranted) {
                        navController.navigate(NavRoutes.MERCHANT_MAP.name)
                    } else {
                        if (mapsPermissions.shouldShowRationale) {
                            viewModel.showRequestMapsPermissionDialog.value = true
                        } else {
                            viewModel.showManualMapPermissionDialog.value = true
                        }
                    }
                }
            )

            if (viewModel.products.isNotEmpty()) {
                HomeRecommendation(
                    modifier = Modifier.padding(top = 16.dp),
                    items = viewModel.products,
                    onItemClicked = {
                        navController.navigate("${NavRoutes.MERCHANT_DETAIL.name}/${it.merchant_id}")
                    }
                )
            }
        }
    }
}