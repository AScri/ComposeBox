package com.ascri.composebox.presentation.flow.camera

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val bottomBarRoute = "permission"

internal fun NavController.navigateToCamera() {
    this.navigate(bottomBarRoute)
}

fun NavGraphBuilder.navigateToCamera(navController: NavController) {
    composable(
        route = bottomBarRoute
    ) {
        CameraView(navController)
    }
}
