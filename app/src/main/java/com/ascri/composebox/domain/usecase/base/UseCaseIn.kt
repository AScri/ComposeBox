package com.ascri.composebox.domain.usecase.base

import com.ascri.composebox.domain.exception.ErrorHandler
import com.ascri.composebox.domain.model.ErrorModel

abstract class UseCaseIn<Type : Any?, in Params>(errorHandler: ErrorHandler) :
    BaseUseCase<Type>(errorHandler) {
    abstract suspend fun run(params: Params): Type

    suspend fun invoke(
        params: Params,
        success: suspend (Type) -> Unit = {},
        error: suspend (t: ErrorModel) -> Unit = defaultErrorLambda
    ) = makeRequest(success, error) { run(params) }

}