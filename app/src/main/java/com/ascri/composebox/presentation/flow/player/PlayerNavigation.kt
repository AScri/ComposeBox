package com.ascri.composebox.presentation.flow.player

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val player = "player_view"

internal fun NavController.navigateToPlayer() {
    this.navigate(player)
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
fun NavGraphBuilder.navigateToPlayer(navController: NavController) {
    composable(
        route = player
    ) {
        PlayerView(navController)
    }
}
