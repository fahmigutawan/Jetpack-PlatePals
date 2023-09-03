package com.example.hackjam2023.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.hackjam2023.R
import com.example.hackjam2023.ui.style.ButtonColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeExploreMakanan(
    modifier: Modifier = Modifier,
    onListClick:() -> Unit,
    onMapClick: () -> Unit
) {
    val height = 128.dp

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(height),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Box {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = R.drawable.splash_top,
                    contentDescription = "",
                    alpha = 0.1f,
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "Eksplor Makanan di Sekitarmu",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = onListClick,
                            colors = ButtonColor.surfaceButton()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.List,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(text = "List", color = MaterialTheme.colorScheme.primary)
                            }
                        }

                        Button(
                            onClick = { onMapClick() },
                            colors = ButtonColor.surfaceButton()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(text = "Maps", color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }

                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = R.drawable.icon_explore,
                    contentDescription = ""
                )
            }
        }

    }
}