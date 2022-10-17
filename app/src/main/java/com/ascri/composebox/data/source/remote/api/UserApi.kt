package com.ascri.composebox.data.source.remote.api

import com.ascri.composebox.data.source.remote.NetworkContract.Users.MY_PROFILE_ENDPOINT
import com.ascri.composebox.domain.model.app.User
import retrofit2.http.GET

interface UserApi {
    @GET(MY_PROFILE_ENDPOINT)
    suspend fun getMyProfile(): User
}
