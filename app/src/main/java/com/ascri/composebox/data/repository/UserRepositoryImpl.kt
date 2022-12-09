package com.ascri.composebox.data.repository

import com.ascri.composebox.data.source.remote.api.UserApi
import com.ascri.composebox.presentation.navigation.domain.model.app.User
import com.ascri.composebox.presentation.navigation.domain.repository.UserRepository

class UserRepositoryImpl(private val userApi: UserApi) : UserRepository {
    override suspend fun getMyProfile(): User {
        return userApi.getMyProfile()
    }
}