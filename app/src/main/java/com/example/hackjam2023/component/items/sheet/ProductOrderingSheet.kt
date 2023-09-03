package com.example.hackjam2023.component.items.sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hackjam2023.model.product.ProductModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductOrderingSheet(
    item: ProductModel,
    onDismissClick: () -> Unit,
    onMasukkanKeranjangClick: (ProductModel) -> Unit
) {
    val tmp = remember {
        mutableStateOf(0)
    }

    ModalBottomSheet(onDismissRequest = onDismissClick) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(84.dp),
                    model = item.thumbnail,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(text = item.title, style = MaterialTheme.typography.headlineMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Rp ${item.price}", style = MaterialTheme.typography.labelLarge)

                        OutlinedCard {
                            Row(
                                modifier = Modifier.padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier
                                        .clickable(
                                            enabled = tmp.value > 0,
                                            onClick = {
                                                tmp.value -= 1
                                            })
                                        .padding(8.dp),
                                    text = "-"
                                )

                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = tmp.value.toString()
                                )

                                Text(
                                    modifier = Modifier
                                        .clickable { tmp.value += 1 }
                                        .padding(8.dp),
                                    text = "+"
                                )
                            }
                        }
                    }
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onMasukkanKeranjangClick(item) }
            ) {
                Text(text = "Masukkan ke Keranjang")
            }
        }
    }
}