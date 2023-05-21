package com.example.genshincharactercompose.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val contentDescription : String,
    val icon: ImageVector,
    val screen: Screen
)
