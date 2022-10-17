package com.ascri.cleanapp.domain.usecase.base

import com.ascri.cleanapp.domain.exception.ErrorHandler
import com.ascri.cleanapp.domain.model.ErrorModel

abstract class UseCaseIn<Type : Any?, in Params>(errorHandler: ErrorHandler) :
    BaseUseCase<Type>(errorHandler) {
    abstract suspend fun run(params: Params): Type

    suspend fun invoke(
        params: Params,
        success: suspend (Type) -> Unit = {},
        error: suspend (t: ErrorModel) -> Unit = defaultErrorLambda
    ) = makeRequest(success, error) { run(params) }

}