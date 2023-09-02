package com.example.hackjam2023.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.hackjam2023.model.category.CategoryModel
import kotlin.math.ceil

@Composable
fun HomeCategory(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
    onItemClick:(CategoryModel) -> Unit,
    items: List<CategoryModel>
) {
    val columnCount = 3.0
    val rowCount = ceil((items.size / columnCount))
    val containerWidth = remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current

    Column(
        modifier = modifier.onSizeChanged {
            density.run {
                containerWidth.value = it.width.toDp()
            }
        },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Kategori",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        repeat(rowCount.toInt()) { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(columnCount.toInt()) { column ->
                    if ((column + (row * 3)) < items.size) {
                        Box(
                            modifier = Modifier
                                .width(((containerWidth.value.value - ((columnCount - 1) * 8)) / 3).dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .clickable{
                                        onItemClick(items[(column + (row * column))])
                                    }
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Text(
                                    modifier = Modifier.padding(12.dp),
                                    text = items[(column + (row * column))].word ?: "...",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}