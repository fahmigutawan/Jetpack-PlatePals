package com.example.hackjam2023.component.navbar

import com.example.hackjam2023.R
import com.example.hackjam2023.routing.NavRoutes

enum class NavbarItems(
    val route: String,
    val word: String,
    val icon: Int
) {
    Home(
        NavRoutes.HOME.name,
        "Beranda",
        R.drawable.navbar_home
    ),
    Order(
        NavRoutes.ORDER.name,
        "Pesanan",
        R.drawable.navbar_order
    ),
    Forum(
        NavRoutes.FORUM.name,
        "Forum",
        R.drawable.navbar_forum
    ),
    Account(
        NavRoutes.ACCOUNT.name,
        "Account",
        R.drawable.navbar_account
    )
}