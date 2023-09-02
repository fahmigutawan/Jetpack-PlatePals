package com.example.hackjam2023.component.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hackjam2023.R

@Composable
fun HomeBanner(
    modifier:Modifier = Modifier.padding(horizontal = 16.dp)
) {
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            model = R.drawable.dummy_banner,
            contentDescription = ""
        )

        //Pager indicator below here
    }
}