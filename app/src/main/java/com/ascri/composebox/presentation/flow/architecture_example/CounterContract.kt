package com.ascri.composebox.presentation.flow.architecture_example

data class CounterState(
    val counter: Int = 0
)

sealed class CounterEvent {
    object IncrementCounter : CounterEvent()
    object DecrementCounter : CounterEvent()
}