package com.ascri.cleanapp.data.repository

import com.ascri.cleanapp.data.source.remote.api.UserApi
import com.ascri.cleanapp.domain.model.app.User
import com.ascri.cleanapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userApi: UserApi) : UserRepository {
    override suspend fun getMyProfile(): User {
        return userApi.getMyProfile()
    }
}