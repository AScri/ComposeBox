package com.ascri.cleanapp.domain.repository

import com.ascri.cleanapp.domain.model.app.User

interface UserRepository {
    suspend fun getMyProfile(): User
}