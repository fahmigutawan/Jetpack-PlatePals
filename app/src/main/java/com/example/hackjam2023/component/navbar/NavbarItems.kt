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

}