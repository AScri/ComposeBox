package com.ascri.composebox.presentation.flow.click

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val clickRoute = "click"

internal fun NavController.navigateToClick() {
    this.navigate(clickRoute)
}

fun NavGraphBuilder.clickScreen(navController: NavController) {
    composable(
        route = clickRoute
    ) {
        ClickScreen(navController)
    }
}
