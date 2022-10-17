package com.ascri.composebox.data.repository

import com.ascri.composebox.data.source.remote.api.UserApi
import com.ascri.composebox.domain.model.app.User
import com.ascri.composebox.domain.repository.UserRepository

class UserRepositoryImpl(private val userApi: UserApi) : UserRepository {
    override suspend fun getMyProfile(): User {
        return userApi.getMyProfile()
    }
}