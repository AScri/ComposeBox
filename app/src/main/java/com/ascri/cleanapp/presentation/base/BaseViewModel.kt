package com.ascri.cleanapp.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ascri.cleanapp.utils.helpers.SingleRunner
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {
    val singleRunner = SingleRunner()

    val ioScope = CoroutineScope(
        SupervisorJob() + MyDispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e(BaseViewModel::class.java.simpleName, "ioScope ${throwable.message}")
            throwable.printStackTrace()
        }
    )

    // Cancel the job when the view model is destroyed
    override fun onCleared() {
        super.onCleared()
        ioScope.cancel("ViewModel is cleared")
    }

}