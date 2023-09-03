package com.example.hackjam2023.presentation.order.screen

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hackjam2023.routing.NavRoutes
import com.example.hackjam2023.ui.style.AppShape
import com.example.hackjam2023.ui.style.TopBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController
) {
    val tmp = remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Pesanan")
                },
                colors = TopBarColors.midAligned()
            )
        }
    ) {
        Column(modifier = Modifier.padding(it), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Belum dibayar", color = MaterialTheme.colorScheme.primary)

                Text(text = "Dikirim")

                Text(text = "Selesai")

                Text(text = "Batal")
            }

            ElevatedCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = AppShape.card,
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(84.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.outline)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "Udang Rambutan Gacoan")
                            Text(text = "Rp 9000", color = MaterialTheme.colorScheme.primary)
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
                                            .padding(4.dp),
                                        text = "-"
                                    )

                                    Text(
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        text = tmp.value.toString()
                                    )

                                    Text(
                                        modifier = Modifier
                                            .clickable { tmp.value += 1 }
                                            .padding(4.dp),
                                        text = "+"
                                    )
                                }
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outline
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(onClick = { navController.navigate(NavRoutes.PAYMENT.name) }) {
                            Text(text = "Bayar")
                        }
                    }
                }
            }
        }
    }
}