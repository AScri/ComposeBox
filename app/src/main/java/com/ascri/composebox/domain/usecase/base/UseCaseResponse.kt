package com.ascri.composebox.domain.usecase.base

import com.ascri.composebox.domain.model.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

