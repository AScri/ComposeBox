package com.ascri.composebox.domain.repository

import com.ascri.composebox.domain.model.app.User

interface UserRepository {
    suspend fun getMyProfile(): User
}