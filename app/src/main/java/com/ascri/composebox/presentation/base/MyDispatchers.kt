package com.ascri.composebox.presentation.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object MyDispatchers {
    var Main: CoroutineDispatcher = Dispatchers.Main
    var IO: CoroutineDispatcher = Dispatchers.IO
    var Default: CoroutineDispatcher = Dispatchers.Default

    fun resetMain() {
        Main = Dispatchers.Main
    }

    fun resetIO() {
        IO = Dispatchers.IO
    }

    fun resetDefault() {
        Default = Dispatchers.Default
    }

    fun resetAll() {
        resetMain()
        resetIO()
        resetDefault()
    }
}