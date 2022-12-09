package com.ascri.composebox.presentation.navigation.domain.usecase

import com.ascri.composebox.presentation.navigation.domain.exception.ErrorHandler
import com.ascri.composebox.presentation.navigation.domain.model.app.User
import com.ascri.composebox.presentation.navigation.domain.repository.UserRepository
import com.ascri.composebox.presentation.navigation.domain.usecase.base.UseCaseOut

class GetMyProfileUseCase(
    private val userRepository: UserRepository,
    apiErrorHandle: ErrorHandler
) : UseCaseOut<User>(apiErrorHandle) {
    override suspend fun run(): User {
        return userRepository.getMyProfile()
    }
}