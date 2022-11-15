package com.ascri.composebox.presentation.flow.image

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ascri.composebox.presentation.flow.top_bar.TopBar


const val imagePicker = "imagePicker"

internal fun NavController.navigateToImagePicker() {
    this.navigate(imagePicker)
}

@ExperimentalUnitApi
@ExperimentalMaterialApi
fun NavGraphBuilder.navigateToImagePicker(navController: NavController) {
    composable(
        route = imagePicker
    ) {
        ImagePickerView(navController)
    }
}
