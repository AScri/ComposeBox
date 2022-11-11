package com.ascri.composebox.presentation.flow.bottom_menu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val bottomBarRoute = "bottom_bar"

internal fun NavController.navigateToBottomBar() {
    this.navigate(bottomBarRoute)
}

fun NavGraphBuilder.bottomBarScreen(navController: NavController) {
    composable(
        route = bottomBarRoute
    ) {
        BottomBarScreen(navController)
    }
}
