package com.ascri.composebox.presentation.flow.horizontal_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ascri.composebox.presentation.flow.top_bar.TopBar

const val horizontal = "horizontal_scroll"

internal fun NavController.navigateToHorizontalScroll() {
    this.navigate(horizontal)
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
fun NavGraphBuilder.navigateToHorizontal(navController: NavController) {
    composable(
        route = horizontal
    ) {
        HorizontalScrollView(navController)
    }
}
