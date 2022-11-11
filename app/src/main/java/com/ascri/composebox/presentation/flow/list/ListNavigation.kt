package com.ascri.composebox.presentation.flow.list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val listRoute = "list"

internal fun NavController.navigateToList() {
    this.navigate(listRoute)
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listScreen(navController: NavController) {
    composable(
        route = listRoute
    ) {
        ListScreen(navController)
    }
}
