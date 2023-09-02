package com.example.hackjam2023.component.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hackjam2023.mainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun LoadingLayout(
    modifier:Modifier = Modifier,
    content:@Composable (() -> Unit)
) {
    val loadingState = rememberSwipeRefreshState(
        isRefreshing = mainViewModel.loading.value
    )

    SwipeRefresh(
        modifier = modifier,
        state = loadingState,
        onRefresh = { /*TODO*/ },
        swipeEnabled = false
    ) {
        content()
    }
}