package com.ascri.composebox.presentation.flow.top_bar

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val topBarRoute = "top_bar"

internal fun NavController.navigateToTopBar() {
    this.navigate(topBarRoute)
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
fun NavGraphBuilder.navigateToTopBar(navController: NavController) {
    composable(
        route = topBarRoute
    ) {
        TopBar(navController)
    }
}
