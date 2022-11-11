package com.ascri.composebox.presentation.base

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow

sealed interface BaseViewState<out T> {
    object Loading : BaseViewState<Nothing>
    object Empty : BaseViewState<Nothing>
    data class Data<T>(val value: T) : BaseViewState<T>
    data class Error(val throwable: Throwable) : BaseViewState<Nothing>
}

inline fun <reified T> StateFlow<BaseViewState<T>>.getData(): T =
    (this.value as BaseViewState.Data<T>).value

inline fun <reified T> StateFlow<BaseViewState<T>>.getDataOrNull(): T? =
    (this.value as? BaseViewState.Data<T>)?.value

inline fun <reified T> BaseViewState<T>.getData(): T =
    (this as BaseViewState.Data<T>).value

inline fun <reified T> BaseViewState<T>.getDataOrNull(): T? =
    (this as? BaseViewState.Data<T>)?.value
