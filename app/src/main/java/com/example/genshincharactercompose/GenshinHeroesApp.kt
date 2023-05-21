package com.example.genshincharactercompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.genshincharactercompose.navigation.NavigationItem
import com.example.genshincharactercompose.navigation.Screen
import com.example.genshincharactercompose.ui.screen.home.HomeScreen
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.genshincharactercompose.ui.screen.about.AboutScreen
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme

@Composable
fun GenshinHeroesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
    ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if(currentRoute != Screen.DetailHero.route){
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
            ){
            composable(Screen.Home.route){
                val context = LocalContext.current
                HomeScreen(
                    navigationToDetail = {
                        heroId ->
                        navController.navigate(Screen.DetailHero.createRoute(heroId))
                    }
                )
            }
            composable(Screen.Favorite.route){

            }
            composable(Screen.About.route){
                AboutScreen()
            }
        }

    }

}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    BottomNavigation (
        modifier = modifier
            ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                    title = stringResource(R.string.home),
                    icon = Icons.Default.Home,
                    screen = Screen.Home,
                    contentDescription = stringResource(id = R.string.home)
        ),
        NavigationItem(
            title = stringResource(R.string.favorite),
            icon = Icons.Default.Favorite,
            screen = Screen.Favorite,
            contentDescription = stringResource(id = R.string.favorite)
        ),
        NavigationItem(
            title = stringResource(R.string.about),
            icon = Icons.Default.AccountCircle,
            screen = Screen.About,
            contentDescription = stringResource(id = R.string.about_page)
        ),
    )
        BottomNavigation {
            navigationItems.map {
                    item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState =true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun GenshinHeroesAppPreview(){
    GenshinCharacterComposeTheme {
        GenshinHeroesApp()
    }
}