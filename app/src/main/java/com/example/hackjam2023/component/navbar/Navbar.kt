package com.example.hackjam2023.component.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Navbar(
    currentRoute: String,
    onItemClicked: (String) -> Unit
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            NavbarItems.values().forEach {
                Column(
                    modifier = Modifier.clickable {
                        onItemClicked(it.route)
                    },
                    horizontalAlignment = CenterHorizontally
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(model = it.icon),
                        contentDescription = "",
                        tint = if (currentRoute == it.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = it.word,
                        color = if (currentRoute == it.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}