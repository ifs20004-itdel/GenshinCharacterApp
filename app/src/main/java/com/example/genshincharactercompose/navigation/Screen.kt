package com.example.genshincharactercompose.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object About: Screen("about_page")
    object DetailHero: Screen("home/{heroId}"){
        fun createRoute(heroId: Long) = "home/$heroId"
    }
}