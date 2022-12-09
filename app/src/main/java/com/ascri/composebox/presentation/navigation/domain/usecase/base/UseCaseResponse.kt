package com.ascri.composebox.presentation.navigation.domain.usecase.base

import com.ascri.composebox.presentation.navigation.domain.model.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

