package com.ascri.composebox.presentation.navigation.domain.usecase.base

import android.util.Log
import com.ascri.composebox.presentation.navigation.domain.exception.ErrorHandler
import com.ascri.composebox.presentation.navigation.domain.model.ErrorModel
import com.ascri.composebox.presentation.navigation.domain.model.ErrorModel.ErrorStatus
import retrofit2.HttpException
import java.util.concurrent.CancellationException

abstract class BaseUseCase<Type : Any?>(private val errorHandler: ErrorHandler) {
    protected val defaultErrorLambda: suspend (t: ErrorModel) -> Unit = {
        Log.e(TAG, "UseCaseError: $it")
        it.throwable.printStackTrace()
    }

    protected suspend fun makeRequest(
        success: suspend (Type) -> Unit = {},
        error: (suspend (t: ErrorModel) -> Unit),
        runAction: suspend () -> Type
    ) {
        try {
            val result = runAction.invoke()
            success.invoke(result)
            Log.d(TAG, "Response: $result")
        } catch (e: CancellationException) {
            e.printStackTrace()
            error.invoke(errorHandler.traceErrorException(e))
        } catch (e: HttpException) {
            val errorModel = errorHandler.traceErrorException(e)
            when (errorModel.errorStatus) {
                ErrorStatus.UNAUTHORIZED -> {
                    try {
                        errorHandler.refreshTokens()
                        val result = runAction.invoke()
                        success.invoke(result)
                        Log.d(TAG, "Response: $result")
                    } catch (e: Exception) {
                        Log.e(TAG, "Exception when retry getToken after 401 -> $errorModel")
                        error.invoke(errorModel)
                    }
                }
                else -> {
                    Log.e(TAG, "Not catch HTTP error -> $errorModel")
                    error.invoke(errorModel)
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            error.invoke(errorHandler.traceErrorException(e))
        }
    }

    companion object {
        private val TAG = BaseUseCase::class.java.name
    }

}