package com.ascri.composebox.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ascri.composebox.presentation.flow.architecture_example.counterRoute
import com.ascri.composebox.presentation.flow.architecture_example.counterScreen
import com.ascri.composebox.presentation.flow.bottom_menu.bottomBarScreen
import com.ascri.composebox.presentation.flow.canvas.customDrawScreen
import com.ascri.composebox.presentation.flow.click.clickScreen
import com.ascri.composebox.presentation.flow.list.listScreen
import com.ascri.composebox.presentation.flow.main.mainRoute
import com.ascri.composebox.presentation.flow.main.mainScreen

@ExperimentalMaterialApi
@ExperimentalUnitApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = mainRoute) {
        mainScreen(navController)
        counterScreen(navController)
        bottomBarScreen(navController)
        customDrawScreen(navController)
        clickScreen(navController)
        listScreen(navController)
    }
}