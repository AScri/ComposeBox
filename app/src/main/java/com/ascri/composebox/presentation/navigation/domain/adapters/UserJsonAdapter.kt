package com.ascri.composebox.presentation.navigation.domain.adapters

import com.ascri.composebox.presentation.navigation.domain.model.api.UserResponse
import com.ascri.composebox.presentation.navigation.domain.model.app.User
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class UserJsonAdapter {
    @FromJson
    fun userFromJson(userJson: UserResponse): User {
        return User(
            id = userJson.id,
            username = userJson.username,
            firstName = userJson.firstName,
            lastName = userJson.lastName,
            email = userJson.email,
            photo = userJson.photo
        )
    }

    @ToJson
    fun userToJson(user: User): UserResponse {
        return UserResponse(
            id = user.id,
            username = user.username,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.username,
            photo = user.photo
        )
    }
}