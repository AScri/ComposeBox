package com.ascri.composebox.domain.usecase.base

import com.ascri.composebox.domain.exception.ErrorHandler
import com.ascri.composebox.domain.model.ErrorModel

abstract class UseCaseOut<Type : Any?>(errorHandler: ErrorHandler) :
    BaseUseCase<Type>(errorHandler) {
    abstract suspend fun run(): Type

    suspend fun invoke(
        success: suspend (Type) -> Unit = {},
        error: suspend (t: ErrorModel) -> Unit = defaultErrorLambda
    ) = makeRequest(success, error) { run() }

}