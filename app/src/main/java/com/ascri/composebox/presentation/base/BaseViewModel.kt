package com.ascri.composebox.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ascri.composebox.utils.helpers.DataState
import com.ascri.composebox.utils.helpers.SingleRunner
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    val singleRunner = SingleRunner()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(SAFE_LAUNCH_EXCEPTION).e(exception)
        handleError(exception)
    }

    private val ioScope = CoroutineScope(
        SupervisorJob() + MyDispatchers.IO + exceptionHandler
    )

    open fun handleError(exception: Throwable) {}

    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler, block = block)
    }

    protected fun ioSafeLaunch(block: suspend CoroutineScope.() -> Unit) {
        ioScope.launch(exceptionHandler, block = block)
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> execute(
        callFlow: Flow<DataState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Error -> handleError(state.error)
                    is DataState.Success -> completionHandler.invoke(state.result)
                }
            }
    }

    // Cancel the job when the view model is destroyed
    override fun onCleared() {
        super.onCleared()
        ioScope.cancel("ViewModel is cleared")
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-Exception"
    }
}