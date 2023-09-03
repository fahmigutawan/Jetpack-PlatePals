package com.example.hackjam2023.presentation.food_around.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackjam2023.component.items.product.ProductListItem
import com.example.hackjam2023.presentation.food_around.viewmodel.FoodAroundViewModel
import com.example.hackjam2023.routing.NavRoutes
import com.example.hackjam2023.ui.style.TopBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodAroundScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopBarColors.midAligned(),
                title = {
                    Text(text = "List Makanan di Sekitarku")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
                    }
                }
            )
        }
    ) {
        val viewModel = hiltViewModel<FoodAroundViewModel>()

        Surface {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier)
                }

                items(viewModel.products) { product ->
                    ProductListItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        item = product,
                        onItemClick = {
                            navController.navigate("${NavRoutes.MERCHANT_DETAIL.name}/${it.merchant_id}")
                        }
                    )
                }
            }
        }
    }
}