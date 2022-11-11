package com.ascri.composebox.presentation.flow.architecture_example

import androidx.lifecycle.SavedStateHandle
import com.ascri.composebox.presentation.base.BaseMviViewModel
import com.ascri.composebox.presentation.base.BaseViewState.Data
import com.ascri.composebox.presentation.base.getData

class CounterViewModel(handle: SavedStateHandle) :
    BaseMviViewModel<CounterState, CounterEvent>() {

    private val counterArgs: CounterScreenArgs = CounterScreenArgs(handle)

    init {
        setState(Data(CounterState(counterArgs.counter)))
    }

    override fun onEvent(event: CounterEvent) {
        when (event) {
            CounterEvent.IncrementCounter ->
                setState(
                    Data(CounterState(uiState.getData().counter + 1))
                )
            CounterEvent.DecrementCounter ->
                setState(
                    Data(CounterState(uiState.getData().counter - 1))
                )
        }
    }
}