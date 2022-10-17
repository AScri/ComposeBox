package com.ascri.cleanapp.domain.usecase

import com.ascri.cleanapp.domain.exception.ErrorHandler
import com.ascri.cleanapp.domain.model.app.User
import com.ascri.cleanapp.domain.repository.UserRepository
import com.ascri.cleanapp.domain.usecase.base.UseCaseOut

class GetMyProfileUseCase(
    private val userRepository: UserRepository,
    apiErrorHandle: ErrorHandler
) : UseCaseOut<User>(apiErrorHandle) {
    override suspend fun run(): User {
        return userRepository.getMyProfile()
    }
}