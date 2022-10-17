package com.ascri.cleanapp.domain.usecase.base

import com.ascri.cleanapp.domain.exception.ErrorHandler
import com.ascri.cleanapp.domain.model.ErrorModel

abstract class UseCaseOut<Type : Any?>(errorHandler: ErrorHandler) :
    BaseUseCase<Type>(errorHandler) {
    abstract suspend fun run(): Type

    suspend fun invoke(
        success: suspend (Type) -> Unit = {},
        error: suspend (t: ErrorModel) -> Unit = defaultErrorLambda
    ) = makeRequest(success, error) { run() }

}