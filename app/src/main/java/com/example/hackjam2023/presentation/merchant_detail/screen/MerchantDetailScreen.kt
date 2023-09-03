package com.example.hackjam2023.presentation.merchant_detail.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hackjam2023.component.items.product.ProductListItem
import com.example.hackjam2023.component.items.sheet.ProductOrderingSheet
import com.example.hackjam2023.helper.SnackbarHandler
import com.example.hackjam2023.presentation.merchant_detail.viewmodel.MerchantDetailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MerchantDetailScreen(
    merchant_id: String,
    navController: NavController
) {
    val viewModel = hiltViewModel<MerchantDetailViewModel>()

    LaunchedEffect(key1 = true) {
        viewModel.getMerchant(merchant_id)
        viewModel.getProducts(merchant_id)
    }

    LaunchedEffect(key1 = viewModel.merchant.value) {
        if (viewModel.merchant.value != null) {
            viewModel.getMerchantPlaceFormatted(
                viewModel.merchant.value?.long ?: 0.0,
                viewModel.merchant.value?.lat ?: 0.0
            )
        }
    }

    if (viewModel.pickedProduct.value != null) {
        viewModel.pickedProduct.value?.let {
            ProductOrderingSheet(
                item = it,
                onDismissClick = { viewModel.pickedProduct.value = null },
                onMasukkanKeranjangClick = {
                    SnackbarHandler.showSnackbar("Item ditambahkan ke keranjang")
                    viewModel.pickedProduct.value = null
                }
            )
        }
    }

    Scaffold(
        topBar = {
            FilledIconButton(
                modifier = Modifier.padding(16.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                viewModel.merchant.value?.let {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                model = it.banner,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = viewModel.merchant.value?.name ?: "",
                                    style = MaterialTheme.typography.headlineMedium,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = viewModel.merchantPlaceFormatted.value,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            items(viewModel.products) {
                ProductListItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    item = it,
                    onItemClick = {
                        viewModel.pickedProduct.value = it
                    }
                )
            }
        }
    }
}