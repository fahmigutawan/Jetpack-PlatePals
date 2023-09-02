package com.example.hackjam2023.component.items.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hackjam2023.model.product.ProductModel
import com.example.hackjam2023.ui.style.AppShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    item: ProductModel,
    onItemClick: (ProductModel) -> Unit
) {
    val cardWidth = 155.dp
    val cardHeight = 210.dp

    ElevatedCard(
        modifier = Modifier
            .height(cardHeight)
            .width(cardWidth),
        onClick = {
            onItemClick(item)
        },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = AppShape.card
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((cardHeight.value / 2).dp),
                    model = item.thumbnail,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = "Rp ${item.price}",
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}