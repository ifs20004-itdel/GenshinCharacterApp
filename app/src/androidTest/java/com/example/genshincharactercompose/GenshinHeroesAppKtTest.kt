package com.example.genshincharactercompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.genshincharactercompose.model.FakeHeroDataSource
import com.example.genshincharactercompose.navigation.Screen
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GenshinHeroesAppKtTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp(){
        composeTestRule.setContent {
            GenshinCharacterComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                GenshinHeroesApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination(){
        navController.ScreenAssertions(Screen.Home.route)
    }
    @Test
    fun navHost_clickItem_navigatesToDetailWithData(){
        composeTestRule.onNodeWithTag("HeroList").performScrollToIndex(25)
        composeTestRule.onNodeWithText(FakeHeroDataSource.dummyHeroes[25].name).performClick()
        navController.ScreenAssertions(Screen.DetailHero.route)
        composeTestRule.onNodeWithText(FakeHeroDataSource.dummyHeroes[25].name).assertIsDisplayed()
    }

    @Test
    fun navHost_clickItem_navigateBack(){
        composeTestRule.onNodeWithTag("HeroList").performScrollToIndex(23)
        composeTestRule.onNodeWithText(FakeHeroDataSource.dummyHeroes[23].name).performClick()
        navController.ScreenAssertions(Screen.DetailHero.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.ScreenAssertions(Screen.Home.route)
    }

    @Test
    fun set_and_check_search_result(){
        val input = "3m7sd"
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search_hero)).performTextInput(input)
        composeTestRule.onNodeWithTag("DataNotFound").assertIsDisplayed()
    }

    @Test
    fun search_and_set_favorite_hero(){
        val input = "aLbeD"
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search_hero)).performTextInput(input)
        composeTestRule.onNodeWithText(FakeHeroDataSource.dummyHeroes[0].name).assertIsDisplayed()

        composeTestRule.onNodeWithText(FakeHeroDataSource.dummyHeroes[0].name).performClick()
        navController.ScreenAssertions(Screen.DetailHero.route)
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("Albedo").fetchSemanticsNodes().size ==1
        }
        composeTestRule.onNodeWithTag("FavoriteButton").performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.ScreenAssertions(Screen.Home.route)
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.favorite)).performClick()
        navController.ScreenAssertions(Screen.Favorite.route)
    }
}
