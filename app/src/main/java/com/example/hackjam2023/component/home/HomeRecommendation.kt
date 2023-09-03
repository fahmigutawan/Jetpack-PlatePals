package com.example.hackjam2023.component.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hackjam2023.component.items.product.ProductCardItem
import com.example.hackjam2023.model.product.ProductModel

@Composable
fun HomeRecommendation(
    modifier: Modifier = Modifier,
    items: List<ProductModel>,
    onItemClicked: (ProductModel) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Rekomendasi",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach {
                ProductCardItem(
                    item = it,
                    onItemClick = {
                        onItemClicked(it)
                    }
                )
            }
        }
    }
}