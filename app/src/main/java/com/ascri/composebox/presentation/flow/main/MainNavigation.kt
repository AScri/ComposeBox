package com.ascri.composebox.presentation.flow.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val mainRoute = "main"

internal fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainScreen(navController: NavController) {
    composable(
        route = mainRoute
    ) {
        MainScreen(navController = navController)
    }
}
