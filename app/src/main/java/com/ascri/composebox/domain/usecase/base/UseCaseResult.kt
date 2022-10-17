package com.ascri.composebox.domain.usecase.base

import com.ascri.composebox.domain.model.ErrorModel

data class UseCaseResult<T>(val success: Boolean, val data: T?, val error: ErrorModel?) {
    constructor(data: T) : this(true, data, null)
    constructor(error: ErrorModel) : this(false, null, error)
}