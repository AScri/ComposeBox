package com.ascri.cleanapp.domain.usecase.base

import com.ascri.cleanapp.domain.model.ErrorModel

data class UseCaseResult<T>(val success: Boolean, val data: T?, val error: ErrorModel?) {
    constructor(data: T) : this(true, data, null)
    constructor(error: ErrorModel) : this(false, null, error)
}