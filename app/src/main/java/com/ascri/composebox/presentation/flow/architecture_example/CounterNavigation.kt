package com.ascri.composebox.presentation.flow.architecture_example

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val counterBaseRoute = "counter"
const val counterArg = "counter"
const val counterRoute = "$counterBaseRoute/{$counterArg}"

internal class CounterScreenArgs(val counter: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle[counterArg] ?: 0
    )
}

internal fun NavController.navigateToCounter(counterScreenArgs: CounterScreenArgs) {
    this.navigate("$counterBaseRoute/${counterScreenArgs.counter}")
}

fun NavGraphBuilder.counterScreen(navController: NavController) {
    composable(
        route = counterRoute,
        arguments = listOf(
            navArgument(counterArg) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) {
        CounterScreen(navController)
    }
}
