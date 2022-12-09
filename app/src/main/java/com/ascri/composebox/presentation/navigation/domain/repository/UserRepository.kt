package com.ascri.composebox.presentation.navigation.domain.repository

import com.ascri.composebox.presentation.navigation.domain.model.app.User

interface UserRepository {
    suspend fun getMyProfile(): User
}