package com.ascri.composebox.domain.usecase

import com.ascri.composebox.domain.exception.ErrorHandler
import com.ascri.composebox.domain.model.app.User
import com.ascri.composebox.domain.repository.UserRepository
import com.ascri.composebox.domain.usecase.base.UseCaseOut

class GetMyProfileUseCase(
    private val userRepository: UserRepository,
    apiErrorHandle: ErrorHandler
) : UseCaseOut<User>(apiErrorHandle) {
    override suspend fun run(): User {
        return userRepository.getMyProfile()
    }
}