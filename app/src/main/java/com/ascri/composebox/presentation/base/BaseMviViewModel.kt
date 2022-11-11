package com.ascri.composebox.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseMviViewModel<STATE, EVENT> : BaseViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<STATE>>(BaseViewState.Empty)
    val uiState = _uiState.asStateFlow()

    abstract fun onEvent(event: EVENT)

    protected fun setState(state: BaseViewState<STATE>) = safeLaunch {
        _uiState.emit(state)
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.value = BaseViewState.Error(exception)
    }
}