package com.example.hackjam2023.component.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.hackjam2023.R
import com.example.hackjam2023.ui.style.AppBorder
import com.example.hackjam2023.ui.style.ButtonColor

@Composable
fun OtherAuthMethodButtons(
    modifier: Modifier = Modifier.fillMaxWidth(),
    onGoogleClicked:() -> Unit,
    onFacebookClicked:() -> Unit
) {
    val density = LocalDensity.current
    val containerWidth = remember { mutableStateOf(0.dp) }

    Row(
        modifier = modifier.onSizeChanged {
            density.run {
                containerWidth.value = it.width.toDp()
            }
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            modifier = Modifier.width(containerWidth.value / 2),
            onClick = onGoogleClicked,
            border = AppBorder.thinPrimary(),
            colors = ButtonColor.surfaceButton()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = rememberImagePainter(data = R.drawable.google_icon),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Text(text = "Google")
            }
        }

        OutlinedButton(
            modifier = Modifier.width(containerWidth.value / 2),
            onClick = onFacebookClicked,
            border = AppBorder.thinPrimary(),
            colors = ButtonColor.surfaceButton()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = rememberImagePainter(data = R.drawable.facebook_icon),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Text(text = "Facebook")
            }
        }
    }
}