package com.example.hackjam2023.presentation.payment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackjam2023.presentation.payment.viewmodel.PaymentViewModel
import com.example.hackjam2023.ui.style.TopBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    val viewModel = hiltViewModel<PaymentViewModel>()
    val tmp = remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Pembayaran")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "")
                    }
                },
                colors = TopBarColors.midAligned()
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Total Harga:")
                        Text(text = "Rp 37000", color = MaterialTheme.colorScheme.primary)
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = "Bayar")
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")

                    Column(modifier = Modifier.padding(vertical = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Alamat pengiriman",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                modifier = Modifier.clickable { },
                                text = "Ubah",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = "Perum ganung asri blok i No. 7, 64419, 06/03, Nganjuk",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }

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
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        //TODO
                    }
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(text = "Kode Promo", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Pilih kode promo",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        //TODO
                    }
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(text = "Metode Pembayaran", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Bayar di tempat",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        //TODO
                    }
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(text = "Detail Pembelian", style = MaterialTheme.typography.titleMedium)

                    Column() {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Harga", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "Rp 25000",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Biaya pengiriman",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Rp 12000",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}