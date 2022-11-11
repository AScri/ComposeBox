package com.ascri.composebox.presentation.flow.canvas

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val customDrawRoute = "custom_draw"

internal fun NavController.navigateToCustomDraw() {
    this.navigate(customDrawRoute)
}

fun NavGraphBuilder.customDrawScreen(navController: NavController) {
    composable(
        route = customDrawRoute
    ) {
        CustomDrawScreen(navController)
    }
}
