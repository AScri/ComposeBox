package com.ascri.cleanapp.domain.usecase.base

import com.ascri.cleanapp.domain.model.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

