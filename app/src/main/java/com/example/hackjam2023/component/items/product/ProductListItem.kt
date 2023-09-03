package com.example.hackjam2023.component.items.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hackjam2023.model.product.ProductModel
import com.example.hackjam2023.ui.style.AppShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListItem(
    modifier: Modifier = Modifier.fillMaxWidth(),
    item: ProductModel,
    onItemClick: (ProductModel) -> Unit
) {
    ElevatedCard(
        modifier = modifier.height(100.dp),
        onClick = { onItemClick(item) },
        shape = AppShape.card,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(84.dp),
                model = item.thumbnail,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = item.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(text = "Rp ${item.price}", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}