package com.example.genshincharactercompose

import androidx.navigation.NavController
import org.junit.Assert


fun NavController.ScreenAssertions(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}